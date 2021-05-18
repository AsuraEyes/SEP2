package client.model.state;

import client.network.Client;
import client.network.RMIClient;
import shared.transferobjects.Administrator;

public class AdministratorState implements LoginState{
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
