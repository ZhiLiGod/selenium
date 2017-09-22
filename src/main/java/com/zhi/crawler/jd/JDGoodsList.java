package com.zhi.crawler.jd;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cn.edu.hfut.dmic.webcollector.model.Page;

import com.zhi.crawler.Goods;
import com.zhi.crawler.GoodsList;
import com.zhi.selenium.util.PageUtils;

public class JDGoodsList extends GoodsList {
	private static final long serialVersionUID = -7487110223660262262L;
	
	@Override
	public void addGoods(Page page) {
		// TODO Auto-generated method stub
		WebDriver driver = null;
		try{
			driver = PageUtils.getWebDriver(page);
			List<WebElement> eles = driver.findElements(By.cssSelector("li.gl-item"));
			if(!eles.isEmpty()){
				for(WebElement ele : eles){
					Goods g = new Goods();
					g.setPlatform(Platform.JD);
					String priceStr = ele.findElement(By.className("p-price"))
							.findElement(By.className("J_price"))
							.findElement(By.tagName("i"))
							.getText();
					if(Tools.notEmpty(priceStr)){
						g.setPrice(Float.parseFloat(priceStr));
					}else{
						g.setPrice(-1f);
					}
					
					g.setName(ele.findElement(By.className("p-name"))
							.findElement(By.tagName("em")).getText());
					g.setUrl(ele.findElement(By.className("p-name"))
							.findElement(By.tagName("a"))
							.getAttribute("href"));
					String commitStr = ele.findElement(By.className("p-commit"))
							.findElement(By.tagName("a"))
							.getText();
					if(Tools.notEmpty(commitStr)){
						g.setCommit(commitStr);
					}else{
						g.setCommit(null);
					}
					
					add(g);
					
				}
			}else{
				System.out.println("else is empty");
			}
		}catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}finally{
			if(driver != null){
				driver.quit();
			}
		}
	}

}
