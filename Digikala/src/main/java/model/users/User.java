package model.users;

abstract public class User {
    private String username;
    private String email;
    private String phoneNumber;
    private String password;

    User() {}

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setter(String username, String email, String phoneNumber, String password) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber=phoneNumber;
    }
    @Override
    public String toString() {
        return  "Username: " + this.username + "\nEmail: " + this.email +
                "\nPhoneNumber: " + this.phoneNumber + "\nPassword: " + this.password;
    }


}
