package top.bestguo.render;

import java.util.List;

/**
 * 返回数据类，一般运用于加载表格的数据。
 * 该类适用于分页。
 *
 * @param <T> 传入哪种是类型
 */
public class MultipleDataResult<T> extends BaseResult {

    /**
     * 返回的状态码
     */
    private Integer code;

    /**
     * 返回的消息提示信息
     */
    private String message;

    /**
     * 返回的数据总数（不是每一页的数据个数）
     */
    private Integer total;

    /**
     * 返回的数据
     */
    private List<T> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
