//THE EDITED CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - Austin Dong
public class BST
{
   public BSTEntry root;	// References the root node of the BST

   public BST()
   {
      root = null;
   }

   public BSTEntry succ(BSTEntry p)
   {
   		BSTEntry toReturn = p;
   		if(p.right != null)
   		{
   			toReturn = p.right;
   			while(toReturn.left != null) toReturn = toReturn.left;
   			return toReturn;
   		}
   		else
   		{
   			while(toReturn.parent != null && toReturn.parent.right == toReturn) toReturn = toReturn.parent;
   			if(toReturn.parent != null && toReturn.parent.left == toReturn) 
   			{
   				toReturn = toReturn.parent;
   				return toReturn;
   			}
   		}
      return null;         // Write this method
   }

   /* ================================================================
      findEntry(k): find entry with key k 

      Return:  reference to (k,v) IF k is in BST
               reference to parent(k,v) IF k is NOT in BST (for put)
      ================================================================ */
   public BSTEntry findEntry(String k)
   {
       BSTEntry curr_node;   // Help variable
       BSTEntry prev_node;   // Help variable

       /* --------------------------------------------
	  Find the node with key == "k" in the BST
          -------------------------------------------- */
       curr_node = root;  // Always start at the root node
       prev_node = root;  // Remember the previous node for insertion

       while ( curr_node != null )
       {
          if ( k.compareTo( curr_node.key ) < 0 )
	  {
	     prev_node = curr_node;       // Remember prev. node
	     curr_node = curr_node.left;  // Continue search in left subtree
	  }
          else if ( k.compareTo( curr_node.key ) > 0 )
	  {
	     prev_node = curr_node;       // Remember prev. node
	     curr_node = curr_node.right; // Continue search in right subtree
	  }
          else 
	  {
	     // Found key in BST 
	     return curr_node;
	  }
       }

       /* ======================================
	  When we reach here, k is NOT in BST
          ====================================== */
       return prev_node;		// Return the previous (parent) node
   }

   /* ================================================================
      get(k): find key k and return assoc. value
      ================================================================ */
   public Integer get(String k)
   {
       BSTEntry p;   // Help variable

       /* --------------------------------------------
	  Find the node with key == "key" in the BST
          -------------------------------------------- */
       p = findEntry(k);

       if ( k.equals( p.key ) )
	  return p.value;
       else
 	  return null;
   }


   /* ================================================================
      put(k, v): store the (k,v) pair into the BST

         1. if the key "k" is found in the BST, we replace the val
            that is associated with the key "k"
         1. if the key "k" is NOT found in the BST, we insert
	    a new node containing (k, v)
      ================================================================ */
   public void put(String k, Integer v)
   {
       BSTEntry p;   // Help variable

       /* ----------------------------------------------------------
	  Just like linked list, insert in an EMPTY BST
	  must be taken care off separately by an if-statement
          ---------------------------------------------------------- */
       if ( root == null )
       {  // Insert into an empty BST

          root = new BSTEntry( k, v );
	  return;
       }

       /* --------------------------------------------
	  Find the node with key == "key" in the BST
          -------------------------------------------- */
       p = findEntry(k);

       if ( k.equals( p.key ) )
       {
          p.value = v;			// Update value
	  return;
       }

       /* --------------------------------------------
	  Insert a new entry (k,v) under p !!!
          -------------------------------------------- */
       BSTEntry q = new BSTEntry( k, v );

       q.parent = p;

       if ( k.compareTo( p.key ) < 0 )
	  p.left = q;            	// Add q as left child
       else 
	  p.right = q;           	// Add q as right child
   }


   /* =======================================================
      remove(k): delete entry containg key k
      ======================================================= */
   public void remove(String k)
   {
       BSTEntry p;        // Help variable
       BSTEntry parent;   // parent node
       BSTEntry succ;     // successor node

       /* --------------------------------------------
          Find the node with key == "key" in the BST
          -------------------------------------------- */
       p = findEntry(k);

       if ( ! k.equals( p.key ) )
          return;			// Not found ==> nothing to delete....


       /* ========================================================
	  Hibbard's Algorithm
	  ======================================================== */

       if ( p.left == null && p.right == null ) // Case 0: p has no children
       {
	  parent = p.parent;

	  /* --------------------------------
	     Delete p from p's parent
	     -------------------------------- */
	  if ( parent.left == p )
	     parent.left = null;
	  else
	     parent.right = null;

          return;
       }

       if ( p.left == null )                 // Case 1a: p has 1 (right) child
       {
          parent = p.parent;

	  /* ----------------------------------------------
	     Link p's right child as p's parent child
             ---------------------------------------------- */
          if ( parent.left == p )
             parent.left = p.right;
          else
             parent.right = p.right;

          return;
       }

       if ( p.right== null )                 // Case 1b: p has 1 (left) child
       {
          parent = p.parent;

          /* ----------------------------------------------
             Link p's left child as p's parent child
             ---------------------------------------------- */
          if ( parent.left == p )
             parent.left = p.left;
          else
             parent.right = p.left;

          return;
       }

       /* ================================================================
	  Tough case: node has 2 children - find successor of p

	  succ(p) is as as follows:  1 step right, all the way left

	  Note: succ(p) has NOT left child !
          ================================================================ */
       succ = p.right;			// p has 2 children....

       while ( succ.left != null )
	   succ = succ.left;

       p.key = succ.key;		// Replace p with successor
       p.value = succ.value;


       /* --------------------------------
          Delete succ from succ's parent
          -------------------------------- */
       parent = succ.parent;		// Prepare to delete

       parent.left = succ.right;	// Link right tree to parent's left

       return;

   }


   /* =======================================================
      Show what the BST look like....
      ======================================================= */

   public void printnode(BSTEntry x, int h)
   {
      for (int i = 0; i < h; i++)
         System.out.print("               ");

      System.out.println("[" + x.key + "," + x.value + "]");
   }

   void printBST()
   {
      showR( root, 0 );
      System.out.println("================================");
   }

   public void showR(BSTEntry t, int h)
   {
      if (t == null)
         return;

      showR(t.right, h+1);
      printnode(t, h);
      showR(t.left, h+1);
   }
}
