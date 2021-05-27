package client.core;


import client.model.member.MemberModel;
import client.model.member.MemberModelManager;
import client.model.message.MessageModel;
import client.model.message.MessageModelManager;
import client.model.rental.RentalModel;
import client.model.rental.RentalModelManager;

import java.io.IOException;

public class ModelFactory {
  private ClientFactory clientFactory;

  private RentalModel rentalModel;
  private MessageModel messageModel;
  private MemberModel memberModel;

  public ModelFactory(ClientFactory clientFactory){
    this.clientFactory = clientFactory;
  }

public RentalModel getRentalModel() throws IOException {
  if(rentalModel == null)
  {
    rentalModel = new RentalModelManager(clientFactory.getClient());
  }
  return rentalModel;
}

  public MemberModel getMemberModel() throws IOException {
    if(memberModel == null)
    {
      memberModel = new MemberModelManager(clientFactory.getClient(), this);
    }
    return memberModel;
  }

  public MessageModel getMessageModel() throws IOException {
    if(messageModel == null)
    {
      messageModel = new MessageModelManager(clientFactory.getClient());
    }
    return messageModel;
  }
}
