package com.whalenut.markovchain;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class StringItemTest {


    @Test
    public void testCreating() {
        Item<String> item = new StringItem("world");
        item.addFollower(new StringItem("foo"));

        String first = item.getItem();
        String second = item.getFollowingItem().getItem();

        assertEquals("world foo", String.format("%s %s", first, second));

    }



}
