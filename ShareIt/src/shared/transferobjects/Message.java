package shared.transferobjects;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message implements Serializable
{
  private int memberFrom;
  private String usernameFrom;
  private int memberTo;
  private String text;
  private final Date timeStamp;

  public Message(int memberFrom, int memberTo, String text, Date timeStamp)
  {
    this.memberFrom = memberFrom;
    this.memberTo = memberTo;
    this.text = text;
    this.timeStamp = timeStamp;
  }

  public Message(Date timeStamp, String usernameFrom, String text)
  {
    this.timeStamp = timeStamp;
    this.usernameFrom = usernameFrom;
    this.text = text;
  }

  public String getUsernameFrom()
  {
    return usernameFrom;
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

  public String getText()
  {
    return text;
  }

  public void setText(String text)
  {
    this.text = text;
  }

  public Date getTimeStamp()
  {
    return timeStamp;
  }

  public String toString()
  {
    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    if (timeStamp != null)
    {
      String strDate = dateFormat.format(timeStamp);
      return usernameFrom + ": " + strDate + " : " + text;
    }
    return usernameFrom + ": " + text;

  }
}
