public abstract class GameObject {
    private String name;

    public GameObject(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public String toString(){

        return this.name;
    }
}
