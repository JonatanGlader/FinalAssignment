import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game{
    Gui gui;
    Player player = new Player("Steven", 4);
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
        currentRoom = house.get(1);
        this.gui = new Gui();
        Thread jeffersson = new Thread(npcs.get(0), "Sailor Jeffersson Thread");
        System.out.println("Welcome! You are currently in room: " + currentRoom.toString());
        jeffersson.start();
//**********************************************************************************************************************
    //Run game
       while(gameRunning) {

    //Get Commands
          // if(gui.gotCommand()){
           command = gui.getCommand();
           if (!command.equals("-1")) {

               if (command.equalsIgnoreCase("Right")) {
                   switchRoom('R');
                   System.out.println("Current room is: " + currentRoom.toString());
               }
               if (command.equalsIgnoreCase("Left")) {
                   switchRoom('L');
                   System.out.println("Current room is: " + currentRoom.toString());
               }
               if (command.equalsIgnoreCase("Pickup")) {
                   //DoSomething-------------------------------------------------------------------------------
                   if (player.isTalking()) {
                       //--------------------------------------------------------------------------------------------
                       System.out.println("Trading with npc");
                   } else if (currentRoom.gotContainer()) {
                       if (!currentRoom.container.isLocked()) {
                           //PickupItem----------------------------------------------------------------------------
                           System.out.println("Container is unlocked and you can pickup item");
                       } else {
                           //ChestisLocked-------------------------------------------------------------------------
                           System.out.println("Container is locked, try unlocking it first");
                       }

                   } else {
                       //FelMeddelande, inget att plocka------------------------------------------------------------
                       System.out.println("Nothing to pick up here");
                   }
               }
               //Command to talk to a NPC in current room
               if (command.equalsIgnoreCase("Talk")) {
                   for (Person p : npcs) {
                       if (p.currentRoomInt == currentRoom.roomNumber) {
                           p.setTalking(true);
                           player.setTalking(true);
                           break;
                       } else {
                           //FelMeddelande, Ingen NPC i Rummet------------------------------------------------------
                           System.out.println("No NPC in the room!");
                       }
                   }
               }
               //Command to unlock the rooms with the needed key!
               if (command.equalsIgnoreCase("Unlock")) {
                   if (currentRoom.roomName.equals("UpperDeck")) {
                       //Check if CaptainQuarter is locked
                       if (house.get(0).locked) {

                       } else {
                           //FelMeddelande karaktären kan gå in, rummet är öppet!----------------------------------
                           System.out.println("Room is locked");
                       }
                   } else if (currentRoom.roomName.equals("Hold")) {
                       //Check if Brig is locked
                       if (house.get(4).locked) {
                           //Check if chest in Hold is locked
                           if (currentRoom.container.isLocked()) {
                               //-Meddelande, både låda och rum är låst, vad vill du låsa upp?
                               System.out.println("There is a locked chest and a locked door in this room");
                           } else {
                               System.out.println("Brig unlocked");
                               //-Här Blir Spelet Klart------------------------------------------------------------
                           }
                       } else {
                           //FelMeddelande karaktären kan gå in, rummet är öppet!----------------------------------
                       }
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
       // }
//**********************************************************************************************************************
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

    public void updateRoom(){

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