package Main.Exeptions;

public class EmptyList extends RuntimeException{
    public EmptyList(String listName) {
        this.listName = listName;
    }

    private String listName;
    @Override
    public String toString() {
        return  listName + " list is empty";
    }
}