package k1oud.com.seckillmall.service;

import k1oud.com.seckillmall.dao.UserDao;
import k1oud.com.seckillmall.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public User getById(int id){
        return userDao.getById(id);
    }

    @Transactional
    public void tx() {
        User u1 = new User();
        User u2 = new User();
        u1.setId(2);
        u1.setName("22222");
        userDao.insertById(u1);

        u2.setId(1);
        u2.setName("333333");
        userDao.insertById(u2);

    }
}
