public class Item  extends GameObject{
    int id;
    String name;

    public Item(String name, int id) {
        super(name, id);
        this.name = name;
        this.id = id;
    }
}
