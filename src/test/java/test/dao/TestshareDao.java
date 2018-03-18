package test.dao;

import org.junit.Test;

import com.tedu.cloudnote.dao.NoteDao;
import com.tedu.cloudnote.dao.ShareDao;

import test.service.BaseTest;

public class TestshareDao extends BaseTest{
	@Test
	public void TestDeleteShare(){
		ShareDao sharedao =ac.getBean("shareDao",ShareDao.class);
		sharedao.removeByNoteId("123456");
	}
	@Test
	public void TestNoteType(){
		NoteDao notedao=ac.getBean("noteDao", NoteDao.class);
		notedao.updateTypeId("40ec8897db644e578b83f0a7075dad1d");
	}
}
