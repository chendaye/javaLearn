/*
Write a SQL query to find all numbers that appear at least three times consecutively.

+----+-----+
| Id | Num |
+----+-----+
| 1  |  1  |
| 2  |  1  |
| 3  |  1  |
| 4  |  2  |
| 5  |  1  |
| 6  |  2  |
| 7  |  2  |
+----+-----+
For example, given the above Logs table, 1 is the only number that appears consecutively for at least three times.

+-----------------+
| ConsecutiveNums |
+-----------------+
| 1               |
+-----------------+

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/consecutive-numbers
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

select distinct num as ConsecutiveNums  from
(
    select num,lead(num,1)over()as num1,lead(num,2)over()as num2
    from logs
) as c
where c.num = c.num1 and c.num1 = c.num2;


/*
编写一个 SQL 查询，来删除 Person 表中所有重复的电子邮箱，重复的邮箱里只保留 Id 最小 的那个。

+----+------------------+
| Id | Email            |
+----+------------------+
| 1  | john@example.com |
| 2  | bob@example.com  |
| 3  | john@example.com |
+----+------------------+

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/delete-duplicate-emails
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

-- 自连接
DELETE p1 FROM Person p1,
    Person p2
WHERE
    p1.Email = p2.Email AND p1.Id > p2.Id

DELETE t1 FROM Person as t1 LEFT JOIN Person as t2 ON t1.Email=t2.Email WHERE t1.id > t2.id;

/*
连续出现若干次

一个表记录了某论坛会员的发贴情况，存储了会员uid ，发贴时间post_time和内容content。找出连续发贴三次及以上的会员。
uid	     post_time	    content
1	2019-03-01 00:00:00	a
2	2019-03-01 00:00:01	b
3	2019-03-01 00:00:02	c
3	2019-03-01 00:00:03	d
3	2019-03-01 00:00:04	e
2	2019-03-01 00:00:05	f
2	2019-03-01 00:00:06	g
1	2019-03-01 00:00:07	h
4	2019-03-01 00:00:08	i
4	2019-03-01 00:00:09	j
4	2019-03-01 00:00:10	k
5	2019-03-01 00:00:11	l

uid	post_time	     content row1 row2	row2-row1
1	2019-03-01 00:00:00	a	1	1	0
2	2019-03-01 00:00:01	b	1	2	1
3	2019-03-01 00:00:02	c	1	3	2
3	2019-03-01 00:00:03	d	2	4	2
3	2019-03-01 00:00:04	e	3	5	2
2	2019-03-01 00:00:05	f	2	6	4
2	2019-03-01 00:00:06	g	3	7	4
1	2019-03-01 00:00:07	h	2	8	6
4	2019-03-01 00:00:08	i	1	9	8
4	2019-03-01 00:00:09	j	2	10	8
4	2019-03-01 00:00:10	k	3	11	8
5	2019-03-01 00:00:11	l	1	12	11
*/

-- row1-row2 相等的就是连续出现的
select uid, post_time,
row_number() over (order by post_time) as row1,
row_number() over (partition by uid order by post_time) as row2
from table_name

select a.uid,a.row1-a.row2, count(a.uid) from (
    select uid, post_time,
    row_number() over (order by post_time) as row1,
    row_number() over (partition by uid order by post_time) as row2
    from table_name
) a group by a.uid,a.row1-a.row2 having count(a.uid) > 3


/*

编写一个 SQL 查询，来查找与之前（昨天的）日期相比温度更高的所有日期的 id 。

返回结果 不要求顺序 。

查询结果格式如下例：

Weather
+----+------------+-------------+
| id | recordDate | Temperature |
+----+------------+-------------+
| 1  | 2015-01-01 | 10          |
| 2  | 2015-01-02 | 25          |
| 3  | 2015-01-03 | 20          |
| 4  | 2015-01-04 | 30          |
+----+------------+-------------+

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/rising-temperature
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

-- 和自己前后的记录比较，考虑自连接
SELECT
    weather.id AS 'Id'
FROM
    weather
        JOIN
    weather w ON DATEDIFF(weather.recordDate, w.recordDate) = 1
        AND weather.Temperature > w.Temperature;

-- 和前后比较，考虑开窗函数
-- lag(expr, n) 前第n行
-- lead(expr, n) 后第n行
select id "Id" from
    ( select T.id ,T.recorddate,
        trunc(T.recorddate -  lag(T.recorddate,1) over(order by T.recordDate))as diff_days, -- T.recorddate 前一行
        T.temperature- lag(T.temperature,1) over(order by T.recordDate) as diff_temperature -- T.recorddate 前一行
    from weather T
    )
where  diff_temperature > 0 and diff_days=1
