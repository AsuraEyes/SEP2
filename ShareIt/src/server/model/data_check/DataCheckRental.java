package server.model.data_check;


import server.model.database.rental.RentalDAOImpl;
import shared.transferobjects.Rental;

import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Class that checks data before running an instance(Rental data in this case)
 */
public class DataCheckRental {
    private String name;
    private String description;
    private String price;
    private int priceNb;

    public String checkRentalData(String name, String pictureLink, String description, String price, String otherInformation, String stateName, String username,ArrayList<String> selectedCategories) {
        this.name = name;
        this.description = description;
        this.price = price;

        if (nameGiven() && descriptionGiven() && priceIsNumber()){
            try {
                RentalDAOImpl.getInstance().create(name, pictureLink, description, priceNb, otherInformation, stateName, username, selectedCategories);
                return "Adding successful";
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        else{
            if(!nameGiven()){
                return "Name cannot be empty";
            }
            if(!descriptionGiven()){
                return "Description cannot be empty";
            }
            if(!priceIsNumber()){
                return "Price is a not number";
            }
        }
        return "Ooops, something went wrong!!";
    }

    public String updateCheckRentalData(String name, String pictureLink, String description, String price, String otherInformation, String stateName, int rentalId, ArrayList<String> selectedCategories) {
        this.name = name;
        this.description = description;
        this.price = price;

        if (nameGiven() && descriptionGiven() && priceIsNumber()){
            try {
                RentalDAOImpl.getInstance().update(name, pictureLink, description, priceNb, otherInformation, stateName, rentalId, selectedCategories);
                return "Edit successful";
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        else{
            if(!nameGiven()){
                return "Name cannot be empty";
            }
            if(!descriptionGiven()){
                return "Description cannot be empty";
            }
            if(!priceIsNumber()){
                return "Price is a not number";
            }
        }
        return "Ooops, something went wrong!!";
    }

    private boolean nameGiven(){
        if (name != null){
            if (!(name.trim().equals("") && name.isBlank() && name.isEmpty())){
                return true;
            }
        }
        return false;
    }

    private boolean descriptionGiven(){
        if (description != null){
            if (!(description.trim().equals("") && description.isBlank() && description.isEmpty())){
                return true;
            }
        }
        return false;
    }

    private boolean priceIsNumber(){
        try{
            this.priceNb = Integer.parseInt(price);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }
}
