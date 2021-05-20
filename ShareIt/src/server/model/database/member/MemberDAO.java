package server.model.database.member;

import shared.transferobjects.Member;

import java.sql.SQLException;
import java.util.List;

public interface MemberDAO {
    Member create(String username, String password, String emailAddress, String phoneNumber, String otherInformation, String addressStreet, String addressNo, int addressPostalCode, String addressCity) throws SQLException;
    List<Member> readByUsername(String username) throws SQLException;
    boolean uniqueUsername(String username) throws SQLException;
    int readIdByUsername(String username) throws SQLException;
    void update(Member member) throws SQLException;
    void delete(String username) throws SQLException;
    Member getMemberById(int id) throws SQLException;
    String checkLogInCredentials(String username, String password) throws SQLException;
    Member getMemberByUsername(String username) throws SQLException;
}
