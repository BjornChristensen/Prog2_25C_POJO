package threads;

public class ThreadSyncExample {
  public static void main(String[] args) {
//    Tabel tabel=new Tabel();
//    MyThread t1=new MyThread(tabel, 5);
//    MyThread t2=new MyThread(tabel, 10);
//    t1.start();
//    t2.start();

    MyThread t1=new MyThread(new Tabel(), 5);
    MyThread t2=new MyThread(new Tabel(), 10);
    t1.start();
    t2.start();
  }
}

class Tabel {
  static synchronized void print(int n){
    for (int i=1; i<=10; i++){
      System.out.println(n+"*"+i+"= "+n*i);
    }
  }
}

class MyThread extends Thread {
  Tabel tabel;
  int number;
  MyThread(Tabel t, int n){
    tabel=t;
    number=n;
  }
  public void run(){
    tabel.print(number);
  }
}
