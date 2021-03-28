package fr.quentinfrancois.JavaEE_Projet.persistance;

import mediatek2021.Utilisateur;

public class User implements Utilisateur {
    private String login;
    private String password;
    private String droits;

    public User(String login, String password, String droits) {
        this.login = login;
        this.password = password;
        this.droits = droits;
    }

    public String login() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getDroits() {return droits;}
    public void setDroits(String droits) { this.droits = droits; }
    public String password() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public Object[] data() {
        return new Object[]{login, password, droits};
    }
}
