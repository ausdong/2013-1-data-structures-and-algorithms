//THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - Austin Dong

import java.util.*;


public class BinPacking
{
  /* -----------------------------------------------------------
   solveBP(k1, s1, k2, s2, k3, s3, resCap, Cap):
   
   returns the min # bins needed to pack:
   
   k1 items of size s1
   k2 items of size s2
   k3 items of size s3
   
   using bins of capacity Cap when the
   current bin has a residual capacity of "resCap"
   ----------------------------------------------------------- */
  public static int solveBP(int k1, int s1, int k2, int s2, int k3, int s3,
                            int resCap, int Cap)
  {
    int sol1=Integer.MAX_VALUE, sol2=Integer.MAX_VALUE, sol3=Integer.MAX_VALUE;
    
    
    //base cases
    if(k1==0 && k2==0 && k3==0 && resCap==Cap) return 0;
    if(k1==0 && k2==0 && k3==0) return 1;
    
    //object 1
    if(k1>0){
      if(s1<=resCap){
        sol1 = solveBP(k1-1, s1, k2, s2, k3, s3, resCap-s1, Cap);
      }
      else {
        sol1 = solveBP(k1-1, s1, k2, s2, k3, s3, Cap-s1, Cap)+1;
      }
    }
    
    //object 2
    if(k2>0){
      if(s2<=resCap){
        sol2 = solveBP(k1, s1, k2-1, s2, k3, s3, resCap-s2, Cap);
      }
      else {
        sol2 = solveBP(k1, s1, k2-1, s2, k3, s3, Cap-s2, Cap)+1;
      }
    }
    
    //object 3
    if(k3>0){
      if(s3<=resCap){
        sol3 = solveBP(k1, s1, k2, s2, k3-1, s3, resCap-s3, Cap);
      }
      else {
        sol3 = solveBP(k1, s1, k2, s2, k3-1, s3, Cap-s3, Cap)+1;
      }
    }
    
    //return minimum
    int min1 = Math.min(sol1, sol2);
    int min0 = Math.min(min1, sol3);
    return min0;
  }
  
  public static void main(String[] args)
  {
    int n1, n2, n3;
    int s1, s2, s3;
    int Cap;
    int out;
    
    Scanner in = new Scanner(System.in);
    
    System.out.print("Size of item 1   = ");
    s1 = in.nextInt();
    System.out.print("Number of item 1 = ");
    n1 = in.nextInt();
    System.out.print("Size of item 2   = ");
    s2 = in.nextInt();
    System.out.print("Number of item 2 = ");
    n2 = in.nextInt();
    System.out.print("Size of item 3   = ");
    s3 = in.nextInt();
    System.out.print("Number of item 3 = ");
    n3 = in.nextInt();
    
    System.out.println();
    System.out.print("Capacity of the bin = ");
    Cap = in.nextInt();
    
    if ( s1 > Cap || s2 > Cap || s3 > Cap )
    {
      System.out.println("Input error: some item's size is too large for bin");      
      System.exit(1);
    }
    
    out = solveBP(n1, s1, n2, s2, n3, s3, Cap, Cap);
    
    System.out.println("Min # bins needed = " + out);
  }
  
}