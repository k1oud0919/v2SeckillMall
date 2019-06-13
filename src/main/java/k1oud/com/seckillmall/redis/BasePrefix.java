package k1oud.com.seckillmall.redis;
/**
 * 模板模式，接口===》抽象类（可以有实例方法）===》具体实现类
 */
public abstract class BasePrefix implements KeyPrefix {

    private int expireSeconds;
    private String prefix;

    public BasePrefix(String prefix) {

        //如果只有prefix做形参的话，默认expireSeconds为0代表永不过期
        //this.prefix = prefix;注意下面的构造方法的写法
        this(0, prefix);
    }

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    public int expireSeconds() {
        return this.expireSeconds;
    }

    /**
        这个方法写成实体方法方便子类继承，子类不同的类名就会凑成不同的prefix
     */
    public String getPrefix() {
        String classname = getClass().getSimpleName();
        return classname+":"+this.prefix;
    }

}
