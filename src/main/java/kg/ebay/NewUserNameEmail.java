package kg.ebay;

public enum NewUserNameEmail {
    ESMA ("Esmaa","eesma@gmail.com");

    private final String name;
    private final String email;

    NewUserNameEmail(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
