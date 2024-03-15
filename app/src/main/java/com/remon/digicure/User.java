package com.remon.digicure;

public class User {
    public String fname;
    public String lname;
    public String natioNo;
    public String regiNo;
    public String email;
    public String pass;

    public User(){

    }
    public User(String fname, String lname, String natioNo, String regiNo, String email, String pass){
            this.fname = fname;
            this.lname = lname;
            this.natioNo = natioNo;
            this.regiNo = regiNo;
            this.email = email;
            this.pass = pass;
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

    public String getNatioNo() {
        return natioNo;
    }

    public void setNatioNo(String natioNo) {
        this.natioNo = natioNo;
    }

    public String getRegiNo() {
        return regiNo;
    }

    public void setRegiNo(String regiNo) {
        this.regiNo = regiNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
