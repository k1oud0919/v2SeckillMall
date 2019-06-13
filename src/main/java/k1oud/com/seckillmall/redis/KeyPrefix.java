package k1oud.com.seckillmall.redis;

public interface KeyPrefix {
    int expireSeconds();
    String getPrefix();
}
