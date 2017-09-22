package com.zhi.crawler;

import java.util.ArrayList;

import cn.edu.hfut.dmic.webcollector.model.Page;

public abstract class GoodsList extends ArrayList<Goods>{
	private static final long serialVersionUID = -6935403464055289581L;  
	  
    public abstract void addGoods(Page page);  
}
