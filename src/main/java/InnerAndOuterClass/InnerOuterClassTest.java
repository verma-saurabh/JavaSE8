package InnerAndOuterClass;

class Outer {
    int x = 0;

    protected class Inner {
        int y = 1;
    }

    public void outerMethod() {
        final int outermethodlocalvariable = 10;
        class MethodLocalInnerClass {
            void innerMethod() {
                System.out.println("Method local Inner Method " + outermethodlocalvariable);

            }
        }
        MethodLocalInnerClass obj = new MethodLocalInnerClass();
        obj.innerMethod();
    }
}

public class InnerOuterClassTest {
    public static void main(String[] args) {
        Outer o = new Outer();
        System.out.println(o.x);
        Outer.Inner inner = o.new Inner();
        System.out.println(inner.y);
        o.outerMethod();
    }
}
