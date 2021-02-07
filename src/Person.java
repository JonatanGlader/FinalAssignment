import java.util.Random;

public class Person extends Npc{

    String name;
    int currentRoom = 0;
    GameObject item;

    public Person(String name, int currentRoom, GameObject item) {
        this.name = name;
        this.currentRoom = currentRoom;
        this.item = item;
    }
    public Person(String name, int currentRoom) {
        this.name = name;
        this.currentRoom = currentRoom;
    }

    public void changeRoom() {
        if (this.currentRoom==0){
            currentRoom += 1;
        } else if (this.currentRoom==5) {
            this.currentRoom -= 1;
        } else {
            Random random = new Random();
            if(random.nextBoolean()){
                this.currentRoom +=1;
            } else this.currentRoom -=1;
        }
    }

    public boolean pickupItem(GameObject item) {
        if (this.item == null){
            this.item = item;
            return true;
        } else return false;
    }

    public boolean hasItem() {
        return this.item != null;
    }

    public GameObject dropItem() {
        GameObject newItem = this.item;
        this.item = null;
        return newItem;

    }

}
