package com.javaex.ex01;

public class AuthorApp {

	public static void main(String[] args) {

		AuthorDao authorDao = new AuthorDao();
		
		int count = authorDao.insertAuthor("황일영", "학원강사");
		
		//authorDao.deleteAuthor(3);
		
		//AuthorVo  authroVo = authorDao.selectAuthor(3);


	}

}
