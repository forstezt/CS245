public class RecursiveHW {

public static int Fin(int n) {

  if (n < 0) {
    return Fun(-n);
  }
  if (n < 10) {
    return n;
  } else {
    return Fun(n / 10);
  }
}

public static void main(String args[]) {
  System.out.println(Fun(-2374));
}
}