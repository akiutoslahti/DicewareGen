
import java.io.File;
import java.io.FileNotFoundException;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class DicewareGen {
    
    private static final String ENGLISH = "wordlists/diceware.txt";
    private static final String ENGLISH_8K = "wordlists/diceware8k.txt";
    private static final String ENGLISH_BEALE = "wordlists/diceware_beale.txt";
    private static final String ENGLISH_EFF = "wordlists/diceware_eff.txt";
    private static final String FINNISH = "wordlists/noppaware.txt";

    public static void main(String[] args) {
        if (args.length == 0) {
            String lang = getWordList("en");
            genPassPhrases(lang , 6, 10);
        } else if (args.length == 1) {
            if (args[0].equals("help")) {
                printHelp();
                return;
            } else {
                String lang = getWordList(args[0]);
                genPassPhrases(lang, 6, 10);
            }
        } else if (args.length == 2) {
            try {
                String lang = getWordList(args[0]);
                int ppLength = Integer.parseInt(args[1]);
                genPassPhrases(lang, ppLength, 10);
            } catch (NumberFormatException e) {
                System.out.println("Illegal arguments." 
                        + " Run 'java DicewareGen help' for more information");
                return;
            }
        } else if (args.length == 3) {
            try {
                String lang = getWordList(args[0]);
                int ppLength = Integer.parseInt(args[1]);
                int ppCount = Integer.parseInt(args[2]);
                genPassPhrases(lang, ppLength, ppCount);
            } catch (NumberFormatException e) {
                System.out.println("Illegal arguments."
                        + " Run 'java DicewareGen help' for more information");
                return;
            }
        } else {
            System.out.println("Illegal arguments."
                   + " Run 'java DicewareGen help' for more information");
        }
    }
    
    private static void printHelp() {
        System.out.println("DicewareGen 1.1, wordlist based passphrase generator");
        System.out.println("Usage:");
        System.out.println(" java DicewareGen [wordlist]");
        System.out.println(" java DicewareGen [wordlist] [pp_length]");
        System.out.println(" java DicewareGen [wordlist] [pp_length] [pp_count]\n");
        System.out.println("Arguments:");
        System.out.println(" wordlist,\tpossible wordlists:");
        System.out.println("\t\t  en -> Diceware wordlist, by Arnold Reinhold");
        System.out.println("\t\t  en_8k -> Diceware wordlist 8k, by Arnold Reinhold");
        System.out.println("\t\t  en_beale -> Diceware wordlist, edited by Alan Beale");
        System.out.println("\t\t  en_eff -> Diceware wordlist, by EFF (2016)");
        System.out.println("\t\t  fi -> Finnish Diceware(Noppaware) wordlist, by Kai Puolamäki");
        System.out.println(" pp_length,\tpassphrase length as wordcount");
        System.out.println(" pp_count,\tnumber of passphrases to be generated\n");
        System.out.println("Default (no arguments):");
        System.out.println(" wordlist: en");
        System.out.println(" pp_length: 6");
        System.out.println(" pp_count: 10");

    }
    
    private static void genPassPhrases(String lang, int ppLength, int ppCount) {
        Random rand = new SecureRandom();
        List<String> wordlist = readWordFile(lang);
        int wordlistsize = wordlist.size();
        double wordlistEntropy = Math.log(wordlistsize) / Math.log(2);
        DecimalFormat df = new DecimalFormat("#.##");
        
        System.out.println("Wordlist: " + lang);
        System.out.println("Wordlist contains: " 
                + wordlist.size() + " words");
        System.out.println("Entropy of wordlist: " 
                + df.format(wordlistEntropy) + " bits");
        System.out.println("Entropy of passphrase: " 
                + df.format(wordlistEntropy * ppLength)
                + " bits\n");
        
        for (int i = 0; i < ppCount; i++) {
            for (int j = 0; j < ppLength; j++) {
                int index = rand.nextInt(wordlistsize);
                System.out.print(wordlist.get(index) + " ");
            }
            System.out.println();
        }        
    }
    
    private static String getWordList(String lang) {
        if (lang.equals("en")) {
            return ENGLISH;
        } else if (lang.equals("fi")) {
            return FINNISH;
        } else if (lang.equals("en_8k")) {
            return ENGLISH_8K;
        } else if (lang.equals("en_beale")) {
            return ENGLISH_BEALE;
        } else if (lang.equals("en_eff")) {
            return ENGLISH_EFF;
        } else {
            System.out.println("Unknown wordlist.");
            System.out.println("Defaulting to English");
            return ENGLISH;
        }
    }
    
    private static List<String> readWordFile(String wordlist) {
        List<String> words= new ArrayList<>();
        try {
            File wordlistFile = new File(wordlist);
            Scanner fileReader = new Scanner(wordlistFile);
            while (fileReader.hasNextLine()) {
                words.add(fileReader.nextLine());
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        }        
        return words;
    }
    
}
