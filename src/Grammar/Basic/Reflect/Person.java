package Grammar.Basic.Reflect;

public class Person {
    static {
        System.out.println("static code 1");
    }


    private String name = "lao wang";
    public String sex = "sex";

    public void Say(String word){
        System.out.println(word+"......"+name);
    }

    private void Run(String run){
        System.out.println(run+"..."+sex);
    }
}
