import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class Save implements Serializable {

    @Serial
    private static final long serialVersionUID = -9081482287705127523L;

    Player player;
    Room currentRoom;
    List<Room> ship;
    List<Person> npcs;

    public Save(Player player, Room currentRoom, List<Room> ship, List<Person> npcs) {
        this.player = player;
        this.currentRoom = currentRoom;
        this.ship = ship;
        this.npcs = npcs;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Room getCurrentRoom() {
        return this.currentRoom;
    }
}
