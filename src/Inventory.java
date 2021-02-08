import java.util.Arrays;

public class Inventory {
    private GameObject[] items;
    private int maxSize;

    public Inventory(int maxSize){
        this.maxSize = maxSize;
        items = new GameObject[maxSize];
    }
    public void addItem(GameObject newItem){

        int index = getFirstEmptyIndex();
        if (index ==-1){
            System.out.println("Inventory är fullt");
            return;
        }
        this.items[index] = newItem;
    }
    public GameObject dropItem(String itemName) {
        GameObject newItem = this.item;
        this.item = null;
        return newItem;
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
        return -1;


    }
}
