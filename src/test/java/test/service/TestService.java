package test.service;

import org.junit.Test;

import com.tedu.cloudnote.service.NoteService;

public class TestService extends BaseTest{
	@Test
	public void removeshare(){
		NoteService noteshare =ac.getBean("noteService",NoteService.class);
		noteshare.shareNote("123456");
	}
}
