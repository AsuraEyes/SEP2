package client.model.state;

public class VisitorState implements LoginState{
    private final String USERTYPE = "Visitor";

    @Override
    public String getUsertype() {
        return USERTYPE;
    }

    @Override
    public String getUsername() {
        return null;
    }
}
