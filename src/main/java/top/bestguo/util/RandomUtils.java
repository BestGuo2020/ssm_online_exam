package top.bestguo.util;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

/**
 * 随机数工具类
 */
public class RandomUtils {

    private static Random random = new Random();

    /**
     * 生成整数随机数，不重复
     *
     * @param count 生成整数随机数
     * @param size 生成整数随机数范围
     * @return
     */
    public static LinkedHashSet<Integer> getRandomInt(int count, int size) {
        LinkedHashSet<Integer> set = new LinkedHashSet<Integer>();
        while (set.size() < count) {
            Integer tmp = random.nextInt(size);
            set.add(tmp);
        }
        return set;
    }
}
