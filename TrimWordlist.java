
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TrimWordlist {

    private static final String SEPARATOR = "\t";
    private static final String WORDLIST = "diceware.txt";
    
    public static void main(String[] args) {
        List<String> wordlist = readList(WORDLIST); 
        List<String> trimmedList = trimNumbersFromList(wordlist);
        saveTrimmedList(trimmedList, WORDLIST + "_trimmed");
    }
    
    public static void saveTrimmedList(List<String> list, String destination) {
        File wordFile = new File(destination);
        try {
            PrintWriter pw = new PrintWriter(wordFile);
            for (String s : list) {
                pw.println(s);
            }
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        }
    }
    
    private static List<String> trimNumbersFromList(List<String> list) {
        List<String> trimmedList = new ArrayList<>();
        for (String s : list) {
            String[] words = s.split(SEPARATOR);
            trimmedList.add(words[1]);
        }
        return trimmedList;
    }
    
    private static List<String> readList(String file) {
        List<String> wordlist = new ArrayList<>();
        try {
            File f = new File(file);
            Scanner fileReader = new Scanner(f);
            while (fileReader.hasNextLine()) {
                wordlist.add(fileReader.nextLine());
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        }
        return wordlist;
    }
    
}
