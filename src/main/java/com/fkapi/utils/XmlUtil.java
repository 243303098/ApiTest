package com.fkapi.utils;

import java.io.File;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.testng.Reporter;

public class XmlUtil {
	public static Object[][] readXMLDocument(String path) throws Exception {

		File file = new File(path);
		if (!file.exists()) {
			Reporter.log("Can't find " + path);
			// throw new IOException("Can't find " + path);
		}
		Document document = Jsoup.parse(file, "UTF-8");
		Elements element = document.getElementsByTag("parameter");
		Object[][] dateMap = new Object[element.size()][element.get(0).attributes().size()];
		for (int i = 0; i < element.size(); i++) {
			int j=0;
			List<Attribute> list = element.get(i).attributes().asList();
			for(Attribute attr:list){
				dateMap[i][j] = attr.getValue();
				j++;
			}
		}
		return dateMap;
	}

}
