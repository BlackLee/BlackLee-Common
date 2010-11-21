package net.blacklee.common.date;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runners.Suite.SuiteClasses;

@SuiteClasses(value = {})
public class DateFormatterTest {

	@Test
	public void testAbc() {
		String str = "2010-09-01 11:11:11";
		Calendar c = Calendar.getInstance();
		c.set(2010, 8, 01, 11, 11, 11);
		c.set(Calendar.MILLISECOND, 0);
		assertEquals(str, DateFormatter.toYyyyMmddHHmmss(c.getTime()));
		try {
			assertEquals(c.getTime().getTime(), DateFormatter.fromYyyyMmddHHmmss(str).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(DateFormatter.toMmddHHmmss(new Date()));
		System.out.println(DateFormatter.toYyyyMmddHHmmss(new Date()));
	}

}
