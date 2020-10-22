package com.corn.springboot.start.mybatisplus.user;

import com.corn.springboot.start.mybatisplus.user.entity.User;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: test
 * @Package com.corn.springboot.start.mybatisplus.user
 * @Description: TODO
 * @date 2020/10/15 15:26
 */
public class test {

    public static void testSupplier() {
        //创建一个Supplier，可以看做为一个创建对象的工厂
        Supplier<User> userSupplier = User::new;
        Supplier<User> supplier = ()->new User();

        User user1 = userSupplier.get();
        user1.setName("test8");

        System.out.println(user1.getName());
    }

    public static void testConsumer(){
        Consumer<Integer> integerConsumer = x->{
            System.out.println("你输入的x是:"+x);
        };

        integerConsumer.accept(1);
    }

    public static void testBIConsumer(){
        //消费两个参数的consumer
        BiConsumer<String,String> biConsumer = (x,y)->{
            System.out.println("left-key:"+x);
            System.out.println("right-value:"+y);
        };

        Map<String, String> map = new HashMap<>();
        map.put("a", "a");
        map.put("b", "b");
        map.put("c", "c");
        map.put("d", "d");

        map.forEach(biConsumer);
        //上下两个方法等价
//        map.forEach((k, v) -> {
//            System.out.println(k);
//            System.out.println(v);
//        });
    }

    public static void testPredicate(){

        Integer[] numbers= {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};

        List<Integer> list = Arrays.asList(numbers);

        //声明几个断言器
        Predicate<Integer> integerPredicate = x->x>5;

        boolean test = integerPredicate.test(1);
        System.out.println("test:"+test);

        Predicate<Integer> p2=i->i<20;
        Predicate<Integer> p3=i->i%2==0;

        List<Integer> integerList = list.stream().filter(integerPredicate.and(p2).and(p3)).collect(Collectors.toList());
        integerList.stream().forEach(x->{
            System.out.println(x);
        });
    }

    public static void testFunction(){
        Function<Integer,Integer> integerFunction1 = x->x+1;
        Function<Integer,Integer> integerFunction2 = x->x*x;

        /**
         * andThen 先处理前面，再处理后面的
         * compost 先处理后面，再处理前面
         * **/
        System.out.println(integerFunction1.andThen(integerFunction2).apply(2));//9
        System.out.println(integerFunction1.compose(integerFunction2).apply(2));//5

    }

    public static void testOptional(){
        User user = new User();
//        user.setName("corn");

        String username = Optional.ofNullable(user).map(user1 -> user.getName()).orElse("nil");

        System.out.println("userName:"+username);

    }


    public static void main(String[] args) {
        testOptional();
    }
}
