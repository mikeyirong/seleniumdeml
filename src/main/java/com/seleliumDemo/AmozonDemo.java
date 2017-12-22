package com.seleliumDemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.seleliumDemo.util.Utils;

public class AmozonDemo {
	public static void main(String[] args) {
		Map<String, String> cookiesMap = Utils.getCookies();
		AmozonDemo test = new AmozonDemo();
		String asgin = "B00MH4QI26";
		test.addCar(asgin, cookiesMap,test.getParamList(asgin));
	}

	public Header[] addCar(String asgin, Map<String, String> cookiesMap, List<NameValuePair> formparams) {
		String url = "https://www.amazon.com/gp/consumables/add-to-cart-ajax.html/ref=dp_start-bbf_1_glance";
		Iterator<Entry<String, String>> entry = cookiesMap.entrySet().iterator();
		String cookies = "";
		while (entry.hasNext()) {
			Map.Entry<String, String> ss = entry.next();
			cookies += ss.getKey() + "=" + ss.getValue() + ";";
		}
		cookies = cookies.substring(0, cookies.length());
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Accept", "*/*");
		headerMap.put("Accept-Encoding", "gzip, deflate, br");
		headerMap.put("Accept-Language", "zh-CN,zh;q=0.8");
		headerMap.put("Content-Type", "application/x-www-form-urlencoded");
		headerMap.put("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");
		headerMap.put("Origin", "https://www.amazon.com");
		headerMap.put("X-Requested-With", "XMLHttpRequest");
		headerMap.put("Cookie","skin=noskin; x-wl-uid=1oMxlGSpfKzzakx2y+1rHF04pgUGfZnlWi5n44spRgr4UJmfA0si7elRAIzeOanfTrTIGmjGhHS28w9+mTjh9/y+/QPnpP8kals5uz+qb+EvqY1y9FeJCvAgsErNH2S/nx58BHBEU/7U=; JSESSIONID=74B6DBC769F5054923C96BAE3CE48AC5; session-token=jaeFMmHIV6QnlZF7v3KrenJbfMRuS09h9IBlOtiW9b3hMTl+vFqFTLSoCZU9VtkPengWl/Lu0NNb1vZgbbHujbqs2N6pSeuGU9Cb34ufgnFNAkaWwQaT+fjBu9Qstl2KokI6qdMa8ZjlQbNQXdw4jXorb6vvRyfKpR3aVx7VD8ktzdi3V0/Wlxp+t1W644f0Voo9+fKi6RMQGT6YVzt1KQeTIU/TsqR8dtNt/qtUHJEgj4YY5ynJUxvDDyndcM5w; csm-hit=9NXGAVH2N3V8XTVX8ZKS+s-8BMGRHYFV623DP1RS7M7|1505312601186; ubid-main=130-3777699-5681367; session-id-time=2082787201l; session-id=132-8284613-9873629");
		Header[] header = InternetIO.post(url, formparams, headerMap);
		return header;
	}

	public void updateNum(String asgin, Map<String, String> cookiesMap) {
		String updateUrl = "https://www.amazon.com/gp/cart/ajax-update.html/ref=ox_sc_update_quantity_1%7C1%7C999";
		Map<String, String> headerMap = new HashMap<String, String>();
		Iterator<Entry<String, String>> entry = cookiesMap.entrySet().iterator();
		String cookies = "";
		while (entry.hasNext()) {
			Map.Entry<String, String> ss = entry.next();
			cookies += ss.getKey() + "=" + ss.getValue() + ";";
		}
		cookies = cookies.substring(0, cookies.length());
		headerMap.put("Accept", "application/json, text/javascript, */*; q=0.01");
		headerMap.put("Accept-Encoding", "gzip, deflate, br");
		headerMap.put("Accept-Language", "zh-CN,zh;q=0.8");
		headerMap.put("Connection", "keep-alive");
		headerMap.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8;");
		headerMap.put("Host", "www.amazon.com");
		headerMap.put("Origin", "https://www.amazon.com");
		headerMap.put("Referer", "https://www.amazon.com/gp/cart/view.html/ref=nav_cart");
		headerMap.put("User-Agent",
				"ozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");
		headerMap.put("X-AUI-View", "Desktop");
		headerMap.put("X-Requested-With", "XMLHttpRequest");
		headerMap.put("Cookie", cookies);
		//
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("hasMoreItems", "0"));
		formparams.add(new BasicNameValuePair("timeStamp", "1505206709"));
		formparams.add(
				new BasicNameValuePair("token", "gNx+yKfTndHI0k5EOUqUGoJxB8ddi1/d5WB67mMAAAAJAAAAAFm3obVyYXcAAAAA"));
		formparams.add(new BasicNameValuePair("requestID", "86C71X2J4Q0VV8K8ST47"));
		formparams.add(new BasicNameValuePair("activeItems", "C1I2EWR0BJI3LO|1|0|1|11.15|||0||"));
		formparams.add(new BasicNameValuePair("addressId", "new"));
		formparams.add(new BasicNameValuePair("addressZip", ""));
		formparams.add(new BasicNameValuePair("closeAddonUpsell", "1"));
		formparams.add(new BasicNameValuePair("quantity.C1I2EWR0BJI3LO", "999"));
		formparams.add(new BasicNameValuePair("pageAction", "update-quantity"));
		formparams.add(new BasicNameValuePair("submit.update-quantity.C1I2EWR0BJI3LO", "1"));
		formparams.add(new BasicNameValuePair("actionItemID", "C1I2EWR0BJI3LO"));
		formparams.add(new BasicNameValuePair("asin", asgin));
		formparams.add(new BasicNameValuePair("encodedOffering",
				"EOAW5thvYT4wlAmJJqOTzpFRglvmR5Ag9TnxBs9yEAAYLpobdL5WR5BHe%2BFWSw7ai%2Fp5RLdr0of7jRGET%2F1akzNRh41lhmIWsTMQwSya0YfyMabaLUQdeg%3D%3D"));
		InternetIO.post(updateUrl, formparams, headerMap);
	}

	public List<NameValuePair> getParamList(String asgin) {
		List<NameValuePair> paramlist = new ArrayList<NameValuePair>();
		String url = "https://www.amazon.com/dp/" + asgin;
		String ss = InternetIO.get(url);
		Document dom = Jsoup.parse(ss);
		Elements eles = dom.getElementById("addToCart").getElementsByTag("input");
		System.out.println(eles.size());
		for (int i = 0; i < eles.size() - 3; i++) {
			Element element = eles.get(i);
			String name= element.attr("name");
			if (name!=""&&!name.startsWith("submit")) {
				System.out.println(element.attr("name")+"=============="+element.attr("value"));
				paramlist.add(new BasicNameValuePair(element.attr("name"), element.attr("value")));
			}
		}
		paramlist.add(new BasicNameValuePair("rcxsubsQuan","1"));
		paramlist.add(new BasicNameValuePair("rcxOrdFreq","2"));
		paramlist.add(new BasicNameValuePair("quantity","1"));
		return paramlist;
	}

}
