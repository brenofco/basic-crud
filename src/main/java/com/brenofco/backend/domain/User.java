package com.brenofco.backend.domain;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    String cpf;
    String fname;
    String lname;
    String phone;
    String mail;
    String bdate;

    public User() {
    }

    public User(String cpf) {
        this.cpf = cpf;
    }

    public User(String cpf, String fname, String lname, String phone, String mail, String bdate) {
        this.cpf = cpf;
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.mail = mail;
        this.bdate = bdate;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }
}
