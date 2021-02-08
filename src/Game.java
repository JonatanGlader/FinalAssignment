import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game{
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
    int foodID = 500;

    public Game(){
        createObjects();
        setupHouse();

        this.gui = new Gui();
       //**************************************************************************************************************
    //Run game
       while(gameRunning) {

    //Get Commands
           command = gui.getCommand();
           if (!command.equals("-1")) {

               if (command.equalsIgnoreCase("Right")) {
                    switchRoom('R');
               }
               if (command.equalsIgnoreCase("Left")) {
                   switchRoom('L');
               }
               if (command.equalsIgnoreCase("Pickup")) {

               }
               //Command to unlock the rooms with the needed key!
               if (command.equalsIgnoreCase("Unlock")) {
                   if(currentRoom.roomName.equals("UpperDeck")){
                       if(house.get(0).locked){
                            if()
                       } else {
                           //FelMeddelande karaktären kan gå in, rummet är öppet!----------------------------------
                       }
                   }
                   else if (currentRoom.roomName.equals("Hold")){
                       if(house.get(0).locked){
                           if()
                       } else {
                           //FelMeddelande karaktären kan gå in, rummet är öppet!----------------------------------
                       }
                   }
               }
    //if the room the player is trying to get into is locked
               //FLYTTA DENNA SÅ DEN INTE LOOPAS--------------------------------------------------
               if(currentRoom.locked) {
                   //FelMeddelande
                   if(currentRoom.roomName.equals("Brig")){
                       currentRoom = house.get(3);
                   }
                   else if (currentRoom.roomName.equals("CaptainsQuarter")){
                    currentRoom = house.get(1);
                   }
               }

           }
        }
       //***************************************************************************************************************
    }


    public void createObjects() {
        //items (keys, food etc)
        items.add(new Key("ChestKey", chestKeyID));
        items.add(new Key("CaptainsKey", captainQuartersKeyID));
        items.add(new Key("BrigKey", brigKeyID));
        items.add(new Item("Apple", foodID));

        //containers (chests etc) if locked, id is the required key
        containers.add(new Container("Bed", 0, false, items.stream().filter(item -> item.getID()==chestKeyID).collect(Collectors.toList()).get(0)));
        containers.add(new Container("chest", chestKeyID, true, items.stream().filter(item -> item.getID()==captainQuartersKeyID).collect(Collectors.toList()).get(0)));
        containers.add(new Container("Bedside table", 0, false, items.stream().filter(item -> item.getID()==brigKeyID).collect(Collectors.toList()).get(0)));

        //NPCs
        npcs.add(new Person("Sailor Jeffersson", 2));
        npcs.add(new Person("Sailor Steven", 3));
    }

    public void setupHouse() {
        house.add(new Room("CaptainsQuarter", 1, true, "Captains Quarter, the captain is sleeping in his bed, and there is a golden key right next to him on his Bedside table"));
        house.add(new Room("UpperDeck", 2, false, "The Upperdeck, sailor Jeffersson is up here polishing the cannons and there are stairs going down to the Tweendeck"));
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

    //If command is to go Right/Left
    public void switchRoom(char direction){
    //Move to the room to the right
        if(direction=='R'){
            switch (currentRoom.roomNumber) {
                case 1:
                    currentRoom = house.get(1);
                    break;
                case 2:
                    currentRoom = house.get(2);
                    break;
                case 3:
                    currentRoom = house.get(3);
                    break;
                case 4:
                    currentRoom = house.get(4);
                    break;
                //Can't go more right than this room
                case 5:
                    //Skicka FelMeddelande------------------------------------------------------------
                    break;
            }
        }
    //Move to the room to the left
        else if(direction=='L'){
            switch (currentRoom.roomNumber) {
                //Can't go more left than this room
                case 1:
                    //Skicka FelMeddelande---------------------------------------------------------------
                    break;
                case 2:
                    currentRoom = house.get(1);
                    break;
                case 3:
                    currentRoom = house.get(2);
                    break;
                case 4:
                    currentRoom = house.get(3);
                    break;
                case 5:
                    currentRoom = house.get(4);
                    break;
            }
        }
    }
}
%