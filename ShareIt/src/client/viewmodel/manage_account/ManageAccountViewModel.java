package client.viewmodel.manage_account;

import client.model.ShareItModel;
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

    public String getMemberUsername(){
        usernameLabel.setValue(shareItModel.getMemberUsername());
        System.out.println("Get member username view member VM : "+shareItModel.getMemberUsername());
        Member member = shareItModel.getMemberByUsername(shareItModel.getMemberUsername());
        locationLabel.setValue(member.getAddressCity());
        ratingLabel.setValue(String.valueOf(member.getAverageReview()));
        addressLabel.setValue(member.getAddressStreet() + ", " + member.getAddressNo());
        contactLabel.setValue(member.getPhoneNo() + "\n" + member.getEmailAddress());
        otherInformationLabel.setValue(member.getOtherInformation());
        return shareItModel.getMemberUsername();
    }
    public void setMemberUsername(){
        shareItModel.setMemberUsername(usernameLabel.getValue());
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
