package top.bestguo.render;

import java.util.List;

/**
 * 返回数据类，一般运用于加载表格的数据
 *
 * @param <T> 传入哪种是有蹄类
 */
public class DataResult<T> {

    /**
     * 返回的状态码
     */
    private Integer code;

    /**
     * 返回的消息
     */
    private String message;

    /**
     * 返回数据的个数
     */
    private Integer total;

    /**
     * 返回的数据
     */
    private List<T> data;


}
