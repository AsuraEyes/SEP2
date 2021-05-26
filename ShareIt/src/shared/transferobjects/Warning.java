package shared.transferobjects;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Warning implements Serializable
{
    private String administratorFrom;
    private int memberTo;
    private String text;
    private Date timeStamp;

    public Warning(String administratorFrom, int memberTo, String text, Date timeStamp) {
        this.administratorFrom = administratorFrom;
        this.memberTo = memberTo;
        this.text = text;
        this.timeStamp = timeStamp;
    }

    public String getAdministratorFrom() {
        return administratorFrom;
    }

    public int getMemberTo() {
        return memberTo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String toString(){
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String strDate = dateFormat.format(timeStamp);
        return strDate + " " +  ": " + administratorFrom + ": " + text;
    }
}
