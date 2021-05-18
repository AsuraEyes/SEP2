package client.model.state;

import shared.transferobjects.Administrator;

import java.io.Serializable;

public class AdministratorState implements LoginState, Serializable {
    private final String USERTYPE = "Administrator";
    private Administrator administrator;

    public AdministratorState(Administrator administrator){
        System.out.println("Chnaged to administrator "+administrator.getUsername());
        this.administrator = administrator;
    }

    @Override
    public String getUsertype() {
        return USERTYPE;
    }

    @Override
    public String getUsername() {
        return administrator.getUsername();
    }
}
