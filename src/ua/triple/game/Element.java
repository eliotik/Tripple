package ua.triple.game;

public class Element {

    public static ElementType baseType = new ElementType();

    private boolean joinable;
    private String type;

    public Element() {

    }

    public boolean isJoinable() {
        return joinable;
    }

    public void setJoinable(boolean joinable) {
        this.joinable = joinable;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
