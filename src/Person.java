import java.util.Random;

public class Person extends Npc{

    String name;
    String description;
    private int currentRoomInt = 0;
    Inventory inventory = new Inventory(1);
    boolean isTalking;

    public Person(String name, int currentRoomInt) {
        this.name = name;
        this.currentRoomInt = currentRoomInt;
        isTalking = false;
    }

  //Randomly change room
    public void changeRoom() {
        if (this.currentRoomInt==1){
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

  //Set talking state to prevent movement
    public void setTalking(boolean state){
        isTalking = state;
        //Flag wait method to suspend/resume Thread
    }
    //if inventory is full, return false
    public boolean pickupItem(GameObject item) {
        return inventory.addItem(item);
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }
/*
    public boolean hasItem() {
        return inventory[0] != null;
    }
*/
    public GameObject dropItem() {
            return inventory.dropItem(inventory.toString());
    }

    public int getCurrentRoomInt(){
        return currentRoomInt;
    }
@Override
    public String toString(){
        return this.name + " " + this.description;
    }

}
