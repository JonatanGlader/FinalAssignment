import java.util.Arrays;

public class Inventory {
    private GameObject[] items;
    private int maxSize;

    public Inventory(int maxSize){
        this.maxSize = maxSize;
        items = new GameObject[maxSize];
    }
    //Add item to inventory, this takes a gameobject to add to its inventory
    public boolean addItem(GameObject newItem){
        int index = getFirstEmptyIndex();
        //if the array has no empty index
        if (index ==-1){
            System.out.println("Inventory är fullt");
            return false;
        }
        this.items[index] = newItem;
        return true;
    }
    //Drop Item to trade item, it returns the GameObject that this inventory shall lose and give to someone else
    public GameObject dropItem(String itemName) {
        GameObject placeHolder = new Item("", 0);
        for(GameObject item : items) {
            if(item.getName().equals(itemName)) {
                placeHolder = item;
                item = null;
            } else placeHolder = new Item("NoSuchItem", 0);
        }
        //if the returned item is "NuSuchItem and id 0, handle it in the return as there is no item with the entered name and try again
        return placeHolder;
    }

    public String toString(){
        return Arrays.toString(this.items);
    }

    private int getFirstEmptyIndex(){

        for (int i = 0; i<this.items.length; i++){
            if (this.items[i] == null){
                return i;
            }
        }
        //-1 means inventory is full
        return -1;


    }
}
