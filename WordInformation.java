public class WordInformation {
   public String word;
   public LinkedList<WordOccurrence> occList;
   public int size; 


   public WordInformation() {
      this.word = "";
      this.occList = new LinkedList<> ();
      this.size =0;
   }
   

   public WordInformation(String word, int Line, int Position) {
      this.word = word;
      this.occList = new LinkedList<> ();
      occList.insert(new WordOccurrence(Line, Position ));
      this.size =1;
   }
   
        
   public void Add (int Line, int Position)    
   {
      occList.insert(new WordOccurrence(Line, Position ));
      this.size ++;
        
   }
   
   
    public String toString() {
        
      String str =  "word{ " +  word + ", Frequency= " + size + ", Length= " + word.length() + ", Locations= " ;
      occList.findFirst();
      while (!occList.last())
      {
         str += occList.retrieve().toString();
         occList.findNext();
      }
      str += occList.retrieve().toString();
      return str;
   }

}