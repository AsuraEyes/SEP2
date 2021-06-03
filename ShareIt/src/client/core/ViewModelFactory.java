package client.core;

import client.viewmodel.add_rental.AddRentalViewModel;
import client.viewmodel.chat_received_messages.ChatReceivedMessagesViewModel;
import client.viewmodel.chat_write_message.ChatWriteMessageViewModel;
import client.viewmodel.create_account.CreateAccountViewModel;
import client.viewmodel.edit_account.EditAccountViewModel;
import client.viewmodel.edit_rental.EditRentalViewModel;
import client.viewmodel.log_in.LogInViewModel;
import client.viewmodel.manage_account.ManageAccountViewModel;
import client.viewmodel.manage_rentals.ManageRentalsViewModel;
import client.viewmodel.menu.MenuViewModel;
import client.viewmodel.rate_feedback.RateFeedbackViewModel;
import client.viewmodel.report_member.ReportMemberViewModel;
import client.viewmodel.search_for_member.SearchForMemberViewModel;
import client.viewmodel.search_for_rental.SearchForRentalViewModel;
import client.viewmodel.send_warning.SendWarningViewModel;
import client.viewmodel.view_member_profile.ViewMemberProfileViewModel;
import client.viewmodel.view_rating.ViewRatingViewModel;
import client.viewmodel.view_rental.ViewRentalViewModel;
import client.viewmodel.view_reported_member.ViewReportedMemberViewModel;
import client.viewmodel.view_reported_member_list.ViewReportedMemberListViewModel;
import client.viewmodel.welcome_page.WelcomePageViewModel;
/**
 * A class that creates or gets a ViewModel object, uses factory pattern.
 * All the methods are using get methods from the ModelFactory to get the needed models
 * 
 */
public class ViewModelFactory
{
  private ModelFactory modelFactory;

