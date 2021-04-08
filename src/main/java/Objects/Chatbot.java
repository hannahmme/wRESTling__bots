package Objects;

public class Chatbot {
    private static String botname;
    private static String botFunction;

    public Chatbot(String botname, String botFunction) {
        this.botname = botname;
        this.botFunction = botFunction;
    }

    public static String getBotname() {
        return botname;
    }

    public static void setBotname(String botname) {
        Chatbot.botname = botname;
    }

    public static String getBotFunction() {
        return botFunction;
    }

    public static void setBotFunction(String botFunction) {
        Chatbot.botFunction = botFunction;
    }
}
