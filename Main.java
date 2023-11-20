import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
       Main m = new Main();
       m.run();
    }

    public Main() {

    }

    private Manager Manager = new Manager();
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private boolean end = false;
    public void run() {
        boolean end = false;
        while(!end) {
            menu();
        }
    }

    public void menu() {
        System.out.println("MENU");
        System.out.println("----");
        System.out.println("1. Load spreadsheet");
        System.out.println("2. Save spreadsheet");
        System.out.println("3. Edit cell");
        System.out.println("4. Print cell");
        System.out.println("5. Create spreadsheet");
        System.out.println("6. Print spreadsheet list");       
        System.out.println("0. Exit");
        String str="";
        try {
            str = reader.readLine();
        } catch(Exception e) {
            System.out.println("Error reading the line");
        }
        switch(str) {
            case "1": loadSpreadsheet(); break;
            case "2": saveSpreadsheet(); break;
            case "3": editCell(); break;
            case "4": printCell(); break;
            case "5": createSpreadsheet();
            case "6": printList();break;
            case "0": end=true; break;
            default:
        }
    }

    public void loadSpreadsheet(){
        System.out.println("File name?");
        String fileName = "";
        try {
            fileName = reader.readLine();
        } catch (Exception e) {
            System.out.println("Error reading line");
        }
        if(Manager.loadSpreadsheet(fileName)==true){
            System.out.println("Spreadsheet loaded from " + fileName);
        }else{
            System.out.println("The file do not exist");
        }
    }

    public void saveSpreadsheet(){
        System.out.println("File name?");
        String fileName = "";
        try {
            fileName = reader.readLine();
        } catch (Exception e) {
            System.out.println("Error reading line");
        }
        if(Manager.saveSpreadsheet(fileName)==true){
            System.out.println("Spreadsheet is saved to " + fileName);
        }else{
            System.out.println("Spreadsheet name not found or the file already exist");
        }
    }

    public void editCell(){
        System.out.println("Spreadsheet name?");
        String ssName = "";
        try {
            ssName = reader.readLine();
        } catch (Exception e) {
            System.out.println("Error reading line");
        }
        System.out.println("Placement?");
        String place = "";
        try {
            place = reader.readLine();
        } catch (Exception e) {
            System.out.println("Error reading line");
        }
        System.out.println("Value?");
        String value = "";
        try {
            value = reader.readLine();
        } catch (Exception e) {
            System.out.println("Error reading line");
        }
        if(Manager.editCell(ssName,place,value)==true){
            System.out.println("The cell is edited");
        }else{
            System.out.println("The spreadsheet do not exist");
        }
    }

    public void printCell(){
        System.out.println("Spreadsheet name?");
        String ssName = "";
        try {
            ssName = reader.readLine();
        } catch (Exception e) {
            System.out.println("Error reading line");
        }
        System.out.println("Placement?");
        String place = "";
        try {
            place = reader.readLine();
        } catch (Exception e) {
            System.out.println("Error reading line");
        }
        String value = Manager.printCell(ssName,place);
        if(value!=null){
            System.out.println("The value of the cell is: " + value);
        }else{
            System.out.println("The spreadsheet do not exist");
        }
    }

    public void createSpreadsheet(){
        System.out.println("Spreadsheet name?");
        String ssName = "";
        try {
            ssName = reader.readLine();
        } catch (Exception e) {
            System.out.println("Error reading line");
        }
        System.out.println("File name?");
        String fileName = "";
        try {
            fileName = reader.readLine();
        } catch (Exception e) {
            System.out.println("Error reading line");
        }
        if(Manager.createSpreadsheet(ssName,fileName)==true){
            System.out.println("Spreadsheet " + ssName + " is created");
        }else{
            System.out.println("The spreadsheet already exist");
        }
    }

    public void printList(){
        System.out.println("Spreadsheet List:");
        System.out.println("-----------------");
    
        for (Spreadsheet spreadsheet : Manager.sList) {
            System.out.println("Spreadsheet Name: " + spreadsheet.getName());
            System.out.println("File Name: " + spreadsheet.getFileName());
            // You can add more information to print if needed
            
            System.out.println("-----------------");
        }
    }
}

