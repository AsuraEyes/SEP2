package server.model.database.member;

import shared.transferobjects.Member;

import java.sql.SQLException;
import java.util.List;
/**
 * The interface of Member Data Access Object.
 */
public interface MemberDAO {
    /**'
     * Creates new member by putting data provided by user
     * @param username username input by user while creating new account
     * @param password password input by user while creating new account
     * @param emailAddress email address input by user while creating new account (can be null if phone number was put)
     * @param phoneNumber phone number input by user while creating new account (can be null if email address was put)
     * @param otherInformation other users information input by user while creating new account
     * @param addressStreet street of the place where user lives input by user while creating new account
     * @param addressNo street's number of the place where user lives input by user while creating new account
     * @param addressPostalCode postal code of the place where user lives input by user while creating new account
     * @param addressCity city where user lives chosen from the list of possible cities by user while creating new account
     * @return returns new object of Member with data which was provided by user while creating new account
     * @throws SQLException
     */
    Member create(String username, String password, String emailAddress, String phoneNumber, String otherInformation, String addressStreet, String addressNo, int addressPostalCode, String addressCity) throws SQLException;
    /**
     * Gets all the possible Members with given value
     * @param username the value that has will be checked on database
     * @return returns list of Members matched with given value
     * @throws SQLException
     */
    List<Member> readByUsername(String username) throws SQLException;
    /**
     * Checks if the username is unique(not in database)
     * @param username username that has to be checked for being unique
     * @return returns true if it is unique or false if it is already in database
     * @throws SQLException
     */
    boolean uniqueUsername(String username) throws SQLException;
    /**
     * Checks ID of member based on his username
     * @param username username that has to be checked for ID
     * @return returns ID of checked member
     * @throws SQLException
     */
    int readIdByUsername(String username) throws SQLException;
    /**
     * Updates member data based on data input provided by user
     * @param username username cannot be changed
     * @param password new password input by user(if it was changed)
     * @param emailAddress new email address input by user(if it was changed)
     * @param phoneNumber new phone number input by user(if it was changed)
     * @param otherInformation new other information input by user(if it was changed)
     * @param addressStreet new street address input by user(if it was changed)
     * @param addressNo new street address input by user(if it was changed)
     * @param addressPostalCode new postal code input by user(if it was changed)
     * @param addressCity new city chosen from the list of possible cities by user(if it was changed)
     * @throws SQLException
     */
    void update(String username, String password, String emailAddress, String phoneNumber, String otherInformation, String addressStreet, String addressNo, int addressPostalCode, String addressCity) throws SQLException;
    /**
     * Deletes member from database
     * @param member Member object that is about to be deleted
     * @return returns true if deleting process was successful
     * @throws SQLException
     */
    boolean delete(Member member) throws SQLException;
    /**
     * Gets member object based on given ID
     * @param id ID of the member that information is needed
     * @return returns member object with all his information
     * @throws SQLException
     */
    Member getMemberById(int id) throws SQLException;
    /**
     * Checks to whom given username and password belongs to
     * @param username username that has to be checked
     * @param password password that has to be checked
     * @return returns username if the data matches from given username and password(if username and password belongs to an administrator it returns administrator data)
     * @throws SQLException
     */
    String checkLogInCredentials(String username, String password) throws SQLException;
    /**
     * Gets member based on given username
     * @param username username of the member that information is needed
     * @return returns Member object with all his information
     * @throws SQLException
     */
    Member getMemberByUsername(String username) throws SQLException;
    /**
     * Gets all the possible Members
     * @return returns a list of all Members that are stored in the database
     * @throws SQLException
     */
    List<Member> readMembers() throws SQLException;
  List<Member> readMembersIdsAndUsernames() throws SQLException;
}
