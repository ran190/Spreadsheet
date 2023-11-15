public class numCell extends Cell{
    protected double value;

    public numCell(){
        
    }

    public numCell(double value){
        this.value = value;
    }

    public numCell(String value){
        this.value = Double. valueOf(value);
    }

    public String getContent(){
        return String.valueOf(value);
    }

    public String getValue(){
        return getContent();
    }
}
