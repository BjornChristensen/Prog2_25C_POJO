package threads;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class CandleApp {
  static Resource[] res=new Resource[2];
  public static void main(String[] args) throws InterruptedException{
    System.out.println("CandleApp started");
    res[0]=new Resource("Candle");
    res[1]=new Resource("Lighter");
    Student s1=new Student("Andrea");
    Student s2=new Student("Kaj");
    s1.start();
    s2.start();
    s1.join();
    s2.join();
    System.out.println("CandleApp finished");
  }
}

class Student extends Thread {
  String name;
  Random gen=new Random();
  static int MaxSleep=5000;
  Student(String name){ this.name=name; }

  public void run(){
    System.out.println(name+" started");
    try {
      boolean keepGoing=true;
      while (keepGoing) {
        Thread.sleep(gen.nextInt(MaxSleep));
        int A=gen.nextInt(2);
        CandleApp.res[A].lock();
        System.out.println(name+" got "+CandleApp.res[A]);

        Thread.sleep(gen.nextInt(MaxSleep));
        int B=(A+1)%2;
        if (CandleApp.res[B].tryLock(1000, TimeUnit.MILLISECONDS)){
          System.out.println(name+" got "+CandleApp.res[B]);
          System.out.println(name+" lights the candle !");
          System.out.println(name+" released "+CandleApp.res[B]);
          CandleApp.res[B].unlock();
          keepGoing=false;
        }
        System.out.println(name+" released "+CandleApp.res[A]);
        CandleApp.res[A].unlock();
      }
    } catch (InterruptedException ex){ }
    System.out.println(name+" finished");
  }
}

class Resource extends ReentrantLock {
  String name;
  Resource(String name) { this.name=name; }
  public String toString() { return name; }
}
