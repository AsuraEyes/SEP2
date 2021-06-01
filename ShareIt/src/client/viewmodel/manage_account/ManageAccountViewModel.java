package client.viewmodel.manage_account;

import client.model.ShareItModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.InfoOverlay;
import shared.transferobjects.Member;
import shared.transferobjects.Rental;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * A class that holds and manages data from the ManageAccount view.
 */
public class ManageAccountViewModel {
    private ShareItModel shareItModel;
    private final SimpleStringProperty usernameLabel;
    private final SimpleStringProperty locationLabel;
    private final SimpleStringProperty ratingLabel;
    private final SimpleStringProperty addressLabel;
    private final SimpleStringProperty contactLabel;
    private final SimpleStringProperty otherInformationLabel;

    /**
     * Instantiates a new Manage account view model.
     *
     * @param shareItModel The model that this ViewModel uses
     */
    public ManageAccountViewModel(ShareItModel shareItModel) {
        this.shareItModel = shareItModel;
        usernameLabel = new SimpleStringProperty();
        locationLabel = new SimpleStringProperty();
        ratingLabel = new SimpleStringProperty();
        addressLabel = new SimpleStringProperty();
        contactLabel = new SimpleStringProperty();
        otherInformationLabel = new SimpleStringProperty();
    }

    /**
     * Sets profile.
     */
    public void setProfile(){
        Member member = shareItModel.getMemberByUsername(shareItModel.getLoggedInUsername());
        usernameLabel.setValue(member.getUsername());
        locationLabel.setValue(member.getAddressCity());
        ratingLabel.setValue(String.valueOf(member.getAverageReview()));
        addressLabel.setValue(member.getAddressStreet() + ", " + member.getAddressNo());
        contactLabel.setValue(member.getPhoneNo() + "\n" + member.getEmailAddress());
        otherInformationLabel.setValue(member.getOtherInformation());
    }

    /**
     * Gets rentals of member list.
     *
     * @return returns Member's rentals
     * @throws RemoteException
     */
    public ArrayList<Rental> getRentalsOfMemberList() throws RemoteException {
        return shareItModel.getRentalsOfMemberList(shareItModel.getLoggedInUsername());
    }

    /**
     * Gets selected rental.
     *
     * @param object the object
     * @throws RemoteException
     */
    public void getRental(Object object) throws RemoteException {
        if(object instanceof StackPane){
            StackPane stackPane = (StackPane) object;
            if(stackPane.getChildren().get(0) instanceof InfoOverlay) {
                InfoOverlay infoOverlay = (InfoOverlay) stackPane.getChildren().get(0);
                if(infoOverlay.getContent() instanceof ImageView) {
                    ImageView imageView = (ImageView) infoOverlay.getContent();
                    for (int i = 0; i < getRentalsOfMemberList().size(); i++) {
                        if(imageView.getId().equals(String.valueOf(getRentalsOfMemberList().get(i).getId()))) {
                            shareItModel.sendSelectedRental(getRentalsOfMemberList().get(i));
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * Gets usernameLabel.
     *
     * @return returns usernameLabel input
     */
    public StringProperty getUsernameLabel()
    {
        return usernameLabel;
    }

    /**
     * Gets locationLabel.
     *
     * @return returns locationLabel input
     */
    public StringProperty getLocationLabel()
    {
        return locationLabel;
    }

    /**
     * Gets ratingLabel.
     *
     * @return returns ratingLabel input
     */
    public StringProperty getRatingLabel()
    {
        return ratingLabel;
    }

    /**
     * Gets addressLabel.
     *
     * @return returns addressLabel input
     */
    public StringProperty getAddressLabel()
    {
        return addressLabel;
    }

    /**
     * Gets contactLabel.
     *
     * @return returns contactLabel input
     */
    public StringProperty getContactLabel()
    {
        return contactLabel;
    }

    /**
     * Gets otherInformationLabel.
     *
     * @return returns otherInformationLabel input
     */
    public StringProperty getOtherInformationLabel()
    {
        return otherInformationLabel;
    }

    /**
     * Sets member.
     */
    public void setMember() {
        shareItModel.setMemberUsername(usernameLabel.getValue());
    }
}
