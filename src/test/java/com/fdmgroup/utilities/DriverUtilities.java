package com.fdmgroup.utilities;

import java.io.FileInputStream;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//Singleton pattern will be applied
public class DriverUtilities {

    //1 private Static Instance
    private static DriverUtilities driverUtilities;
    private WebDriver driver;

    //2 Private Constructor
    private DriverUtilities(){
        super();
    }

    //3 Public static getInstance()
    public static DriverUtilities getInstance(){
        if(driverUtilities == null){
            driverUtilities =  new DriverUtilities();
        }
        return driverUtilities;
    }
    public WebDriver getDriver(){
        if(driver == null){
            createDriver();
        }
        return driver;
    }

    private void createDriver() {
        String driverName = getDriverName();

        switch (driverName){
            case "Chrome":
                this.driver = new ChromeDriver();
                break;
            case "Firefox":
                this.driver = new FirefoxDriver();
                break;
            case "Edge":
                this.driver = new EdgeDriver();
                break;
            default:
                break;
        }
    }

    private String getDriverName(){
        //return browser that you will use for Aut. Test (Chrome/Firefox/Edge)
        String driverName = "";

        Properties config = new Properties();

        try {
            config.load(new FileInputStream("src/test/resources/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        driverName = config.getProperty("browser");
        return driverName;
    }
}
