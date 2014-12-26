package com.dushuhui.entity;

import java.io.Serializable;

public class DouBookEntity implements Serializable {

	private static final long serialVersionUID = 5048997449212366892L;
	public String title;
	public String author;
	public String isbn;
	public String pubdate;
	public String subtitle;
	public String alt;
	public String publisher;
	public String summary;
	public String authorIntro;
	public String catalog;
	public String translator;
	public String pages;
	public String average;
	public BookImage image = new BookImage();
	
	public static class BookImage {
		public String small;
		public String large;
		public String medium;
	}
}
