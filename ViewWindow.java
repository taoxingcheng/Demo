package main;

import java.awt.*;
import java.awt.event.*;
import java.awt.Frame;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

public class ViewWindow {
  


	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Frame f = new Frame("豆瓣图书搜索");
		f.setLocation(300, 300);
		f.setSize(500, 500);
		f.setVisible(true);
		f.setLayout(new GridLayout(0,1));
		
		//建立输入图书ISBN码项
		Panel panelISBN = new Panel();
		final TextField textISBN = new TextField(10);
		Button butt = new Button("搜索");
		panelISBN.add(new Label("请输入图书ISBN:"));
		panelISBN.add(textISBN);
		panelISBN.add(butt);
		f.add(panelISBN);
		
		//
		Panel panelName = new Panel();
		final TextField bookName = new TextField(10);
		bookName.setEnabled(false);
		panelName.add(new Label("图书名:"));
		panelName.add(bookName);
//		f.add(new Label("图书名:"));
		f.add(panelName);
		//
		final TextField authorName = new TextField(10);
		f.add(new Label("图书作者:"));
		f.add(authorName);
		//
		final TextField bookIsbn10 = new TextField(10);
		f.add(new Label("图书ISBN10码:"));
		f.add(bookIsbn10);
		//
		final TextField bookIsbn13 = new TextField(10);
		f.add(new Label("图书ISBN13码:"));
		f.add(bookIsbn13);
		//
		final TextField bookPage = new TextField(10);
		f.add(new Label("图书页数:"));
		f.add(bookPage);
		//
		final TextField bookPrice = new TextField(10);
		f.add(new Label("图书价格:"));
		f.add(bookPrice);
		//
		final TextField bookBinding = new TextField(10);
		f.add(new Label("图书装订方式:"));
		f.add(bookBinding);
		//
		final TextField publisher = new TextField(10);
		f.add(new Label("图书出版社:"));
		f.add(publisher);
		//
		final TextField pubdate = new TextField(10);
		f.add(new Label("图书出版日期:"));
		f.add(pubdate);
		//
		final TextField bookDetail = new TextField(10);
		f.add(new Label("图书内容概要:"));
		f.add(bookDetail);
		//设置按钮监听
		butt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Books book;
				ISBN isbnTest = new ISBN();
				String isbn = textISBN.getText();//获取输入的图书ISBN码
				String bookjson;
				try {
					bookjson = isbnTest.fetchBookInfoByXML(isbn);
					JSONObject jsonobj=isbnTest.stringToJson(bookjson);				    
				    book = (Books)isbnTest.setBookData();
				    bookName.setText(book.getTitle());
				    authorName.setText(book.getAuthor());
				    bookIsbn10.setText(book.getIsbn10());
				    bookIsbn13.setText(book.getIsbn13());
				    bookBinding.setText(book.getBinding());
				    bookDetail.setText(book.getSummary());
				    bookPage.setText(book.getPage());
				    bookPrice.setText(book.getPrice());
				    publisher.setText(book.getPublisher());
				    pubdate.setText(book.getPubdate());
				    
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (JSONException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			    
			}
		});
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent args) {
			   System.exit(0);
			}
		});
		//f.setResizable(false);
	}

}
