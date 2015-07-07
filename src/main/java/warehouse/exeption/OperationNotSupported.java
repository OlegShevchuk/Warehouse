package warehouse.exeption;

/**
 * Created by Олег on 03.07.2015.
 */
public class OperationNotSupported extends Exception {
    public OperationNotSupported(){
        super("Операция не поддерживается");
    }
}
