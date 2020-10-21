# 基本语法

-- 建表
/*
CREATE [EXTERNAL] TABLE [IF NOT EXISTS] table_name
  [(col_name data_type [COMMENT col_comment], ...)]
  [COMMENT table_comment]
  [PARTITIONED BY (col_name data_type
    [COMMENT col_comment], ...)]
  [CLUSTERED BY (col_name, col_name, ...)
  [SORTED BY (col_name [ASC|DESC], ...)]
  INTO num_buckets BUCKETS]
  [ROW FORMAT row_format]
  [STORED AS file_format]
  [LOCATION hdfs_path]
*/

show databases
use databases
show alter tablespace
drop table tab_name

-- [ROW FORMAT DELIMITED]关键字，是用来设置创建的表在加载数据的时候，支持的列分隔符；
-- [STORED AS file_format]关键字是用来设置加载数据的数据类型。Hive本身支持的文件格式只有：Text File，Sequence File。
-- 如果文件数据是纯文本，可以使用 [STORED AS TEXTFILE]。如果数据需要压缩，使用 [STORED AS SEQUENCE] 。
-- 通常情况，只要不需要保存序列化的对象，我们默认采用[STORED AS TEXTFILE]。
CREATE TABLE test_1(id INT, name STRING, city STRING) SORTED BY TEXTFILE ROW FORMAT DELIMITED ‘\t’

-- 外部表
CREATE EXTERNAL TABLE test_1(id INT, name STRING, city STRING) SORTED BY TEXTFILE ROW FORMAT DELIMITED ‘\t’ LOCATION ‘hdfs://../../..’

-- 分区表
CREATE TABLE test_1(id INT, name STRING, city STRING) PARTITIONED BY (pt STRING) SORTED BY TEXTFILE ROW FORMAT DELIMITED ‘\t’

-- 删除分区
ALTER TABLE table_name drop partition (partcol1[=value1]);

-- 增加分区
ALTER TABLE table_name ADD
    partition_spec [ LOCATION 'location1' ]
    partition_spec [ LOCATION 'location2' ] ...
/*
    加载数据
LOAD DATA [LOCAL] INPATH 'filepath' [OVERWRITE] INTO TABLE tablename [PARTITION (partcol1=val1, partcol2=val2 ...)]

    */

-- 关键字[OVERWRITE]意思是是覆盖原表里的数据，不写则不会覆盖。
-- 关键字[LOCAL]是指你加载文件的来源为本地文件，不写则为hdfs的文件。
LOAD DATA LOCAL INPATH '/home/admin/test/test.txt' OVERWRITE INTO TABLE test_1

-- 加载到分区表
LOAD DATA LOCAL INPATH '/home/admin/test/test.txt' OVERWRITE INTO TABLE test_1 PARTITION（pt=’xxxx）

-- insert+select
INSERT OVERWRITE TABLE tablename1 [PARTITION (partcol1=val1, partcol2=val2 ...)] select_statement1 FROM from_statement




# SQL_1

```
表结构：uid,subject_id,score
求：找出所有科目成绩都大于某一学科平均成绩的学生

数据集如下
1001	01	90
1001	02	90
1001	03	90
1002	01	85
1002	02	85
1002	03	70
1003	01	70
1003	02	70
1003	03	85

```
-- 建表

create table score (
    uid string,
    subject_id string,
    score int
) row format delimited fields terminated by '\t'

-- 2）求出每个学科平均成绩
select uid, score, avg(score) over(partition by subject_id) avg_score from score;t1
-- 3）根据是否大于平均成绩记录flag，大于则记为0否则记为1
select uid, if(score>avg_score,0,1) flag from t1;t2
-- 4）根据学生id进行分组统计flag的和，和为0则是所有学科都大于平均成绩
select uid from t2 group by  uid having sum(flag)=0;

-- sql
select uid
from (select uid,if(score>avg_score,0,1) flag  from   (select uid,score, avg(score) over(partition by subject_id) avg_score from score) t1
    )t2
group by uid having sum(flag)=0; -- 所有的都高于平均成绩


# SQL_2

