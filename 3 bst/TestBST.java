
import java.util.Scanner;

public class TestBST
{
   public static void main(String[] args)
   {
      Scanner in = new Scanner(System.in);

      BST x = new BST();  
                  // Key = String, Value = String

      x.put("lion", 9999);
      x.put("dog",  1000);
      x.put("tiger", 8888);
      x.put("cat", 500);
      x.put("horse", 2000);
      x.put("ape", 1500);
      x.put("cow", 700);
      x.put("donkey", 1900);
      x.put("jackal", 4000);
      x.put("owl", 2000);
      x.put("man", 5000);
      x.put("zebra", 1600);
      x.printBST();

      BSTEntry p, q;

      p = x.findEntry("lion");
      q = x.succ(p);
      System.out.println("Succ of " + p + " = " + q);
      System.out.println("Correct answer: man");
      System.out.println();

      p = x.findEntry("dog");
      q = x.succ(p);
      System.out.println("Succ of " + p + " = " + q);
      System.out.println("Correct answer: donkey");
      System.out.println();

      p = x.findEntry("jackal");
      q = x.succ(p);
      System.out.println("Succ of " + p + " = " + q);
      System.out.println("Correct answer: lion");
      System.out.println();

      p = x.findEntry("man");
      q = x.succ(p);
      System.out.println("Succ of " + p + " = " + q);
      System.out.println("Correct answer: owl");
      System.out.println();

      p = x.findEntry("zebra");
      q = x.succ(p);
      System.out.println("Succ of " + p + " = " + q);
      System.out.println("Correct answer: null");
      System.out.println();

   }
}
