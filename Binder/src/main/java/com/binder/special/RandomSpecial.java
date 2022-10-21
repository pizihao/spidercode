package com.binder.special;

import com.binder.util.StringUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;
import java.util.function.Function;

/**
 * For the special case of random number processing
 */
public class RandomSpecial implements Function<String, Object> {

    private static final String PREFIX = "random.";
    Random random;

    public RandomSpecial(Random random) {
        this.random = random;
    }

    public Object getProperty(String name) {
        if (!name.startsWith(PREFIX)) {
            return null;
        }
        return getRandomValue(name.substring(PREFIX.length()));
    }

    private Object getRandomValue(String type) {
        if (type.equals("int")) {
            return random.nextInt();
        }
        if (type.equals("long")) {
            return random.nextLong();
        }
        String range = getRange(type, "int");
        if (range != null) {
            return getNextIntInRange(range);
        }
        range = getRange(type, "long");
        if (range != null) {
            return getNextLongInRange(range);
        }
        if (type.equals("uuid")) {
            return UUID.randomUUID().toString();
        }
        return getRandomBytes();
    }

    private String getRange(String type, String prefix) {
        if (type.startsWith(prefix)) {
            int startIndex = prefix.length() + 1;
            if (type.length() > startIndex) {
                return type.substring(startIndex, type.length() - 1);
            }
        }
        return null;
    }

    private int getNextIntInRange(String range) {
        String[] tokens = StringUtil.split(range, ",");
        int start = Integer.parseInt(tokens[0]);
        if (tokens.length == 1) {
            return random.nextInt(start);
        }
        return start + random.nextInt(Integer.parseInt(tokens[1]) - start);
    }

    private long getNextLongInRange(String range) {
        String[] tokens = StringUtil.split(range, ",");
        if (tokens.length == 1) {
            return Math.abs(random.nextLong() % Long.parseLong(tokens[0]));
        }
        long lowerBound = Long.parseLong(tokens[0]);
        long upperBound = Long.parseLong(tokens[1]) - lowerBound;
        return lowerBound + Math.abs(random.nextLong() % upperBound);
    }

    private Object getRandomBytes() {
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        try {
            return MessageDigest.getInstance("MD5").digest(bytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object apply(String s) {
        return getProperty(s);
    }
}
