package client.model.data_check;

import client.model.database.member.MemberDAO;
import client.model.database.member.MemberDAOImpl;
import org.controlsfx.control.Notifications;

import java.sql.SQLException;

public class DataCheckMember {

    //does not check for length of ANY of the given Strings
    //mention this in project report

    private MemberDAO memberDAO;
    private String username;
    private String password;
    private String passwordAgain;
    private String email;
    private String phone1;
    private String phone2;
    private String postalCode;
    private int postalCodeNb;

    public DataCheckMember() throws SQLException {
        memberDAO = new MemberDAOImpl();
    }

    public void checkData(String username, String password, String passwordAgain, String email, String otherInformation, String phone1, String phone2, String street, String streetNumber, String postalCode, String city) {
        this.username = username;
        this.password = password;
        this.passwordAgain = passwordAgain;
        this.email = email;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.postalCode = postalCode;

        if(matchingPasswords()){
            if(uniqueUsername()){
                if(oneContactInformationGiven()){
                    if(postalCodeIsNumber()){
                        try{
                            memberDAO.create(username, password, email, otherInformation, street, streetNumber, postalCodeNb, city);
                            System.out.println("successful");
                        }
                        catch (SQLException e){

                        }
                    }
                    else{
                        System.out.println("postal code not number");
                    }
                }
                else{
                    System.out.println("no contact");
                }
            }
            else {
                System.out.println("Username not unique");
            }
        }
        else{
            System.out.println("password not matching");
        }

    }

    private boolean matchingPasswords(){
        if(password.equals(passwordAgain)){
            return true;
        }
        return false;
    }

    private boolean uniqueUsername(){
        try{
            return memberDAO.uniqueUsername(username);
        }
        catch (SQLException e){
            return false;
        }
    }

    private boolean oneContactInformationGiven(){
        if(phone1 != null){
            if(!(phone1.trim().equals("") && phone1.isBlank() && phone1.isEmpty())){
                return true;
            }
        }
        if(phone2 != null){
            if(!(phone2.trim().equals("") || phone2.isBlank() || phone2.isEmpty())){
                return true;
            }
        }
        if(email != null){
            return !(email.trim().equals("") || email.isBlank() || email.isEmpty());
        }
        return false;
    }

    private boolean postalCodeIsNumber(){
        try{
            this.postalCodeNb = Integer.parseInt(postalCode);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

}
