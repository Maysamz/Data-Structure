import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
import java.io.IOException;


public class WordAnalysis {
   int k;
   int n;
   int m;
   
   
   LinkedList<WordInformation> [] arrayOfDifferentLengths ;
   WordInformation [] sortedArray;
    
   public WordAnalysis()
   {
   }
   

   private String allFileData ( String f )
   {
      String Lines = "" ;
      k = 0;
      try{
         BufferedReader reader = new BufferedReader(new FileReader(f));
         String line = reader.readLine();
         while (line != null)
         {
                
            while (line.contains("\\n"))
            {
               line = line.replace("\\n" ,  System.lineSeparator()); 
            }
            line = line.replace("?", " ");
            line = line.replace("\"", " ");
            line = line.replace("“", " ");
            line = line.replace("”", " ");
            line = line.replace("!", " ");
         
            String [] str = line.split("[;, . ]");  
            for (int i = 0; i < str.length; i++) 
               if (k < str[i].trim().length())
                  k = str[i].trim().length();
            
            Lines += line + System.lineSeparator() ; 
            line = reader.readLine();
         }
         k++;
         reader.close();
            
      }
      catch (Exception ex) 
      {
         System.out.println("error");
      }
      return Lines;
   }
   
   
    
   public void readFileAndAnalyse (String filename)
   {
        
      String f = allFileData (filename); 
      sortedArray = new WordInformation [200];
        
      arrayOfDifferentLengths =  new LinkedList [k];
      for ( int i = 0 ; i< arrayOfDifferentLengths.length ; i ++)
         arrayOfDifferentLengths[i] = new LinkedList<WordInformation> ();
   
      n =0;
      m =0;
      String[] lines = f.split(System.lineSeparator());
      String str[] = null;
      int index = 0 ;        
      while (index < lines.length) 
      {
         int position =0;
         str = lines[index].split("[;, . ]");   
                
         for (int i = 0; i < str.length; i++) 
         {
               
            String word = str[i].trim();
            if (word.equalsIgnoreCase("") != true)   
            {
               position ++ ;
               n++;
               boolean found = false;
                    
                    
            
               if (arrayOfDifferentLengths[word.length()].empty())
                  arrayOfDifferentLengths[word.length()].insert(new WordInformation (word , index+1, position));
               else
               {
                  arrayOfDifferentLengths[word.length()].findFirst();
                  while (! arrayOfDifferentLengths[word.length()].last() && ! found)
                  {
                     WordInformation data = arrayOfDifferentLengths[word.length()].retrieve();
                     if (data.word.equalsIgnoreCase(word) == true)
                     {
                        data.Add(index+1, position);
                        arrayOfDifferentLengths[word.length()].update(data);
                        found = true;
                     }
                     else
                        arrayOfDifferentLengths[word.length()].findNext();
                  }
                  if ( ! found)
                  {
                     WordInformation data = arrayOfDifferentLengths[word.length()].retrieve();
                     if (data.word.equalsIgnoreCase(word) == true)
                     {
                        data.Add(index+1, position);
                        arrayOfDifferentLengths[word.length()].update(data);
                        found = true;
                     }
                  }
               
                  if (!found )
                     arrayOfDifferentLengths[word.length()].insert(new WordInformation (word , index+1, position));
               }
                   
               if (!found )
               {
                  sortedArray[m] = new WordInformation (word , index+1, position);
                  m++;
               }
               else
               {
                  for ( int x = 0 ; x < m ; x++)
                     if (sortedArray[x] != null && sortedArray[x].word.equalsIgnoreCase(word) == true)
                        sortedArray[x].size ++ ;
               }
            }
         
         }
         index ++;
      }
        
      mergesort(0, m-1 );
   }
    
    
    
    
    
   public int documentLength  ()
   { 
      return n;
   }
    
    
    
    
    
   public int uniqueWords  ()
   {
      return m;
   }
    
   
   
   
   
   public int totalWord (String w)
   {
      int Count = 0 ;
      if ( arrayOfDifferentLengths[w.length()].getsize() > 0)
      {   
         arrayOfDifferentLengths[w.length()].findFirst();
         while (! arrayOfDifferentLengths[w.length()].last())
         {
            if ( arrayOfDifferentLengths[w.length()].retrieve().word.equalsIgnoreCase(w)== true)
               Count = arrayOfDifferentLengths[w.length()].retrieve().size;
            arrayOfDifferentLengths[w.length()].findNext();
         }
         if ( arrayOfDifferentLengths[w.length()].retrieve().word.equalsIgnoreCase(w)== true)
            Count = arrayOfDifferentLengths[w.length()].retrieve().size;
      }
      return Count;
        
   }
    
    
    
    
    
