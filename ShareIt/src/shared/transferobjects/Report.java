package shared.transferobjects;

import java.io.Serializable;

public class Report implements Serializable
{
  private String commentary;
  private int memberFrom;
  private int memberTo;

  public Report(String commentary, int memberFrom, int memberTo)
  {
    this.commentary = commentary;
    this.memberFrom = memberFrom;
    this.memberTo = memberTo;
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
