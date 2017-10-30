package com.example.Dunni.myapplication.backend;

/**
 * Created by Dunni on 10/21/2017.
 */

public class Customer {
    //Customer Properties
    private String name;
    private String email;
    private String address;
    private String phone;
    private Long id;

    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String ADDRESS = "address";
    public static final String PHONE = "phone";
    public static final String ID = "id";

    private Customer(Builder builder){
        this.name = builder.name;
        this.email = builder.email;
        this.address = builder.address;
        this.phone = builder.phone;
        this.id = builder.id;
    }

    public static class Builder{
        private String name;
        private String email;
        private String address;
        private String phone;
        private Long id;

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder email(String email){
            this.email = email;
            return this;
        }

        public Builder address(String address){
            this.address = address;
            return this;
        }

        public Builder phone(String phone){
            this.phone = phone;
            return this;
        }

        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Customer build(){
            return new Customer(this);
        }

    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String toString(){
        return
                "Name: "+ name+", Email: "+ email+", Address: "+ address
                + ", Phone: "+ phone;
    }

}

