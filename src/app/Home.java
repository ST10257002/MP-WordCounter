
package app;

// ST10257002 (MP)

import java.util.*;
import javax.swing.table.DefaultTableModel;


public class Home {
    
    private static final String jGridField01 = "Length";
    private static final String jGridField02 = "Word Total";
    private static final String jGridField03 = "Word Unique";
    private static final String jGridField04 = "Character";
    private static final String jGridField05 = "Letter";
    private static final String jGridField06 = "Uppercase";
    private static final String jGridField07 = "Lowercase";
    private static final String jGridField08 = "Number";
    private static final String jGridField09 = "Symbol";
    private static final String jGridField10 = "Space";
    private static final String jGridField11 = null;
    private static final String jGridField12 = null;
    private static final String jGridField13 = null;
    private static final String jGridField14 = null;
    private static final String jGridField15 = null;
    private static final String jGridField16 = null;
    private static final String jGridField17 = null;
    private static final String jGridField18 = "TIme (read)";
    private static final String jGridField19 = "Time (speak)";
    private static final String jGridField20 = "Time (write)";
    
    // ---
    
    public static DefaultTableModel onUpdate(String argv, boolean advanced) {

        Object[] gridHeader = {"Type", "Value"};
        Object[][] gridData = {
            {jGridField01, getTotal(argv)},
            {jGridField02, getWords(argv)},
            {jGridField03, getWordsUnique(argv)},
            {jGridField04, },
            {jGridField05, },
            {jGridField06, },
            {jGridField07, },
            {jGridField08, },
            {jGridField09, },
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
            {jGridField20, (getWords(argv) / 40) + " min"},
        };
        
        return new DefaultTableModel(gridData, gridHeader);

    }
    
    // ---
    
    public static int getTotal(String argv) {
        return argv.length();
    }
    
    public static int getWords(String argv) {
        return argv.trim().isEmpty() ? 0 : argv.trim().split("\\s+").length;
    }
    
    public static int getWordsUnique(String argv) {
        
        String[] text = argv.split("\\s+");
        
        Arrays.sort(text);
        
        int count = 0;
        for (int i = 0; i < text.length; i++) {
            if (!text[i].trim().isEmpty()) {
                if (i == 0 || !text[i].equalsIgnoreCase(text[i - 1])) {
                    count++;
                }
            }
        }
        
        return count;
    }
    
    public static int getSpace(String argv) {
        int count = 0;
        for (char c : argv.toCharArray()) {
            count += Character.isWhitespace(c) ? 1 : 0;
        }
        return count;
    }

}
