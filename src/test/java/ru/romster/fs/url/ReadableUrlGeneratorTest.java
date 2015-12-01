package ru.romster.fs.url;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by r0m5t3r on 11/12/15.
 */
public class ReadableUrlGeneratorTest {


    ReadableIdGenerator generator = new ReadableIdGenerator();

    @Before
    public void setUp() throws Exception {
        generator.init();
    }

    @Test
    public void testCreteNewUrl() throws Exception {
        String url = generator.getId();
        System.out.println(url);
        url = generator.getId();
        System.out.println(url);
        url = generator.getId();
        System.out.println(url);
        url = generator.getId();
        System.out.println(url);
    }
}