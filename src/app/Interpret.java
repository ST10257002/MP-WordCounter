
package app;

// ST10257002 (MP)

import java.util.*;
import javax.swing.table.DefaultTableModel;


public class Interpret {
    
    private static final String inDev = "n/a";
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
    private static final String jGridField11 = inDev;
    private static final String jGridField12 = inDev;
    private static final String jGridField13 = inDev;
    private static final String jGridField14 = inDev;
    private static final String jGridField15 = inDev;
    private static final String jGridField16 = inDev;
    private static final String jGridField17 = inDev;
    private static final String jGridField18 = "Time to read";
    private static final String jGridField19 = "Time to speak";
    private static final String jGridField20 = "Time to write";
    
    // ---
    
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
            {jGridField11, },
            {jGridField12, },
            {jGridField13, },
            {jGridField14, },
            {jGridField15, },
            {jGridField16, },
            {jGridField17, },
            {jGridField18, (getWords(argv) / 200) + " min"},
            {jGridField19, (getWords(argv) / 150) + " min"},
            {jGridField20, (getWords(argv) / 40) + " min"}
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
                if (column == 0) {
                    builder.append(value + ": ");
                } else {
                    builder.append(value);
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
        
        int[] symbols = getSymbolsDefinition();
        
        int count = 0;
        for (char c : argv.toCharArray()) {
            int v = (int) c;
            if (Arrays.binarySearch(symbols, v) >= 0) {
                count++;
            }
            /*
            for (int i : symbols) {
                if (i == v) {
                    count++;
                    break;
                }
            }
            */
        }
        
        return count;
        
    }
    
    public static int[] getSymbolsDefinition() {
        
        int[] definition = {
            33,  // !
            34,  // "
            35,  // #
            36,  // $
            37,  // %
            38,  // &
            39,  // '
            40,  // (
            41,  // )
            42,  // *
            43,  // +
            44,  // ,
            45,  // -
            46,  // .
            47,  // /
            58,  // :
            59,  // ;
            60,  // <
            61,  // =
            62,  // >
            63,  // ?
            64,  // @
            91,  // [
            92,  // \
            93,  // ]
            94,  // ^
            95,  // _
            96,  // `
            123, // {
            124, // |
            125, // }
            126, // ~
            128, // €
            133, // …
            145, // ‘
            146, // ’
            147, // “
            148, // ”
            149, // •
            150, // –
            151, // —
            153, // ™
            161, // ¡
            162, // ¢
            163, // £
            164, // ¤
            165, // ¥
            166, // ¦
            167, // §
            169, // ©
            174, // ®
            176, // °
            177, // ±
            191, // ¿
            247, // ÷
        };
                
        return definition;
                
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

}
