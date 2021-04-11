package Objects;

import java.util.ArrayList;
import java.util.Arrays;

public class Chatbots {

    private static ArrayList<Chatbot> chatbots = new ArrayList<>();

    public void initialize() {
        Chatbot moderatorBot = new Chatbot("Moderator");
        Chatbot helperbot = new Chatbot("Caroline");
        Chatbot neutralbot = new Chatbot("Hannah");
        Chatbot negativebot = new Chatbot("Amalie");

        chatbots.add(moderatorBot);
        chatbots.add(helperbot);
        chatbots.add(neutralbot);
        chatbots.add(negativebot);
    }

    // tar inn meldingen fra bruker og finner ut hva som skal svares
    public String respond(String msg){

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

        return getResponse(action_verb, greeting);


    }

    private String getResponse(String action, String greeting){
        String response;

        // no greeting but verb
        if(!action.equals("") & greeting.equals("")){
            response = "cool. Let's " + action;
        }
        // no greeting nor verb
        else if(action.equals("") & greeting.equals("")){
            response = "";
        }
        // both greeting and verb
        else if(!action.equals("") & !greeting.equals("")){
            response = "Hello there! I would love to " + action;
        }
        else {
            response = "HI! How are you?";
        }

        return response;
    }


}
