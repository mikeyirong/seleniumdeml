package com.seleliumDemo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public abstract class InternetIO {
	
	public static String get(String url) {
		String content="";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet get =new HttpGet(url);
		get.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        //get.setHeader("Accept-Encoding", "gzip, deflate, br");
		get.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
		get.setHeader("Cache-Control", "max-age=0");
		get.setHeader("Connection", "keep-alive");
		get.setHeader("Host", "www.amazon.com");
		get.setHeader("Upgrade-Insecure-Requests","1");
		get.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");
		CloseableHttpResponse respon;
		try {
			respon = httpclient.execute(get);
			HttpEntity resp = respon.getEntity();
			InputStream input = resp.getContent();
	        ByteArrayOutputStream ss =new ByteArrayOutputStream();
	        int i=0;
	        byte [] b = new byte[1024];
	        do {
				i = input.read(b);
				if (i != -1)
					ss.write(b, 0, i);
			} while (i != -1);
            input.close();
	        content =new String(ss.toByteArray());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}

	public static Header[] post(String url, List<NameValuePair> formparams,Map<String,String> headerMap) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		Header[]  storeCookie = null;
		HttpPost post = new HttpPost(url);
		Iterator<Entry<String, String>> headerEntry=headerMap.entrySet().iterator();
		while(headerEntry.hasNext()) {
			Entry<String, String> ss = headerEntry.next();
			post.setHeader(ss.getKey(),ss.getValue());
		}
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			post.setEntity(uefEntity);
			CloseableHttpResponse respon = httpclient.execute(post);
			storeCookie = respon.getHeaders("Set-cookie");
			HttpEntity resp = respon.getEntity();
			System.out.println("执行中"+toString(resp.getContent()));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		
		}
		return storeCookie;
	}
    
	public static String toString(InputStream in) throws IOException {
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[1024];
		for (int n; (n = in.read(b)) != -1;) {
			out.append(new String(b, 0, n));
		}
		return out.toString();
	}
}
