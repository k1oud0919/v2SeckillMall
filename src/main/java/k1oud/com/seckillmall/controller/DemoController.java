package k1oud.com.seckillmall.controller;

import k1oud.com.seckillmall.domain.User;
import k1oud.com.seckillmall.redis.RedisService;
import k1oud.com.seckillmall.redis.UserKey;
import k1oud.com.seckillmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/hello")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "K1oud");
        //这里少了这个return
        //templates里的{name}就是解析的这一句匹配上了
        return "hello";
    }

    @RequestMapping("/db/get")
    @ResponseBody
    public User dbGet() {
        return userService.getById(1);
    }

    //测试一下事务
    @RequestMapping("/db/transaction")
    @ResponseBody
    public void dbTx() {
        userService.tx();
    }

    //测试一下redis连接

    /**
     2019年6月13日，测试通过，主要是redis连接那里出了问题，我没有改好密码！
     */
    @RequestMapping("/redis")
    @ResponseBody
    public <T> T dbRedis() {
        T t = (T) redisService.redisGet(null,"key2", String.class);
        System.out.println(t);
        return t;

    }

    @RequestMapping("/redisSet")
    public String dbRedisSet() {
        String value= "2019年6月13日，测试向redis里面写入数据";
        redisService.redisSet(null,"key4", value);
        String result = redisService.redisGet(null,"key4",String.class);
        System.out.println(result);
        return result;
    }

    @RequestMapping("/redis/keyTest")
    public String keyTest(){
        redisService.redisSet(UserKey.getKeyById,"user","k1oud wang");
        return null;
    }
}
