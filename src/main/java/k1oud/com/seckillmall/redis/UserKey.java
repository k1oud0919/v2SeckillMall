package k1oud.com.seckillmall.redis;

public class UserKey extends BasePrefix {
    public UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getKeyById = new UserKey("id");
    public static UserKey getKeyByName = new UserKey("name");
}
