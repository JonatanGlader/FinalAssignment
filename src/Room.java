public class Room {

    String roomName;
    int roomNumber;
    boolean canGoLeft = true;
    boolean canGoRight = true;
    boolean locked = false;
    String description;
    Container container;

    Room(String roomName, int roomNumber, boolean locked, String description) {
        this.roomName = roomName;
        this.roomNumber = roomNumber;
        this.locked = locked;
        this.description = description;
        if(roomNumber == 1) canGoLeft = false;
        if(roomNumber == 5) canGoRight = false;
    }

    Room(String roomName, int roomNumber, boolean locked, String description, Container container) {
        this.roomName = roomName;
        this.roomNumber = roomNumber;
        this.locked = locked;
        this.description = description;
        this.container = container;
        if(roomNumber == 1) canGoLeft = false;
        if(roomNumber == 5) canGoRight = false;
    }

    public void addContainer(Container container) {
        this.container = container;
    }

    public void setDesciption(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

}
