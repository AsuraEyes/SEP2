package client.model.state;

import java.io.Serializable;

public class StateManager implements Serializable {
    private LoginState currentState;
    private static StateManager instance;

    private StateManager(){
        currentState = new MemberState("bob");
    }

    public static synchronized StateManager getInstance(){
        if(instance == null){
            instance = new StateManager();
        }
        return instance;
    }

    public String getUsertype(){
        return currentState.getUsertype();
    }
    public String getUsername(){
        return currentState.getUsername();
    }
    public void setLoginState(LoginState state){
        currentState = state;
    }
}
