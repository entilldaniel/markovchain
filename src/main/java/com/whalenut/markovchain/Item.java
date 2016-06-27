package com.whalenut.markovchain;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;


/**
 * I'll begin with a dumb implementation that will
 * not be a real markov chain, instead i'll just randomly
 * select the next word.
 *
 * @param <T>
 */
public abstract class Item<T> {

    protected final T item;
    protected final Map<Item<T>, Integer> followers;
    private Map<Integer, Item<T>> probabilities;
    private int maxVal = 0;
    private static final Random rand = new Random();

    public Item(final T item, final Map<Item<T>, Integer> followers) {
        this.item = item;
        this.followers = followers;
    }

    abstract public void addFollower(Item<T> item);

    public T getItem() {
        return item;
    }

    private void calculateProbabilities() {
        int base = followers.size();
        probabilities = new TreeMap<>();

        double latest = 0;
        for (Map.Entry<Item<T>, Integer> entry : followers.entrySet()) {
            double probability = base / entry.getValue().intValue();
            //TODO: Change stuff to long instead.
            probabilities.put(new Integer((int)Math.round(latest + probability)), entry.getKey());
            latest = latest + probability;
        }
        maxVal = (int) Math.round(latest);
    }

    public Item<T> getFollowingItem() {
        if(probabilities == null) {
            calculateProbabilities();
        }

        int keyValue = rand.nextInt(maxVal + 1);

        Integer prev = -1;
        for(Integer key : probabilities.keySet()) {
            if (keyValue <= key) {
                return probabilities.get(key);
            }
            prev = key;
        }
        return probabilities.get(prev);
    }

}
