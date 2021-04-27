package ground_classes;

public class Member {
    private String username;
    private String password;
    private String emailAddress;
    private String otherInformation;
    private PhoneNo phoneNo1;
    private PhoneNo phoneNo2;
    private Address address;

    public Member(String username, String password, String emailAddress, String otherInformation, PhoneNo phoneNo1, PhoneNo phoneNo2, Address address) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.otherInformation = otherInformation;
        this.phoneNo1 = phoneNo1;
        this.phoneNo2 = phoneNo2;
        this.address = address;
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

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getOtherInformation() {
        return otherInformation;
    }

    public void setOtherInformation(String otherInformation) {
        this.otherInformation = otherInformation;
    }

    public PhoneNo getPhoneNo1() {
        return phoneNo1;
    }

    public void setPhoneNo1(PhoneNo phoneNo1) {
        this.phoneNo1 = phoneNo1;
    }

    public PhoneNo getPhoneNo2() {
        return phoneNo2;
    }

    public void setPhoneNo2(PhoneNo phoneNo2) {
        this.phoneNo2 = phoneNo2;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
