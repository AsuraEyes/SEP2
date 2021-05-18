package client.model.state;

import shared.transferobjects.Member;

public class MemberState implements LoginState{
    private final String USERTYPE = "Member";
    private Member member;

    public MemberState(Member member){
        this.member = member;
    }

    @Override
    public String getUsertype() {
        return USERTYPE;
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }
}
