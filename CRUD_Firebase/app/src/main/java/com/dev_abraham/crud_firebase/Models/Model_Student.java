package com.dev_abraham.crud_firebase.Models;
import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Model_Student implements Serializable {


    //Exclude para que firebase sepa que los únicos campos que necesito
    //para enviar y obtener son los q no están excluidos para este objecto o clase.
    @Exclude
    public String key ;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String nombre;
    public String apellidoPaterno;
    public String apellidoMaterno;
    public String telefono;
    public String email;

    public Model_Student(){

    }
    public Model_Student(String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String email) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.email = email;
    }



    public String getNombre() {
        return nombre;
    }


    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
