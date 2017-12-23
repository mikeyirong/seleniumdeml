package com.seleliumDemo.util;

import java.io.File;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class SeleliumDemo {
	private File pathToBinary;
	private FirefoxBinary firefoxBinary;
	private FirefoxProfile firefoxProfile;
	private WebDriver driver;

	@SuppressWarnings("deprecation")
	public SeleliumDemo() {
		System.setProperty("webdriver.gecko.driver", "D:\\install\\webdriver\\geckodriver.exe");
		// 安装路径
		this.pathToBinary = new File("D:\\Mozilla Firefox\\firefox.exe");
		this.firefoxBinary = new FirefoxBinary(pathToBinary);
		this.firefoxProfile = new FirefoxProfile();
		this.driver = new FirefoxDriver(firefoxBinary, firefoxProfile);
	}

	public static void main(String[] args) {
		String seller = "A2N01J8SZ7W4B3";
		String seller1 = "A334XHS1C5Y12J";
		String seller2 = "A39YHAI3NCE75F";
		String seller3 = "A21371WWGM2SV9";
		String url = "https://www.amazon.com/sp?_encoding=UTF8&asin=B00YD545CC&seller=";
		SeleliumDemo sele = new SeleliumDemo();
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			try {
				sele.getCookes(url + seller);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("spendtime is==============" + ((endTime - startTime)/1000/60));
	}

	public Set<Cookie> getCookes(String url) throws Exception{
		driver.get(url);
//		String askUrl = driver.findElement(By.xpath("//a[@id='seller-contact-button-announce']")).getAttribute("href");
//		driver.get(askUrl);
		String currentUrl = driver.getCurrentUrl();
		if (currentUrl.startsWith("https://www.amazon.com/ap/signin")) {
			driver.findElement(By.xpath("//input[@id='ap_email']")).sendKeys("1135556405@qq.com");
			driver.findElement(By.xpath("//input[@id='ap_password']")).sendKeys("Yirongshao1");
			driver.findElement(By.xpath("//input[@id='signInSubmit']")).click();
		}
//		driver.findElement(By.xpath("//span[contains(@id,'a-autoid-0')]")).click();
//		driver.findElement(By.xpath("//a[@id='preOrderSubject_4']")).click();
//		driver.findElement(By.xpath("//input[@name='writeButton']")).click();
//		driver.findElement(By.xpath("//textarea[@id='comment']")).sendKeys("hello word");
//		driver.findElement(By.xpath("//button[@id='a-autoid-1-announce']")).click();
		Set<Cookie> cookieSet= driver.manage().getCookies();
		driver.close();
		return cookieSet;
	}
}
