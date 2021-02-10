public class Container{

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

    public boolean isLocked() { return locked; }

}
