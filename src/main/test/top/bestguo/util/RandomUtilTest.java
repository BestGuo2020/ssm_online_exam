package top.bestguo.util;

import org.junit.Test;

public class RandomUtilTest {

    @Test
    public void test1() {
        System.out.println(RandomUtils.getRandomInt(10, 10));
    }

    @Test
    public void test2() {
        for (int i = 0; i < 20; i++) {
            System.out.println(RandomUtils.generateCodeLength8());
        }
    }

}
