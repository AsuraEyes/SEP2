package shared.transferobjects;

public class Report
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

  public void setCommentary(String commentary)
  {
    this.commentary = commentary;
  }

  public int getMemberFrom()
  {
    return memberFrom;
  }

  public void setMemberFrom(int memberFrom)
  {
    this.memberFrom = memberFrom;
  }

  public int getMemberTo()
  {
    return memberTo;
  }

  public void setMemberTo(int memberTo)
  {
    this.memberTo = memberTo;
  }
}
