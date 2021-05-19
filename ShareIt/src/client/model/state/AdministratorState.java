package client.model.state;

import shared.transferobjects.Administrator;

import java.io.Serializable;

public class AdministratorState implements LoginState, Serializable {
    private final String USERTYPE = "Administrator";
    private String username;

    public AdministratorState(String username){
        System.out.println("Chnaged to administrator "+username);
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
