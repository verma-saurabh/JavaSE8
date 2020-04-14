package StreamAPI;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamCollectors {
    public static void main(String[] args) {
        Stream<Integer> integerStream = Stream.of(5, 4, 3, 2, 1, 1);
        List<Integer> list = integerStream.collect(Collectors.toList());

        Set<Integer> integerSet = integerStream.collect(Collectors.toSet());

        //// toMap

        Stream<String[]> strStream = Stream.of(new String[][]{
                {"key", "value"},
                {"Saurabh", "verma"},
                {"Hello", "World"},
                {"Test", "Test"},
                {"Java", "Eight"},
        });

        Map<String, String> map = strStream
                .collect(Collectors.toMap(s -> s[0], s -> s[1]));


    }
}
