package Generics;

public class GenericsWithBoundedTypes {
    public static void main(String[] args) {
        GenericsWithBoundedTypes obj = new GenericsWithBoundedTypes();
        String str = "Hello World";
        int number = 10;
        double decimal = 10.0;
        float f = 10F;
        long l = 10L;

        // obj.print(str); // gives compilation error

        obj.print(number);
        obj.print(decimal);
        obj.print(f);
        obj.print(l);

    }

    public <T extends Number> void print(T data) {
        System.out.println(data.getClass().getName() + " => " + data);
    }
}
