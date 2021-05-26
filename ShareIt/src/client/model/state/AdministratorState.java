package client.model.state;

import java.io.Serializable;

public class AdministratorState implements LoginState, Serializable {
    private final String USERTYPE = "Administrator";
    private String username;

    public AdministratorState(String username){
        this.username = username;
    }

    @Override
    public String getUsertype() {
        return USERTYPE;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
