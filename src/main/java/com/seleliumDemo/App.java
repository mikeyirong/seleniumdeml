package com.seleliumDemo;

public class App {
	public static void main(String[] args) {
		String url = "https://dcms.1688.com/open/query.json?offerImgUrl=https%3A%2F%2Fae01.alicdn.com%2Fkf%2FHTB1RAY6anZRMeJjSspkq6xGpXXaA%2FOL-Brand-Preppy-Style-Canvas-Printing-Backpack-School-Bag-for-Teenager-Girls-boys-Rucksack-backpack-T2046.jpg&app=DCMS&needStock=N&dataId=446&to=3000&n=10&fields=mdw.returnOrdRate3m002,offerOS.bizType,MBP.swtbh,MBP.esytbhfw,MBP.essxsfh,daixiao_offer_enhanced_datasource.sales30,MBP.ssbxsfh,MBP.lstbhfw,MBP.qtbh,od.url,od.unit,offerSendAddr.offerSendAddr,mbsr.scoreFhRate,offerOS.tpServiceYear,mdrs.companyName,promotionInfo.promotionList,mbsr.scoreHmRate,od.price,od.lowPrice,od.highPrice,od.subject,od.offerImgUrl,od.quantityBegin,kjTongKuan.offerId,mWinport.winportIndex,mbsr.scoreXyRate,offerOS.useAlipay&pageNo=30&sk0=616251fc220a9e3beb8fb6f7c5f39cf9&callback=jsonp_1508825663263_51620";
		String[] urlArr = url.split("?");
		System.out.println(urlArr[1]);
	}
}
