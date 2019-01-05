package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id;
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
        this.id = Integer.MAX_VALUE;
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

    public ContactData(int id, String FirstName, String LastName, String NickName, String CompanyName, String Address, String Email, String HomePage, String BDay, String BMonth, String BYear) {
        this.id = id;
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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}
