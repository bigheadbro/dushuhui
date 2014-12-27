package com.dushuhui.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.dushuhui.entity.DouBookEntity;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class Util {

	//从豆瓣获取搜索的书
	public static List<DouBookEntity> searchBooks(String param)
	{
		List<DouBookEntity> books = new ArrayList<DouBookEntity>();
		URLConnection connection = null;
        try {
            connection = new URL("https://api.douban.com/v2/book/search?q="+param).openConnection();
            connection.connect();
 
            InputStream fin = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(fin));
            StringBuffer buffer = new StringBuffer();
            String temp = null;
            while ((temp = br.readLine()) != null) {
                buffer.append(temp);
            }
            JSONObject obj = JSONObject.fromObject(buffer.toString());
            JSONArray jsonArray = obj.getJSONArray("books");
            for (int i = 0; i < jsonArray.size(); i++)
            {
            	DouBookEntity book = new DouBookEntity();
            	JSONObject obj1 = JSONObject.fromObject(jsonArray.get(i).toString());
            	book.title = obj1.getString("title");
            	book.author = obj1.getString("author");
            	book.isbn = obj1.getString("isbn13");
            	book.pubdate = obj1.getString("pubdate");
            	book.subtitle = obj1.getString("subtitle");
            	book.alt = obj1.getString("alt");
            	book.publisher = obj1.getString("publisher");
            	book.summary = obj1.getString("summary");

            	book.authorIntro = obj1.getString("author_intro");
            	book.catalog = obj1.getString("catalog");
            	book.translator = obj1.getString("translator");
            	book.pages = obj1.getString("pages");
            	book.average = JSONObject.fromObject(obj1.getString("rating")).getString("average");
            	JSONObject images = JSONObject.fromObject(obj1.getString("images"));
            	book.image.small = images.getString("small");
            	book.image.large = images.getString("large");
            	book.image.medium = images.getString("medium");
            	
            	books.add(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return books;
	}
	
	public static void main(String args[]) { 
    } 
}
