package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id=Integer.MAX_VALUE;
    private String firstName;
    private String lastName;
    private String nickName;
    private String companyName;
    private String address;
    private String email;
    private String homePage;
    private String BDay;
    private String BMonth;
    private String BYear;

    public ContactData withId(int id) {
        this.id=id;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public ContactData withCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withHomePage(String homePage) {
        this.homePage = homePage;
        return this;
    }

    public ContactData withBDay(String BDay) {
        this.BDay = BDay;
        return this;
    }

    public ContactData withBMonth(String BMonth) {
        this.BMonth = BMonth;
        return this;
    }

    public ContactData withBYear(String BYear) {
        this.BYear = BYear;
        return this;
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
