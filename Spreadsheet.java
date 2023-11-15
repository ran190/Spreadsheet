import java.util.Map;
import java.util.TreeMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Spreadsheet {    
    private TreeMap<Integer, TreeMap<Integer, Cell>> spreadsheet;
    private String name;
    private String fileName;

    public Spreadsheet(String name){
        spreadsheet = new TreeMap<>();
        this.name = name;
    }

    public Spreadsheet(String name, String fileName){
        spreadsheet = new TreeMap<>();
        this.name = name;
        this.fileName = fileName;
    }

    public String getName(){
        return name;
    }

    public String getFileName(){
        return fileName;
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public void loadSpreadsheet(String filename){
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int rowNumber = 0;
            while ((line = reader.readLine()) != null) {
                String[] cellContents = line.split(";");
                for (int col = 0; col < cellContents.length; col++) {
                    String content = cellContents[col].trim();
                    if (!content.isEmpty()) {
                        printCell(rowNumber, col);
                        System.out.print(";");
                    }
                }
                rowNumber++;
                System.out.println();
            }
            System.out.println("Spreadsheet loaded from " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveSpreadsheet(String filename){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<Integer, TreeMap<Integer, Cell>> rowEntry : spreadsheet.entrySet()) {
                TreeMap<Integer, Cell> rowData = rowEntry.getValue();
    
                for (Map.Entry<Integer, Cell> cellEntry : rowData.entrySet()) {
                    Cell cell = cellEntry.getValue();
                    String content = cell.getContent();
    
                    writer.write(content + ";");
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editCell(Integer row, Integer column, String value){
        int type = cellType(value);
        if (type == 1){
            //how to make sure is from numCell
            spreadsheet.computeIfAbsent(row, k -> new TreeMap<>()).put(column, new formulasCell(value));
        }
        if (type == 2){
            spreadsheet.computeIfAbsent(row, k -> new TreeMap<>()).put(column, new numCell(value));
        }
        if (type == 3){
            spreadsheet.computeIfAbsent(row, k -> new TreeMap<>()).put(column, new textCell(value));
        }
    }

    public String printCell(Integer row, Integer column){
        TreeMap<Integer, Cell> rowData = spreadsheet.get(row);
        if (rowData != null) {
            Cell cell = rowData.get(column);
            if (cell != null) {
                return cell.getContent();
            }
        }
        return null;
    }

    public int cellType(String value) {
        // If the string begins with "=", return 1
        if (value.startsWith("=")) {
            return 1;
        }
        // If the string can be parsed as a number, return 2
        try {
            Double.parseDouble(value);
            return 2;
        } catch (NumberFormatException e) {
            // If parsing as a number fails, return 3
            return 3;
        }
    }
}
