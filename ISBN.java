package main;

import java.io.*;
import java.net.*;

import org.json.JSONException;
import org.json.JSONObject;


class ISBN {
  String isbnUrl = "https://api.douban.com/v2/book/isbn/:";
	JSONObject jsonobj;
	Books book;
    /*
     * 根据传递的图书ISBN号利用豆瓣API获取json字符串
     */
	public String fetchBookInfoByXML(String isbnNo) throws IOException  {    
	    String requestUrl = isbnUrl + isbnNo+"?alt=json";    
	    URL url = new URL(requestUrl);    
	    URLConnection conn = url.openConnection();    
	    InputStream is = conn.getInputStream();    
	    InputStreamReader isr = new InputStreamReader(is, "utf-8");    
	    BufferedReader br = new BufferedReader(isr);    
	    StringBuilder sb = new StringBuilder();    
	        
	    String line = null;    
	    while ((line = br.readLine()) != null) {    
	        sb.append(line);    
	    }    
	        
	    br.close();    
	    return sb.toString();    
	}
	//把json字符串转换为json
	public JSONObject stringToJson(String str) throws JSONException
	{
		jsonobj = new JSONObject(str);
		return jsonobj;
	}
	//设置获取的图书信息并返回对象
	public Books setBookData() throws JSONException
	{
		book = new Books();
		String name = jsonobj.getString("title");
		book.setTitle(name);//设置书名
		
		String author = jsonobj.getString("author");
		book.setAuthor(author);//设置作者姓名
		
		String isbn10 = jsonobj.getString("isbn10");
		book.setIsbn10(isbn10);//设置ISBN10
		
		String isbn13 = jsonobj.getString("isbn13");
		book.setIsbn13(isbn13);//设置ISBN13
		
		String pages = jsonobj.getString("pages");
		book.setPage(pages);//设置图书页数
		
		String price = jsonobj.getString("price");
		book.setPrice(price);//设置图书价格
		
		String binding = jsonobj.getString("binding");
		book.setBinding(binding);//设置装订方式
		
		String publisher = jsonobj.getString("publisher");
		book.setPublisher(publisher);//设置出版社
		
		String pubdate = jsonobj.getString("pubdate");
		book.setPubdate(pubdate);//设置出版日期
		
		String summary = jsonobj.getString("summary");
		book.setSummary(summary);//设置图书概要信息
		return book;
	}	
}
