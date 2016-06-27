package com.whalenut.markovchain;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class StringItemTest {

    @Test
    public void testCreating() {
        Item<String> item = new StringItem("world");
        item.addFollower(new StringItem("foo"));

        Item<String> followingItem = item.getFollowingItem();
        assertEquals("foo", followingItem.getItem());
    }

}
