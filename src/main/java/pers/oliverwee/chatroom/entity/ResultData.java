package pers.oliverwee.chatroom.entity;


/**
 * 结果返回对象.
 *
 * @author Weiws
 */
public class ResultData<T> {

    /**
     * 请求是否成功.
     */
    private boolean success;

    /**
     * 返回信息.
     */
    private String message;

    /**
     * 返回对象.
     */
    private T data;

    public ResultData(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    /**
     * 构建一个成功的查询响应结果.
     *
     * @param data 返回对象
     * @param <T>  对象类型
     * @return 返回结果.
     */
    public static <T> ResultData<T> ok(T data) {
        return build(true, "OK", data);
    }

    /**
     * 构建一个失败的查询响应结果.
     *
     * @param data 返回对象
     * @param <T>  对象类型
     * @return 返回结果.
     */
    public static <T> ResultData<T> error(String message, T data) {
        return build(false, message, data);
    }

    /**
     * 构建返回结果.
     *
     * @param success 请求是否成功.
     * @param message 返回信息.
     * @param data    返回对象.
     * @param <T>     返回对象类型.
     * @return 返回结果.
     */
    protected static <T> ResultData<T> build(boolean success, String message, T data) {
        return new ResultData<>(success, message, data);
    }

    /**
     * 获取 请求是否成功.
     *
     * @return success 请求是否成功.
     */
    public boolean isSuccess() {
        return this.success;
    }

    /**
     * 设置 请求是否成功.
     *
     * @param success 请求是否成功.
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * 获取 返回信息.
     *
     * @return message 返回信息.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * 设置 返回信息.
     *
     * @param message 返回信息.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取 返回对象.
     *
     * @return data 返回对象.
     */
    public T getData() {
        return this.data;
    }

    /**
     * 设置 返回对象.
     *
     * @param data 返回对象.
     */
    public void setData(T data) {
        this.data = data;
    }
}
