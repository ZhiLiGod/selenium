package com.zhi.selenium.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;

import cn.edu.hfut.dmic.webcollector.model.Page;

public class PageUtils {
	public static HtmlUnitDriver getDriver(Page page){
		HtmlUnitDriver driver = new HtmlUnitDriver();
		driver.setJavascriptEnabled(true);
		driver.get(page.getUrl());
		return driver;
	}
	
	public static HtmlUnitDriver getDriver(Page page,
			BrowserVersion browserVersion){
		HtmlUnitDriver driver = new HtmlUnitDriver(browserVersion);
		driver.setJavascriptEnabled(true);
		driver.get(page.getUrl());
		return driver;
	}
	
	//get PhantomJSDriver
	public static WebDriver getWebDriver(Page page){
		System.setProperty("phantomjs.binary.path", 
				"D:\\Selenium WebDriver\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
	
		WebDriver driver = new PhantomJSDriver();
		driver.get(page.getUrl());
		return driver;
	}
	
	public static String getPhantomJSDriver(Page page)throws Exception{
		Runtime rt = Runtime.getRuntime();
		Process process = null;
		process = rt.exec("D:\\Selenium WebDriver\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe"
				+ "D:\\MyEclipseWorkplace\\seleniumTest1\\src\\main\\resources\\parser.js");
		page.getUrl().trim();
		InputStream in = process.getInputStream();
		InputStreamReader reader = new InputStreamReader(in, "UTF-8");
		BufferedReader br = new BufferedReader(reader);
		StringBuffer sbf = new StringBuffer();
		String tmp = "";
		while((tmp = br.readLine())!=null){
			sbf.append(tmp);
		}
		return sbf.toString();
	}
	
	
}
