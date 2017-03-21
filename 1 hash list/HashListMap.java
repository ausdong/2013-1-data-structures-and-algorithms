//THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - Austin Dong

import java.util.*;
public class HashListMap<K, V> implements Map<K, V>
{
   public HashListEntry<K,V>[] bucket;   // The hash table (buckets)
   public int capacity;                  // capacity == bucket.length
   public int NItems;                    // NItems = # entries in hash table

   public int MAD_p;
   public int MAD_a;
   public int MAD_b;
   

		public HashListMap(int p, int MapArraySize)
		{
			capacity = MapArraySize;
			bucket = new HashListEntry[capacity];
			NItems = 0;
			java.util.Random rand = new java.util.Random(1234);
			MAD_p = p;
			MAD_a = rand.nextInt(MAD_p-1) + 1;
			MAD_b = rand.nextInt(MAD_p);
			
		}
		
		public HashListMap(int MapArraySize)
		{
			this(109345121, MapArraySize);
		}
		
		public HashListMap()
		{
			this(109345121, 1000);
		}
		
		public int size()
		{
			return NItems;
		}
		
		public boolean isEmpty()
		{
			return (NItems==0);
		}
		
		public int hashValue(K key)
		{
			int x = key.hashCode();
			return (int) ((Math.abs(x*MAD_a+MAD_b)%MAD_p)%capacity);
		}

   // ****************************************************************
   // Todo: implement these 3 methods
   //
   //     1. insert()
   //     2. delete()
   //     3. findEntry()
   //
   //  See project description for details
   // ****************************************************************

   // ****************************************************************
   // insert(bucketID, e): insert entry e in bucket[bucketID]
   //
   // Write this method
   // ****************************************************************
   public void insert(int bucketID, HashListEntry<K, V> e)
   {
   		if(bucket[bucketID] == null)
   		{
   			bucket[bucketID] = e;
   			NItems++;
   			return;
   		}
   		bucket[bucketID].prev = e;
			e.next = bucket[bucketID];
			bucket[bucketID] = e;
			NItems++;
   }

   // *****************************************************************
   // delete(bucketID, e): delete entry e from bucket[bucketID]
   //
   // Write this method
   // *****************************************************************
   public void delete(int bucketID, HashListEntry<K, V> p)
   {
			if(p.prev != null) p.prev.next = p.next;
			else bucket[bucketID] = p.next;
			NItems--;
   }


   // *********************************************************************
   // findEntry(key): find entry for given key
   //
   // Return value:
   //
   //       The reference to entry that contains the given key
   //       If key is NOT found, return null
   //
   // Write this method
   // *********************************************************************
    public HashListEntry<K,V> findEntry(K key)
    {
			int bucketID;
			if(key == null) return null;
			bucketID = hashValue(key);
			HashListEntry<K,V> e = bucket[bucketID];
			if(e == null) return null;
			while(e.getKey() != key)
			{
				if(e.next == null) return null;
				e = e.next;
			}
			return e;
    }

    /* ===========================================================
       get(), put() and remove() are very easily implemented
       using

                findEntry()
                insert()
                delete()
       =========================================================== */

    public V get(K k)
    {
       HashListEntry<K, V> e;

       e = findEntry(k);  // Find entry for the key k

       if ( e != null )
       {
          return e.getValue();       // return value
       }
       else
       {
          return null;               // Key not found
       }
    }

		public V put(K key, V value)
		{
			HashListEntry<K, V> e = findEntry(key);
			if(e != null)
			{
				V oldValue = e.setValue(value);
				return oldValue;
			}
			else
			{
				int bucketID = hashValue(key);
				insert(bucketID, new HashListEntry<K,V>(key,value));
				return null;
			}
		}
		
		public V remove(K key)
		{
			HashListEntry<K,V> e = findEntry(key);
			if(e != null)
			{
				V oldValue = e.getValue();
				int bucketID = hashValue(key);
				delete(bucketID, e);
				return oldValue;
			}
			else return null;
		}
		
		public String toString()
		{
			boolean more;
			String output = "{";
			for(int i = 0; i < bucket.length; i++)
			{
				HashListEntry<K,V> e = bucket[i];
				if(e != null && e.next != null)
				{
					more = true;
					output += "[";
				}
				else more = false;
				while(e != null)
				{
					output = output + e + " ";
					e = e.next;
				}
				if(more) output += "] ";
			}
			output += "} (" + NItems + " entries)";
			return output;
		}
		
}
