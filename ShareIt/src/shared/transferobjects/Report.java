package shared.transferobjects;

import java.io.Serializable;

public class Report implements Serializable
{
  private String commentary;
  private int memberFrom;
  private int memberTo;
  private String usernameFrom;
  private String usernameTo;

  public Report(String commentary, int memberFrom, int memberTo)
  {
    this.commentary = commentary;
    this.memberFrom = memberFrom;
    this.memberTo = memberTo;
  }
  public String getUsernameFrom()
  {
    return usernameFrom;
  }

  public void setUsernameFrom(String usernameFrom)
  {
    this.usernameFrom = usernameFrom;
  }

  public String getUsernameTo()
  {
    return usernameTo;
  }

  public void setUsernameTo(String usernameTo)
  {
    this.usernameTo = usernameTo;
  }


  public String getCommentary()
  {
    return commentary;
  }

  public int getMemberFrom()
  {
    return memberFrom;
  }

  public int getMemberTo()
  {
    return memberTo;
  }
}
