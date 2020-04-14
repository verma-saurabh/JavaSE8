package StreamAPI;

import java.util.ArrayList;
import java.util.List;

public class StreamTutorial {
    public static void main(String[] args) {
        List<String> datasource = new ArrayList<>();
        datasource.add("Hello");
        datasource.add("Streams");
        datasource.add("Api");
        datasource.add("Java");
        datasource.add("eight");
        datasource.add("tutorial");
        datasource.add("this");
        datasource.add("is");
        datasource.add("A");

        long count = datasource.stream()
                .filter((str) -> {
                    return str.length() >= 4;
                }).count();
        System.out.println("count of string greater than 4 in length is " + count);


    }
}
