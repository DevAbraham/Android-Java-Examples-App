package com.dev_abraham.crud_sqlite.Moderls;

import java.io.Serializable;

public class ModelStudent implements Serializable {


    public int id;
    public String name;
    public String phone;
    public String email;


    public ModelStudent (){

    }
    public ModelStudent( String name,String phone,String email) {
        this.name = name;
        this.email=email;
        this.phone=phone;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
