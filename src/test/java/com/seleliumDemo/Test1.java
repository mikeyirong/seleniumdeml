package com.seleliumDemo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test1 {
  public static void main(String[] args) {
	System.out.println(cleanAvgStar("平均 5.0 星","CN"));
}
  public static String cleanAvgStar(String reviewStar, String siteCode) {
     

      String star = group(reviewStar, "([0-9.,]+)", 1);

      if (star.contains(",")) {
          star = star.replace(", ", ".");
      }

      return star;
  }
  public static String group(String src, String regex, int group) {
      Matcher matcher = Pattern.compile(regex).matcher(src);
      if (matcher.find()) {
          return matcher.group(group);
      }
      return "";
  }
}
