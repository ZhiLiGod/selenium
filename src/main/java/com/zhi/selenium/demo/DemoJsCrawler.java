package com.zhi.selenium.demo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.zhi.selenium.util.PageUtils;

import cn.edu.hfut.dmic.webcollector.crawler.DeepCrawler;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;

public class DemoJsCrawler extends DeepCrawler{
	public DemoJsCrawler(String crawlPath){
		super(crawlPath);
	}

	@Override
	public Links visitAndGetNextLinks(Page page) {
		handleByPhantomJsDriver(page);
		//handleByHtmlUnitDriver(page);  
		return null;
	}
	
	protected void handleByHtmlUnitDriver(Page page){  
        
	     HtmlUnitDriver driver=PageUtils.getDriver(page,BrowserVersion.CHROME);  
	     print(driver);  
   }  

	protected void handleByPhantomJsDriver(Page page) {
		WebDriver driver = PageUtils.getWebDriver(page);
		print(driver);
		//driver.quit();
		
	}

	private void print(WebDriver driver) {
		List<WebElement> divInfos = driver.findElements(By.cssSelector("li.gl-item"));
		for(WebElement divInfo : divInfos){
			WebElement price = divInfo.findElement(By.className("J_price"));
			System.out.println(price + ":" + price.getText());
		}
			
	}
	
	public static void main(String[] args) throws Exception {
		DemoJsCrawler crawler = new DemoJsCrawler("D:\\Selenium_Crawler\\demo");
		crawler.addSeed("http://list.jd.com/list.html?cat=1319,1523,7052&page=1&go=0&JL=6_0_0");
		crawler.start(1);
	}
	
	
}
