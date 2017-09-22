package com.zhi.crawler.jd;

import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;


import com.zhi.crawler.ECCrawler;
import com.zhi.crawler.Goods;

public class JDCrawler extends ECCrawler {
	
	private JDGoodsList goodsList;
	
	public JDCrawler(String crawlPath, String seedFormat) {
		super(crawlPath, seedFormat);
		// TODO Auto-generated constructor stub
		goodsList = new JDGoodsList();
		
	}

	@Override
	public int getTotalPage(Page page) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public void visit(Page page, Links links) {
		System.out.println("url:"+page.getUrl()+"\tlinks size:"+links.size());  
        goodsList.addGoods(page);  
	}
	
	public static void main(String[] args) throws Exception {
		JDCrawler crawler = new JDCrawler("D:\\Selenium_Crawler\\crawler\\JD", 
				"http://list.jd.com/list.html?cat=1319,1523,7052&page=%s&go=0&JL=6_0_0");
		//crawler.addSeed("http://item.jd.com/.*.html");
		crawler.setThreads(100);
		crawler.start(1);
		crawler.print();
	}
	
	protected void print(){
		for(Goods g : goodsList){
			System.out.println(g);
		}
	}
	
}
