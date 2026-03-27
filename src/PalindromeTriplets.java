import java.util.ArrayList;
import java.util.List;

public class PalindromeTriplets {

    //check if a string is a palindrome
    public static boolean isPalindrome(String s){
        int left = 0;
        int right = s.length() - 1;
        while(left < right){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    //find triplets where words[i] + words[j] + words[k] is a palindrome
    public static List<int[]> palindromeTriplets(String[] words){
        List<int[]> result = new ArrayList<>();
        int n = words.length;

        //try different combinations of three indices
        for(int i = 0; i < words.length; i++){
            for(int j = 0; j < words.length; j++){
                for(int k = 0; k < words.length; k++){
                    //skip if two indices are the same
                    if(i != j && j != k && i != k){

                        //concatenate and check if palindrome
                        String combined = words[i] + words[j] + words[k];

                        if(isPalindrome(combined)){
                            result.add(new int[]{i, j, k});
                        }
                    }
                }
            }
        }

        return result;
    }


    public static void main(String[] args){
        String[] words = {"race", "car", "ecar", ""};

        List<int[]> triplets = palindromeTriplets(words);

        //print all triplets
        System.out.print("[");
        for(int i = 0; i < triplets.size(); i++){
            int[] triplet = triplets.get(i);
            System.out.print("{" + triplet[0] + ", " + triplet[1] + ", " + triplet[2] + "}");
            if (i < triplets.size() -1){
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
