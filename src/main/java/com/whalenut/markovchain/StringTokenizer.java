package com.whalenut.markovchain;

import java.io.*;
import java.util.*;

public class StringTokenizer implements Tokenizer<String> {

    private final String seed;
    private final Map<String, Item<String>> saved = new HashMap<>();
    private final Random rand = new Random();

    public StringTokenizer(InputStream data) {
        try(BufferedInputStream bis = new BufferedInputStream(data);
            BufferedReader reader = new BufferedReader(new InputStreamReader(bis))) {

            String line;
            StringBuilder sb = new StringBuilder();
            while((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
            seed = sb.toString();
        } catch (IOException ex) {
            throw new RuntimeException("Could not read data", ex);
        }
    }

    @Override
    public void tokenize() {
        String[] parts = seed.toLowerCase().split("\\s");

        List<String> tokens = new ArrayList<>(parts.length + (parts.length/10));
        for (String part : parts) {
            if(part.endsWith(",") || part.endsWith(".")) {
                tokens.add(part.substring(0, part.length() - 1));
                tokens.add(part.substring(part.length() - 1, part.length()));
            } else {
                tokens.add(part);
            }
        }

        Item<String> prev = null;
        for(String token : tokens) {
            Item<String> current;
            if(saved.containsKey(token)) {
                current = saved.get(token);
            } else {
                current = new StringItem(token);
                saved.put(token, current);
            }

            if(prev != null) {
                prev.addFollower(current);
            }
            prev = current;
        }
    }


    @Override
    public String getFeed(int count) {
        Item<String> item = null;
        String key;
        int i = 0;
        int target = rand.nextInt((saved.entrySet().size()));
        for(Item<String> current : saved.values()) {
            item = current;
            if(i == target) {
                break;
            }
            i++;
        }

        List<String> tokens = new ArrayList<>();
        for (int j = 0; j < count; j++) {
            tokens.add(item.get());
            item = item.getFollowingItem();
            if(item == null) {
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        int index = 0;
        Boolean first = true;
        do {
            String token = item.get();
            if(token.matches("\\.|,") && index != 0) {
                if(token.equals(".")) {
                    sb.append(token).append(" ");
                    first = true;
                } else {
                    sb.append(token);
                }
            } else if(index == 0 || first) {
                sb.append(Character.toUpperCase(token.charAt(0)))
                        .append(token.substring(1));
                index++;
                first = false;
            } else {
                sb.append(" ").append(token);
                index++;
            }

            if(index >= 50) {
                break;
            }
        } while((item = item.getFollowingItem()) != null);
        sb.append(".");

        return sb.toString();
    }
}
