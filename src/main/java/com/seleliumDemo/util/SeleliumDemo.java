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

	public static void main(String[] args){
		String url = "https://www.amazon.com/ss/help/contact/writeMessage?writeButton=%E6%8F%90%E4%BA%A4&sellerID=A18A938UBK28OZ&language=en_US";
		SeleliumDemo sele = new SeleliumDemo();
	    sele.getCookes(url);
	}

	public Set<Cookie> getCookes(String url){
		driver.get(url);
		String currentUrl = driver.getCurrentUrl();
		//1836318977@qq.com,Songmeihong123
		if (currentUrl.startsWith("https://www.amazon.com/ap/signin")) {
			driver.findElement(By.xpath("//input[@id='ap_email']")).sendKeys("1836318977@qq.com");
			driver.findElement(By.xpath("//input[@id='ap_password']")).sendKeys("Songmeihong123");
			driver.findElement(By.xpath("//input[@id='signInSubmit']")).click();
		}
		if(driver.getCurrentUrl().startsWith("https://www.amazon.com/ap/cvf/request")) {
			System.out.println("需要发送验证码！");
			String clas= driver.findElement(By.xpath("//input[@id='continue']")).getAttribute("class");
			driver.findElement(By.xpath("//input[@id='continue']")).click();
  		    String valicode = EmailUtil.getvalicode("1836318977@qq.com", "xcihobwkgroydigc");
			driver.findElement(By.xpath("//input[@name='code']")).sendKeys(valicode);
			System.out.println(clas);
		}
//		driver.findElement(By.xpath("//span[contains(@id,'a-autoid-0')]")).click();
//		driver.findElement(By.xpath("//a[@id='preOrderSubject_4']")).click();
//		driver.findElement(By.xpath("//input[@name='writeButton']")).click();
//		driver.findElement(By.xpath("//textarea[@id='comment']")).sendKeys("hello word");
//		driver.findElement(By.xpath("//button[@id='a-autoid-1-announce']")).click();
		Set<Cookie> cookieSet= driver.manage().getCookies();
		//driver.close();
		return cookieSet;
	}
}
