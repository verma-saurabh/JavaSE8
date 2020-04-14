package Generics;

public class WithoutGenerics {
    public static void main(String[] args) {

        String str = "Hello World";
        int number = 10;
        double decimal = 10.0;
        float f = 10F;
        long l = 10L;

        WithoutGenerics obj = new WithoutGenerics();
        // In the below cases explicit type casting is needed
        obj.printFloat((float) decimal);
        obj.printString(String.valueOf(number));
        obj.printLong((long) f);

    }

    public void printString(String data) {
        System.out.println("The data is " + data);
    }

    public void printInt(int data) {
        System.out.println("The data is " + data);
    }

    public void printDouble(double data) {
        System.out.println("The data is " + data);
    }

    public void printFloat(float data) {
        System.out.println("The data is " + data);
    }

    public void printLong(long data) {
        System.out.println("The data is " + data);
    }

}
