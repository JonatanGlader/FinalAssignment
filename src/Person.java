import java.util.Random;

public class Person extends Npc{

    String name;
    String description;
    private int currentRoomInt = 0;
    private String helloPhrase;
    Inventory inventory = new Inventory(1);
    boolean isTalking;

    public Person(String name, int currentRoomInt, String helloPhrase) {
        this.name = name;
        this.currentRoomInt = currentRoomInt;
        this.helloPhrase = helloPhrase;
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
    }
    //if inventory is full, return false
    public void pickupItem(GameObject item) {
        inventory.addItem(item);
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }
    public void setHello(String phrase) {

    }
    public String getHello() {
        return this.helloPhrase;
    }

    public void setCurrentRoomInt(int currentRoomInt) {
        this.currentRoomInt = currentRoomInt;
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

    public void setPerson(Person savedPerson) {
        this.name = savedPerson.name;
        this.description = savedPerson.description;
        this.currentRoomInt = savedPerson.currentRoomInt;
        this.helloPhrase = savedPerson.getHello();
        this.inventory = savedPerson.inventory;
        this.isTalking = savedPerson.isTalking;
    }

@Override
    public String toString(){
        return this.name + " " + this.description;
    }

}
