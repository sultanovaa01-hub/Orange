package kg.ebay;

public enum NewUsers {

    ESMA ("Mrs","123456",27,"July",2021,"Esma",
            "Talantbek","IT","Sary Chelek","United States","Los Angeles","123",1234,1234);


    private final String title;
    private final String password;
    private final int dayOfBirth;
    private final String monthOfBirth;
    private final int yearOfBirth;
    private final String firstName;
    private final String lastName;
    private final String company;
    private final String address;
    private final String country;
    private final String state;
    private final String city;
    private final int zipcode;
    private final int mobileNumber;

    NewUsers( String title, String password, int dayOfBirth, String monthOfBirth,
             int yearOfBirth, String firstName, String lastName, String company, String address, String country,
             String state, String city, int zipcode, int mobileNumber) {
        this.title = title;
        this.password = password;
        this.dayOfBirth = dayOfBirth;
        this.monthOfBirth = monthOfBirth;
        this.yearOfBirth = yearOfBirth;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.address = address;
        this.country = country;
        this.state = state;
        this.city = city;
        this.zipcode = zipcode;
        this.mobileNumber = mobileNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getPassword() {
        return password;
    }

    public int getDayOfBirth() {
        return dayOfBirth;
    }

    public String getMonthOfBirth() {
        return monthOfBirth;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public int getZipcode() {
        return zipcode;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }
}
