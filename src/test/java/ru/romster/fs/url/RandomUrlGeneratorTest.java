package ru.romster.fs.url;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by r0m5t3r on 11/12/15.
 */
public class RandomUrlGeneratorTest {


    RandomUrlGenerator generator = new RandomUrlGenerator();

    @Before
    public void setUp() throws Exception {
        generator.init();
    }

    @Test
    public void testCreteNewUrl() throws Exception {
        String url = generator.creteNewUrl();
        System.out.println(url);
        url = generator.creteNewUrl();
        System.out.println(url);
        url = generator.creteNewUrl();
        System.out.println(url);
        url = generator.creteNewUrl();
        System.out.println(url);
    }
}