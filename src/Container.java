import java.io.Serial;
import java.io.Serializable;

public class Container implements Serializable {

    @Serial
    private static final long serialVersionUID = 35714810164337710L;

    String name;
    int id;
    private boolean locked;
    GameObject item;
    boolean empty = false;

    public Container(String name, int id, boolean locked, GameObject item) {
        this.name = name;
        this.id = id;
        this.locked = locked;
        this.item = item;
    }

    public Container(String name, boolean empty) {
        this.name = name;
        this.empty = empty;
        this.locked = false;
    }

    public GameObject takeItem(){
        GameObject item = this.item;
        this.item = null;
        this.empty = true;
        return item;
    }

    public boolean isLocked() { return locked; }

    public void unlock() { this.locked = false; }

}
