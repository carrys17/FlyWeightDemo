package com.example.shang.flyweightdemo;

import java.util.Random;

/**
 * Created by shang on 2017/12/6.
 */


// 具体享元对象
public class TrainTicket implements Ticket {
    public String from; // 始发地
    public String to;   // 目的地
    public String bunk; // 铺位
    public int price;  // 价格

    public TrainTicket(String from,String to){
        this.from = from;
        this.to = to;
    }

    @Override
    public void showTicketInfo(String bunk) {
        price = new Random().nextInt(300);
        System.out.println("购买从 "+from +" 到 "+ to +" 的 "+ bunk +" 火车票，价格为 "+price);
    }
}
