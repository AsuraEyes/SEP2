package shared.transferobjects;

import java.io.Serializable;

/**
 * A class that creates and handles Reports.
 */
public class Report implements Serializable
{
  private String commentary;
  private int memberFrom;
  private int memberTo;
  private String usernameFrom;
  private String usernameTo;

  /**
   * Constructor initializing fields.
   *
   * @param commentary Commentary that was added while reporting a member.
   * @param memberFrom Member's ID that reported another member.
   * @param memberTo   Member's ID that was reported.
   */
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
  /**
   * Gets commentary that Member gave.
   *
   * @return returns String of commentary.
   */
  public String getCommentary()
  {
    return commentary;
  }

  /**
   * Gets member that was reporting.
   *
   * @return returns int type of Member's ID that reported other member.
   */
  public int getMemberFrom()
  {
    return memberFrom;
  }

  /**
   * Gets member that was reported.
   *
   * @return returns int type of Member's ID that was reported.
   */
  public int getMemberTo()
  {
    return memberTo;
  }
}
