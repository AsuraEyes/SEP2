package client.model.state;

import shared.transferobjects.Member;

public class MemberState implements LoginState{
    private final String USERTYPE = "Member";
    private Member member;

    public MemberState(Member member){
        System.out.println("Changed to member "+member.getUsername());
        this.member = member;
    }

    @Override
    public String getUsertype() {
        System.out.println("asked for member");
        return USERTYPE;
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }
}
