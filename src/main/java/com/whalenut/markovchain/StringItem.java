package com.whalenut.markovchain;

import java.util.HashMap;

public class StringItem extends Item<String> {

    public StringItem(final String item) {
        super(item, new HashMap<>());
    }

    public void addFollower(Item<String> item) {
        if(followers.containsKey(item)) {
            followers.put(item, followers.get(item) + 1);
        }
        this.followers.put(item, 1);
    }
}
