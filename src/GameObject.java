public abstract class GameObject {
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
