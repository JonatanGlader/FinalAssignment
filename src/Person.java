import java.util.Random;

public class Person extends Npc{

    String name;
    int currentRoomInt = 0;
    Inventory inventory = new Inventory(1);
    boolean isTalking;
    boolean suspended;

    public Person(String name, int currentRoomInt) {
        this.name = name;
        this.currentRoomInt = currentRoomInt;
        isTalking = false;
        suspended = false;
    }

    @Override
    public void run(){
        while(true) {
            sleep(15);
            changeRoom();
            System.out.println(this.name + " is in room " + currentRoomInt);
            synchronized(this) {
                while (suspended) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void sleep(long seconds) {
    try {
        Thread.sleep(seconds*1000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    }

  //Randomly change room
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

  //Set talking state to prevent movement
    public void setTalking(boolean state){
        isTalking = state;
        //Flag wait method to suspend/resume Thread
        if(state) { suspend(); } else { resume(); }
    }
    synchronized void suspend() { suspended = true; }
    synchronized void resume() { suspended = false; notify(); }

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
