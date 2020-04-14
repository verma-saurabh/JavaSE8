package Generics;

public class GenericsWithTwoTypes {
    public static void main(String[] args) {
        GenericsWithTwoTypes obj = new GenericsWithTwoTypes();
        String str = "Hello World";
        int number = 10;
        double decimal = 10.0;
        float f = 10F;
        long l = 10L;

        obj.print(str, number);
        obj.print(number, decimal);
        obj.print(decimal, f);
        obj.print(f, l);
        obj.print(l, str);

    }

    public <T, G> void print(T data1, G data2) {
        System.out.println(data1.getClass().getName() + " =>The data 1 is " + data1 + "\n"
                + data2.getClass().getName() + " =>The data 2 is " + data2);
        System.out.println("**********************");
    }
}
