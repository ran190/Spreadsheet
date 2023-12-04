import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Manager {
    protected List<Spreadsheet> sList;

    public Manager() {
        sList = new ArrayList<>();
    }

    public boolean createSpreadsheet(String name,String fileName){
        if (searchFileSpreadsheet(sList, fileName)==null){
            Spreadsheet ss = new Spreadsheet(name,fileName);
            sList.add(ss);
            return true;
        }else{
            return false;
        }
    }

    public boolean loadSpreadsheet(String fileName) {
        Spreadsheet ss = searchFileSpreadsheet(sList, fileName);
    
        if (ss != null) {
            ss.loadSpreadsheet(fileName);
            return true;
        } else {
            // The file doesn't exist in the sList
            File currentDirectory = new File(".");
            File[] files = currentDirectory.listFiles();
    
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().equals(fileName)) {
                        // Create ss and add it to the sList
                        Spreadsheet ssnew = new Spreadsheet(fileName, fileName);
                        sList.add(ssnew);
                        ssnew.loadSpreadsheet(fileName);
                        return true;
                    }
                }
                // File not found in the directory
                return false;
            } else {
                // Unable to list files in the directory
                return false;
            }
        }
    }

    public boolean saveSpreadsheet(String fileName){
        Spreadsheet ss = searchFileSpreadsheet(sList, fileName);
        if (ss!=null) {
            ss.saveSpreadsheet(fileName);
            return true;
        } else {
            return false;
        }
    }

    public boolean editCell(String ssName, String place, String value) {
        Spreadsheet ss = searchSpreadsheet(sList, ssName);
        if (ss!=null) {
            int[] intPlace = convert(place);
            ss.editCell(intPlace[0], intPlace[1], value);
            return true;
        } else {
            return false;
        }
    }

    public String printCell(String ssName, String place){
        Spreadsheet ss = searchSpreadsheet(sList, ssName);
        if (ss!=null) {
            int[] intPlace = convert(place);
            String value = ss.printCell(intPlace[0], intPlace[1]);
            return value;
        } else {
            return null;
        }
    }

    public static int[] convert(String cellReference) {
        int column = 0;
        int row = 0;
        
        for (int i = 0; i < cellReference.length(); i++) {
            char c = cellReference.charAt(i);
            if (Character.isDigit(c)) {
                row = row * 10 + Character.getNumericValue(c);
            } else {
                column = column * 26 + (c - 'A' + 1);
            }
        }
        
        int[] result = { row, column };
        return result;
    }

    private static Spreadsheet searchSpreadsheet(List<Spreadsheet> sList, String name) {
        for (Spreadsheet spreadsheet : sList) {
            // Assuming Spreadsheet class has an appropriate equals method
            if (spreadsheet.getName().equals(name)) {
                return spreadsheet; // Found the spreadsheet in the list
            }
        }
        return null; // Spreadsheet not found in the list
    }

    private static Spreadsheet searchFileSpreadsheet(List<Spreadsheet> sList, String fileName) {
        for (Spreadsheet spreadsheet : sList) {
            // Assuming Spreadsheet class has an appropriate equals method
            if (spreadsheet.getFileName().equals(fileName)) {
                return spreadsheet; // Found the spreadsheet in the list
            }
        }
        return null; // Spreadsheet not found in the list
    }
}
