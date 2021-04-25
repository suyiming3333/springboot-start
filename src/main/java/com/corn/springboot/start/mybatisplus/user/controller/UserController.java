package com.corn.springboot.start.mybatisplus.user.controller;


import com.corn.springboot.start.mybatisplus.user.entity.User;
import com.corn.springboot.start.mybatisplus.user.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author suyiming3333
 * @since 2020-10-11
 */
@RestController
@RequestMapping("/user/user")
public class UserController {

    private final String USERS_ID_ZSET_KEY = "userIds";

    private final String USER_NAME_PREFIX = "userName:";

    private final String USER_ID_PREFIX = "userId:";



    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/listRedis/{pageNo}")
    public List<User> listRedis(@PathVariable("pageNo")Integer pageNo){
        ListOperations listOperations = redisTemplate.opsForList();
        List<User> list = listOperations.range("userIds:", 0L, 15L);
        return list;
    }

    @RequestMapping("/listFromRedis")
    public List<User> listFromRedis(){
        List<User> list = (List<User>) redisTemplate.opsForValue().get("list");
        return list;
    }

    @RequestMapping("/initDataToRedis")
    public void initDataToRedis(){
        List<User> users = userService.list();
        users.stream().forEach(c->{
            redisTemplate.opsForZSet().add("zlist",c.getName(),c.getAge());
        });
    }

    @RequestMapping("/rank")
    public String rank(){
        Set<String> set = redisTemplate.opsForZSet().rangeWithScores("zlist", 1L, 20L);
        return null;
    }


    /**
     * 数据初始化到redis demo
     * todo 很多数据要初始化的时候如何处理？
     * @return
     */
    @RequestMapping("/putDataToList")
    public String putDataToList(){
        List<User> users = userService.getByPage(1);
        ValueOperations<String,User> valueOperations = redisTemplate.opsForValue();
        ListOperations<String,User> listOperations = redisTemplate.opsForList();
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        users.stream().forEach(c->{
            //利用valueOperations将单个记录按key:id保存
            valueOperations.set(USER_ID_PREFIX+c.getId(),c);
            //username用于单一查找
            valueOperations.set(USER_NAME_PREFIX+c.getName(),c);
            //利用zSetOperations,将上面的key:id以及排序字段age存放到zset
            zSetOperations.add(USERS_ID_ZSET_KEY,USER_ID_PREFIX+c.getId(),c.getAge());
        });

        return "ok";
    }


    /**
     * 添加user
     * @param user
     * @return
     */
    @RequestMapping("/addUser")
    public Long userAdd(@RequestBody User user){
        boolean save = userService.save(user);

        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        ValueOperations<String,User> valueOperations = redisTemplate.opsForValue();
        //根据userId
        valueOperations.set(USER_ID_PREFIX+user.getId(),user);
        //username用于单一查找
        valueOperations.set(USER_NAME_PREFIX+user.getName(),user);
        //利用zSetOperations,将上面的key:id以及排序字段age存放到zset
        zSetOperations.add(USERS_ID_ZSET_KEY,USER_ID_PREFIX+user.getId(),user.getAge());
        return user.getId();
    }


    /**
     * 更新数据
     * @param userId
     * @param user
     */
    @RequestMapping("/updateUserById/{userId}")
    public void updateUserById(@PathVariable("userId")Long userId, @RequestBody User user){
        User oldUser = userService.getUserById(userId);
        //先更新数据库
        userService.updateById(user);
        //在更新缓存
        ValueOperations<String,User> valueOperations = redisTemplate.opsForValue();
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        valueOperations.set(USER_ID_PREFIX+user.getId(),user);
        //删除旧名字的缓存
        redisTemplate.delete(USER_NAME_PREFIX+oldUser.getName());
        //username用于单一查找
        valueOperations.set(USER_NAME_PREFIX+user.getName(),user);
        //利用zSetOperations,将上面的key:id以及排序字段age存放到zset
        zSetOperations.add(USERS_ID_ZSET_KEY,USER_ID_PREFIX+user.getId(),user.getAge());
    }

    /**
     * 数据删除
     * @param userId
     */
    @RequestMapping("/delUserById/{userId}")
    public void delUserById(@PathVariable("userId")Long userId){
        User userById = userService.getUserById(userId);

        //先删除数据库，在清除缓存
        userService.delUserById(userId);
        //删缓存
        redisTemplate.delete(USER_ID_PREFIX+userId);
        //通过userName缓存的也要删除
        redisTemplate.delete(USER_NAME_PREFIX+userById.getName());
    }

    @RequestMapping("/getUserInfoByName")
    public User getUserInfoByName(@RequestParam("userName") String userName){
        long startTime = System.currentTimeMillis();
        ValueOperations<String,User> valueOperations = redisTemplate.opsForValue();
        User user = valueOperations.get(USER_NAME_PREFIX + userName);
        //缓存取不到取数据库
        if(user == null){
            //todo 可能userName在数据库也不存在，每次都要查数据库
            user = userService.getUserByName(userName);
            if(user == null){
                user = new User();
            }
            //然后重新放入缓存
            valueOperations.set(USER_NAME_PREFIX + userName,user);
            //userName 的缓存也要设置
            valueOperations.set(USER_NAME_PREFIX+userName,user);
        }
        System.out.println("redis cost time"+(System.currentTimeMillis()-startTime)+"s");
        return user;
    }


    @RequestMapping("/getUserInfoByNameFromRedis")
    public User getUserInfoByNameFromRedis(@RequestParam("userName") String userName){
        long startTime = System.currentTimeMillis();
        ValueOperations<String,User> valueOperations = redisTemplate.opsForValue();
        User user = valueOperations.get(USER_NAME_PREFIX + userName);
        System.out.println("redis cost time"+(System.currentTimeMillis()-startTime)+"s");
        return user;
    }

    @RequestMapping("/getUserInfoByNameFromDb")
    public User getUserInfoByNameFromDb(@RequestParam("userName") String userName){
        long startTime = System.currentTimeMillis();
        User user = userService.getUserByName(userName);
        System.out.println("mysql cost time"+(System.currentTimeMillis()-startTime)+"s");
        return user;
    }


    @RequestMapping("/loadFromRedisByPage/{pageNo}")
    public List<User> loadFromRedisByPage(@PathVariable("pageNo") Long pageNo){
        long startTime = System.currentTimeMillis();
        //先从zet(可按顺序)找到要的数据的key
        Set set = redisTemplate.opsForZSet().range(USERS_ID_ZSET_KEY, pageNo, 15);
        //然后在批量获取数据
        ValueOperations<String,User> valueOperations = redisTemplate.opsForValue();
        List<User> list = valueOperations.multiGet(set);
        System.out.println("redis cost time"+(System.currentTimeMillis()-startTime)+"s");
        return list;
    }

    @RequestMapping("/listFromDbByPage/{pageNo}")
    public List<User> list(@PathVariable("pageNo")Integer pageNo){
        long startTime = System.currentTimeMillis();
        List<User> users = userService.getByPage(pageNo);
        System.out.println("mysql cost time"+(System.currentTimeMillis()-startTime)+"s");
        return users;
    }
}