```
数据集
u01     2017/1/21       5
u02     2017/1/23       6
u03     2017/1/22       8
u04     2017/1/20       3
u01     2017/1/23       6
u01     2017/2/21       8
u02     2017/1/23       6
u01     2017/2/22       4

统计出每个用户的累积访问次数
```

-- 建表
create table action (
    userId string,
    visitDate string,
    visitCount int
) row format delimited fields terminated by '\t'

-- 2）修改数据格式
select userId, date_format(regexp_replace(visitDate,'/','-'),'yyyy-MM') mn, visitCount from action;t1
-- 3）计算每人单月访问量
select userId, mn, sum(visitCount) mn_count from t1 group by userId,mn;t2
-- 4）按月累计访问量
select userId, mn, mn_count, sum(mn_count) over(partition by userId order by mn) from t2;

-- sql
select
    userId,
    mn,
    mn_count,
    sum(mn_count) over(partition by userId order by mn)
from
(   select
        userId,
        mn,
        sum(visitCount) mn_count
    from
         (select
             userId,
             date_format(regexp_replace(visitDate,'/','-'),'yyyy-MM') mn,
             visitCount
         from action) t1
group by userId,mn) t2;

# SQL_3

```
有50W个京东店铺，每个顾客访客访问任何一个店铺的任何一个商品时都会产生一条访问日志，访问日志存储的表名为Visit，访客的用户id为user_id，被访问的店铺名称为shop，请统计：
1）每个店铺的UV（访客数）
2）每个店铺访问次数top3的访客信息。输出店铺名称、访客id、访问次数
数据集
u1	a
u2	b
u1	b
u1	a
u3	c
u4	b
u1	a
u2	c
u5	b
u4	b
u6	c
u2	c
u1	b
u2	a
u2	a
u3	a
u5	a
u5	a
u5	a

```
-- 建表
create table visit(user_id string,shop string) row format delimited fields terminated by '\t';

-- sql
--2）每个店铺的UV（访客数）
select shop,count(distinct user_id) from visit group by shop;
--3）每个店铺访问次数top3的访客信息。输出店铺名称、访客id、访问次数
--（1）查询每个店铺被每个用户访问次数
select shop,user_id,count(*) ct from visit group by shop,user_id;t1
--（2）计算每个店铺被用户访问次数排名
select shop,user_id,ct,rank() over(partition by shop order by ct) rk from t1;t2
--（3）取每个店铺排名前3的
select shop,user_id,ct from t2 where rk<=3;
--（4）最终SQL
select shop,user_id,ct
from (select shop,user_id, ct,rank() over(partition by shop order by ct) rk
from   (select shop,user_id,count(*) ct from visit group by  shop,user_id) t1 )t2
where rk<=3;


# SQL_4

```
已知一个表STG.ORDER，有如下字段:Date，Order_id，User_id，amount。请给出sql进行统计:数据样例:2017-01-01,10029028,1000003251,33.57。
1）给出 2017年每个月的订单数、用户数、总成交金额。
2）给出2017年11月的新客数(指在11月才有第一笔订单)

```

-- 建表
create table order_tab(dt string,order_id string,user_id string,amount decimal(10,2)) row format delimited fields terminated by '\t';

-- 1）给出 2017年每个月的订单数、用户数、总成交金额。
select date_format(dt,'yyyy-MM'), count(order_id),  count(distinct user_id),  sum(amount) from order_tab
where date_format(dt,'yyyy')='2017'group by  date_format(dt,'yyyy-MM');

-- 2）给出2017年11月的新客数(指在11月才有第一笔订单)
select count(user_id) from order_tab group by user_id having date_format(min(dt),'yyyy-MM')='2017-11';

# SQL_5

```
有日志如下，请写出代码求得所有用户和活跃用户的总数及平均年龄。（活跃用户指连续两天都有访问记录的用户）日期 用户 年龄
数据集
2019-02-11,test_1,23
2019-02-11,test_2,19
2019-02-11,test_3,39
2019-02-11,test_1,23
2019-02-11,test_3,39
2019-02-11,test_1,23
2019-02-12,test_2,19
2019-02-13,test_1,23
2019-02-15,test_2,19
2019-02-16,test_2,19

```

