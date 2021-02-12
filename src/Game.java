import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Game{
    Gui gui;
    Player player = new Player("Steven", 4);
    List<Room> ship = new ArrayList<>();
    List<Person> npcs = new ArrayList<>();
    List<GameObject> items = new ArrayList<>();
    List<Container> containers = new ArrayList<>();
    UpdateNpc updateNpc;
    UpdateGui updateGui;

    Room currentRoom;
    Person npcTalking;
    Save save;

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
        currentRoom = ship.get(1);
        this.gui = new Gui();
        //Create Threads
        updateNpc = new UpdateNpc(npcs.get(0), gui, currentRoom);
        updateGui = new UpdateGui(gui, currentRoom, player, npcs.get(0), npcs.get(1));
        Thread guiThread = new Thread(updateGui, "GUI Thread");
        Thread jefferssonThread = new Thread(updateNpc, "Sailor Jeffersson Thread");
        guiThread.start();
        jefferssonThread.start();


        System.out.println("Welcome! You are currently in room: " + currentRoom.toString());
        updateRoom();
        updateInventory();
        //Schedules
        ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(5);
        pool.scheduleAtFixedRate(updateGui ,0,500, TimeUnit.MILLISECONDS);
        pool.scheduleAtFixedRate(updateNpc ,10,15, TimeUnit.SECONDS);

//**********************************************************************************************************************
    //Run game
       while(gameRunning) {

    //Get Commands
          // if(gui.gotCommand()){
           command = gui.getCommand();
           if (!command.equals("-1")) {

               if (command.equalsIgnoreCase("Right")) {
                   gui.output("You went right");
                   switchRoom('R');
                   System.out.println("Current room is: " + currentRoom.toString());

               }
               if (command.equalsIgnoreCase("Left")) {
                   gui.output("You went left");
                   switchRoom('L');
                   System.out.println("Current room is: " + currentRoom.toString());
               }

               if(command.equalsIgnoreCase("Save")) {
                   save = new Save(this.player, this.currentRoom, this.ship, this.npcs);
                   save();
               }
               if(command.equalsIgnoreCase("Load")) {
                   load();
               }

               //Command to unlock the rooms with the needed key!
               if (command.equalsIgnoreCase("Unlock")) {
                   if (currentRoom.roomName.equals("UpperDeck")) {
                       //Check if CaptainQuarter is locked
                       if (ship.get(0).isLocked()) {
                        if (player.checkItem(captainQuartersKeyID)) {
                            ship.get(0).setLock(false);
                            gui.output("Captains Quarters Unlocked");
                        } else gui.output("You don't have the key!");
                       } else {
                           System.out.println("Room is already unlocked");
                           gui.output("Room is already unlocked");
                       }
                   } else if (currentRoom.roomName.equals("Hold")) {
                       //Check if Brig is locked
                       if (ship.get(4).isLocked()) {
                           //Check if chest in Hold is locked
                           if (currentRoom.container.isLocked()) {
                               if (player.checkItem(chestKeyID)) {
                                 //Unlock the chest and chagne room description
                                   gui.message("You unlocked the chest!");
                                   currentRoom.container.unlock();
                                   currentRoom.setDesciption("The Hold, Here we store all our loot. \nthere is one unlocked chest here, with a key in it\nYou see the locked brig here where your friend is imprisoned");
                                   updateRoom();
                                   updateInventory();
                               }
                           } else {
                               if (player.checkItem(brigKeyID)) {
                                   ship.get(0).setLock(false);
                                   gui.output("Brig Unlocked");
                               } else gui.output("You don't have the key!");
                           }
                       } else {
                           System.out.println("Room is already unlocked");
                           gui.output("Room is already unlocked, try entering it!");
                       }
                   }
               }
            //Pickup item from rooms container
               if (command.equalsIgnoreCase("Pickup")) {
                   if (currentRoom.gotContainer()) {
                       if (!currentRoom.container.isLocked()) {
                           player.pickupItem(currentRoom.container.takeItem());
                           gui.message("You picked up an item!");
                           updateInventory();
                           if(currentRoom.roomName.equals("Hold")) {
                               currentRoom.setDesciption("The Hold, Here we store all our loot. \nthere is one unlocked chest here, but it's empty\nYou see the locked brig here where your friend is imprisoned");
                               updateRoom();
                           }
                       } else {
                           gui.output("The chest is locked! \n you need to find a key and unlock it.");
                           System.out.println("Container is locked, try unlocking it first");
                       }

                   } else {
                       gui.output("There is nothing to pick up here!");
                       System.out.println("Nothing to pick up here!");
                   }
               }

               //Command to talk to a NPC in current room
               if (command.equalsIgnoreCase("Talk")) {
                   for (Person p : npcs) {
                       if (p.getCurrentRoomInt() == currentRoom.roomNumber) {
                           p.setTalking(true);
                           player.setTalking(true);
                           gui.message(p.getHello());
                           System.out.println("YES!");
                           npcTalking = p;
                           break;
                       } else {
                           System.out.println(p.getCurrentRoomInt() + " " + currentRoom.roomNumber);
                           System.out.println("No NPC in the room!");
                           gui.output("There is no NPC in this room!");
                       }
                   }
               }
             //While player is talking to a NPC **********************************************
               while(player.isTalking()) {
                   command = gui.getCommand();

                   if (command.equalsIgnoreCase("trade")) {
                       //trade with npc--------------------------------------------------------------------------
                       System.out.println("Trading with npc");
                       if(npcTalking.inventory.gotItems()) {
                           this.player.pickupItem(npcTalking.inventory.dropFirstItem());
                           updateInventory();
                       }
                   }

                       //Stop talking to npc
                   if (command.equalsIgnoreCase("Bye")) {
                       for(Person npc: npcs) {
                        if(npc.isTalking) {
                            npcTalking.setTalking(false);
                            npc = npcTalking;
                            player.setTalking(false);
                            gui.message("Have a nice day!");
                            break;
                        }
                       }
                   }
               }
             //*******************************************************************************
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
        containers.add(new Container("Bed", 0, false, items.stream().filter(item -> item.getID()==foodID).collect(Collectors.toList()).get(0)));
        containers.add(new Container("chest", chestKeyID, true, items.stream().filter(item -> item.getID()==captainQuartersKeyID).collect(Collectors.toList()).get(0)));
        containers.add(new Container("Bedside table", 0, false, items.stream().filter(item -> item.getID()==brigKeyID).collect(Collectors.toList()).get(0)));

        //NPCs
        npcs.add(new Person("Sailor Jeffersson", 2, "Yoho! how can i assist you"));
        npcs.add(new Person("Sailor Steven", 3, "Hello there! Food is soon done!"));
        npcs.get(1).setDescription("Sailor Steven is cooking some fish at the stove");
        npcs.get(1).pickupItem(items.get(0));
    }
    public void setupHouse() {
        ship.add(new Room("CaptainsQuarter", 1, true, "Captains Quarter, the captain is sleeping in his bed, and there is a golden key right next to him on his Bedside table"));
        ship.add(new Room("UpperDeck", 2, false, "The Upperdeck, \n  there are stairs going down to the Tweendeck"));
        ship.add(new Room("TweenDeck", 3, false, "The Tweendeck, \n this is the middle deck where sailors sleep and eat. \n there are stairs here leading down to the hold and there is a bed in the corner."));
        ship.add(new Room("Hold", 4, false, containers.get(0),"The Hold, Here we store all our loot. \nthere is one Locked very shiny chest here, \nYou see the locked brig here where your friend is imprisoned"));
        ship.add(new Room("Brig", 5, true, "The brig!"));

        //add Bedside table to captainsQuarter
        ship.get(0).addContainer(containers.get(2));
        //add chest to the Hold
        ship.get(3).addContainer(containers.get(1));
        //add bed to tweendeck
        ship.get(2).addContainer(containers.get(0));
    }

  //Update Room information, wich npcs are in the room, wich to show in GUI etc.
    public void updateRoom(){
      //Check so that the room isn't locked, and throw an output if they are and move player to latest room
        if(currentRoom.isLocked()) {
            if(currentRoom.roomName.equals("Brig")){
                currentRoom = ship.get(3);
                gui.setShowRoom(currentRoom.getDescription());
                gui.output("You can't enter the Brig since it's locked! \n you need to find a key!");
                System.out.println("You have been placed in the Hold, you tried to enter a locked room!");
            }
            else if (currentRoom.roomName.equals("CaptainsQuarter")){
                currentRoom = ship.get(1);
                gui.setShowRoom(currentRoom.getDescription());
                gui.output("You can't enter the CaptainsQuarters since it's locked! \n you need to find a key!");
                System.out.println("You have been placed in the Upperdeck, you tried to enter a locked room!");
            }
        }
        updateNpc.updateCurrent(currentRoom);
        updateGui.updateCurrent(currentRoom);
        gui.setShowRoom(currentRoom.getDescription());
    }

    public void updateInventory() {
        gui.setShowInventory(this.player.getInventory());
    }

  //IPlayer movement
    public void switchRoom(char direction){
    //Move to the room to the right
        if(direction=='R'){
            switch (currentRoom.roomNumber) {
                case 1:
                    currentRoom = ship.get(1);
                    break;
                case 2:
                    currentRoom = ship.get(2);
                    break;
                case 3:
                    currentRoom = ship.get(3);
                    break;
                case 4:
                    currentRoom = ship.get(4);
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
                    currentRoom = ship.get(0);
                    break;
                case 3:
                    currentRoom = ship.get(1);
                    break;
                case 4:
                    currentRoom = ship.get(2);
                    break;
                case 5:
                    currentRoom = ship.get(3);
                    break;
            }
        }
        updateRoom();
    }

    public void save() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./newSave.bin"));
            oos.writeObject(save);
            oos.close();

            System.out.println("Saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./newSave.bin"));
            save = (Save) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        //Restore the values
        currentRoom = save.getCurrentRoom();
        player.setInventoryContains(save.getPlayer().getInventoryContains());

        for(int i = 0; i < this.ship.size(); i++) {
            this.ship.get(i).setRoom(save.ship.get(i));
        }
        for(int i = 0; i < this.npcs.size(); i++) {
            this.npcs.get(i).setPerson(save.npcs.get(i));
        }
        updateRoom();
        updateInventory();
    }


}