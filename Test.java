import java.util.*;
public class Test {

    
    public static void main(String[] args) {
    Scanner input = new Scanner (System.in);
        WordAnalysis wa_adt = new WordAnalysis();
        System.out.println("please enter file name");
        String fil=input.next();
        wa_adt.readFileAndAnalyse(fil);
        System.out.println("");
        System.out.println("The output of operation (1) would be" + wa_adt.documentLength());
        System.out.println("The output of operation (2) would be "+ wa_adt.uniqueWords());
        System.out.println("please enter word ");
        String ent=input.next();
        System.out.println("please enter length ");
        int len=input.nextInt();
   
        System.out.println("The output of operation (3) for the word would be " + wa_adt.totalWord(ent));
        System.out.println("The output of operation (4) for word length "+len+ "would be " + wa_adt.totalWordsForLength(len));
        System.out.println("The output of operation (5) would be ");
        wa_adt.displayUniqueWords();
        System.out.println("");
        System.out.println("please enter word ");
        String ent1 =input.next();
        System.out.print("The output of operation (6) for the word "+ent1+ " would be " );
        LinkedList <WordOccurrence> L = wa_adt.occurrences(ent1);
        if (!L.empty())
        {
            L.findFirst();
            while ( ! L.last())
            {
                System.out.print(L.retrieve().toString() + "  ");
                L.findNext();
            }
            System.out.print(L.retrieve().toString() + "  ");
        }
        
        
        System.out.println("");
        
        
       System.out.println("please enter two words to check operation (7)");
      String ob7_1 = input.next();
      String ob7_2 = input.next();
      System.out.println("The output of operation (7) for the two words would be " + wa_adt.checkAdjacent(ob7_1, ob7_2));
   

    }
    
}