package com.corn.springboot.start.crawler;

public class Demo {
    public static void main(String[] args) {
        CrawlerBase crawlerBase = new CrawlerBase();

//        crawlerBase.jsoupList("https://voice.hupu.com/nba");
        crawlerBase.jsoupList("https://soccer.hupu.com/england/");

    }
}
