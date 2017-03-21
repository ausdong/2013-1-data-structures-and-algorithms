//THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - Austin Dong

import java.util.*;


public class BinPacking1
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
    int[][][][] A = new int[k1+1][k2+1][k3+1][Cap+1];
    int sol1=0,sol2=0,sol3=0;
    int min1=0, min0=0;
    for(int i=0; i<Cap; i++) A[0][0][0][i]=1;
    A[0][0][0][Cap]=0;

//    if(A[k1][k2][k3][resCap]>=0) return A[k1][k2][k3][resCap];
    
    for(int a=0; a<=k1; a++){
      for(int b=0; b<=k2; b++){
        for(int c=0; c<=k3; c++){
          for(int d=0; d<=Cap; d++){
            if(a==0 && b==0 && c==0) continue;
            sol1 = Integer.MAX_VALUE;
            sol2 = Integer.MAX_VALUE;
            sol3 = Integer.MAX_VALUE;
            if(a>0){
              if(s1<=d) sol1 = A[a-1][b][c][d-s1];
              else sol1 = A[a-1][b][c][Cap-s1]+1;
            }
            if(b>0){
              if(s2<=d) sol2 = A[a][b-1][c][d-s2];
              else sol2 = A[a][b-1][c][Cap-s2]+1;
            }
            if(c>0){
              if(s3<=d) sol3 = A[a][b][c-1][d-s3];
              else sol3 = A[a][b][c-1][Cap-s3]+1;
            }
            min1 = Math.min(sol1, sol2);
            min0 = Math.min(min1, sol3);
            A[a][b][c][d] = min0;
            A[a][b][c][d] = min0;
            A[a][b][c][d] = min0;

          }
        }
      }
    }

    
    
    //return minimum
   // int min1 = Math.min(sol1, sol2);
   // int min0 = Math.min(min1, sol3);
  //  A[k1][k2][k3][resCap] = min0;
    return A[k1][k2][k3][resCap];
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