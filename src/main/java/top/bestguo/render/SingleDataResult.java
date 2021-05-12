package top.bestguo.render;

/**
 * 单个数据返回时的结果
 * @param <T> 需要传递的对象类型
 */
public class SingleDataResult<T> extends BaseResult {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
