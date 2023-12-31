
package app;

// ST10257002 (MP)

import java.util.*;
import javax.swing.table.DefaultTableModel;


public class Interpret {
    
    private static final String jGridField01 = "Length";
    private static final String jGridField02 = "Word Total";
    private static final String jGridField03 = "Word Unique";
    private static final String jGridField04 = "Character";
    private static final String jGridField05 = "Letter";
    private static final String jGridField06 = "Letter UpperCase";
    private static final String jGridField07 = "Letter LowerCase";
    private static final String jGridField08 = "Number";
    private static final String jGridField09 = "Symbol";
    private static final String jGridField10 = "Space";
    private static final String jGridField11 = "Paragraphs";
    private static final String jGridField12 = "Pages";
    private static final String jGridField13 = "Lines";
    private static final String jGridField14 = "Sentences";
    private static final String jGridField15 = "Avg. Word (char)";
    private static final String jGridField16 = "Sentence Longest";
    private static final String jGridField17 = "Sentence Shortest";
    private static final String jGridField18 = "Time to read";
    private static final String jGridField19 = "Time to speak";
    private static final String jGridField20 = "Time to write";
    
    // ---
    
    public static String calcActionTime(String argv, int speed) {
        
        double word = (double) getWords(argv);
        double time = (double) (word / speed);
        int minutes = (int) Math.floor(word / 200);
        int seconds = (int) Math.floor(((time - Math.floor(time)) * 0.60) * 100);
        
        return (minutes + "m " + seconds + "s");
        
    }
    
    public static DefaultTableModel updateTable(String argv, boolean advanced) {

        Object[] gridHeader = {"Type", "Value"};
        Object[][] gridData = {
            {jGridField01, getTotal(argv)},
            {jGridField02, getWords(argv)},
            {jGridField03, getWordsUnique(argv)},
            {jGridField04, getCharacters(argv)},
            {jGridField05, getLetters(argv)},
            {jGridField06, getLettersUpperCase(argv)},
            {jGridField07, getLettersLowerCase(argv)},
            {jGridField08, getDigits(argv)},
            {jGridField09, getSymbols(argv)},
            {jGridField10, getSpace(argv)},
            {jGridField11, getParagraphs(argv)},
            {jGridField12, getPages(argv)},
            {jGridField13, },
            {jGridField14, getSentences(argv)},
            {jGridField15, },
            {jGridField16, },
            {jGridField17, },
            {jGridField18, calcActionTime(argv, 200)},
            {jGridField19, calcActionTime(argv, 150)},
            {jGridField20, calcActionTime(argv, 40)}
        };
        
        return new DefaultTableModel(gridData, gridHeader);

    }
    
    public static String updateLogFile(String argv) {
        
        DefaultTableModel table = updateTable(argv, false);
        StringBuilder builder = new StringBuilder();
        
        builder.append("WORDCOUNTER \n (github.com/ST10257002/MP-WordCounter/)");
        builder.append("\n\n");
        
        int countX = table.getRowCount();
        int countY = table.getColumnCount();
        for (int row = 0; row < countX; row++) {
            for (int column = 0; column < countY; column++) {
                Object value = table.getValueAt(row, column);
                builder.append(value);
                if (column == 0) {
                    builder.append(": ");
                }   
            }
            builder.append("\n");
        }

        return builder.toString();
        
    }
    
    // ---
    
    public static int getTotal(String argv) {
        return argv.length();
    }
    
    public static int getWords(String argv) {
        return argv.trim().isEmpty() ? 0 : argv.trim().split("\\s+").length;
    }
    
    public static int getWordsUnique(String argv) {
        
        String[] array = argv.split("\\s+");
        Arrays.sort(array);
        
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (!array[i].trim().isEmpty()) {
                if (i == 0 || !array[i].equalsIgnoreCase(array[i - 1])) {
                    count++;
                }
            }
        }
        
        return count;
    }
    
    public static int getCharacters(String argv) {
        
        int count = 0;
        for (char c : argv.toCharArray()) {
            if (!Character.isWhitespace(c)) {
                count++;
            }
        }
        
        return count;
        
    }
    
    public static int getSpace(String argv) {
        
        int count = 0;
        for (char c : argv.toCharArray()) {
            if (Character.isWhitespace(c)) {
                count++;
            }
        }
        
        return count;
        
    }
    
    public static int getSymbols(String argv) {
        
        int[] symbols = Definitions.defineSymbol();
        
        int count = 0;
        for (char c : argv.toCharArray()) {
            int v = (int) c;
            if (Arrays.binarySearch(symbols, v) >= 0) {
                count++;
            }
        }
        
        return count;
        
    }
    
    public static int getLetters(String argv) { // support: ENG
        return argv.replaceAll("[^A-Za-z]", "").length();
    }
    
    public static int getLettersUpperCase(String argv) { // support: ENG
        return argv.replaceAll("[^A-Z]", "").length();
    }

    public static int getLettersLowerCase(String argv) { // support: ENG
        return argv.replaceAll("[^a-z]", "").length();
    }
    
    public static int getDigits(String argv) { // support: ENG
        return argv.replaceAll("[^0-9]", "").length();
    }
    
    public static int getParagraphs(String argv) {
        
        String[] paragraphs = argv.split("\n\n|\r\n\r\n");
        
        if (!argv.isBlank()) {
            return paragraphs.length;
        }
        
        return 0;
        
    }
    
    public static int getPages(String argv) {
        return ((int) Math.floor(getWords(argv) / 250));
    }
    
    public static int getSentences(String argv) {
        
        String[] paragraphs = argv.split("[.?!]");
        
        if (!argv.isBlank()) {
            return paragraphs.length;
        }
        
        return 0;
        
    }

}