  private LogInViewModel logInViewModel;
  private ChatReceivedMessagesViewModel chatReceivedMessagesViewModel;
  private ChatWriteMessageViewModel chatWriteMessageViewModel;
  private AddRentalViewModel addRentalViewModel;
  private CreateAccountViewModel createAccountViewModel;
  private SearchForRentalViewModel searchForRentalViewModel;
  private SearchForMemberViewModel searchForMemberViewModel;
  private ViewRatingViewModel viewRatingViewModel;
  private ViewReportedMemberViewModel viewReportedMemberViewModel;
  private ViewReportedMemberListViewModel viewReportedMemberListViewModel;
  private WelcomePageViewModel welcomePageViewModel;
  private MenuViewModel menuViewModel;
  private ViewMemberProfileViewModel viewMemberProfileViewModel;
  private ViewRentalViewModel viewRentalViewModel;
  private EditRentalViewModel editRentalViewModel;
  private ManageRentalsViewModel manageRentalsViewModel;
  private EditAccountViewModel editAccountViewModel;
  private RateFeedbackViewModel rateFeedbackViewModel;
  private ReportMemberViewModel reportMemberViewModel;
  private ManageAccountViewModel manageAccountViewModel;
  private SendWarningViewModel sendWarningViewModel;
  /**
   * ViewModelFactory constructor
   *
   * @param modelFactory the model factory
   */
  public ViewModelFactory(ModelFactory modelFactory)
  {
    this.modelFactory = modelFactory;
  }
  /**
   * Creates LogInViewModel if not created, otherwise returns it.
   *
   * @return LogInViewModel
   */
  public LogInViewModel getLogInViewModel()
  {
    if (logInViewModel == null)
    {
      logInViewModel = new LogInViewModel(modelFactory.getMemberModel());
    }
    return logInViewModel;
  }
  /**
   * Creates ChatReceivedMessagesViewModel if not created, otherwise returns it.
   *
   * @return ChatReceivedMessagesViewModel
   */
  public ChatReceivedMessagesViewModel getChatReceivedMessagesViewModel()
  {
    if (chatReceivedMessagesViewModel == null)
    {
      chatReceivedMessagesViewModel = new ChatReceivedMessagesViewModel(
          modelFactory.getMemberModel(), modelFactory.getMessageModel());
    }
    return chatReceivedMessagesViewModel;
  }
  /**
   * Creates ChatWriteMessagesViewModel if not created, otherwise returns it.
   *
   * @return ChatWriteMessagesViewModel
   */
  public ChatWriteMessageViewModel getChatWriteMessagesViewModel()
  {
    if (chatWriteMessageViewModel == null)
    {
      chatWriteMessageViewModel = new ChatWriteMessageViewModel(
          modelFactory.getMemberModel(), modelFactory.getMessageModel());
    }
    return chatWriteMessageViewModel;
  }
  /**
   * Creates AddRentalViewModel if not created, otherwise returns it.
   *
   * @return AddRentalViewModel
   */
  public AddRentalViewModel getAddRentalViewModel()
  {
    if (addRentalViewModel == null)
    {
      addRentalViewModel = new AddRentalViewModel(modelFactory.getRentalModel(),
          modelFactory.getMemberModel());
    }
    return addRentalViewModel;
  }
  /**
   * Creates WelcomePageViewModel if not created, otherwise returns it.
   *
   * @return WelcomePageViewModel
   */
  public WelcomePageViewModel getWelcomePageViewModel()
  {
    if (welcomePageViewModel == null)
    {
      welcomePageViewModel = new WelcomePageViewModel(
          modelFactory.getMemberModel(), modelFactory.getMessageModel());
    }
    return welcomePageViewModel;
  }
  /**
   * Creates CreateAccountViewModel if not created, otherwise returns it.
   *
   * @return CreateAccountViewModel
   */
  public CreateAccountViewModel getCreateAccountViewModel()
  {
    if (createAccountViewModel == null)
    {
      createAccountViewModel = new CreateAccountViewModel(
          modelFactory.getRentalModel(), modelFactory.getMemberModel());
    }
    return createAccountViewModel;
  }
  /**
   * Creates SearchForRentalViewModel if not created, otherwise returns it.
   *
   * @return SearchForRentalViewModel
   */
  public SearchForRentalViewModel getSearchForRentalViewModel()
  {
    if (searchForRentalViewModel == null)
    {
      searchForRentalViewModel = new SearchForRentalViewModel(
          modelFactory.getRentalModel(), modelFactory.getMessageModel());
    }
    return searchForRentalViewModel;
  }
  /**
   * Creates SearchForMemberViewModel if not created, otherwise returns it.
   *
   * @return SearchForMemberViewModel
   */
  public SearchForMemberViewModel getSearchForMemberViewModel()
  {
    if (searchForMemberViewModel == null)
    {
      searchForMemberViewModel = new SearchForMemberViewModel(
          modelFactory.getRentalModel(), modelFactory.getMemberModel(),
          modelFactory.getMessageModel());
    }
    return searchForMemberViewModel;
  }
  /**
   * Creates ViewRatingViewModel if not created, otherwise returns it.
   *
   * @return ViewRatingViewModel
   */
  public ViewRatingViewModel getViewRatingViewModel()
  {
    if (viewRatingViewModel == null)
    {
      viewRatingViewModel = new ViewRatingViewModel(
          modelFactory.getMemberModel(), modelFactory.getMessageModel());
    }
    return viewRatingViewModel;
  }
  /**
   * Creates ViewReportedMemberViewModel if not created, otherwise returns it.
   *
   * @return  ViewReportedMemberViewModel
   */
  public ViewReportedMemberViewModel getViewReportedMemberViewModel()
  {
    if (viewReportedMemberViewModel == null)
    {
      viewReportedMemberViewModel = new ViewReportedMemberViewModel(
          modelFactory.getRentalModel(), modelFactory.getMemberModel());
    }
    return viewReportedMemberViewModel;
  }
  /**
   * Creates ViewReportedMemberListViewModel if not created, otherwise returns it.
   *
   * @return ViewReportedMemberListViewModel
   */
  public ViewReportedMemberListViewModel getViewReportedMemberListViewModel()
  {
    if (viewReportedMemberListViewModel == null)
    {
      viewReportedMemberListViewModel = new ViewReportedMemberListViewModel(
          modelFactory.getMemberModel());
    }
    return viewReportedMemberListViewModel;
  }
  /**
   * Creates MenuViewModel if not created, otherwise returns it.
   *
   * @return MenuViewModel
   */
  public MenuViewModel getMenuViewModel()
  {
    if (menuViewModel == null)
    {
      menuViewModel = new MenuViewModel(modelFactory.getRentalModel(),
          modelFactory.getMemberModel(), modelFactory.getMessageModel());
    }
    return menuViewModel;
  }
  /**
   * Creates ViewMemberProfileViewModel if not created, otherwise returns it.
   *
   * @return ViewMemberProfileViewModel
   */
  public ViewMemberProfileViewModel getViewMemberProfileViewModel()
  {
    if (viewMemberProfileViewModel == null)
    {
      viewMemberProfileViewModel = new ViewMemberProfileViewModel(
          modelFactory.getRentalModel(), modelFactory.getMemberModel());
    }
    return viewMemberProfileViewModel;
  }
  /**
   * Creates ViewRentalViewModel if not created, otherwise returns it.
   *
   * @return ViewRentalViewModel
   */
  public ViewRentalViewModel getViewRentalViewModel()
  {
    if (viewRentalViewModel == null)
    {
      viewRentalViewModel = new ViewRentalViewModel(
          modelFactory.getRentalModel(), modelFactory.getMemberModel());
    }
    return viewRentalViewModel;
  }
  /**
   * Creates ReportMemberViewModel if not created, otherwise returns it.
   *
   * @return ReportMemberViewModel
   */
  public ReportMemberViewModel getReportMemberViewModel()
  {
    if (reportMemberViewModel == null)
    {
      reportMemberViewModel = new ReportMemberViewModel(
          modelFactory.getMemberModel(), modelFactory.getMessageModel());
    }
    return reportMemberViewModel;
  }
  /**
   * Creates RateFeedbackViewModel if not created, otherwise returns it.
   *
   * @return RateFeedbackViewModel
   */
  public RateFeedbackViewModel getRateFeedbackViewModel()
  {
    if (rateFeedbackViewModel == null)
    {
      rateFeedbackViewModel = new RateFeedbackViewModel(
          modelFactory.getMemberModel(), modelFactory.getMessageModel());
    }
    return rateFeedbackViewModel;
  }
  /**
   * Creates EditRentalViewModel if not created, otherwise returns it.
   *
   * @return EditRentalViewModel
   */
  public EditRentalViewModel getEditRentalViewModel()
  {
    if (editRentalViewModel == null)
    {
      editRentalViewModel = new EditRentalViewModel(
          modelFactory.getRentalModel(), modelFactory.getMemberModel());
    }
    return editRentalViewModel;
  }
  /**
   * Creates ManageRentalsViewModel if not created, otherwise returns it.
   *
   * @return ManageRentalsViewModel
   */
  public ManageRentalsViewModel getManageRentalsViewModel()
  {
    if (manageRentalsViewModel == null)
    {
      manageRentalsViewModel = new ManageRentalsViewModel(
          modelFactory.getRentalModel(), modelFactory.getMemberModel());
    }
    return manageRentalsViewModel;
  }
  /**
   * Creates EditAccountViewModel if not created, otherwise returns it.
   *
   * @return EditAccountViewModel
   */
  public EditAccountViewModel getEditAccountViewModel()
  {
    if (editAccountViewModel == null)
    {
      editAccountViewModel = new EditAccountViewModel(
          modelFactory.getRentalModel(), modelFactory.getMemberModel());
    }
    return editAccountViewModel;
  }
  /**
   * Creates ManageAccountViewModel if not created, otherwise returns it..
   *
   * @return ManageAccountViewModel
   */
  public ManageAccountViewModel getManageAccountViewModel()
  {
    if (manageAccountViewModel == null)
    {
      manageAccountViewModel = new ManageAccountViewModel(
          modelFactory.getRentalModel(), modelFactory.getMemberModel());
    }
    return manageAccountViewModel;
  }
  /**
   * Creates SendWarningViewModel if not created, otherwise returns it.
   *
   * @return SendWarningViewModel
   */
  public SendWarningViewModel getSendWarningViewModel()
  {
    if (sendWarningViewModel == null)
    {
      sendWarningViewModel = new SendWarningViewModel(
          modelFactory.getMemberModel(), modelFactory.getMessageModel());
    }
    return sendWarningViewModel;
  }
}
