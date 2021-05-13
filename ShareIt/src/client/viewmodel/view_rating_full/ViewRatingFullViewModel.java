package client.viewmodel.view_rating_full;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ViewRatingFullViewModel {
    private final StringProperty searchField;
    private final StringProperty userNameLabel;
    private final StringProperty locationLabel;
    private final StringProperty ratingLabel;
    private final StringProperty addressLabel;
    private final StringProperty contactLabel;
    private final StringProperty otherInfoLabel;

    private final StringProperty commenterNameLabel;
    private final StringProperty commenterRateLabel;
    private final StringProperty commentLabel;

    public ViewRatingFullViewModel() {
        searchField = new SimpleStringProperty("Search");
        userNameLabel = new SimpleStringProperty();
        locationLabel = new SimpleStringProperty();
        ratingLabel = new SimpleStringProperty();
        addressLabel = new SimpleStringProperty();
        contactLabel = new SimpleStringProperty();
        otherInfoLabel = new SimpleStringProperty();
        commenterNameLabel = new SimpleStringProperty();
        commenterRateLabel = new SimpleStringProperty();
        commentLabel = new SimpleStringProperty();
    }

    public StringProperty getSearchField(){
        return searchField;
    }

    public StringProperty getUserNameLabel(){
        return userNameLabel;
    }

    public StringProperty getLocationLabel(){
        return locationLabel;
    }

    public StringProperty getRatingLabel(){
        return ratingLabel;
    }

    public StringProperty getAddressLabel(){
        return addressLabel;
    }

    public StringProperty getContactLabel(){
        return contactLabel;
    }

    public StringProperty getOtherInfoLabel(){
        return otherInfoLabel;
    }

    public StringProperty getCommenterNameLabel(){
        return commenterNameLabel;
    }

    public StringProperty getCommenterRateLabel(){
        return commenterRateLabel;
    }

    public StringProperty getCommentLabel(){
        return commentLabel;
    }
}
