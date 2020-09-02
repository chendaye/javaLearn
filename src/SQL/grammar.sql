-- 条件语法
IF( expr1 , expr2 , expr3 ) -- expr1条件，条件为true，则值是expr2 ，false，值就是expr3

IFNULL( expr1 , expr2) -- 在 expr1 的值不为 NULL的情况下都返回 expr1，否则返回 expr

COALESCE(x,y,z) -- 返回x y z 中第一个非空（NULL）的，若都为空 返回 空

CASE 列名  -- 分支语法
    WHEN 条件 THEN 结果
    ELSE 其他结果
    END 别名

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
select stu_id, score, lag(score, 1) over (order by score) as pre from score
-- 汇总上一步结果
select score - pre from (select stu_id, score, lag(score, 1) over (order by score) as pre from score) as table

-- 头尾函数：
-- FIRST_VALUE(expr) : 返回第一个（FIRST_VALUE(expr)）或最后一个（LAST_VALUE(expr)）expr的值
-- LAST_VALUE(expr)

/**
+--------+-----------+-------+-------------+-------------+------------+
| stu_id | lesson_id | score | create_time | first_score | last_score |
+--------+-----------+-------+-------------+-------------+------------+
|      3 | L001      |   100 | 2018-08-07  |         100 |        100 |
|      1 | L001      |    98 | 2018-08-08  |         100 |         98 |
|      2 | L001      |    84 | 2018-08-09  |         100 |         99 |
|      4 | L001      |    99 | 2018-08-09  |         100 |         99 |
|      3 | L002      |    91 | 2018-08-07  |          91 |         91 |
|      1 | L002      |    86 | 2018-08-08  |          91 |         86 |
|      2 | L002      |    90 | 2018-08-09  |          91 |         90 |
|      4 | L002      |    88 | 2018-08-10  |          91 |         88 |
+--------+-----------+-------+-------------+-------------+------------+
 */

/*截止到当前成绩，按照日期排序查询第1个和最后1个同学的分数*/
 SELECT stu_id, lesson_id, score, create_time,
   FIRST_VALUE(score) OVER w AS first_score,
    LAST_VALUE(score) OVER w AS last_score
    FROM t_score
    WHERE lesson_id IN ('L001','L002')
    WINDOW w AS (PARTITION BY lesson_id ORDER BY create_time)


-- 其它函数：
-- NTH_VALUE(expr, n)  返回窗口中第n个expr的值。expr可以是表达式，也可以是列名
-- NTILE(n)

/*
+--------+-----------+-------+--------------+-------------+
| stu_id | lesson_id | score | second_score | third_score |
+--------+-----------+-------+--------------+-------------+
|      1 | L003      |    79 |         NULL |        NULL |
|      1 | L002      |    86 |           86 |        NULL |
|      1 | L004      |    88 |           86 |          88 |
|      1 | L001      |    98 |           86 |          88 |
|      1 | L005      |    98 |           86 |          88 |
|      2 | L004      |    75 |         NULL |        NULL |
|      2 | L005      |    77 |           77 |        NULL |
|      2 | L001      |    84 |           77 |          84 |
|      2 | L003      |    86 |           77 |          84 |
|      2 | L002      |    90 |           77 |          84 |
+--------+-----------+-------+--------------+-------------+
*/

SELECT stu_id, lesson_id, score,
    NTH_VALUE(score,2) OVER w AS second_score,
    NTH_VALUE(score,3) OVER w AS third_score
    FROM t_score
    WHERE stu_id IN (1,2)
    WINDOW w AS (PARTITION BY stu_id ORDER BY score)

-- NTILE(n) 将分区中的有序数据分为n个等级，记录等级数

/*将每门课程按照成绩分成3组*/

/*
+------+--------+-----------+-------+
| nf   | stu_id | lesson_id | score |
+------+--------+-----------+-------+
|    1 |      2 | L001      |    84 |
|    1 |      1 | L001      |    98 |
|    2 |      4 | L001      |    99 |
|    3 |      3 | L001      |   100 |
|    1 |      1 | L002      |    86 |
|    1 |      4 | L002      |    88 |
|    2 |      2 | L002      |    90 |
|    3 |      3 | L002      |    91 |
+------+--------+-----------+-------+
*/
SELECT
NTILE(3) OVER w AS nf,stu_id, lesson_id, score
FROM t_score
WHERE lesson_id IN ('L001','L002')
WINDOW w AS (PARTITION BY lesson_id ORDER BY score)

-- 聚合函数作为窗口函数

-- 在窗口中每条记录动态地应用聚合函数（SUM()、AVG()、MAX()、MIN()、COUNT()），可以动态计算在指定的窗口内的各种聚合函数值

/*截止到当前时间，查询stu_id=1的学生的累计分数、分数最高的科目、分数最低的科目*/

SELECT stu_id, lesson_id, score, create_time,
    SUM(score) OVER w AS score_sum,
    MAX(score) OVER w AS score_max,
    MIN(score) OVER w AS score_min
    FROM t_score
    WHERE stu_id = 1
    WINDOW w AS (PARTITION BY stu_id ORDER BY create_time)