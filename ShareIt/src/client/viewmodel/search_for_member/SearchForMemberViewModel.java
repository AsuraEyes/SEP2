package client.viewmodel.search_for_member;

import client.model.ShareItModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import shared.transferobjects.Member;

import java.io.IOException;
import java.util.List;

/**
 * A class that holds and manages data from the SearchForMember view.
 */
public class SearchForMemberViewModel {
    private ShareItModel model;
    private final StringProperty searchField;

    /**
     * Instantiates a new SearchForMemberViewModel.
     *
     * @param model The model that this ViewModel uses
     */
    public SearchForMemberViewModel(ShareItModel model)
    {
        this.model = model;
        searchField = new SimpleStringProperty();
    }

    /**
     * Gets searchField.
     *
     * @return returns searchField input
     */
    public StringProperty getSearchField() {
        return searchField;
    }

    /**
     * Sets search field.
     */
    public void setSearchField(){
        searchField.setValue(model.getSearchText());
    }

    /**
     * After Search button have been pressed this method sends data to the model.
     *
     * @return returns a list of Members dependable on input
     */
    public List<Member> onSearchButtonPressed() {
        return model.checkSearchForMember(searchField.getValue());
    }

    /**
     * Gets list of Members.
     *
     * @return returns list of members
     */
    public List<Member> getMembersList() {
        return model.getMembersList();
    }

    /**
     * Sets member username.
     *
     * @param source (?)
     */
    public void setMemberUsername(Object source) {
        if(source instanceof VBox){
            VBox vBox = (VBox) source;
            if(vBox.getChildren().get(0) instanceof Label)
            {
                Label label = (Label) vBox.getChildren().get(0);
                String username = label.getText();
                model.setMemberUsername(username.substring(10));
            }
        }
    }
}
