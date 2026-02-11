package recursion;

public class Factorial {
  static int f(int n){
    int r;
    if(n==1)
      { r=1; }
    else
      {r= n * f(n-1);}
//    System.out.println("f("+n+")"+" r="+r);
    return r;
  }

  public static void main(String[] args) {
    System.out.println(f(5));
  }

}
