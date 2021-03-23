package com.corn.springboot.start.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: SentinelService
 * @Package com.corn.springboot.start.sentinel
 * @Description: TODO
 * @date 2021/3/23 15:07
 */

@Service
public class SentinelService {

    @SentinelResource(value = "sayHello", blockHandler = "sayHelloExceptionHandler")
    public String sayHello(String name){
        return "hello,"+ name;
    }

    @SentinelResource(value = "circuitBreaker", fallback = "circuitBreakerFallback", blockHandler = "sayHelloExceptionHandler")
    public String circuitBreaker(String name){
        if ("suyiming".equals(name)){
            return "hello,"+ name;
        }
        throw new RuntimeException("发生异常");
    }

    public String circuitBreakerFallback(String name){
        return "服务异常，熔断降级, 请稍后重试!";
    }

    public String sayHelloExceptionHandler(String name, BlockException ex){
        return "访问过快，限流降级, 请稍后重试!";
    }
}
