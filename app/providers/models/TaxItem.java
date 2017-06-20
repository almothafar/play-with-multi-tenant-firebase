package providers.models;

import controllers.base.BaseVO;

public class TaxItem extends BaseVO {
    private String country;
    private String state;
    private String city;
    private float salesTax;
    private float wirelessTax;

    public String getCountry() {
        return country;
    }

    public TaxItem setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getState() {
        return state;
    }

    public TaxItem setState(String state) {
        this.state = state;
        return this;
    }

    public String getCity() {
        return city;
    }

    public TaxItem setCity(String city) {
        this.city = city;
        return this;
    }

    public float getSalesTax() {
        return salesTax;
    }

    public TaxItem setSalesTax(float salesTax) {
        this.salesTax = salesTax;
        return this;
    }

    public float getWirelessTax() {
        return wirelessTax;
    }

    public TaxItem setWirelessTax(float wirelessTax) {
        this.wirelessTax = wirelessTax;
        return this;
    }
}
