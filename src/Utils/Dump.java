package Utils;

import java.util.Collection;
import java.util.Iterator;

public  class Dump {

    public static <T> void dump(T object){
        System.out.println(object);
    }

    public static <T extends Collection> void iterator(T object){
        Iterator iterator = object.iterator();
        while (iterator.hasNext()){
            dump(iterator.next());
        }
    }
}
