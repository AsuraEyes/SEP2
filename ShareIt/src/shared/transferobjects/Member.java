package shared.transferobjects;

import java.io.Serializable;

public class Member implements Serializable {
    private int id;
    private String username;
    private String password;
    private String emailAddress;
    private String otherInformation;
    private String phoneNo;
    private String addressStreet;
    private String addressNo;
    private int addressPostalCode;
    private String addressCity;
    private float averageReview;


    public Member(int id, String username, String password, String emailAddress,String phoneNo, String otherInformation, String address_street, String address_no, int address_postal_code, String address_city, float averageReview) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.otherInformation = otherInformation;
        this.phoneNo = phoneNo;
        this.addressStreet = address_street;
        this.addressNo = address_no;
        this.addressPostalCode = address_postal_code;
        this.addressCity = address_city;
        this.averageReview = averageReview;
    }
    
    public Member(int id){this.id = id;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getOtherInformation() {
        return otherInformation;
    }

    public void setOtherInformation(String otherInformation) {
        this.otherInformation = otherInformation;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public String getAddressNo() {
        return addressNo;
    }

    public int getAddressPostalCode() {
        return addressPostalCode;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public float getAverageReview() {
        return averageReview;
    }


}
