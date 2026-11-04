package deepening02.restrictions;

import java.util.HashMap;
import java.util.Map;

public class GenericsRestrictions {
    public static void main(String[] args) {
        String str = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam interdum malesuada urna, "
                + "vel ornare massa pellentesque id. Donec ultricies, mauris id rutrum porta, tellus sem accumsan "
                + "risus, eu porttitor urna arcu quis nulla. Etiam lacinia, lorem sit amet sagittis venenatis, "
                + "quam libero semper elit, vel pharetra lectus dolor sit amet risus. In libero ante, "
                + "feugiat eu finibus nec, fermentum ac.";
        String[] words = str.replaceAll("[.,]", "").split(" ");

        @SuppressWarnings("unchecked")
        HashMap<String, Integer>[] maps = (HashMap<String, Integer>[]) new HashMap[5];

        for (int i = 0; i < maps.length; i++)
            maps[i] = new HashMap<>();

        for (int j = 0; j < words.length; j++) {
            String word = words[j];
            if (word.contains("a") && maps[0].putIfAbsent(word, 1) != null) {
                maps[0].put(word, maps[0].get(word) + 1);
            } else if (word.contains("e") && maps[1].putIfAbsent(word, 1) != null) {
                maps[1].put(word, maps[1].get(word) + 1);
            } else if (word.contains("i") && maps[2].putIfAbsent(word, 1) != null) {
                maps[2].put(word, maps[2].get(word) + 1);
            } else if (word.contains("o") && maps[3].putIfAbsent(word, 1) != null) {
                maps[3].put(word, maps[3].get(word) + 1);
            } else if (word.contains("u") && maps[4].putIfAbsent(word, 1) != null) {
                maps[4].put(word, maps[4].get(word) + 1);
            }
        }

        for (int i = 0; i < maps.length; i++) {
            System.out.printf("%s=", "aeiou".charAt(i));
            HashMap<String, Integer> map = maps[i];
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                final String k = entry.getKey();
                final Integer v = entry.getValue();
                System.out.printf("%s: %s, ", k, v);
            }
            System.out.println();
        }
    }
}
