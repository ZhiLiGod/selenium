package com.zhi.crawler;

import java.util.concurrent.atomic.AtomicInteger;

import org.jsoup.nodes.Document;

import cn.edu.hfut.dmic.webcollector.crawler.DeepCrawler;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpResponse;
import cn.edu.hfut.dmic.webcollector.util.RegexRule;

public abstract class ECCrawler extends DeepCrawler {
	private String seedFormat;//format of seed;
	protected RegexRule regexRule;
	public RegexRule getRegexRule() {
		return regexRule;
	}
	public void setRegexRule(RegexRule regexRule) {
		this.regexRule = regexRule;
	}
	
	public ECCrawler(String crawlPath, String seedFormat) {
		super(crawlPath);
		this.seedFormat = seedFormat;
		this.regexRule = new RegexRule();
	}
	
	//use this id as the file name
	AtomicInteger id = new AtomicInteger(0);
	
	@Override
	public Links visitAndGetNextLinks(Page page){
		Links nextLinks = new Links();
		String conteType = page.getResponse().getContentType();
		if(conteType != null && conteType.contains("text.html")){
			Document doc = page.getDoc();
			if(doc != null){
				nextLinks.addAllFromDocument(page.getDoc(), regexRule);
			}
		}
		try{
			visit(page, nextLinks);
			
		}catch (Exception e) {
			LOG.info("Exception", e);// TODO: handle exception
		}
		return nextLinks;
	}
	
	@Override 
	public void start(int depth) throws Exception{
		addSeed();
		super.start(depth);
	}
	
	private void addSeed() throws Exception{
		int totalPage = getTotalPage(getPage(getSeed(seedFormat, 1)));
		for(int page=1;page<=totalPage;page++){  
            this.addSeed(getSeed(seedFormat, page));  
        }  
	}
	
	private Page getPage(String url) throws Exception{
		HttpRequest request = new HttpRequest(url);
		HttpResponse response = request.getResponse();
		Page page = new Page();
		page.setUrl(url);
		page.setHtml(response.getHtmlByCharsetDetect());
		page.setResponse(response);
		return page;
	}
	
	public abstract int getTotalPage(Page page);
	
	public String getSeed(String seedFormat, Object ... page){
		return String.format(seedFormat, page);
	}
	
	public abstract void visit(Page page, Links links);
}
