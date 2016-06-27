package com.whalenut.markovchain;

import java.io.InputStream;

public interface Tokenizer<T> {

    void tokenize();

    T getFeed(int count);

}
