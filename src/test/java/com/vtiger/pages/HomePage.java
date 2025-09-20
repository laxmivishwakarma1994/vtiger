package com.vtiger.pages;

import com.vtiger.utilities.UIactions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends UIactions {


    public HomePage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
        System.out.println("home");
    }


    @FindBy(linkText = "Home")
    WebElement lnk_home;
    @FindBy(linkText = "Logout")
    WebElement lnk_logout;
    @FindBy(linkText = "New Lead")
    WebElement lnk_NewLead;


    public void verifyHome()
    {

        ElementExist(lnk_home,"home tab is displaying on Home Page");
    }

    public void verifyLogout()
    {

        ElementExist(lnk_logout,"link logout exist on home page");
    }

    public void ClickLogout()

    {
        ClickElement(lnk_logout,"logout link cliked");
    }

    public void ClickNewLead()

    {
        ClickElement(lnk_NewLead,"New Lead link cliked");
    }


}
