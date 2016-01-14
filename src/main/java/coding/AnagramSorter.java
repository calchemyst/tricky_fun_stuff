package coding;

import java.util.*;
import java.util.stream.Collector;

/**
 * Created by annafuller on 1/1/16.
 */
public class AnagramSorter {
    private static String[] words = {"low", "house",  "cow", "woc", "ehous", "owl",  "horse"};
    private static String [] test = {"ray adverb","anagram","apple","bean","xylophone","nag a ram","dave barry"};

    public static void main(String[] args) {
        String[] wordsToAnalyze = test;
        Set<String> anagramSet = new HashSet<>();
        List<String> anagrams = new ArrayList<>();
        List<String> notAnagrams = new ArrayList<>();
        for (String w: wordsToAnalyze) {
            if (!anagramSet.contains(w)) {
                List<String> potentialAnagrams = getAnagrams(w, wordsToAnalyze);
                anagramSet.addAll(potentialAnagrams);
                if (potentialAnagrams.size() > 0) {
                    anagrams.addAll(potentialAnagrams);
                } else {
                    notAnagrams.addAll(potentialAnagrams);
                }
            }
        }

        System.out.println(anagrams.toString());
    }

    private static List<String> getAnagrams(String s, String[] wordsToAnalyze) {
        List<String> anagrams = new ArrayList<>();
        anagrams.add(s);
        for (String w: wordsToAnalyze) {

            if (isAnagram(s.replaceAll(" ", ""), w.replaceAll(" ", ""))) {
                System.out.println("Found an anagram " + s + " " + w);
                anagrams.add(w);
            }
        }
        return anagrams;
    }


    private static boolean isAnagram(String w, String v) {
        if (w.length() != v.length()) {
            return false;
        } else if (v.equals(w)) {
                return false;
        } else {
            String sortedV = sortCharacters(v);
            String sortedW = sortCharacters(w);
            return sortedV.equals(sortedW);
        }
    }

    private static String sortCharacters(String stringToSort) {
        char[] toSort = stringToSort.toCharArray();
        int curr = 0;
        while (curr < toSort.length) {
            for (int i = 0; i < toSort.length; i++) {
                if (i == toSort.length -1) {
                    if (Character.getNumericValue(toSort[i]) <
                            Character.getNumericValue(toSort[i - 1])) {
                        char tmp = toSort[i  - 1];
                        toSort[i - 1] = toSort[i];
                        toSort[i] = tmp;
                    }
                } else {
                    swap(toSort, i);
                }
            }
            curr++;
        }
        return new String(toSort);
    }

    private static void swap(char[] toSort, int i) {
        if (Character.getNumericValue(toSort[i]) > Character.getNumericValue(toSort[i + 1])) {
            char tmp = toSort[i + 1];
            toSort[i + 1] = toSort[i];
            toSort[i] = tmp;
        }
    }

}
