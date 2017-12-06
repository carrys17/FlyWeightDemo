package com.example.shang.flyweightdemo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by shang on 2017/12/6.
 */


// 享元工厂
public class TicketFactory {
    // 传入内部状态和享元对象
    static Map<String ,Ticket> sMap = new ConcurrentHashMap<>();

    public static Ticket getTicket(String from,String to){
        String key = from+"-"+to;
        if (sMap.containsKey(key)){
            System.out.println("使用缓存---" +key);
            return sMap.get(key);
        }else {
            System.out.println("创建对象---"+key);
            Ticket ticket = new TrainTicket(from,to);
            return ticket;
        }
    }

}
