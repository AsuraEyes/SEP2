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

  private boolean matchingPasswords()
  {
      return password.equals(passwordAgain);
  }

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
