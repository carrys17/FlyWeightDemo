package com.example.shang.flyweightdemo;

/**
 * Created by shang on 2017/12/6.
 */

// 客户类
public class Client {
    public static void main(String []args){
        Ticket ticket1= TicketFactory.getTicket("广州","深圳");
        ticket1.showTicketInfo("上铺");

        Ticket ticket2 = TicketFactory.getTicket("广州","深圳");
        ticket2.showTicketInfo("下铺");

        Ticket ticket3 = TicketFactory.getTicket("广州","深圳");
        ticket3.showTicketInfo("坐票");
    }
}
