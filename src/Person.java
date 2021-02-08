import java.util.Random;

public class Person extends Npc{

    String name;
    int currentRoomInt = 0;
    GameObject item;

    public Person(String name, int currentRoomInt, GameObject item) {
        this.name = name;
        this.currentRoomInt = currentRoomInt;
        this.item = item;
    }
    public Person(String name, int currentRoomInt) {
        this.name = name;
        this.currentRoomInt = currentRoomInt;
    }

    public void changeRoom() {
        if (this.currentRoomInt==0){
            currentRoomInt += 1;
        } else if (this.currentRoomInt==5) {
            this.currentRoomInt -= 1;
        } else {
            Random random = new Random();
            if(random.nextBoolean()){
                this.currentRoomInt +=1;
            } else this.currentRoomInt -=1;
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
