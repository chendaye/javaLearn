-- 设置变量

-- 方式一

select id, @i := @i+id FROM `require`, (select @i := 1) as num

-- 方式二

set @i := 1;
SELECT id, @i := @i+id from `require`


-- 窗口函数的使用

-- 序号函数： 算排序
-- number_row() 1 2 3
-- rank()  1 1 3
-- dense_rank() 1 1 2

/**
score 表
+--------+-------------+-----------+-------+
| stu_id | score_order | lesson_id | score |
+--------+-------------+-----------+-------+
|      1 |           1 | L005      |    98 |
|      1 |           2 | L001      |    98 |
|      1 |           3 | L004      |    88 |
|      2 |           1 | L002      |    90 |
|      2 |           2 | L003      |    86 |
|      2 |           3 | L001      |    84 |
|      3 |           1 | L001      |   100 |
|      3 |           2 | L002      |    91 |
|      3 |           3 | L003      |    85 |
|      4 |           1 | L001      |    99 |
|      4 |           2 | L005      |    98 |
|      4 |           3 | L002      |    88 |
+--------+-------------+-----------+-------+
 */

 /*应用场景：查询每个学生的分数最高的前3门课程*/

 -- 首先每一个学生分数都要排序: 先用窗口函数 给每个学生的所有记录进行排序 得到一个子查询
 select stu_id, rank() over (partition by stu_id order by score desc) as score_order from score
 -- 在求出每个学生排序的基础上，汇总结果
 select stu_id, score_order
 from (select stu_id, rank() over (partition by stu_id order by score desc) as score_order from score) as t
 where score <= 4

 /*
 uuid city ip date

 每天 访问前 10 的 IP
 */
 -- 按 ip date 分组 统计每个 分组的数量
select count(uuid) as cnt, ip,date  group by date,ip  from A
-- 在上面的基础上 进一步 按 date 划分窗口，在窗口内按 cnt 排序
select ip, date, cnt, number_row() over (partition by date order by cnt desc) as cnt_order from (
    select count(uuid) as cnt, ip,date  group by date,ip  from A
) as B
-- 汇聚上面两步的结果
select date,ip,cnt from (
    select ip, date, cnt, number_row() over (partition by date order by cnt desc) as cnt_order from (
        select count(uuid) as cnt, ip,date  group by date,ip  from A
    ) as B
) as C where cnt_order <=10 order by date asc


 -- 分布函数：算百分比
 -- PERCENT_RANK() :每行按照公式(rank-1) / (rows-1)进行计算。其中，rank为RANK()函数产生的序号，rows为当前窗口的记录总行数
 -- CUME_DIST() : 分组内小于、等于当前rank值的行数 / 分组内总行数

 /**
 +----+------+--------+-----------+-------+
| rk | prk  | stu_id | lesson_id | score |
+----+------+--------+-----------+-------+
|  1 |    0 |      1 | L003      |    79 |
|  2 | 0.25 |      1 | L002      |    86 |
|  3 |  0.5 |      1 | L004      |    88 |
|  4 | 0.75 |      1 | L005      |    98 |
|  4 | 0.75 |      1 | L001      |    98 |
+----+------+--------+-----------+-------+
  */
  select rank() over win as rk, percent_rank() over win as prk, stu_id
  from score
  where score > 1
  windows w as (partition by stu_id order by score)


select stu_id, cume_dist() over (order by score) as all_order, cume_dist() over (partition by lesson_id order by score) as ord from score


-- 前后函数：
-- LAG(expr,n) 返回位于当前行的前n行（LAG(expr,n)）或后n行（LEAD(expr,n)）的expr的值(expr 值字段)
-- LEAD(expr,n)

/**
score
+--------+-----------+-------+-----------+------+
| stu_id | lesson_id | score | pre_score | diff |
+--------+-----------+-------+-----------+------+
|      2 | L001      |    84 |      NULL | NULL |
|      1 | L001      |    98 |        84 |   14 |
|      4 | L001      |    99 |        98 |    1 |
|      3 | L001      |   100 |        99 |    1 |
|      1 | L002      |    86 |      NULL | NULL |
|      4 | L002      |    88 |        86 |    2 |
|      2 | L002      |    90 |        88 |    2 |
|      3 | L002      |    91 |        90 |    1 |
+--------+-----------+-------+-----------+------+
 */
-- 查询前1名同学的成绩和当前同学成绩的差值

-- 先得到前一名同学的成绩
select stu_id, score, lag(score, 1) over (order by score) from score