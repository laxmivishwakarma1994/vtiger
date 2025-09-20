package com.vtiger.pages;

import com.vtiger.utilities.UIactions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadPage extends UIactions {

    public LeadPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name = 'firstname']")
    WebElement tb_fname;
    @FindBy(xpath = "//input[@name = 'lastname']")
    WebElement tb_lname;
    @FindBy(xpath = "//input[@name = 'company']")
    WebElement tb_comp;
    @FindBy(name = "button")
    WebElement btn_save;
    @FindBy(xpath = "//td[text()='First Name:']//following::td[1]")
    WebElement txt_fname;
    @FindBy(xpath = "//td[text()='Last Name:']//following::td[1]")
    WebElement txt_lname;
    @FindBy(xpath = "//td[text()='Company:']//following::td[1]")
    WebElement txt_comp;


    public void createlead_with_mandatory_fields(String fname, String lname,String comp) {

        SetFirstName(fname);
        SetLastName(lname);
        SetComp(comp);
        ClickSave();
    }

    public void SetFirstName(String fname) {
        SetInput(tb_fname, fname, fname + "firstname been entered into firstname fields");
    }

    public void SetLastName(String lname) {
        SetInput(tb_lname, lname, lname + "lastname been entered into lastname filed");
    }

    public void SetComp(String comp) {
        SetInput(tb_comp, comp, comp + "company been entered into companyname filed");
    }

    public void ClickSave() {
        ClickElement(btn_save, "save button clicked");
    }

    public void verifyleadcreation(String fname, String lname, String comp) {
        verifytext(txt_fname, fname, fname + "expected text match with actual text");
        verifytext(txt_lname, lname, fname + "expected text match with actual text");
        verifytext(txt_comp, comp, comp + "expected text match with actual text");
    }
}




