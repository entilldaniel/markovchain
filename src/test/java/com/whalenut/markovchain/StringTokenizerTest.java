package com.whalenut.markovchain;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static junit.framework.TestCase.assertEquals;

public class StringTokenizerTest {

    private final String story = "The most notable thing about Time is that it is so purely relative. A large amount of reminiscence is, by common consent, conceded to the drowning man and it is not past belief that one may review an entire courtship while removing one's gloves. That is what Trysdale was doing, standing by a table in his bachelor apartments. On the table stood a singular-looking green plant in a red earthen jar. The plant was one of the species of cacti, and was provided with long, tentacular leaves that perpetually swayed with the slightest breeze with a peculiar beckoning motion. Trysdale's friend, the brother of the bride, stood at a sideboard complaining at being allowed to drink alone. Both men were in evening dress. White favors like stars upon their coats shone through the gloom of the apartment. As he slowly unbuttoned his gloves, there passed through Trysdale's mind a swift, scarifying retrospect of the last few hours. It seemed that in his nostrils was still the scent of the flowers that had been banked in odorous masses about the church, and in his ears the lowpitched hum of a thousand well-bred voices, the rustle of crisp garments, and, most insistently recurring, the drawling words of the minister irrevocably binding her to another. From this last hopeless point of view he still strove, as if it had become a habit of his mind, to reach some conjecture as to why and how he had lost her. Shaken rudely by the uncompromising fact, he had suddenly found himself confronted by a thing he had never before faced --his own innermost, unmitigated, arid unbedecked self. He saw all the garbs of pretence and egoism that he had worn now turn to rags of folly. He shuddered at the thought that to others, before now, the garments of his soul must have appeared sorry and threadbare. Vanity and conceit? These were the joints in his armor. And how free from either she had always been.";

    @Test
    public void testTokenization() {
        InputStream is = new ByteArrayInputStream("hello world".getBytes());
        Tokenizer<String> tokenizer = new StringTokenizer(is);
        tokenizer.tokenize();
        String feed = tokenizer.getFeed(2);

        assertEquals("hello world", feed);
    }

    @Test
    public void testLargerTokenization() {
        InputStream is = new ByteArrayInputStream(story.getBytes());
        Tokenizer<String> tokenizer = new StringTokenizer(is);
        tokenizer.tokenize();
        String feed = tokenizer.getFeed(50);
        assertEquals(50, feed.split(" ").length);
    }




}
