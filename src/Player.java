import java.util.ArrayList;
import java.util.List;

public class Player {

    String name;
    int health = 100;
    Inventory inventory;
    private boolean isTalking;

    public Player(String name, int inventorySize) {
        this.name = name;
        this.inventory = new Inventory(inventorySize);
        isTalking = false;
    }
  //Check if player is talking to a npc
    public boolean isTalking() { return isTalking; }
  //change state if player is talking or not
    public void setTalking(boolean state){ isTalking = state; }
  //Send name of item to be dropped returns the selected item as GameObject
    public GameObject dropItem(String itemName) {
        return inventory.dropItem(itemName);
    }
  //Send item to be pickedup, returns false if inventory is full and true if succes
    public boolean pickupItem(GameObject item) {
        return inventory.addItem(item);
    }
}

