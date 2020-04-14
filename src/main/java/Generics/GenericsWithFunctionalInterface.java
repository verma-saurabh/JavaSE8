package Generics;

public class GenericsWithFunctionalInterface {

    public static void main(String[] args) {
        ArgumentProcessor<String> stringArgumentProcessor;
        //Java 8 style
        stringArgumentProcessor = (arg1, arg2) -> arg1 + "_" + arg2;

        ArgumentProcessor<Integer> integerArgumentProcessor = new ArgumentProcessor<Integer>() {
            @Override
            public Integer process(Integer arg1, Integer arg2) {
                return arg1 * arg2;
            }
        };

        System.out.println(stringArgumentProcessor.process("Hello", "World"));
        System.out.println(integerArgumentProcessor.process(2, 3));
    }


}

@FunctionalInterface
interface ArgumentProcessor<X> {

    X process(X arg1, X arg2);

}
