package hms;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jimmy.pojo.Memo;
import com.jimmy.service.MemoService;

@ContextConfiguration(locations= {"classpath:springmvc.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestMemo {
	
	@Autowired
	private MemoService memoService;

	@Test
	public void testGetMemos() throws Exception {
		String dateStr="2019-11-05";
		DateTime dt=new DateTime();
//		DateTime dt=new DateTime(new Date());
		//dt=DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(dt);
		
		
//		System.out.println(dt.toString("yyyy-MM-dd"));
		
		List<Memo> memos=memoService.getMemos(DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(dateStr));
		for(Memo m:memos)
			System.out.println(m);
	}
}
