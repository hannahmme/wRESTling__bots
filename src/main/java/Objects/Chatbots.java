package Objects;

import java.util.ArrayList;
import java.util.Arrays;

public class Chatbots {

    static User bot1;
    static User bot2;
    static User bot3;
    static User bot4;

    public static void initialize() {
        bot1 = new User("Moderator");
        bot2 = new User("Caroline (bot)");
        bot3 = new User("Hannah (bot)");
        bot4 = new User("Amalie (bot)");

        Users.addUser(bot1);
        Users.addUser(bot2);
        Users.addUser(bot3);
        Users.addUser(bot4);
    }

    private static String getResponse(Message msg, String botname){
        String message = msg.getMessage();

        ArrayList<String> actions = new ArrayList<>(Arrays.asList("dance", "sing", "run", "eat"));
        ArrayList<String> greetings = new ArrayList<>(Arrays.asList("hi", "hello", "yo", "howdy"));

        String action = "";
        String greeting = "";

        for(String verb : actions){
            if (message.contains(verb)){
                action = verb;
            }
        }

        for(String g : greetings){
            if (message.contains(g)){
                greeting = g;
            }
        }

        String response;

        // no greeting but verb
        if(!action.equals("") & greeting.equals("") && botname.equals("hannah")){
            response = "cool. Let's " + action;
        }
        // no greeting nor verb
        else if(action.equals("") & greeting.equals("")){
            response = null;
        }
        // both greeting and verb
        else if(!action.equals("")){
            response = "Hello there! I would love to " + action;
        }
        else if(botname.equals("hannah")) {
            response = "HI! How are you?";
        }
        else {
            response = "I am the moderator. LOL";
        }

        return response;
    }

    public static Message modResp(Message message){
        String answer = getResponse(message, "moderator");
        Message botMsg = new Message(bot1.getUserID(), answer, message.getRoomID());

        if(botMsg.getMessage()!=null) {
            return botMsg;
        }
        else{
            return null;
        }
    }

    public static Message hannahResp(Message message){
        String answer = getResponse(message, "hannah");
        if(answer!=null) {
            return new Message(bot3.getUserID(), answer, message.getRoomID());
        }
        else{
            return null;
        }
    }

    public static Message carolineResp(Message message){
        return new Message(bot2.getUserID(), "hi", message.getRoomID());
    }

    public static Message amalieResp(Message message){
        return new Message(bot4.getUserID(), "hello", message.getRoomID());
    }

    // code to genereate new chatrooms
    public static void generateChatroom(int i){
        String roomName = new String [] {"Cat room","Disney room","Coding room","Dog room", "Reading room", "Gaming room"}[i];

        Chatroom cr = new Chatroom(roomName, bot1);
        Chatrooms.addRoom(cr);

        // add moderator to chatroom
        cr.addParticipant(bot1.getUserID());
    }
}