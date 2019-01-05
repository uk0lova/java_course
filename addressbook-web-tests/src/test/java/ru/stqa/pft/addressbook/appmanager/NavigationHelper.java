package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groupPage() {
        click(By.linkText("groups"));
    }

    public void addContactPage() {
      wd.findElement(By.linkText("add new")).click();
    }

    public void contactPage() {
        wd.findElement(By.linkText("home")).click();
    }
}
