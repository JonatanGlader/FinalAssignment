import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    Gui gui;
    List<Room> house = new ArrayList<>();
    List<Person> npcs = new ArrayList<>();
    List<GameObject> items = new ArrayList<>();
    List<Container> containers = new ArrayList<>();

    Room currentRoom;
    String command;

    boolean gameRunning = true;

    //MyID:s
    int chestKeyID = 900;
    int captainQuartersKeyID = 905;
    int brigKeyID = 910;

    public Game(){
        createObjects();
        setupHouse();

        this.gui = new Gui();

        command = gui.getCommand();
        System.out.println(command);

        while(gameRunning) {

            command = gui.getCommand();
            System.out.println(command);
        }
    }


    public void createObjects() {
        //items (keys, food etc)
        items.add(new Key("ChestKey", chestKeyID));
        items.add(new Key("CaptainsKey", captainQuartersKeyID));
        items.add(new Key("BrigKey", brigKeyID));
        items.add(new Item());

        //containers (chests etc) if locked, id is the required key
        containers.add(new Container("Bed", 0, false, items.stream().filter(item -> item.id==chestKeyID).collect(Collectors.toList()).get(0)));
        containers.add(new Container("chest", chestKeyID, true, items.stream().filter(item -> item.id==captainQuartersKeyID).collect(Collectors.toList()).get(0)));
        containers.add(new Container("Bedside table", 0, false, items.stream().filter(item -> item.id==brigKeyID).collect(Collectors.toList()).get(0)));
    }

    public void setupHouse() {
        house.add(new Room("CaptainsQuarter", 1, true, "Captains Quarter, the captain is sleeping in his bed, and there is a golden key right next to him on his Bedside table"));
        house.add(new Room("UpperDeck", 2, false, "The Upperdeck, there is a sailor up here polishing the cannons and stairs going down to the Tweendeck"));
        house.add(new Room("TweenDeck", 3, false, "The Tweendeck, this is the middle deck where sailors sleep and eat, there is a stair here down to the hold, a sailor at the stove and a bed in the corner."));
        house.add(new Room("Hold", 4, false, "The Hold, Here we store all our loot, there is one very shiny chest here, and there is also the locked brig here where your friend is imprisoned"));
        house.add(new Room("Brig", 5, true, "The brig!"));

        //add Bedside table to captainsQuarter
        house.get(0).addContainer(containers.get(2));
        //add chest to the Hold
        house.get(3).addContainer(containers.get(1));
        //add bed to tweendeck
        house.get(2).addContainer(containers.get(0));
    }
}
%