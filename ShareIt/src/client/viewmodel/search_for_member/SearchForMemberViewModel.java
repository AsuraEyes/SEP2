package client.viewmodel.search_for_member;

import client.model.ShareItModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.InfoOverlay;
import shared.transferobjects.Member;
import shared.transferobjects.Rental;

import java.util.List;

public class SearchForMemberViewModel {
    private ShareItModel model;
    private final StringProperty searchField;

    public SearchForMemberViewModel(ShareItModel model)
    {
        this.model = model;
        searchField = new SimpleStringProperty();
    }

    public StringProperty getSearchField() {
        return searchField;
    }

    public void setSearchField(){
        searchField.setValue(model.getSearchText());
        System.out.println("View model: " + searchField.getValue());
    }

    public List<Member> onSearchButtonPressed() {
        return model.checkSearchForMember(searchField.getValue());
    }

    public List<Member> getMembersList() {
        return model.getMembersList();
    }

    public void setMemberUsername(Object source) {
        System.out.println("we get here");
        if(source instanceof VBox){
            VBox vBox = (VBox) source;
            if(vBox.getChildren().get(0) instanceof Label)
            {
                Label label = (Label) vBox.getChildren().get(0);
                String username = label.getText();
                System.out.println("Hurray we get here :"+username.substring(10));
                model.setMemberUsername(username.substring(10));
            }
        }
    }
}
