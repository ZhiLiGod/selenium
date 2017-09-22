package com.zhi.selenium.demo;

import org.jsoup.nodes.Document;

import cn.edu.hfut.dmic.webcollector.crawler.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;

public class SeleniumCrawler2 extends BreadthCrawler {
	public SeleniumCrawler2(String crawlPath, 
			boolean autoParse){
		super(crawlPath, autoParse);
		this.addRegex("http://item.jd.com/.*.html");
	}

	@Override
	public void visit(Page page, 
			Links nextLinks) {
		// TODO Auto-generated method stub
		Document doc = page.getDoc();
		String title = doc.title();
		System.out.println("URL: " + page.getUrl() + " Title: " + title);
		
		
	}
	
	public static void main(String[] args) throws Exception {
		SeleniumCrawler2 crawler = new SeleniumCrawler2("D:\\Selenium_Crawler\\demo", 
				true);
		crawler.setThreads(50);//set threads
		crawler.addSeed("http://list.jd.com/list.html?cat=1319,1523,7052&page=1&go=0&JL=6_0_0");
		crawler.setResumable(false);//if set break-point
		crawler.setTopN(1000);
		crawler.start(2);
		
	}
	
	
}
