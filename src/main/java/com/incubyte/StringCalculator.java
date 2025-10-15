package main.java.com.incubyte;

import org.junit.platform.commons.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StringCalculator {

    public int add(String numbers) {
        if (StringUtils.isBlank(numbers)) return 0;

        String input = numbers;
        String customDelimiter = null;

        if (numbers.startsWith("//")) {
            int nl = numbers.indexOf('\n');
            if (nl == -1) {
                throw new IllegalArgumentException("Invalid input: missing newline after delimiter header");
            }
            customDelimiter = numbers.substring(2, nl);
            input = numbers.substring(nl + 1);
        }

        String delimRegex = (customDelimiter == null)
                ? ",|\n"
                : ",|\n|" + Pattern.quote(customDelimiter);

        String[] tokens = input.split(delimRegex, -1);
        int sum = 0;
        List<Integer> negatives = new ArrayList<>();

        for (String t : tokens) {
            if (t == null || t.isEmpty()) continue;
            int val = Integer.parseInt(t.trim());
            if (val < 0) negatives.add(val);
            else sum += val;
        }

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("negative numbers not allowed " + csv(negatives));
        }
        return sum;
    }

    private static String csv(List<Integer> ns) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ns.size(); i++) {
            if (i > 0) sb.append(',');
            sb.append(ns.get(i));
        }
        return sb.toString();
    }
}
