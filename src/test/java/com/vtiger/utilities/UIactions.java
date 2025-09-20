package com.vtiger.utilities;

import com.vtiger.stepdefinitions.commonsteps;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class UIactions {
    public WebDriver driver;
    public WebDriverWait wait;


    public UIactions(WebDriver driver)
    {
      this.driver= driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    public void SetInput(WebElement elm, String val,String msg)
    {
        try {

            wait.until(ExpectedConditions.visibilityOf(elm));
            elm.clear();
            elm.sendKeys(val);
            commonsteps.report.pass(msg+getScreenshort());

        }
        catch (Exception e)
        {
            commonsteps.report.fail(e.getMessage()+getScreenshort());
            e.printStackTrace();

        }
    }

    public void ClickElement(WebElement elm,String msg)
    {
        try {

            wait.until(ExpectedConditions.elementToBeClickable(elm));
            elm.click();
            commonsteps.report.pass(msg+getScreenshort());

        }
        catch (Exception e)
        {
            commonsteps.report.fail(e.getMessage()+getScreenshort());
            e.printStackTrace();

        }
    }

    public void ElementExist(WebElement elm,String msg)
    {
        try {

            wait.until(ExpectedConditions.visibilityOf(elm));
            elm.isDisplayed();
            commonsteps.report.pass(msg+getScreenshort());

        }
        catch (Exception e)
        {
            commonsteps.report.fail(e.getMessage()+getScreenshort());
            e.printStackTrace();

        }
    }

    public void verifytext(WebElement elm,String ExpText,String msg)
    {
        try {

            wait.until(ExpectedConditions.visibilityOf(elm));
            String actText = elm.getText();
            if (actText.trim().equals(ExpText))
            commonsteps.report.pass(msg+getScreenshort());
            else
                commonsteps.report.pass(ExpText+"expected text did not match with actual text"+actText+" "+getScreenshort());

        }
        catch (Exception e)
        {
            commonsteps.report.fail(e.getMessage()+getScreenshort());
            e.printStackTrace();

        }
    }

    public String getScreenshort()
    {
        Date d = new Date ();
        DateFormat ft = new SimpleDateFormat("ddMMyyyyhhmmss");
        String fileName = ft.format(d);
        String path = System.getProperty("user.dir")+"/src/test/java/com/vtiger/reports/screenshot/"+fileName+".png";
        TakesScreenshot ts = ((TakesScreenshot) driver);//screenshort code
        File SrcFile = ts.getScreenshotAs(OutputType.FILE);//screenshort code
        //Move Image File to a new destination
        File DestFile = new File (path);
        //Copy file at destination
        try{
            FileUtils.copyFile(SrcFile,DestFile);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        String imagepath = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='"+path+"'><span class= 'label time-taken grey lighten-1 white-text'>Screenshot</span><a>";
        return imagepath;

    }


}
