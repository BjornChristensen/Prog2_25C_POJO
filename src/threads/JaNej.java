package threads;

public class JaNej {
  public static void main(String[] args) {
    System.out.println("main() started");
    JaNejThread t1=new JaNejThread("Ja");
    JaNejThread t2=new JaNejThread("Nej");
    t1.start();
    t2.start();
    System.out.println("main() finished");
  }
}

class JaNejThread extends Thread {
  String word;
  JaNejThread(String w){
    word=w;
  }

  public void run(){
    for (int i=1; i<=10; i++)
      System.out.println(word);
  }
}