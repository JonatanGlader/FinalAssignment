import java.io.Serial;
import java.io.Serializable;

public abstract class GameObject implements Serializable {
    @Serial
    private static final long serialVersionUID = 1079741198463759872L;

    private int id;
    private String name;

    public GameObject(String name, int id){
        this.name = name;
        this.id = id;
    }
    public String getName(){
        return this.name;
    }
    public int getID(){
        return this.id;
    }
    public String toString(){

        return this.name;
    }
}
