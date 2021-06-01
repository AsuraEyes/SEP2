package shared.transferobjects;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A class that creates and handles Warning.
 */
public class Warning implements Serializable
{
    private String administratorFrom;
    private int memberTo;
    private String text;
    private Date timeStamp;

    /**
     * Constructor initializing fields.
     *
     * @param administratorFrom Username of administrator that sent a warning.
     * @param memberTo          ID of member that got warned.
     * @param text              Content of warning that was sent.
     * @param timeStamp         The time when warning was sent.
     */
    public Warning(String administratorFrom, int memberTo, String text, Date timeStamp) {
        this.administratorFrom = administratorFrom;
        this.memberTo = memberTo;
        this.text = text;
        this.timeStamp = timeStamp;
    }

    /**
     * Gets administrator username.
     *
     * @return returns String type of administrator's  username.
     */
    public String getAdministratorFrom() {
        return administratorFrom;
    }

    /**
     * Gets member that was warned.
     *
     * @return returns int type of Member's ID that was warned.
     */
    public int getMemberTo() {
        return memberTo;
    }

    /**
     * Gets content of warning.
     *
     * @return returns String type of warning message that was sent.
     */
    public String getText() {
        return text;
    }

    /**
     * Sets content of warning.
     *
     * @param text String type of warning message that was sent.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets exact time when warning was sent.
     *
     * @return returns Date type of time when warning was sent.
     */
    public Date getTimeStamp() {
        return timeStamp;
    }

    /**
     * Gets warning and all its content as a String.
     * @return returns information about who sent a warning(administrator's username) and its content and date when it was sent.
     */
    public String toString(){
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String strDate = dateFormat.format(timeStamp);
        return strDate + " " +  ": " + administratorFrom + ": " + text;
    }
}
