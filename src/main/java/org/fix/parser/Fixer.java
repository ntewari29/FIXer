package org.fix.parser;

import java.util.HashMap;
import java.util.Map;

public class Fixer {
    static String tags;
    static StringBuilder sb = new StringBuilder();
    static FixTagMappings fixTagMappings = new FixTagMappings();

    public static Map<String, String> split(String msg) {
        Map<String, String> splitMsg = new HashMap<String, String>();
        String[] ctx = msg.split("=|\\;");
        for (int i = 0; i < ctx.length; i+=2) {
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
        sb.append(tags + " : " + v + "\n");
        }
        return String.valueOf(sb);
    }
}
