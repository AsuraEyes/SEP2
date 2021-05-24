package shared.transferobjects;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Message implements Serializable
{
  private int memberFrom;
  private String usernameFrom;
  private int memberTo;
  private String text;
  private Date timeStamp;

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

  public void setUsernameFrom(String usernameFrom)
  {
    this.usernameFrom = usernameFrom;
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

  public void setTimeStamp(Date timeStamp)
  {
    this.timeStamp = timeStamp;
  }

  public String toString(){
    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    String strDate = dateFormat.format(timeStamp);
    return strDate + " " +  ": " + memberFrom + ": " + text;
  }
}
