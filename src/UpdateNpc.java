public class UpdateNpc implements Runnable{

    Person npc;
    Gui gui;
    Room currentRoom;

    public UpdateNpc(Person npc, Gui gui, Room currentRoom){
        this.npc = npc;
        this.gui = gui;
        this.currentRoom = currentRoom;
    }

    public void updateCurrent(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    @Override
    public void run() {
            //Only update when npc isn't talking to player
            if(!npc.isTalking) {
                npc.changeRoom();
                updateRoomDescription();
                System.out.println(npc.name + " is in room " + npc.getCurrentRoomInt() + " You are in room: " + currentRoom.roomName);
            }
    }

    public void updateRoomDescription() {
        switch (npc.getCurrentRoomInt()) {
            case 1 -> npc.setDescription("Sailor Jeffersson is guarding the sleeping captain");
            case 2 -> npc.setDescription("Sailor Jeffersson is up here polishing the cannons");
            case 3 -> npc.setDescription("Sailor Jeffersson is down here eating");
            case 4 -> npc.setDescription("Sailor Jeffersson is down here checking the prisoner");
            case 5 -> npc.setDescription("Sailor Jeffersson is in the brig feeding the prisoner");
        }
    }
}
