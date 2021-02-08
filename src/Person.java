import java.util.Random;

public class Person extends Npc{

    String name;
    int currentRoomInt = 0;
    Inventory inventory = new Inventory(1);

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
    //if inventory is full, return false
    public boolean pickupItem(GameObject item) {
        return inventory.addItem(item);
    }
/*
    public boolean hasItem() {
        return inventory[0] != null;
    }
*/
    public GameObject dropItem() {
            return inventory.dropItem(inventory.toString());
    }

}
