package ru.stqa.pft.addressbook;

public class ContactData {
    private final String firstName;
    private final String lastName;
    private final String nickName;
    private final String companyName;
    private final String address;
    private final String email;
    private final String homePage;
    private final String BDay;
    private final String BMonth;
    private final String BYear;

    public ContactData(String FirstName, String LastName, String NickName, String CompanyName, String Address, String Email, String HomePage, String BDay, String BMonth, String BYear) {
        firstName = FirstName;
        lastName = LastName;
        nickName = NickName;
        companyName = CompanyName;
        address = Address;
        email = Email;
        homePage = HomePage;
        this.BDay = BDay;
        this.BMonth = BMonth;
        this.BYear = BYear;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getHomePage() {
        return homePage;
    }

    public String getBDay() {
        return BDay;
    }

    public String getBMonth() {
        return BMonth;
    }

    public String getBYear() {
        return BYear;
    }
}
