package com.mindagile.questiongenerator.nlp;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WikiPediaHelper {

	public static void main(String args[]) throws IOException {
		System.out.println(getWikiPediaString("https://en.wikipedia.org/wiki/Amitabh_Bachchan"));
	}

	public static String getWikiPediaString(String url) throws IOException {
		String result = "";
		Document doc = Jsoup.connect(url).get();
		Elements es = doc.getAllElements();
		for (Element e : es) {
			if (e.tagName().equalsIgnoreCase("p"))
				result = result + "\n" + Jsoup.parse(e.toString()).text();
		}
		
		return result.replaceAll("\\[\\d+\\]", "").trim();
	}

}
