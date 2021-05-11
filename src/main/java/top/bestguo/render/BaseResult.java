package top.bestguo.render;

public class BaseResult {

    /**
     * 返回的状态码，0为正常，1为不正常
     */
    private Integer code;

    /**
     * 返回的消息提示信息
     */
    private String message;

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
}
