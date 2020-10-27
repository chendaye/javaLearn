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



/*
Trips 表中存所有出租车的行程信息。每段行程有唯一键 Id，Client_Id 和 Driver_Id 是 Users 表中 Users_Id 的外键。Status 是枚举类型，枚举成员为 (‘completed’, ‘cancelled_by_driver’, ‘cancelled_by_client’)。

+----+-----------+-----------+---------+--------------------+----------+
| Id | Client_Id | Driver_Id | City_Id |        Status      |Request_at|
+----+-----------+-----------+---------+--------------------+----------+
| 1  |     1     |    10     |    1    |     completed      |2013-10-01|
| 2  |     2     |    11     |    1    | cancelled_by_driver|2013-10-01|
| 3  |     3     |    12     |    6    |     completed      |2013-10-01|
| 4  |     4     |    13     |    6    | cancelled_by_client|2013-10-01|
| 5  |     1     |    10     |    1    |     completed      |2013-10-02|
| 6  |     2     |    11     |    6    |     completed      |2013-10-02|
| 7  |     3     |    12     |    6    |     completed      |2013-10-02|
| 8  |     2     |    12     |    12   |     completed      |2013-10-03|
| 9  |     3     |    10     |    12   |     completed      |2013-10-03|
| 10 |     4     |    13     |    12   | cancelled_by_driver|2013-10-03|
+----+-----------+-----------+---------+--------------------+----------+
Users 表存所有用户。每个用户有唯一键 Users_Id。Banned 表示这个用户是否被禁止，Role 则是一个表示（‘client’, ‘driver’, ‘partner’）的枚举类型。

+----------+--------+--------+
| Users_Id | Banned |  Role  |
+----------+--------+--------+
|    1     |   No   | client |
|    2     |   Yes  | client |
|    3     |   No   | client |
|    4     |   No   | client |
|    10    |   No   | driver |
|    11    |   No   | driver |
|    12    |   No   | driver |
|    13    |   No   | driver |
+----------+--------+--------+
写一段 SQL 语句查出 2013年10月1日 至 2013年10月3日 期间非禁止用户的取消率。基于上表，你的 SQL 语句应返回如下结果，取消率（Cancellation Rate）保留两位小数。

取消率的计算方式如下：(被司机或乘客取消的非禁止用户生成的订单数量) / (非禁止用户生成的订单总数)

+------------+-------------------+
|     Day    | Cancellation Rate |
+------------+-------------------+
| 2013-10-01 |       0.33        |
| 2013-10-02 |       0.00        |
| 2013-10-03 |       0.50        |
+------------+-------------------+


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/trips-and-users
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

-- SUM(IF(T.STATUS = 'completed',0,1))
-- 	ROUND() 四舍五入
SELECT T.request_at AS `Day`,
	ROUND(
			SUM(
				IF(T.STATUS = 'completed',0,1)
			)
			/
			COUNT(T.STATUS),
			2
	) AS `Cancellation Rate`
FROM Trips AS T
JOIN Users AS U1 ON (T.client_id = U1.users_id AND U1.banned ='No')
JOIN Users AS U2 ON (T.driver_id = U2.users_id AND U2.banned ='No')
WHERE T.request_at BETWEEN '2013-10-01' AND '2013-10-03'
GROUP BY T.request_at


