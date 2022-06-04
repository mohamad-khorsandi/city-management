package Main.Exeptions;

abstract public class CancelTravel extends RuntimeException{
    public CancelTravel(String causeMsg) {
        this.causeMsg = causeMsg;
    }

    String causeMsg;
    @Override
    public String toString() {
        System.out.println("travel has been canceled because "+causeMsg);
        return "";
    }
}
