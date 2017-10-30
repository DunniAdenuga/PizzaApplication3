package com.example.Dunni.myapplication.backend;

/**
 * Created by Dunni on 10/21/2017.
 */

public class Orders {
    //Order Properties
   private String customerId;
    private String cost;
    private String date;
    private String size;
    private String crust;
    private String protein;
    private String sauce;
    private Long id;

    public static final String CUSTOMERID = "customerId";
    public static final String COST = "cost";
    public static final String DATE = "date";
    public static final String SIZE = "size";
    public static final String CRUST = "crust";
    public static final String PROTEIN = "protein";
    public static final String SAUCE = "sauce";
    public static final String ID = "id";

    public Orders(Builder builder) {
        this.customerId = builder.customerId;
        this.cost = builder.cost;
        this.date = builder.date;
        this.size = builder.size;
        this.crust = builder.crust;
        this.protein = builder.protein;
        this.sauce = builder.sauce;
        this.id = builder.id;
    }

    public static class Builder{
        private String customerId;
        private String cost;
        private String date;
        private String size;
        private String crust;
        private String protein;
        private String sauce;
        private Long id;

        public Builder customerId(String customerId){
            this.customerId = customerId;
            return this;
        }

        public Builder cost(String cost){
            this.cost = cost;
            return this;
        }

        public Builder date(String date){
            this.date = date;
            return this;
        }

        public Builder size(String size){
            this.size = size;
            return this;
        }

        public Builder crust(String crust){
            this.crust = crust;
            return this;
        }

        public Builder protein(String protein){
            this.protein = protein;
            return this;
        }

        public Builder sauce(String sauce){
            this.sauce = sauce;
            return this;
        }

        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Orders build(){
            return new Orders(this);
        }
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCrust() {
        return crust;
    }

    public void setCrust(String crust) {
        this.crust = crust;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getSauce() {
        return sauce;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return
                "Order ID: " + id + '\n' +
                "size: " + size + '\n' +
                "crust: " + crust + '\n' +
                "protein: " + protein + '\n' +
                "sauce: " + sauce + '\n' +
                "cost: " + cost + '\n'
                ;
    }
}