   public int totalWordsForLength (int l)
   {
      if (  l <=  0 || l >= arrayOfDifferentLengths.length )
         return 0 ;
      return arrayOfDifferentLengths[l].getsize();
   }
    
    
    
    
    
   public void displayUniqueWords  ()
   {
      for ( int i = 0 ; i <m ;  i++)
         System.out.println(" ( " + sortedArray[i].word + " , " + sortedArray[i].size + " ) ");
   }    
    
    
    
    
    
    
    
    
    
    
   public  LinkedList<WordOccurrence> occurrences  (String w)
   {
        
      LinkedList<WordOccurrence> tmp = null;
        
      if ( arrayOfDifferentLengths[w.length()].getsize() > 0)
      {   
         arrayOfDifferentLengths[w.length()].findFirst();
         while (! arrayOfDifferentLengths[w.length()].last())
         {
            if ( arrayOfDifferentLengths[w.length()].retrieve().word.equalsIgnoreCase(w)== true)
               tmp = arrayOfDifferentLengths[w.length()].retrieve().occList;
            arrayOfDifferentLengths[w.length()].findNext();
         }
         if ( arrayOfDifferentLengths[w.length()].retrieve().word.equalsIgnoreCase(w)== true)
            tmp = arrayOfDifferentLengths[w.length()].retrieve().occList;
      }
        
      return tmp;
   }

    
     
     
   public boolean checkAdjacent  (String word1, String word2)
   {
      if (( arrayOfDifferentLengths[word1.length()].getsize() ==0) || (arrayOfDifferentLengths[word2.length()].getsize() == 0))
         return false;
   
      LinkedList<WordOccurrence> W1 = occurrences (word1);
      LinkedList<WordOccurrence> W2 = occurrences (word2);
        
      if ( W1 != null && W2 != null )
      {
         W1.findFirst();
         W2.findFirst();
            
         while ( ! W1.last() && !W2.last())
         {
            int line1 = W1.retrieve().lineNo;
            int line2 =W2.retrieve().lineNo;
            if ( line1 == line2 )
            {
               int w_no1 = W1.retrieve().position;
               int w_no2 =W2.retrieve().position;
               if (Math.abs(w_no2 - w_no1) == 1)
                  return true;
               else if (  (w_no2 - w_no1) > 1)
                  W1.findNext();
               else
                  W2.findNext();
            }
            else if ( line1 > line2)
            {
               W2.findNext();
            }
            else
            {
               W1.findNext();
            }
         }
            
         while ( W1.last() && !W2.last())
         {
            int line1 = W1.retrieve().lineNo;
            int line2 =W2.retrieve().lineNo;
            if ( line1 == line2 )
            {
               int w_no1 = W1.retrieve().position;
               int w_no2 =W2.retrieve().position;
               if (Math.abs(w_no2 - w_no1) == 1)
                  return true;
            }
            W2.findNext();
         }
                    
         while ( !W1.last() && W2.last())
         {
            int line1 = W1.retrieve().lineNo;
            int line2 =W2.retrieve().lineNo;
            if ( line1 == line2 )
            {
               int w_no1 = W1.retrieve().position;
               int w_no2 =W2.retrieve().position;
               if (Math.abs(w_no2 - w_no1) == 1)
                  return true;
            }
            W1.findNext();
         }
      
         if ( W1.last() && W2.last())
         {
            int line1 = W1.retrieve().lineNo;
            int line2 =W2.retrieve().lineNo;
            if ( line1 == line2 )
            {
               int w_no1 = W1.retrieve().position;
               int w_no2 =W2.retrieve().position;
               if (Math.abs(w_no2 - w_no1) == 1)
                  return true;
            }
         }
      }   
      return false;
     
   }    




   private void mergesort ( int l , int r ) 
   {
      if ( l >= r )
         return;
      int m = ( l + r ) / 2;
      mergesort (l , m ) ;          
      mergesort (m + 1 , r ) ;    
      merge (l , m , r ) ;         
   }



   private void merge ( int l , int m , int r ) 
   {
      WordInformation [] B = new WordInformation [ r - l + 1];
      int i = l , j = m + 1 , k = 0;
    
      while ( i <= m && j <= r )
      {
         if ( sortedArray[ i ].size >= sortedArray [ j ].size)
            B [ k ++] = sortedArray[ i ++];
         else
            B [ k ++] = sortedArray[ j ++];
      }
        
      if ( i > m )
         while ( j <= r )
            B [ k ++] = sortedArray[ j ++];
      else
         while ( i <= m )
            B [ k ++] = sortedArray[ i ++];
        
      for ( k = 0; k < B . length ; k ++)
         sortedArray[ k + l ] = B [ k ];
   }
}