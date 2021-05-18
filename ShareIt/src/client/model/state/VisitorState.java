package client.model.state;

public class VisitorState implements LoginState{
    private final String USERTYPE = "Visitor";

    public VisitorState(){
        System.out.println("Changed to visitor");
    }

    @Override
    public String getUsertype() {
        return USERTYPE;
    }

    @Override
    public String getUsername() {
        return null;
    }
}
