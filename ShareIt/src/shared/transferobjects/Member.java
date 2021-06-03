package shared.transferobjects;

import java.io.Serializable;
/**
 * A class that handles Members information.
 */
public class Member implements Serializable
{
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
    /**
     * Constructor initializing fields.
     *
     * @param id                  Id of the member that is assigned to him automatically when creating account.
     * @param username            Username of members choice while creating account.
     * @param password            Password of members choice while creating account.
     * @param emailAddress        Email address provided by member while creating account.
     * @param phoneNo             Phone number provided by member while creating account.
     * @param otherInformation    Other information provided by member while creating account.
     * @param address_street      The address street that the member is living on.
     * @param address_no          The address number of a street that the member is living on.
     * @param address_postal_code The postal code of a place that the member is living on.
     * @param address_city        The name of the city that the member is living in.
     * @param averageReview       An average of reviews that the member has.
     */
  public Member(int id, String username, String password, String emailAddress,
      String phoneNo, String otherInformation, String address_street,
      String address_no, int address_postal_code, String address_city,
      float averageReview)
  {
    this.id = id;
    this.username = username;
    this.password = password;
    if(emailAddress == null){
      this.emailAddress = "";
    }
    else{
      this.emailAddress = emailAddress;
    }
    if(otherInformation == null){
      this.otherInformation = "";
    }
    else{
      this.otherInformation = otherInformation;
    }

    if(phoneNo == null){
      this.phoneNo = "";
    }
    else{
      this.phoneNo = phoneNo;
    }

    this.addressStreet = address_street;
    this.addressNo = address_no;
    this.addressPostalCode = address_postal_code;
    this.addressCity = address_city;
    this.averageReview = averageReview;
  }
    /**
     * Constructor initializing fields.
     *
     * @param id Id of the member that is assigned to him automatically when creating account.
     * @param username Username of the member
     */
  public Member(int id, String username)
  {
    this.id = id;
    this.username = username;
  }

  /**
   * Constructor initializing fields.
   *
   * @param id Id of the member that is assigned to him automatically when creating account.
   */
  public Member(int id)
  {
    this.id = id;
  }
    /**
     * Gets id of the Member.
     *
     * @return returns int type of Member's Id.
     */
  public int getId()
  {
    return id;
  }
    /**
     * Sets id of the Member.
     *
     * @param id int type of Member's Id.
     */
  public void setId(int id)
  {
    this.id = id;
  }
    /**
     * Gets Member's username.
     *
     * @return returns String type of Member's username.
     */
  public String getUsername()
  {
    return username;
  }
    /**
     * Gets Member's username.
     *
     * @param username String type of Member's username.
     */
  public void setUsername(String username)
  {
    this.username = username;
  }
    /**
     * Gets Member's password.
     *
     * @return returns String type of Member's password.
     */
  public String getPassword()
  {
    return password;
  }
    /**
     * Sets Member's password.
     *
     * @param password String type of Member's password.
     */
  public void setPassword(String password)
  {
    this.password = password;
  }
    /**
     * Gets Member's email address.
     *
     * @return returns String type of Member's email address.
     */
  public String getEmailAddress()
  {
    return emailAddress;
  }
    /**
     * Gets Member's other information.
     *
     * @return returns String type of Member's other information.
     */
  public String getOtherInformation()
  {
    return otherInformation;
  }
    /**
     * Sets Member's other information.
     *
     * @param otherInformation String type of Member's other information.
     */
  public void setOtherInformation(String otherInformation)
  {
    this.otherInformation = otherInformation;
  }
    /**
     * Gets Member's phone number.
     *
     * @return returns String type of Member's phone number(String type because there is possibility of occurrence the phone number's prefix with plus at the beginning).
     */
  public String getPhoneNo()
  {
    return phoneNo;
  }

    /**
     * Gets Member's address street.
     *
     * @return returns String type of street where Member lives.
     */
  public String getAddressStreet()
  {
    return addressStreet;
  }
    /**
     * Gets Member's address number.
     *
     * @return returns String type of street number where Member lives(String in case of occurrence numbers with letters like: 6A).
     */
  public String getAddressNo()
  {
    return addressNo;
  }
    /**
     * Gets Member's address postal code.
     *
     * @return returns int type of place where Member lives.
     */
  public int getAddressPostalCode()
  {
    return addressPostalCode;
  }
    /**
     * Gets Member's city.
     *
     * @return returns String type of Member's city.
     */
  public String getAddressCity()
  {
    return addressCity;
  }
    /**
     * Gets Member's average review.
     *
     * @return returns float type of Member's average of reviews.
     */
  public float getAverageReview()
  {
    return averageReview;
  }

}
