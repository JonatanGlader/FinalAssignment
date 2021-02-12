import java.io.Serial;
import java.io.Serializable;

public class Room implements Serializable {

    @Serial
    private static final long serialVersionUID = 5269390184123840948L;

    String roomName;
    int roomNumber;
    boolean canGoLeft = true;
    boolean canGoRight = true;
    private boolean locked = false;
    String description;
    Container container;
    private boolean gotContainer;

    Room(String roomName, int roomNumber, boolean locked, String description) {
        this.roomName = roomName;
        this.roomNumber = roomNumber;
        this.locked = locked;
        this.description = description;
        if(roomNumber == 1) canGoLeft = false;
        if(roomNumber == 5) canGoRight = false;
        gotContainer = false;
    }

    Room(String roomName, int roomNumber, boolean locked, Container container, String description) {
        this.roomName = roomName;
        this.roomNumber = roomNumber;
        this.locked = locked;
        this.description = description;
        this.container = container;
        if(roomNumber == 1) canGoLeft = false;
        if(roomNumber == 5) canGoRight = false;
        gotContainer = true;
    }

    public boolean gotContainer() { return gotContainer; }

    public void addContainer(Container container) {
        this.container = container;
    }

    public void setDesciption(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

@Override
    public String toString() {
        return this.roomName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isLocked() {
        return this.locked;
    }
    public void setLock(boolean locked) {
        this.locked = locked;
    }

    public void setRoom(Room room) {
        this.roomName = room.roomName;
        this.roomNumber = room.roomNumber;
        this.canGoLeft = room.canGoLeft;
        this.canGoRight = room.canGoRight;
        this.locked = room.locked;
        this.description = room.getDescription();
        this.container = room.container;
        this.gotContainer = room.gotContainer();
    }
}
