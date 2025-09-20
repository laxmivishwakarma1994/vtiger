package com.vtiger.pages;

import com.vtiger.utilities.UIactions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends UIactions {
    public WebDriver driver;

    public LoginPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@name = 'user_name']")
    WebElement tb_userid;
    @FindBy(xpath = "//input[@name = 'user_password']")
    WebElement tb_password;
    @FindBy(name = "Login")
    WebElement btn_login;
    @FindBy(xpath = "//td[contains(text(),'You must specify a valid username and password. ')]")
    WebElement txt_ErrorMsg;




    public void login (String uid, String pwd)
    {

        SetUserid(uid);
        SetPassword(pwd);
        ClickLogin();
    }

    public void SetUserid(String uid)
    {
        SetInput(tb_userid,uid,uid + "has been entered into username fields");
    }

    public void SetPassword(String pwd)
    {
        SetInput(tb_password,pwd,pwd +"has been entered into password filed");
    }

    public void ClickLogin()
    {
        ClickElement(btn_login,"login button clicked");
    }

    public void verifyLogin()
    {
        ElementExist(btn_login,"login button exist on login page");
    }

    public void verifyErrorMsg()
    {
        ElementExist(txt_ErrorMsg,"Error message validated successfully");
    }
}
