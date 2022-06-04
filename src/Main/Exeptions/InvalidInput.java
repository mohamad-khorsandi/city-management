package Main.Exeptions;

public class InvalidInput extends RuntimeException {
    public InvalidInput(String msg) {
        this.msg = msg;
    }
    private String msg;
    @Override
    public String toString() {
        System.out.println(msg);
        return "";
    }
}
