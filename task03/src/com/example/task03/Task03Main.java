package com.example.task03;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Stream;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }

    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset));
        HashMap<Integer, Set<String>> hashMap = new HashMap<>();
        bufferedReader.lines().forEach(line -> {
            if (line.length() >= 3) {
                Stream<Character.UnicodeBlock> unicodeBlockStream = line.chars().mapToObj(Character.UnicodeBlock::of);
                if (unicodeBlockStream.allMatch(s -> s.equals(Character.UnicodeBlock.CYRILLIC))) {
                    Set<String> set = hashMap.computeIfAbsent(line.length(), key -> new HashSet<>());
                    set.add(line.toLowerCase());
                }
            }
        });
        HashSet<Set<String>> result = new HashSet<>();
        hashMap.forEach((key, set) -> set.forEach(string -> {
            TreeSet<String> inter_res = new TreeSet<>();
            int[] string_chars = string.chars().sorted().toArray();
            set.forEach(another -> {
                if (Arrays.equals(string_chars, another.chars().sorted().toArray())) {
                    inter_res.add(another);
                }
            });
            if (inter_res.size() >= 2) result.add(inter_res);
        }));
        ArrayList<Set<String>> resultList = new ArrayList<>(result);
        resultList.sort(Comparator.comparing(s -> s.iterator().next()));
        return resultList;
    }
}
