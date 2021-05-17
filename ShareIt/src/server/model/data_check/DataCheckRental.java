package server.model.data_check;


import server.model.database.rental.RentalDAOImpl;
import shared.transferobjects.Member;

import java.sql.SQLException;

public class DataCheckRental {
    private String name;
    private String pictureLink;
    private String description;
    private String price;
    private int priceNb;

    public void checkData(String name, String pictureLink, String description, String price, String otherInformation, String stateName, Member member) {
        this.name = name;
        this.pictureLink = pictureLink;
        this.description = description;
        this.price = price;

        if (nameGiven() && pictureLinkGiven() && descriptionGiven() && priceIsNumber()){
            try {
                RentalDAOImpl.getInstance().create(name, pictureLink, description, priceNb, otherInformation, stateName, member);
            }
            catch (SQLException e){
                //fgh
            }
        }
    }

    private boolean nameGiven(){
        if (name != null){
            if (!(name.trim().equals("") && name.isBlank() && name.isEmpty())){
                return true;
            }
        }
        System.out.println("Name cannot be empty");
        return false;
    }

    private boolean pictureLinkGiven(){
        if (pictureLink != null){
            if (!(pictureLink.trim().equals("") && pictureLink.isBlank() && pictureLink.isEmpty())){
                return true;
            }
        }
        System.out.println("Picture link cannot be empty");
        return false;
    }

    private boolean descriptionGiven(){
        if (description != null){
            if (!(description.trim().equals("") && description.isBlank() && description.isEmpty())){
                return true;
            }
        }
        System.out.println("Description cannot be empty");
        return false;
    }

    private boolean priceIsNumber(){
        try{
            this.priceNb = Integer.parseInt(price);
            return true;
        }
        catch (NumberFormatException e){
            System.out.println("price is a not number");
            return false;
        }
    }
}
