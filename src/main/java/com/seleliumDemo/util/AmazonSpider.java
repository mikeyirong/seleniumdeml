package com.seleliumDemo.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.openqa.selenium.Cookie;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
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
		if(page.getRequest().getMethod()=="POST") {
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
		String actionUrl="https://www.amazon.com"+page.getHtml().xpath("//form[@id='sendEmailForm']/@action").get();
		List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
		Selectable sele = page.getHtml().xpath("//form[@id='sendEmailForm']");
		List<Selectable> inputList = sele.xpath("//form/input[@type='hidden']").nodes();
		for (Selectable inputSele : inputList) {
			String name = inputSele.xpath("//input/@name").get();
			String value = inputSele.xpath("//input/@value").get();
			System.out.println(name+"====="+value);
			BasicNameValuePair pair = new BasicNameValuePair(name, value);
			pairList.add(pair);
		}
		pairList.add(new BasicNameValuePair("comment", "hello world"));		
		NameValuePair[] values1 = pairList.toArray(new NameValuePair[] {});
		Map<String, Object> params = new HashMap<String, Object>();
		Request request = new Request();
		params.put("nameValuePair", values1);
		request.setUrl(actionUrl);
		request.setExtras(params);
		request.setMethod(HttpConstant.Method.POST);
		page.addTargetRequest(request);
	}
	public static void main(String[] args) throws Exception {
	   String url="https://www.amazon.com/ss/help/contact/writeMessage?writeButton=%E6%8F%90%E4%BA%A4&subject=5&orderID=&sellerID=A334XHS1C5Y12J&asin=&marketplaceID=ATVPDKIKX0DER&language=en_US";
       Set<Cookie> setCookie = new SeleliumDemo().getCookes(url);
       for(Cookie cookie:setCookie) {
    	  site.addCookie(cookie.getName(), cookie.getValue());
       }
       Request req = new Request();
       req.setMethod(HttpConstant.Method.GET);
       req.setUrl(url);
       Spider.create(new AmazonSpider()).thread(1).addRequest(req).run();
	}
	
}
