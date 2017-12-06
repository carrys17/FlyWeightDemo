# FlyWeightDemo

###1、介绍：

享元模式（Flyweight Pattern），Flyweight 代表轻量级的意思，享元模式是对象池的一种实现。享元模式用来尽可能减少内存使用量，它适合用于可能存在大量重复对象的场景，缓存可共享的对象，来达到对象共享和避免创建过多对象的效果，这样一来就可以提升性能，避免内存移除和频繁 GC 等。

享元对象中的部分状态是可以共享的，这些状态称为**内部状态**，内部状态不会随着环境变化而变化；

不可共享的状态称为**外部状态**，它们会随着环境的改变而改变。

在享元模式下会建立一个对象容器，在经典的享元模式中该容器为一个**Map,它的键是享元对象的内部状态，它的值是享元对象本身。**

###2、定义：

使用共享对象可有效地支持大量的细粒度的对象。

###3、使用场景：

1） 系统中存在大量的相似对象

2）细粒度的对象都具备较接近的外部状态，而且内部状态与环境无关，也就是说对象没有特定身份

3）需要缓冲池的场景

###4、UML类图

![](https://i.imgur.com/dPZGViw.png)


Flyweight：
享元对象抽象基类或者接口；

ConcreteFlyweight：
具体的享元对象；

UnsharedConcreteFlyweight：
非共享具体享元类，指出那些不需要共享的Flyweight子类；

FlyweightFactory：
享元工厂，负责管理享元对象池和创建享元对象。

###5、代码示例：


抽象享元对象

	public interface Ticket {
	    public void showTicketInfo(String bunk);
	}

具体享元对象

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


享元工厂


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


 客户类

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

###6、Android中应用： Message的使用，内部是一个链表来维护

###7、Java中的应用：String  

###8、小结：

享元模式实现比较简单，但是它的作用在某些场景确实极其重要。它可以大大减少应用程序创建对象的数量和频率，降低程序内存的占用，增强程序的性能，但它同时也增加了系统的复杂性，需要分离出外部状态和内部状态，内部状态为不变的共享部分，存储于享元对象内部；而外部状态具有固化特性，应当由客户端来负责，不应该随着内部状态改变而改变，否则会导致系统的逻辑混乱。

享元模式优点：

能够极大的减少系统中对象的个数；
享元模式由于使用了外部状态，外部状态相对独立，不会影响到内部状态，所以享元模式使得享元对象能够在不同的环境中被共享。

享元模式缺点：

由于享元模式需要区分外部状态和内部状态，使得应用程序在某种程度上来说更加复杂化了；
为了使对象可以共享，享元模式需要将享元对象的状态外部化，而读取外部状态使得运行时间变长。
