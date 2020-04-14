package Generics;

import java.util.ArrayList;
import java.util.List;

public class WithGenerics {
    public static void main(String[] args) {
        WithGenerics obj = new WithGenerics();
        String str = "Hello World";
        int number = 10;
        double decimal = 10.0;
        float f = 10F;
        long l = 10L;

        List list = new ArrayList();
        obj.print(str);
        obj.print(number);
        obj.print(decimal);
        obj.print(f);
        obj.print(l);
        obj.print(list);

    }

    public <T> void print(T data) {
        System.out.println(data.getClass().getName()+" => The data is " + data);
    }
}
