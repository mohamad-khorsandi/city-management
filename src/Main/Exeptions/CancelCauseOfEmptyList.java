package Main.Exeptions;

public class CancelCauseOfEmptyList extends CancelTravel{
    public CancelCauseOfEmptyList(EmptyList cause) {
        super(cause.toString());
    }
}
