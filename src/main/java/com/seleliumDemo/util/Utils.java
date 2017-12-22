package com.seleliumDemo.util;

import java.util.HashMap;
import java.util.Map;

public class Utils {
	public static Map<String, String> getCookies() {
		String cookieStr = "skin=noskin; x-wl-uid=1oMxlGSpfKzzakx2y+1rHF04pgUGfZnlWi5n44spRgr4UJmfA0si7elRAIzeOanfTrTIGmjGhHS28w9+mTjh9/y+/QPnpP8kals5uz+qb+EvqY1y9FeJCvAgsErNH2S/nx58BHBEU/7U=; JSESSIONID=74B6DBC769F5054923C96BAE3CE48AC5; session-token=jaeFMmHIV6QnlZF7v3KrenJbfMRuS09h9IBlOtiW9b3hMTl+vFqFTLSoCZU9VtkPengWl/Lu0NNb1vZgbbHujbqs2N6pSeuGU9Cb34ufgnFNAkaWwQaT+fjBu9Qstl2KokI6qdMa8ZjlQbNQXdw4jXorb6vvRyfKpR3aVx7VD8ktzdi3V0/Wlxp+t1W644f0Voo9+fKi6RMQGT6YVzt1KQeTIU/TsqR8dtNt/qtUHJEgj4YY5ynJUxvDDyndcM5w; csm-hit=9NXGAVH2N3V8XTVX8ZKS+s-VM10F9FDBJNQZDWK3K0K|1505310316286; ubid-main=130-3777699-5681367; session-id-time=2082787201l; session-id=132-8284613-9873629";
		HashMap<String, String> map = new HashMap<String, String>();
		String[] re = cookieStr.split(";");
		System.out.println(re.length);
		for (int i = 0; i < re.length; i++) {
			String[] temp = re[i].split("=");
			map.put(temp[0], temp[1]);
		}
		return map;
	}
}
