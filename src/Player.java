import java.io.Serial;
import java.io.Serializable;

public class Player implements Serializable {

    @Serial
    private static final long serialVersionUID = 8820846388530363284L;

    String name;
    int health = 100;
    private Inventory inventory;
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
    public void pickupItem(GameObject item) {
        inventory.addItem(item);
    }
  //Inventory toString
    public String getInventory() {
        return this.inventory.toString();
    }

    public boolean checkItem(int itemID) {
        return inventory.findItem(itemID);
    }

    public void setInventoryContains(Inventory inventory) {
        this.inventory = inventory;
    }
    public Inventory getInventoryContains() {
        return this.inventory;
    }
}

