package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase {

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[value='Signup']"));
    }

    public void login(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        click(By.cssSelector("input[value='Login']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Login']"));
    }

    public void resetPassword(String username) {
        click(By.xpath(".//*[normalize-space(text()) and normalize-space(.)='Manage']"));
        click(By.linkText("Manage Users")); //a[contains(text(),'Manage Users')]
        click(By.linkText(String.format("%s",username)));
        click(By.xpath("//input[@value='Reset Password']"));
    }

    public void updatePassword(String confirmationLink, String username, String password) {
        wd.get(confirmationLink);
        type(By.name("realname"), username);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.tagName("button"));  // .cssSelector("input[value='Update User']"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.tagName("button"));  // .cssSelector("input[value='Update User']"));
    }
}
