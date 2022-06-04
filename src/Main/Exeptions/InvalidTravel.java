package Main.Exeptions;

abstract public class InvalidTravel extends RuntimeException {
    public InvalidTravel(String msg) {
        this.msg = msg;
    }

    String msg;

    @Override
    public String toString() {
        System.out.println("travel is invalid because "+msg);
        return "";
    }
}
