package ground_classes;

public class PhoneNo {
    private String countryCode;
    private int number;

    public PhoneNo(String countryCode, int number){
        this.countryCode = countryCode;
        this.number = number;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String toString(){
        return countryCode + (String.valueOf(number).replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1)-$2-$3"));
    }
}
