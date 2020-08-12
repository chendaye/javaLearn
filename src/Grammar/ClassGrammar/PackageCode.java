package Grammar.ClassGrammar;

/**
 * 1、 包与包之间进行访问，被访问的包中的类以及类中的成员，需要public修饰
 * 2、不同包中的子类可以访问父类中被protect修饰的成员
 * 3、包与包之间可以使用的权限只有 public protect
 *
 * 权限：
 *                   public   protect  default  private
 * 同一个类            ok        ok       ok       ok
 * 同一个包            ok        ok       ok
 * 子类                ok        ok
 * 不同包              ok
 */
public class PackageCode {
}
