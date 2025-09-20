package com.vtiger.stepdefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.vtiger.pages.HomePage;
import com.vtiger.pages.LeadPage;
import com.vtiger.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class commonsteps {
    public static  Properties prop;
    public static WebDriver driver;
    public static LoginPage lp;
    public static HomePage hp;
   public static Map<String,Map<String,String>> dt;
   public static String TCName;
   public static ExtentHtmlReporter htmlReporter;
   public static ExtentReports extent;
   public static ExtentTest report;
    public static LeadPage ldp;
   


    public void initiation()
    {

        if (dt==null)
        readexcel();

        if (prop==null)
        readproperties();
        if (driver==null)
        launchApp();
        System.out.println("hello laxmi");

    }



    public void launchApp()//(3)

    {
        if(prop.getProperty("browser").equalsIgnoreCase("edge"))
        {
            driver = new EdgeDriver();
        }
        else if (prop.getProperty("browser").equalsIgnoreCase("firefox"))
        {
            driver = new FirefoxDriver();
        }
        else if (prop.getProperty("browser").equalsIgnoreCase("headless"))
        {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless"); // Run in headless mode
            options.addArguments("--disable-gpu"); // Recommended for Windows
            options.addArguments("--window-size=1920,1080"); // Optional
            driver = new ChromeDriver(options);
        }
        else
        {
            driver = new ChromeDriver();
        }
        driver.get(prop.getProperty("AppUrl"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("globalwait"))));

    }


    public void readproperties()  //this will read config.properties(1)
    {
        try {


            prop = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
            prop.load(fis);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    //fillo excel reading-latest version

      public void readexcel()
      {
          try {


              Fillo fillo = new Fillo();
              Connection connection = fillo.getConnection(System.getProperty("user.dir")+"/src/test/resources/TestData/data.xlsx");
              String strQuery = "Select * from data1";
              Recordset recordset = connection.executeQuery(strQuery);

              int rows = recordset.getCount();
              List<String> ls = recordset.getFieldNames();
              int colms = ls.size();
              dt = new HashMap<>();
             while (recordset.next())

              {
                  Map<String,String> rowdata = new HashMap<>();
                  for (int j = 1; j<colms;j++)
                  {
                      String colname = ls.get(j);
                      String colmval = recordset.getField(ls.get(j));
                      rowdata.put(ls.get(j),recordset.getField(ls.get(j)));

                  }
                  dt.put(recordset.getField("TCName"),rowdata);
              }

              System.out.println(dt);


              recordset.close();
              connection.close();
          }
          catch (Exception e)
          {
              System.out.println(e.getMessage());
          }
      }

    //extent report

    public void createExtendreport()
    {
        Date d = new Date();
        DateFormat ft = new SimpleDateFormat("ddMMyyyyhhmmss");
        String filename = ft.format(d);
        htmlReporter= new ExtentHtmlReporter(System.getProperty("user.dir")+"/src/test/java/com/vtiger/reports/"+"extentreport_"+filename+".html");
        // ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir")+"src/test/java/com/vtiger/reports");
        extent = new ExtentReports();  //Create an object
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Tester", "Laxmi");
        extent.setSystemInfo("Environment", "QA");
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Cucumber Test Results");
    }








}
