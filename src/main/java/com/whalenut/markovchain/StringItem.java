package com.whalenut.markovchain;

import java.util.ArrayList;

public class StringItem extends Item<String> {

    public StringItem(final String item) {
        super(item, new ArrayList<Item<String>>());
    }

    public void addFollower(Item<String> item) {
        this.followers.add(item);
    }
}
