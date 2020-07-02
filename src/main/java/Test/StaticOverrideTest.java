package Test;

public class StaticOverrideTest {
    public static void main(String[] args) {
        A a = new A();
        a.show();
        A b = new B();
        b.show();
        B c = new B();
        c.show();


    }
}

class A {
    public static void show() {
        System.out.println("Inside A");
    }

}

class B extends A {
    public static void show() {
        System.out.println("Inside B");
    }
}
