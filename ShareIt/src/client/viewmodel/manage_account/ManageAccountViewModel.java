package client.viewmodel.manage_account;

import client.model.ShareItModel;
import client.model.state.StateManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.InfoOverlay;
import shared.transferobjects.Member;
import shared.transferobjects.Rental;

import java.rmi.RemoteException;
import java.util.ArrayList;

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
        System.out.println(usernameLabel.getValue());
    }

    public ArrayList<Rental> getRentalsOfMemberList() throws RemoteException {
        System.out.println("This is the username i am looking for: "+shareItModel.getMemberUsername());
        return shareItModel.getRentalsOfMemberList(shareItModel.getLoggedInUsername());
    }

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

    public void setMember() {
        shareItModel.setMemberUsername(usernameLabel.getValue());
    }
}