-- 1）建表
create table user_age(dt string,user_id string,age int)row format delimited fields terminated by ',';
-- 2）按照日期以及用户分组，按照日期排序并给出排名
select dt, user_id, min(age) age, rank() over(partition by user_id order by dt) rk from user_age group by dt,user_id;t1
-- 3）计算日期及排名的差值
select user_id, age, date_sub(dt,rk) flag from t1;t2
-- 4）过滤出差值大于等于2的，即为连续两天活跃的用户
select user_id, min(age) age from t2 group by  user_id,flag having count(*)>=2;t3
-- 5）对数据进行去重处理（一个用户可以在两个不同的时间点连续登录），例如：a用户在1月10号1月11号以及1月20号和1月21号4天登录。
select user_id,  min(age) age from t3 group by user_id;t4
-- 6）计算活跃用户（两天连续有访问）的人数以及平均年龄
select count(*) ct, cast(sum(age)/count(*) as decimal(10,2)) from t4;
-- 7）对全量数据集进行按照用户去重
select user_id, min(age) age from user_age group by user_id;t5
-- 8）计算所有用户的数量以及平均年龄 cast(arg1 as arg2);arg1是要转换的数据，arg2是目标类型
select count(*) user_count, cast((sum(age)/count(*)) as decimal(10,1)) from  t5;
-- 9）将第5步以及第7步两个数据集进行union all操作
select 0 user_total_count, 0 user_total_avg_age, count(*) twice_count, cast(sum(age)/count(*) as decimal(10,2)) twice_count_avg_age
from
(
   select user_id, min(age) age from (select user_id, min(age) age from
   (
    select user_id, age,  date_sub(dt,rk) flag from
   (
    select dt, user_id, min(age) age, rank() over(partition by user_id order by dt) rk from user_age group by dt,user_id )t1
    )t2 group by user_id,flag having count(*)>=2) t3 group by user_id
)t4
union all
select count(*) user_total_count, cast((sum(age)/count(*)) as decimal(10,1)), 0 twice_count, 0 twice_count_avg_age
from (  select user_id, min(age) age from  user_age group by  user_id )t5;t6
-- 10）求和并拼接为最终SQL
select
    sum(user_total_count),
    sum(user_total_avg_age),
    sum(twice_count),
    sum(twice_count_avg_age)
from
(select 0 user_total_count, 0 user_total_avg_age, count(*) twice_count, cast(sum(age)/count(*) as decimal(10,2)) twice_count_avg_age
from
(
   select user_id, min(age) age from
   (select user_id, min(age) age from
   ( select user_id, age, date_sub(dt,rk) flag from
   ( select dt, user_id, min(age) age, rank() over(partition by user_id order by dt) rk from user_age group by dt,user_id)t1 )t2
group by user_id,flag having count(*)>=2)t3
group by user_id)t4

union all

select
   count(*) user_total_count,
   cast((sum(age)/count(*)) as decimal(10,1)),
   0 twice_count,
   0 twice_count_avg_age
from ( select user_id, min(age) age from user_age group by user_id )t5)t6;

# SQL_6

```
请用sql写出所有用户中在今年10月份第一次购买商品的金额，表ordertable字段（购买用户：userid，金额：money，
购买时间：paymenttime(格式：2017-10-01)，订单id：orderid）
```

-- 1）建表
create table ordertable(
    userid string,
    money int,
    paymenttime string,
    orderid string)
row format delimited fields terminated by '\t';

-- 2）查询出
select userid, min(paymenttime) paymenttime from ordertable where date_format(paymenttime,'yyyy-MM')='2017-10' group by userid;t1

select t1.userid, t1.paymenttime, od.money from t1 join ordertable od on t1.userid=od.userid and t1.paymenttime=od.paymenttime;

select t1.userid,t1.paymenttime, od.money
from (select userid, min(paymenttime) paymenttime from ordertable where date_format(paymenttime,'yyyy-MM')='2017-10'
group by userid)t1 join ordertable od on t1.userid=od.userid and t1.paymenttime=od.paymenttime;


# SQL_7

