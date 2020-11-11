# 正则
```java
import java.util.regex.*;
/*
Pattern 类：
pattern 对象是一个正则表达式的编译表示。Pattern 类没有公共构造方法。要创建一个 Pattern 对象，你必须首先调用其公共静态编译方法，它返回一个 Pattern 对象。该方法接受一个正则表达式作为它的第一个参数。

Matcher 类：
Matcher 对象是对输入字符串进行解释和匹配操作的引擎。与Pattern 类一样，Matcher 也没有公共构造方法。你需要调用 Pattern 对象的 matcher 方法来获得一个 Matcher 对象。

PatternSyntaxException：
PatternSyntaxException 是一个非强制异常类，它表示一个正则表达式模式中的语法错误。

*/

boolean isMatch = Pattern.matches(pattern, content);

 s.matches("^[a-z]$");
```

```
^  开头
$  结尾
*  >= 0  {0, }
+  >= 1  {1, }
?  >=0 <=1  {0,1}
{n}  正好n次
{m,n}  >=m <=n
{n,}  >=n
(pattern)  一个模式为一组
x|y  或的关系
[xyz]  [^xyz]  [a-z]  [^a-z]  [^a-z0-9A-Z]
\d  数字字符匹配。等效于 [0-9]
\D  非数字字符匹配。等效于 [^0-9]
\n  换行
\r  回车
\s  匹配任何空白字符，包括空格、制表符、换页符等。与 [ \f\n\r\t\v] 等效。
\S  匹配任何非空白字符。与 [^ \f\n\r\t\v] 等效
\w  [A-Za-z0-9_]
\W  [^A-Za-z0-9_]

```

