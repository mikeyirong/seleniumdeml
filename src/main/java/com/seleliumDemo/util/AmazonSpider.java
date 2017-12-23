package com.seleliumDemo.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Set;

import org.openqa.selenium.Cookie;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant;

public class AmazonSpider implements PageProcessor {
	static Site site = Site.me().setSleepTime(1000).setTimeOut(1000);

	@Override
	public Site getSite() {
		site.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		site.addHeader("Accept-Encoding", "gzip, deflate, br");
		site.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		site.addHeader("Cache-Control", "max-age=0");
		site.addHeader("Connection", "keep-alive");
		site.addHeader("Host", "www.amazon.com");
		site.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
		return this.site;
	}

	@Override
	public void process(Page page) {
		String html = page.getHtml().get();
		try {
			PrintStream printStream = new PrintStream(new FileOutputStream("C:\\Users\\Administrator\\Desktop\\amazon.html"));
			printStream.println(html);
			printStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) throws Exception {
	   String url="https://www.amazon.com/ss/help/contact/writeMessage?writeButton=%E6%8F%90%E4%BA%A4&subject=5&orderID=&sellerID=A334XHS1C5Y12J&asin=&marketplaceID=ATVPDKIKX0DER&language=en_US";
       Set<Cookie> setCookie = new SeleliumDemo().getCookes(url);
       System.out.println(setCookie.size());
       for(Cookie cookie:setCookie) {
    	  //System.out.println(cookie.getName()+"========"+cookie.getValue());
    	  site.addCookie(cookie.getName(), cookie.getValue());
       }
       Request req = new Request();
       req.setMethod(HttpConstant.Method.GET);
       req.setUrl(url);
       Spider.create(new AmazonSpider()).thread(1).addRequest(req).run();
	}
	
	public static void writeFile(InputStream stream) throws Exception {
		FileOutputStream out = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\amazon.html"));
		byte[] by = new byte[1024];
		int length = 0;
		do {
			length = stream.read(by);
			if (length != -1) {
				out.write(by, 0, length);
			}
		} while (length != -1);
		out.flush();
		out.close();
	}
}
