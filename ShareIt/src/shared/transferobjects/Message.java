package shared.transferobjects;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A class that handles Messages information.
 */
public class Message implements Serializable {
    private final Date timeStamp;
    private int memberFrom;
    private String usernameFrom;
    private int memberTo;
    private String text;

    /**
     * Constructor initializing fields.
     *
     * @param memberFrom ID of who is sending a message.
     * @param memberTo   ID of who will receive a message.
     * @param text       Content of message that was sent.
     * @param timeStamp  The time when message was sent.
     */
    public Message(int memberFrom, int memberTo, String text, Date timeStamp) {
        this.memberFrom = memberFrom;
        this.memberTo = memberTo;
        this.text = text;
        this.timeStamp = timeStamp;
    }

    /**
     * Constructor initializing fields.
     *
     * @param timeStamp    The time when message was sent.
     * @param usernameFrom Username of who is sending a message.(Specified by username)
     * @param text         Content of message that was sent.
     */
    public Message(Date timeStamp, String usernameFrom, String text) {
        this.timeStamp = timeStamp;
        this.usernameFrom = usernameFrom;
        this.text = text;
    }

    /**
     * Gets Member's username that sent a message.
     *
     * @return String type of Member's username who sent a message.
     */
    public String getUsernameFrom() {
        return usernameFrom;
    }

    /**
     * Gets Member's ID that sent a message.
     *
     * @return int type of Member's ID who sent a message.
     */
    public int getMemberFrom() {
        return memberFrom;
    }

    /**
     * Sets Member's ID that sent a message.
     *
     * @param memberFrom int type of Member's ID who sent a message.
     */
    public void setMemberFrom(int memberFrom) {
        this.memberFrom = memberFrom;
    }

    /**
     * Gets Member's ID that receives a message.
     *
     * @return int type of Member's ID who received a message.
     */
    public int getMemberTo() {
        return memberTo;
    }

    /**
     * Sets Member's ID that receives a message.
     *
     * @param memberTo int type of Member's ID who received a message.
     */
    public void setMemberTo(int memberTo) {
        this.memberTo = memberTo;
    }

    /**
     * Gets content of message.
     *
     * @return String type of text message that was sent.
     */
    public String getText() {
        return text;
    }

    /**
     * Sets content of message.
     *
     * @param text String type of text message that was sent.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets exact time when message was sent.
     *
     * @return Date type of time when message was sent.
     */
    public Date getTimeStamp() {
        return timeStamp;
    }

    /**
     * Gets message and all its content as a String.
     *
     * @return information about who sent a message (username) and its content, if time was set it is returned as well.
     */
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        if (timeStamp != null) {
            String strDate = dateFormat.format(timeStamp);
            return usernameFrom + ": " + strDate + " : " + text;
        }
        return usernameFrom + ": " + text;
    }
}
