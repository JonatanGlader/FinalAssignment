public class UpdateGui implements Runnable{

    Gui gui;
    Room currentRoom;
    Player player;
    Person jeffersson;
    Person steven;

    public UpdateGui(Gui gui, Room currentRoom, Player player, Person jeffersson, Person steven) {
        this.gui = gui;
        this.currentRoom = currentRoom;
        this.player = player;
        this.jeffersson = jeffersson;
        this.steven = steven;
    }
    public void updateCurrent(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    @Override
    public void run() {
        //Om Jeffersson(npc) är i samma rum som spelaren
        if(jeffersson.getCurrentRoomInt()==currentRoom.getRoomNumber()) {
            //Om både jeffersson och spelaren är i rum 3
            if(currentRoom.getRoomNumber()==3) {
                gui.setShowPersons(steven.getDescription() + "\n" + jeffersson.getDescription());
            }
            //om bara jeffersson är i samma rum som spelaren
            else {
                gui.setShowPersons(jeffersson.getDescription());
            }
        //om jeffersson inte är i rum 3, men spelaren är där
        } else if(currentRoom.getRoomNumber()==3) {
            gui.setShowPersons(steven.getDescription());
        } else gui.setShowPersons("");
    }
}
