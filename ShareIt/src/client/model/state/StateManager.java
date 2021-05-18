package client.model.state;

public class StateManager {
    private static LoginState currentState = new VisitorState();

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
