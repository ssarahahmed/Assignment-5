import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class RadixSort {
    public List<String> radixSort(String[] s) {
        //handles empty array
        if (s == null || s.length == 0){
            return new ArrayList<>();
        }

        //find the longest string to know how many passes needed
        int maxLength = 0;
        for (String word : s) {
            if (word.length() > maxLength) {
                maxLength = word.length();
            }
        }

        //add all elements into a list
        List<String> list = new ArrayList<>();
        for (String word : s) {
            list.add(word);
        }

        //pass every character left to right
        for (int pos = 0; pos < maxLength; pos++) {
            //each bucket will hold words sharing same char at pos
            HashMap<Integer, List<String>> buckets = new HashMap<>();

            //bucket for words shorter than current position
            buckets.put(-1, new ArrayList<>());

            for (String word : list) {
                if (word.length() <= pos) {
                    buckets.get(-1).add(word);
                } else {
                    int ch = (int) word.charAt(pos);
                    if (!buckets.containsKey(ch)) {
                        buckets.put(ch, new ArrayList<>());
                    }
                    buckets.get(ch).add(word);
                }
            }

            //restart list starting with shhorter words
            list.clear();
            list.addAll(buckets.get(-1));

            //sort character keys to add buckets back in correct order
            List<Integer> keys = new ArrayList<>(buckets.keySet());
            keys.remove((-1));


            for (int i = 0; i < keys.size(); i++) {
                int minIndex = i;
                for (int j = i + 1; j < keys.size(); j++) {
                    if (keys.get(j) < keys.get(minIndex)) {
                        minIndex = j;
                    }
                }
                int temp = keys.get(i);
                keys.set(i, keys.get(minIndex));
                keys.set(minIndex, temp);
            }


            for (int key : keys) {
                list.addAll(buckets.get(key));
            }
        }

        return list;
    }

    }
