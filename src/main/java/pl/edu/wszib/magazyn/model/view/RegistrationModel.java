package pl.edu.wszib.magazyn.model.view;

public class RegistrationModel {

    private String login;
    private String pass;
    private String pass2;

    public RegistrationModel() {
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPass2() {
        return this.pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }
}
