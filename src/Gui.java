import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/*Extremt enkelt Gui för att kunna komma igång.
Snygga gärna till/gör ett eget. Men tänk på att gör GUI:s INTE är ett kursmoment - så fastna inte här!
 */


    public class Gui extends JFrame {

        private JPanel panel;
        private JTextArea showRoom;
        private JTextArea showPersons;
        private JTextField input;
        private JTextArea output;
        private JTextArea inventory;
        private String command;
        private boolean gotCommand;
        private JButton button;
        public Gui(){
            this.gotCommand = false;
            this.command = "";
            this.setTitle("Game");
            this.setSize(800, 600);
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setUpElements();
            setUpPanel();
            this.add(panel);
            this.setVisible(true);
            this.setResizable(false);
        }

        //Returnera det senaste commitade kommandot
        public String getCommand(){
            if (this.gotCommand){
                gotCommand();
                return this.command;
            }
            return "-1";

        }

        //Här kan man updatera respektive fält:
        public void setShowRoom(String roomDescription){
            this.showRoom.setText(roomDescription);
         }
        public void setShowPersons(String personDesc){ this.showPersons.setText(personDesc); }
        public void setShowInventory(String inventory){
            this.inventory.setText(inventory);
        }
        public void output(String output) {
            this.output.setText(output);
            this.output.setForeground(Color.red);
        }
        public void message(String message) {
            this.output.setText(message);
            this.output.setForeground(Color.green);
        }

//Nedanståenda spaghetti är inte vacker...
        public void gotCommand(){
            this.gotCommand = false;
        }

        private void setUpPanel(){
            this.panel.add(showPersons);
            this.panel.add(showRoom);
            this.panel.add(input);
            this.panel.add(output);
            this.panel.add(inventory);
            this.panel.add(button);

        }

        private void setUpElements(){
            this.panel = new JPanel(new GridLayout(4,3));
            this.showRoom = new JTextArea("Room: ");
            this.showPersons = new JTextArea("Persons");
            this.inventory = new JTextArea("Inventory");
            this.input = new JTextField("Give command");
            this.output = new JTextArea(" ");
            this.showPersons.setEditable(false);
            this.showRoom.setEditable(false);
            this.inventory.setEditable(false);

            //Styling
            this.output.setForeground(Color.red);

            ActionListener inputListener = e -> {
                this.command = input.getText();
                this.gotCommand = true;
                System.out.println(this.command);};

            input.addActionListener(inputListener);

            this.button = new JButton("commit");
            this.button.addActionListener(inputListener);

        }


    }









