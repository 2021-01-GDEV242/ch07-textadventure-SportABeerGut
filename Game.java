import javax.swing.JLayer;
import java.util.concurrent.Phaser;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Colin Jones
 * @version 2021.03.16
 */

public class Game
{
    
    private Parser parser;
    //private Room currentRoom;
    private Player player;    
    //private Player player  
    private int counter = 0;
    
    /**
     * Main method
     */
    public static void main(String[] args)
    {
        Game game = new Game();
        game.play();
    }

    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        player = new Player("A boy who likes sports", 120, "Colin");
        createRooms();
    }
       
    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office, cellar, museam, dormRooms, diningHall, studentsCenter, gym, yoga, lockerRoom, pool, basketballCourt, weightRoom, basement;
        Item cup, book, key;
         
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        cellar = new Room("in the cellar");
        museam = new Room("in the museam");
        dormRooms = new Room("in a dorm room");
        diningHall = new Room("in a dining hall");
        studentsCenter = new Room("in the student's center");
        gym = new Room("in the gym");
        yoga = new Room("in the yoga room");
        lockerRoom = new Room("in the locker room");
        pool = new Room("in the pool");
        basketballCourt = new Room("in the basketball court");
        weightRoom = new Room("in the weight room");

        basement = new Room("in the basement");
        
        // create the items
        
        cup = new Item("a cup", 5, "cup");
        book = new Item("a book", 1200, "book");
        key = new Item("a key", 500,"key");
        
                
        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        outside.setExit("north", dormRooms);
        outside.setItem("cup", cup);
        outside.setItem("book", book);
        
        theater.setExit("west", outside);
        theater.setExit("south", museam);
        outside.setItem("key", key);

        pub.setExit("east", outside);
        pub.setExit("down", cellar);
        pub.setExit("south", gym);

        lab.setExit("north", outside);
        lab.setExit("east", office);
        
        office.setExit("west", lab);
        office.setExit("south", studentsCenter);
        office.setExit("down", basement);
               
        museam.setExit("north", theater);
        
        dormRooms.setExit("east", diningHall);
        dormRooms.setExit("south", outside);
        dormRooms.setLocked(true);
        
        diningHall.setExit("west", dormRooms);
        
        studentsCenter.setExit("north", office);
        
        gym.setExit("north", pub);
        gym.setExit("south", pool);
        gym.setExit("east", lockerRoom);
        gym.setExit("west", yoga);
        
        pool.setExit("north", gym);
        pool.setExit("south", basketballCourt);
        
        lockerRoom.setExit("west", gym);
        lockerRoom.setExit("south", weightRoom);
        
        yoga.setExit("east", gym);
        
        basketballCourt.setExit("north", pool);
        basketballCourt.setLocked(true);
        
        basement.setExit("up", office);
        
        //currentRoom = outside;  // start game outside
        player.setCurrentRoom(office);
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while(! finished){
            if(counter > 7){
                System.out.println("Sorry your time has run out :( ");
                finished = true;
            }
            else{
                Command command = parser.getCommand();
                finished = processCommand(command);
                counter++;
            }
        }
        
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println("Your goal is to find a key and unlock a door before your time runs out.  Good luck! ");
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
                
            case LOOK:
                 look(command);
                 break;
                 
            case EAT:
                eat(command);
                break;
                
            case TAKE:
                take(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null){
            System.out.println("There is no door!");
        }
        else
        {
            if(nextRoom.isLocked())
            {
                if(player.containsThing("key"))
                {
                    player.setCurrentRoom(nextRoom);
                    System.out.println(player.getCurrentRoom().getLongDescription());
                }
                else{
                    System.out.println("This room is locked.  You must have a key. " );
                }
            }
            else 
            {
                player.setCurrentRoom(nextRoom);
                System.out.println(player.getCurrentRoom().getLongDescription());
            }
        }        
    }
    
    
    /** 
     * Look at what room you are currently in and what the exits are.
     */
    private void look(Command command) 
    {
         System.out.println(player.getCurrentRoom().getLongDescription());
    }
    
    /** 
     * Eat something.
     * @param command tells you what to eat
     */
    private void eat(Command command) 
    {
         System.out.println("You have eaten. ");
    }
    
    /** 
     * Take something.
     * @param command tells you what to take
     */
    private void take(Command command) 
    {
         if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to take...
            System.out.println("Take what?");
            return;
        }
        
        String thingName = command.getSecondWord();
        Item thing = player.getCurrentRoom().getThing(thingName);
        
        if(thing != null){
            player.addThing(thing);
            player.getCurrentRoom().removeThing(thingName);
        //player.getCurrentRoom().containsThing(thingName)){
            //add Player.;
            //remove Room.
        }
        else{
            System.out.println("The thing does not exist in this room ");
        }
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    }


