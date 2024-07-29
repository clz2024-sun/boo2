package com.javaex.ex01;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {

		AuthorDao authorDao = new AuthorDao();
		
		//*등록 수정 *삭제  조회1  *조회전체
		
		
		//int count = authorDao.insertAuthor("황일영", "학원강사");
		
		//authorDao.deleteAuthor(9);
		
		
		List<AuthorVo> authorList = authorDao.selectAuthorAll(); //new리스트만들어서 주소만 전달한다
		//System.out.println(authorList);
		//이름만출력
		for(int i=0; i<authorList.size(); i++) {
			System.out.println(authorList.get(i).getAuthorName());
		}
		
		authorDao.updateAuthor(6, "황일영", "학원강사");
		AuthorVo authorVo = authorDao.selectAuthor(6);
		System.out.println(authorVo);
		
		//

	}

}
