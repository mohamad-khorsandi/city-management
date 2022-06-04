package Main.Exeptions;

public class illegalNumberOfPassengers extends CancelTravel{
    public illegalNumberOfPassengers() {
        super("number of passengers is illegal");
    }
}
