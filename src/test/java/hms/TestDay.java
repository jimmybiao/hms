package hms;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestDay {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date d1=sdf.parse("2019-09-20");
		Date d2=sdf.parse("2019-10-25");
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(d1);
		long time1 = cal.getTimeInMillis();				
		cal.setTime(d2);
		long time2 = cal.getTimeInMillis();		
		long between_days=(time2-time1)/(1000*3600*24);
		int mills=Integer.parseInt(String.valueOf(between_days))+1;
		
		System.out.println(mills);
	}

}
