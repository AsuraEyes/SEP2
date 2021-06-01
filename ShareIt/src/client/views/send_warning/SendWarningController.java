package client.views.send_warning;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.send_warning.SendWarningViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * A class that manages an interface and handle interactions in SendWarning view
 */
public class SendWarningController {
    @FXML private Label toUsernameLabel;
    @FXML private TextArea textChatArea;
    @FXML private TextField inputTextChatField;

    private ViewHandler viewHandler;
    private SendWarningViewModel sendWarningViewModel;

    /**
     * Init.
     *
     * @param viewHandler      the view handler
     * @param viewModelFactory the view model factory
     * @throws IOException the io exception
     */
    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) throws IOException {
        this.viewHandler = viewHandler;
        textChatArea.clear();
        sendWarningViewModel = viewModelFactory.getSendWarningViewModel();
        toUsernameLabel.textProperty().bind(sendWarningViewModel.getUsername());
        inputTextChatField.textProperty().bindBidirectional(sendWarningViewModel.getInputTextChat());
        sendWarningViewModel.getMember();
        textChatArea.textProperty().bind(sendWarningViewModel.getWarnings());
        sendWarningViewModel.loadLogs();
    }

    /**
     * Changes a view when button was pressed.
     *
     * @throws IOException
     */
    public void onGoBack() throws IOException {
        textChatArea.clear();
        viewHandler.setView(viewHandler.menu(), viewHandler.viewMemberProfile());
    }

    /**
     * Uses a method from viewModel.
     */
    public void onSend() {
        sendWarningViewModel.sendWarning();
        inputTextChatField.clear();
    }
}
