package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Type;
import ru.stqa.pft.addressbook.tests.ContactPhoneTests;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")
public class ContactData {
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column(name = "firstname")
    private String firstName = "";
    @Expose
    @Column(name = "lastname")
    private String lastName = "";
    @Expose
    @Column(name = "nickname")
    private String nickName = "";
    @Expose
    @Column(name = "company")
    private String companyName = "";
    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address = "";
    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String email = "";
    @Expose
    @Column(name = "email2")
    @Type(type = "text")
    private String email2 = "";
    @Expose
    @Column(name = "email3")
    @Type(type = "text")
    private String email3 = "";
    @Expose
    @Column(name = "homepage")
    @Type(type = "text")
    private String homePage = "";
    @Expose
    @Column(name = "bday", columnDefinition = "tinyint")
    private String BDay = "";
    @Expose
    @Column(name = "bmonth")
    private String BMonth = "";
    @Expose
    @Column(name = "byear")
    private String BYear = "";
    @Expose
    @Type(type = "text")
    @Column(name = "home")
    private String homePhone = "";
    @Expose
    @Type(type = "text")
    @Column(name = "mobile")
    private String mobilePhone = "";
    @Expose
    @Type(type = "text")
    @Column(name = "work")
    private String workPhone = "";
    @Transient
    private String allPhones;
    @Transient
    private String allEmails;
    @Expose
    @Type(type = "text")
    @Column(name = "photo")
    private String photo;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="address_in_groups",
            joinColumns = @JoinColumn(name="id"), inverseJoinColumns = @JoinColumn(name="group_id"))
    private Set<GroupData> groups=new HashSet<GroupData>();


    public Groups getGroups() {
        return new Groups(groups);
    }

    public ContactData withId(int id) {
        this.id = id;
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

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
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

    public ContactData withHomePhone(String home) {
        this.homePhone = home;
        return this;
    }

    public ContactData withMobilePhone(String mobile) {
        this.mobilePhone = mobile;
        return this;
    }

    public ContactData withWorkPhone(String work) {
        this.workPhone = work;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public File getPhoto() {
        return new File(photo);
    }

    public String getAllEmails() {
        return allEmails;
    }

    public String getAllPhones() {
        return allPhones;
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

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
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

    public String getWorkPhone() {
        return workPhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", email2='" + email2 + '\'' +
                ", email3='" + email3 + '\'' +
                ", homePage='" + homePage + '\'' +
                ", BDay='" + BDay + '\'' +
                ", BMonth='" + BMonth + '\'' +
                ", BYear='" + BYear + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", workPhone='" + workPhone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(nickName, that.nickName) &&
                Objects.equals(companyName, that.companyName) &&
                Objects.equals(address, that.address) &&
                Objects.equals(email, that.email) &&
                Objects.equals(email2, that.email2) &&
                Objects.equals(email3, that.email3) &&
                Objects.equals(homePage, that.homePage) &&
                Objects.equals(BDay, that.BDay) &&
                Objects.equals(BMonth, that.BMonth) &&
                Objects.equals(BYear, that.BYear) &&
                Objects.equals(homePhone, that.homePhone) &&
                Objects.equals(mobilePhone, that.mobilePhone) &&
                Objects.equals(workPhone, that.workPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, nickName, companyName, address, email, email2, email3, homePage, BDay, BMonth, BYear, homePhone, mobilePhone, workPhone);
    }

    public ContactData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }
}
