package com.whalenut.markovchain;

import java.io.InputStream;


/**
 * This is the class that is responsible for
 * tokenizing the input source and making it to something
 * that may be converted into a {@link Item}
 *
 * @param <T> The type that the result will have,
 * commonly the same as the {@link Item}
 */
public interface Tokenizer<T> {

    /**
     * Explicitly call this method to tokenize the source data that should be passed into the constructor.
     */
    void tokenize();

    /**
     * Select the following item at maximum <b>count</b> times
     * from the available {@link Item}s.
     *
     * @param count the upper limit of how many calls to Item.getFollowingItem() will be made.
     * @return T the aggregated result.
     */
    T getFeed(int count);

}
