package com.corn.springboot.start.sentinel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: SentinelController
 * @Package com.corn.springboot.start.sentinel
 * @Description: TODO
 * @date 2021/3/23 15:00
 */

@RestController
public class SentinelController {

    @Autowired
    private SentinelService sentinelService;

    @RequestMapping(value = "/hello/{name}")
    public String testMethod(@PathVariable("name") String name){
        return sentinelService.circuitBreaker(name);
    }

}
