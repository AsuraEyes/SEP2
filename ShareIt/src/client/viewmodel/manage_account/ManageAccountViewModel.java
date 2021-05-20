package client.viewmodel.manage_account;

import client.model.ShareItModel;
import client.model.state.StateManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobjects.Member;

public class ManageAccountViewModel {
    private ShareItModel shareItModel;
    private final SimpleStringProperty usernameLabel;
    private final SimpleStringProperty locationLabel;
    private final SimpleStringProperty ratingLabel;
    private final SimpleStringProperty addressLabel;
    private final SimpleStringProperty contactLabel;
    private final SimpleStringProperty otherInformationLabel;

    public ManageAccountViewModel(ShareItModel shareItModel) {
        this.shareItModel = shareItModel;
        usernameLabel = new SimpleStringProperty();
        locationLabel = new SimpleStringProperty();
        ratingLabel = new SimpleStringProperty();
        addressLabel = new SimpleStringProperty();
        contactLabel = new SimpleStringProperty();
        otherInformationLabel = new SimpleStringProperty();
    }

    public void setProfile(){

        Member member = shareItModel.getMemberByUsername(StateManager.getInstance()
            .getUsername());
        usernameLabel.setValue(member.getUsername());
        locationLabel.setValue(member.getAddressCity());
        ratingLabel.setValue(String.valueOf(member.getAverageReview()));
        addressLabel.setValue(member.getAddressStreet() + ", " + member.getAddressNo());
        contactLabel.setValue(member.getPhoneNo() + "\n" + member.getEmailAddress());
        otherInformationLabel.setValue(member.getOtherInformation());
        //return shareItModel.getMemberUsername();
    }

    public StringProperty getUsernameLabel()
    {
        return usernameLabel;
    }

    public StringProperty getLocationLabel()
    {
        return locationLabel;
    }

    public StringProperty getRatingLabel()
    {
        return ratingLabel;
    }

    public StringProperty getAddressLabel()
    {
        return addressLabel;
    }

    public StringProperty getContactLabel()
    {
        return contactLabel;
    }

    public StringProperty getOtherInformationLabel()
    {
        return otherInformationLabel;
    }
}
