package client.model.data_check;

import client.model.database.member.MemberDAO;
import client.model.database.member.MemberDAOImpl;
import org.controlsfx.control.Notifications;

import java.sql.SQLException;

public class DataCheckMember {

    //does not check for length of ANY of the given Strings
    //mention this in project report

    private String username;
    private String password;
    private String passwordAgain;
    private String email;
    private String phone;
    private String postalCode;
    private int postalCodeNb;

    public void checkData(String username, String password, String passwordAgain, String email, String otherInformation, String phone, String street, String streetNumber, String postalCode, String city) {
        this.username = username;
        this.password = password;
        this.passwordAgain = passwordAgain;
        this.email = email;
        this.phone = phone;
        this.postalCode = postalCode;

        if(matchingPasswords() && uniqueUsername() && oneContactInformationGiven() && postalCodeIsNumber()){
            try{
                MemberDAOImpl.getInstance().create(username, password, email, phone, otherInformation, street, streetNumber, postalCodeNb, city);
                System.out.println("successful");
            }
            catch (SQLException e){
                //
            }
        }
    }

    private boolean matchingPasswords(){
        if(password.equals(passwordAgain)){
            return true;
        }
        System.out.println("password not matching");
        return false;
    }

    private boolean uniqueUsername(){
        try{
            return MemberDAOImpl.getInstance().uniqueUsername(username);
        }
        catch (SQLException e){
            System.out.println("database issue (probably forgot to change password ;) )");
        }
        System.out.println("Username not unique");
        return false;
    }

    private boolean oneContactInformationGiven(){
        if(phone != null){
            if(!(phone.trim().equals("") && phone.isBlank() && phone.isEmpty())){
                return true;
            }
        }
        if(email != null){
            return !(email.trim().equals("") || email.isBlank() || email.isEmpty());
        }
        System.out.println("no contact");
        return false;
    }

    private boolean postalCodeIsNumber(){
        try{
            this.postalCodeNb = Integer.parseInt(postalCode);
            return true;
        }
        catch (NumberFormatException e){
            System.out.println("postal code not number");
            return false;
        }
    }

}
