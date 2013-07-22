package ua.triple.game;

public class Element {

    private boolean joinable;
    private String type;

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
