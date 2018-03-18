package test.dao;

import java.util.List;

import org.junit.Test;

import com.tedu.cloudnote.dao.AssociationDao;
import com.tedu.cloudnote.entity.Book;

import test.service.BaseTest;

public class TestAssociationDao extends BaseTest{
	@Test
	public void test2(){
		AssociationDao dao = 
			ac.getBean("associationDao",
				AssociationDao.class);
		List<Book> list = dao.findAllBook();
		for(Book book : list){
			if(book.getUser()!=null){
				System.out.println(
						book.getCn_notebook_name()+" "
					+book.getCn_notebook_createtime()+" "
					+book.getUser().getCn_user_name());
			}
		}
	}
	
	@Test
	public void test1(){
		AssociationDao dao = 
			ac.getBean("associationDao",
				AssociationDao.class);
		Book book = dao.findById(
			"1755013b-05fc-4218-83cf-956287a81b49");
		System.out.println(
			"笔记本名:"+book.getCn_notebook_name());
		System.out.println("创建时间:"+
			book.getCn_notebook_createtime());
		System.out.println("所属用户名:"+
			book.getUser().getCn_user_name());
	}
}
