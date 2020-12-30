package com.corn.springboot.start.mybatisplus;

import com.corn.springboot.start.mybatisplus.user.entity.User;
import com.corn.springboot.start.mybatisplus.user.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: MybatisDemo
 * @Package com.corn.springboot.start.mybatisplus
 * @Description: TODO
 * @date 2020/12/30 15:22
 */
public class MybatisDemo {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User blog = mapper.selectUser("1");
            System.out.println(1);
        }
    }
}
