package it.xpug.kata.birthday_greetings;

public class JSONUtilites {

    private final String json;

    public JSONUtilites(String json) {
        this.json = json;
    }

    public String extractValue(String key) {
        String[] split = json.split("\"" + key + "\": \"");
        String firstSeparator = "\"";
        if (split[1].contains(firstSeparator))
            split = split[1].split(firstSeparator);
        String returnValue = split[0];
        return returnValue;
    }
}