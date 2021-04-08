package Objects;

public class Chatbot {
    private static String botname;

    public Chatbot(String botname) {
        this.botname = botname;
    }

    public static String getBotname() {
        return botname;
    }

    public static void setBotname(String botname) {
        Chatbot.botname = botname;
    }
}
