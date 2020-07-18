import java.lang.reflect.Array;
import java.util.*;

public class Anagrams {
    public static List<String> funWithAnagrams(List<String> text) {

        for (int i = 0; i < text.size() - 1; i++) {
            for (int j = i + 1; j < text.size(); j++) {
                if (text.get(i).length() == text.get(j).length()) {
                    char[] a = text.get(i).toCharArray();
                    char[] b = text.get(j).toCharArray();
                    Arrays.sort(a);
                    Arrays.sort(b);
                    String c = new String(a);
                    String d = new String(b);
                    if (c.equals(d)) {
                        text.remove(i);
                        i--;
                    }

                }
            }

        }
        Collections.sort(text);
        return text;
    }

    public static int countPairs(List<Integer> numbers, int k) {
        // Write your code here


        Collections.sort(numbers);
        HashSet<Integer> set= new HashSet<>(numbers);
        numbers= new ArrayList<>(set);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < set.size(); i++) {
            for (int j = i + 1; i < set.size(); j++) {
                int num1 = numbers.get(i);
                int num2 = numbers.get(j);
                if (Math.abs(num1 - num2) == k) {
                    boolean exists = false;
                    if (map.size() != 0) {
                        if (map.get(num1) != null && map.get(num2) != null) {
                            if (map.get(num1) == num2 || map.get(num2) == num1) {
                                exists = true;
                            }
                        }


                    }
                    if (!exists) {
                        map.put(num1, num2);
                        map.put(num2, num1);
                    }
                }
            }

        }
        return map.size();
    }
}
