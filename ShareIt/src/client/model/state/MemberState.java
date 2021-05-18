package client.model.state;

import shared.transferobjects.Member;

import java.io.Serializable;

public class MemberState implements LoginState, Serializable {
    private final String USERTYPE = "Member";
    private String username;

    public MemberState(String username){
        System.out.println("Changed to member "+username);
        this.username = username;
    }

    @Override
    public String getUsertype() {
        System.out.println("asked for member");
        return USERTYPE;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