```
有一个线上服务器访问日志格式如下（用sql答题）
时间                    接口                         ip地址
2016-11-09 11：22：05    /api/user/login                  110.23.5.33
2016-11-09 11：23：10    /api/user/detail                  57.3.2.16
.....
2016-11-09 23：59：40    /api/user/login                  200.6.5.166
求11月9号下午14点（14-15点），访问api/user/login接口的top10的ip地址
数据集
2016-11-09 14:22:05	/api/user/login	110.23.5.33
2016-11-09 11:23:10	/api/user/detail	57.3.2.16
2016-11-09 14:59:40	/api/user/login	200.6.5.166
2016-11-09 14:22:05	/api/user/login	110.23.5.34
2016-11-09 14:22:05	/api/user/login	110.23.5.34
2016-11-09 14:22:05	/api/user/login	110.23.5.34
2016-11-09 11:23:10	/api/user/detail	57.3.2.16
2016-11-09 23:59:40	/api/user/login	200.6.5.166
2016-11-09 14:22:05	/api/user/login	110.23.5.34
2016-11-09 11:23:10	/api/user/detail	57.3.2.16
2016-11-09 23:59:40	/api/user/login	200.6.5.166
2016-11-09 14:22:05	/api/user/login	110.23.5.35
2016-11-09 14:23:10	/api/user/detail	57.3.2.16
2016-11-09 23:59:40	/api/user/login	200.6.5.166
2016-11-09 14:59:40	/api/user/login	200.6.5.166
2016-11-09 14:59:40	/api/user/login	200.6.5.166

```

-- 1）建表
create table ip(
    time string,
    interface string,
    ip string)
row format delimited fields terminated by '\t';

-- 2）最终SQL
select ip, interface, count(*) ct from ip where  date_format(time,'yyyy-MM-dd HH')>='2016-11-09 14' and  date_format(time,'yyyy-MM-dd HH')<='2016-11-09 15'
    and interface='/api/user/login' group by ip,interface order by ct desc limit 2;t1

# SQL_8

```
有一个账号表如下，请写出SQL语句，查询各自区组的money排名前十的账号（分组取前10）

```
-- 1）建表（MySQL）
CREATE TABLE `account`
(   `dist_id` int (11) DEFAULT NULL COMMENT ''区组id'',
    `account` varchar(100) DEFAULT NULL COMMENT ''账号'',
    `gold` int (11) DEFAULT 0 COMMENT ''金币'');
-- 2）最终SQL count(distinct(a1.gold))
select * from account as a
where
    (select count(distinct(a1.gold)) from account as a1  where a1.dist_id=a.dist_id and a1.gold>a.gold)<3;

# SQL_9

```
1）有三张表分别为会员表（member）销售表（sale）退货表（regoods）
（1）会员表有字段memberid（会员id，主键）credits（积分）；
（2）销售表有字段memberid（会员id，外键）购买金额（MNAccount）；
（3）退货表中有字段memberid（会员id，外键）退货金额（RMNAccount）。
2）业务说明
（1）销售表中的销售记录可以是会员购买，也可以是非会员购买。（即销售表中的memberid可以为空）；
（2）销售表中的一个会员可以有多条购买记录；
（3）退货表中的退货记录可以是会员，也可是非会员；
（4）一个会员可以有一条或多条退货记录。
查询需求：分组查出销售表中所有会员购买金额，同时分组查出退货表中所有会员的退货金额，把会员id相同的购买金额-退款金额得到的结果更新到表会员表中对应会员的积分字段（credits）
数据集
sale
1001    50.3
1002    56.5
1003    235
1001    23.6
1005    56.2
        25.6
        33.5

regoods
1001    20.1
1002    23.6
1001    10.1
        23.5
        10.2
1005    0.8

```

-- 1）建表
create table member(memberid string,credits double) row format delimited fields terminated by '\t';

create table sale(memberid string,MNAccount double) row format delimited fields terminated by '\t';

create table regoods(memberid string,RMNAccount double) row format delimited fields terminated by '\t';

-- 2）最终SQL
insert into table member
select  t1.memberid,  MNAccount-RMNAccount from
 (select  memberid, sum(MNAccount) MNAccount from sale where memberid!='' group by memberid )t1
join
    (select memberid, sum(RMNAccount) RMNAccount from  regoods where  memberid!='' group by memberid )t2
on t1.memberid=t2.memberid;

