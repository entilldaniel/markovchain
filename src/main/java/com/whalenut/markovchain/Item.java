package com.whalenut.markovchain;

import java.util.List;


/**
 * I'll begin with a dumb implementation that will
 * not be a real markov chain, instead i'll just randomly
 * select the next word.
 *
 * The next phase of implementation will be to select words
 * base on the probability that they actually will occur.
 *
 *
 * @param <T>
 */
public abstract class Item<T> {

    protected final T item;
    protected final List<Item<T>> followers;

    public Item(final T item, final List<Item<T>> followers) {
        this.item = item;
        this.followers = followers;
    }

    abstract public void addFollower(Item<T> item);

    public T getItem() {
        return item;
    }

    public Item<T> getFollowingItem() {

        return followers.get(0);
    }

}
