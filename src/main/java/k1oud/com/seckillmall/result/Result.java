package k1oud.com.seckillmall.result;

public class Result<T> {

    private int code;
    private String msg;
    private T data;

    /**
     * 请求成功的时候调用
     */
    public static <T>Result<T> success(T data){
        return new Result<>(data);
    }

    /**
     * 请求失败的时候调用
     * 请求失败时没有data返回，所以返回一个codemsg就可以
     */
    public static <T> Result<T>error(CodeMsg codeMsg){
        return new Result<T>(codeMsg);
    }

    private Result(T data) {
        this.data = data;
    }

    private Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Result(CodeMsg codeMsg) {
        if (msg != null) {
            this.code = codeMsg.getCode();
            this.msg = codeMsg.getMsg();
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
