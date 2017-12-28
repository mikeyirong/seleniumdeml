package com.seleliumDemo;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class SeleniumAmazon {
	private File pathToBinary;
	private FirefoxBinary firefoxBinary;
	private FirefoxProfile firefoxProfile;
	private WebDriver driver;

	@SuppressWarnings("deprecation")
	public SeleniumAmazon() {
		System.setProperty("webdriver.gecko.driver", "D:\\install\\webdriver\\geckodriver.exe");

		// 安装路径
		this.pathToBinary = new File("D:\\Mozilla Firefox\\firefox.exe");
		this.firefoxBinary = new FirefoxBinary(pathToBinary);
		this.firefoxProfile = new FirefoxProfile();
		this.driver = new FirefoxDriver(firefoxBinary, firefoxProfile);
	}

	public static void main(String[] args) {
		String url = "https://login.aliexpress.com/?flag=1&return_url=http%3A%2F%2Fmyae.aliexpress.com%2Fseller%2Findex.htm%3Fspm%3D2114.11010108.1000001.4.464431423RBRaD";
		new SeleniumAmazon().getTest(url);
	}

	public void getTest(String url) {
		driver.get(url);
		WebElement jfram = driver.findElement(By.tagName("iframe"));
		//WebElement name = driver.findElement(By.tagName("iframe")).findElement(By.xpath("//input[@id='fm-login-id']"));
		System.out.println(jfram.toString());
		driver.close();
	}
}
