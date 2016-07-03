package com.whalenut.markovchain;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;


/**
 * This is a representation of a item, it may be a {@link String} or any other reasonable type
 * Internally this class will keep track of alternative following items.
 *
 * @param <T>
 */
public abstract class Item<T> {

    protected final T item;
    protected final Map<Item<T>, Integer> followers;
    private Map<Long, Item<T>> probabilities;
    private int maxVal = 0;
    private static final Random rand = new Random();

    public Item(final T item, final Map<Item<T>, Integer> followers) {
        this.item = item;
        this.followers = followers;
    }

    /**
     * Add a reference to a following {@link Item} to this {@link Item}
     *
     * @param item the reference to a {@link Item}
     */
    abstract public void addFollower(Item<T> item);

    /**
     * Retrieve the actual data from an item.
     *
     * @return T get the raw type from this item.
     */
    public T get() {
        return item;
    }

    private void calculateProbabilities() {
        int base = followers.size();
        probabilities = new TreeMap<>();

        double latest = 0;
        for (Map.Entry<Item<T>, Integer> entry : followers.entrySet()) {
            double probability = base / entry.getValue();
            probabilities.put(Math.round(latest + probability), entry.getKey());
            latest = latest + probability;
        }
        maxVal = (int) Math.round(latest);
    }

    /**
     * Based on probability and a java.util.Random
     * this will calculate a following item when called.
     *
     * @return {@Item<T>} the following item.
     */
    public Item<T> getFollowingItem() {
        if(probabilities == null) {
            calculateProbabilities();
        }

        int keyValue = rand.nextInt(maxVal + 1);

        Long prev = -1L;
        for(Long key : probabilities.keySet()) {
            if (keyValue <= key) {
                return probabilities.get(key);
            }
            prev = key;
        }
        return probabilities.get(prev);
    }

}
