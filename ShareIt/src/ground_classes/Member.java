package ground_classes;

public class Member {
    private int id;
    private String username;
    private String password;
    private String emailAddress;
    private String otherInformation;
    private String phoneNo1;
    private String phoneNo2;
    private String addressStreet;
    private String addressNo;
    private int addressPostalCode;
    private String addressCity;
    private float averageReview;


    public Member(int id, String username, String password, String emailAddress, String otherInformation, String phoneNo1, String phoneNo2, String address_street, String address_no, int address_postal_code, String address_city) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.otherInformation = otherInformation;
        this.phoneNo1 = phoneNo1;
        this.phoneNo2 = phoneNo2;
        this.addressStreet = address_street;
        this.addressNo = address_no;
        this.addressPostalCode = address_postal_code;
        this.addressCity = address_city;
        averageReview = 0;
    }
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

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getOtherInformation() {
        return otherInformation;
    }

    public void setOtherInformation(String otherInformation) {
        this.otherInformation = otherInformation;
    }

    public String getPhoneNo1() {
        return phoneNo1;
    }

    public void setPhoneNo1(String phoneNo1) {
        this.phoneNo1 = phoneNo1;
    }

    public String getPhoneNo2() {
        return phoneNo2;
    }

    public void setPhoneNo2(String phoneNo2) {
        this.phoneNo2 = phoneNo2;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String address_street) {
        this.addressStreet = address_street;
    }

    public String getAddressNo() {
        return addressNo;
    }

    public void setAddressNo(String address_no) {
        this.addressNo = address_no;
    }

    public int getAddressPostalCode() {
        return addressPostalCode;
    }

    public void setAddressPostalCode(int address_postal_code) {
        this.addressPostalCode = address_postal_code;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String address_city) {
        this.addressCity = address_city;
    }

    public float getAverageReview() {
        return averageReview;
    }

    public void setAverageReview(float average_review) {
        this.averageReview = average_review;
    }


}
