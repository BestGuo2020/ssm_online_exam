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
     * @param count 生成整数随机数个数
     * @param size 生成整数随机数范围
     * @return
     */
    public static LinkedHashSet<Integer> getRandomInt(int count, int size) {
        LinkedHashSet<Integer> set = new LinkedHashSet<Integer>();
        if(count > size) {
            throw new IllegalArgumentException("生成整数的随机个数不得大于生成整数随机" +
                    "数范围：count:" + count + " > " + "size:" + size);
        }
        while (set.size() < count) {
            Integer tmp = random.nextInt(size);
            set.add(tmp);
        }
        return set;
    }

    /**
     * 生成8位数的代码
     *
     * @return 8位数的代码
     */
    public static Integer generateCodeLength8() {
        return random.nextInt(90000000) + 10000000;
    }
}
