package com.seleliumDemo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Test {
	public static void main(String[] args) throws IOException {
		System.out.println(new Test().keepaTimeMinutesToTime("3323110"));
		System.out.println(new Test().keepaTimeMinutesToTime("3532420"));
		FileInputStream fis = new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\stock.txt"));
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		// 将file文件内容转成字符串
		@SuppressWarnings("resource")
		BufferedReader bf = new BufferedReader(isr);
		String content = "";
		StringBuilder sb = new StringBuilder();
		while (content != null) {
			content = bf.readLine();
			if (content == null) {
				break;
			}
			sb.append(content.trim());
		}
		JSONObject stockJson = JSON.parseObject(sb.toString());
		JSONArray offerArr = stockJson.getJSONArray("products").getJSONObject(0).getJSONArray("offers");
		System.out.println("offerArr--------"+offerArr);
		JSONArray stockArr = new JSONArray();
		for (int i = 0; i < offerArr.size(); i++) {
			JSONArray stockArrA = offerArr.getJSONObject(i).getJSONArray("stockCSV");
			if (stockArr.size() < stockArrA.size()) {
				stockArr = stockArrA;
			}
		}
		long stockDif = 0; // 库存差
		for (int i = 1; i < stockArr.size() - 2;) {
			Long difNum = stockArr.getLong(i) - stockArr.getLong(i + 2);
			if (difNum < 0) {
				stockDif = stockDif+0;
			} else {
				stockDif = stockDif + difNum;
			}
			i = i + 2;
		}
		long timeDif =stockArr.getLong(stockArr.size()-2)-stockArr.getLong(0);
		System.out.println("stockDif=============="+stockDif+"=============="+timeDif);
	}
    private static String keepaTimeMinutesToTime(String time) {
        long l1 = (Long.valueOf(time) + 21564000L) * 60000L;
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(l1));
    }
    
}
