package server.model.data_check;

import server.model.database.member.MemberDAOImpl;

import java.sql.SQLException;

/**
 * Class that checks data before running an instance(Member data in this case)
 */
public class DataCheckMember
{
  private String username;
  private String password;
  private String passwordAgain;
  private String email;
  private String phone;
  private String postalCode;
  private int postalCodeNb;
    /**
     * Check of the Member data before passing it into the DAO.
     *
     * @param username         Username of members choice while creating account.
     * @param password         Password of members choice while creating account.
     * @param passwordAgain    Password input once again to make sure that the mistake was not done.
     * @param email            Email address provided by member while creating account.
     * @param otherInformation Other information provided by member while creating account.
     * @param phone            Phone number provided by member while creating account.
     * @param street           The address street that the member is living on.
     * @param streetNumber     The address number of a street that the member is living on.
     * @param postalCode       The postal code of a place that the member is living on.
     * @param city             The name of the city that the member is living in.
     * @return returns String type dependable on the result.
     */
  public String checkData(String username, String password,
      String passwordAgain, String email, String otherInformation, String phone,
      String street, String streetNumber, String postalCode, String city)
  {
    this.username = username;
    this.password = password;
    this.passwordAgain = passwordAgain;
    this.email = email;
    this.phone = phone;
    this.postalCode = postalCode;

    if (matchingPasswords() && uniqueUsername() && oneContactInformationGiven()
        && postalCodeIsNumber())
    {
      try
      {
        MemberDAOImpl.getInstance()
            .create(username, password, email, phone, otherInformation, street,
                streetNumber, postalCodeNb, city);
        return "Adding successful";
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      if (!matchingPasswords())
      {
        return "Not matching passwords.";
      }
      if (!uniqueUsername())
      {
        return "This username is already taken.";
      }
      if (!oneContactInformationGiven())
      {
        return "At least one contact information has to be given.";
      }
      if (!postalCodeIsNumber())
      {
        return "Postal code has to be a number.";
      }
    }
    return "Ooops, something went wrong!!";
  }
    /**
     * Check of the updated Member data before passing it into the DAO.
     *
     * @param username         Username of members choice while creating account. (It cannot be updated)
     * @param password         Updated password of members choice while creating account.
     * @param passwordAgain    Password input once again to make sure that the mistake was not done.
     * @param email            Updated email address provided by member while creating account.
     * @param otherInformation Updated other information provided by member while creating account.
     * @param phone            Updated phone number provided by member while creating account.
     * @param street           Updated address street that the member is living on.
     * @param streetNumber     Updated address number of a street that the member is living on.
     * @param postalCode       Updated postal code of a place that the member is living on.
     * @param city             Updated name of the city that the member is living in.
     * @return returns String type dependable on the result.
     */
  public String updateCheckData(String username, String password,
      String passwordAgain, String email, String otherInformation, String phone,
      String street, String streetNumber, String postalCode, String city)
  {
    this.username = username;
    this.password = password;
    this.passwordAgain = passwordAgain;
    this.email = email;
    this.phone = phone;
    this.postalCode = postalCode;

    if (matchingPasswords() && oneContactInformationGiven()
        && postalCodeIsNumber())
    {
      try
      {
        MemberDAOImpl.getInstance()
            .update(username, password, email, phone, otherInformation, street,
                streetNumber, postalCodeNb, city);
        return "Edit successful";
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      if (!matchingPasswords())
      {
        return "Not matching passwords.";
      }
      if (!oneContactInformationGiven())
      {
        return "At least one contact information has to be given.";
      }
      if (!postalCodeIsNumber())
      {
        return "Postal code has to be a number.";
      }
    }
    return "Ooops, something went wrong!!";
  }
    /**
     * Compares password with passwordAgain.
     * @return returns true if passwords are the same if not returns false
     */
  private boolean matchingPasswords()
  {
    if (password.equals(passwordAgain))
    {
      return true;
    }
    return false;
  }
    /**
     * Checks if username already exist in the database
     * @return returns false if username does not exist in the database yet
     */
  private boolean uniqueUsername()
  {
    try
    {
      return MemberDAOImpl.getInstance().uniqueUsername(username);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return false;
  }
    /**
     * Checks if at least one contact information was given
     * @return returns false if both phone and email were not given
     */
  private boolean oneContactInformationGiven()
  {
    if (phone != null)
    {
      if (!(phone.trim().equals("") && phone.isBlank() && phone.isEmpty()))
      {
        return true;
      }
    }
    if (email != null)
    {
      return !(email.trim().equals("") || email.isBlank() || email.isEmpty());
    }
    return false;
  }
    /**
     * Checks if given postal code is a number
     * @return returns true if object is returning a integer, false if not
     */
  private boolean postalCodeIsNumber()
  {
    try
    {
      this.postalCodeNb = Integer.parseInt(postalCode);
      return true;
    }
    catch (NumberFormatException e)
    {
      return false;
    }
  }

}
