package org.fix.parser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Fixer {
    static String tags;
    static StringBuilder sb = new StringBuilder();
    static StringBuilder response = new StringBuilder();
    static FixTagMappings fixTagMappings = new FixTagMappings();

    public static Map<String, String> split(String msg) {
        Map<String, String> splitMsg = new HashMap<String, String>();
        String[] ctx = msg.split("=|\\;");
        for (int i = 0; i < ctx.length; i += 2) {
            splitMsg.put(ctx[i].trim(), ctx[i + 1].trim());
        }
        return splitMsg;
    }

    public static String translate(String msg) {
        sb.delete(0, sb.length());
        Map<String, String> fixMsg = split(msg);
        for (Map.Entry<String, String> entry : fixMsg.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            if (fixTagMappings.getTagName(k) != null && fixTagMappings.getTagIDs().contains(k)) {
                tags = fixTagMappings.getTagName(k);
            } else {
                tags = k;
            }
            sb.append(tags + " = " + v + "\n");
        }
        return String.valueOf(sb);
    }

    public static Map<String, String> convertToAMap(String str) {
        return Arrays.stream(translate(str).split("\n"))
                .map(s -> s.split(" = "))
                .collect(Collectors.toMap(s -> s[0], s -> s[1]));
    }

    public static String compareFix(Map<String, String> m1, Map<String, String> m2) {
        response.delete(0, response.length());
        if (m1.keySet().equals(m2.keySet())) {
            for (Map.Entry<String, String> entry : m1.entrySet()) {
                String key = entry.getKey();
                String v1 = entry.getValue();
                String v2 = m2.get(key);
                if (!v1.equals(v2)) {
                    response.append("Expected " + "'" + v1 + "'" + " v/s Actual " + "'" + v2 + "'" + " for the FIX Tag " + entry.getKey() + "\n");
                }
            }
        } else {
            response.append("Expected \n" + m1.keySet() + "\n" + " v/s Actual\n" + m2.keySet());
        }
        return String.valueOf(response);
    }
}