package Objects;

import API.ChatroomAPI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
    }

    // tar inn meldingen fra bruker og finner ut hva som skal svares
    public static Message respond(Message message){
        String msg = message.getMessage();

        ArrayList<String> actions = new ArrayList<>(Arrays.asList("sing", "dance", "run", "eat"));
        ArrayList<String> greetings = new ArrayList<>(Arrays.asList("hi", "hello", "yo", "howdy"));

        String action_verb = "";
        String greeting = "";

        for(String verb : actions){
            if (msg.contains(verb)){
                action_verb = verb;
            }
        }

        for(String g : greetings){
            if (msg.contains(g)){
                greeting = g;
            }
        }

        String answer = getResponse(action_verb, greeting);

        Message botMsg = new Message(bot1.getUserID(), answer, message.getRoomID());

        if(botMsg.getMessage()!=null) {
            return botMsg;
        }
        else{
            return null;
        }

    }

    private static String getResponse(String action, String greeting){
        String response;

        // no greeting but verb
        if(!action.equals("") & greeting.equals("")){
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
        else {
            response = "HI! How are you?";
        }

        return response;
    }


    // code to genereate new chatrooms
    public static void generateChatroom(int i){
        String roomName = new String [] {"Cats","Disney","Coding","Dogs", "Reading", "Gaming"}[i];

        Chatroom cr = new Chatroom(roomName, bot1);
        Chatrooms.addRoom(cr);

        // add moderator to chatroom
        cr.addParticipant(bot1.getUserID());
    }

}