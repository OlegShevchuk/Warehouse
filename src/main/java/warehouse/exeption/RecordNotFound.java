package warehouse.exeption;

/**
 * Created by Олег on 06.07.2015.
 */
public class RecordNotFound extends Exception {
    public RecordNotFound(){
        super("Запись отсутствует в базе данных");
    }
    public RecordNotFound(String str){
        super(str);
    }
}
