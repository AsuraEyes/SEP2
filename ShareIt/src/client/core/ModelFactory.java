package client.core;

import client.model.member.MemberModel;
import client.model.member.MemberModelManager;
import client.model.message.MessageModel;
import client.model.message.MessageModelManager;
import client.model.rental.RentalModel;
import client.model.rental.RentalModelManager;
/**
 * A class that creates a Model object without having to specify the exact class of the object.
 */
public class ModelFactory
{
  private ClientFactory clientFactory;

  private RentalModel rentalModel;
  private MessageModel messageModel;
  private MemberModel memberModel;
  /**
   * Instantiates a new Model factory.
   *
   * @param clientFactory
   */
  public ModelFactory(ClientFactory clientFactory)
  {
    this.clientFactory = clientFactory;
  }
  /**
   * Gets RentalModel when it needs to be accessed..
   *
   * @return returns the RentalModel
   */
  public RentalModel getRentalModel()
  {
    if (rentalModel == null)
    {
      rentalModel = new RentalModelManager(clientFactory.getClient(), this);
    }
    return rentalModel;
  }
  /**
   * Gets MemberModel when it needs to be accessed.
   *
   * @return returns the MemberModel
   */
  public MemberModel getMemberModel()
  {
    if (memberModel == null)
    {
      memberModel = new MemberModelManager(clientFactory.getClient(), this);
    }
    return memberModel;
  }
  /**
   * Gets MessageModel when it needs to be accessed..
   *
   * @return returns the MessageModel
   */
  public MessageModel getMessageModel()
  {
    if (messageModel == null)
    {
      messageModel = new MessageModelManager(clientFactory.getClient());
    }
    return messageModel;
  }
}
