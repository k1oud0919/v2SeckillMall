package k1oud.com.seckillmall.dao;

import k1oud.com.seckillmall.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {

    //妈的，这里的注解里面的sql语句没有给{id}添加#标识符
    //@Select("select * from user where id = {id}")
    @Select("select * from user where id = #{id}")
    User getById(@Param("id")int id);

    @Insert("insert into user(id,name) values(#{id},#{name})")
    void insertById(User user);
}
