package kg.ebay;

public enum User {
    USER_AIMAN ("aliyaaiman@gmail.com","12345"),
    USER_ALINUR("alinur@gmail.com","Alinur");

    private final String email;
    private final String password;

    User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
