package com.corn.springboot.start.sentinel;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: SentinelConfig
 * @Package com.corn.springboot.start.sentinel
 * @Description: TODO
 * @date 2021/3/23 15:00
 */

@Configuration
public class SentinelConfig {

    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }
}
