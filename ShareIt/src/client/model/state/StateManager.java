package client.model.state;

import server.model.database.member.MemberDAOImpl;

public class StateManager {
    private static LoginState currentState;
    private static StateManager instance;

    private StateManager(){
        currentState = new VisitorState();
    }

    public static synchronized StateManager getInstance(){
        if(instance == null){
            instance = new StateManager();
        }
        return instance;
    }

    public static String getUsertype(){
        return currentState.getUsertype();
    }
    public static String getUsername(){
        return currentState.getUsername();
    }
    public static void setLoginState(LoginState state){
        currentState = state;
    }
}
