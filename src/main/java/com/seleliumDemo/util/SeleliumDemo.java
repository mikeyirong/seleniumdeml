package com.seleliumDemo.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

@SuppressWarnings("deprecation")
public class SeleliumDemo {
	static File pathToBinary;
	static FirefoxBinary firefoxBinary;
	static FirefoxProfile firefoxProfile;
	static WebDriver driver;
	static {
		pathToBinary = new File("D:\\Mozilla Firefox\\firefox.exe");
		System.setProperty("webdriver.gecko.driver", "D:\\install\\webdriver\\geckodriver.exe");
		firefoxBinary = new FirefoxBinary(pathToBinary);
		firefoxProfile = new FirefoxProfile();
		driver = new FirefoxDriver(firefoxBinary, firefoxProfile);
	}

	public static void main(String[] args) throws InterruptedException {
		new SeleliumDemo().getCookes();
	}

	public void getCookes() throws InterruptedException {
		String url ="https://www.amazon.com/ss/help/contact/?_encoding=UTF8&marketplaceID=ATVPDKIKX0DER&ref_=v_sp_contact_seller&sellerID=A3SQJGY68K7HLU";
		//driver.manage().addCookie(cookie);
		driver.get(url);
		driver.findElement(By.xpath("//input[@id='ap_email']")).sendKeys("1836318977@qq.com");
		driver.findElement(By.xpath("//input[@id='ap_password']")).sendKeys("Songmeihong123");
		driver.findElement(By.xpath("//input[@id='signInSubmit']")).click();
	    driver.findElement(By.xpath("//input[@id='continue']")).click();
	}
}
