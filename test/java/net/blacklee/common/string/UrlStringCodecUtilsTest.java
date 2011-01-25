package net.blacklee.common.string;

import java.nio.charset.Charset;
import java.util.Iterator;

import org.junit.Test;
import org.junit.runners.Suite.SuiteClasses;

@SuiteClasses(value = {})
public class UrlStringCodecUtilsTest {
	@Test
	public void testDecode() {
		String s1 = "http://31.0.23.9/adtest/abc.htm?q1=1234565&bb=ssa2341&cnstr=%D6%D0%CE%C4%BB%E1%D4%F5%D1%F9%C4%D8";
		System.out.println(UrlStringCodecUtils.decodeURL(s1));
		s1 = "http://31.0.23.9/adtest/abc.htm?cnstr=%u4E2D%u6587%u662F%u600E%u6837%u7684";
		System.out.println(UrlStringCodecUtils.decodeURL(s1));
//		Iterator<Charset> it = Charset.availableCharsets().values().iterator();
//		while (it.hasNext()) {
//			Charset cs = it.next();
//			System.out.println(cs.displayName() + ": " + UrlStringCodecUtils.decode(s1, cs.displayName()));
//		}
		s1 = "http://31.0.23.9/adtest/abc.htm?q1=1234565&bb=ssa2341&cnstr=%E4%B8%AD%E6%96%87%E6%98%AF%E6%80%8E%E6%A0%B7%E7%9A%84";
		System.out.println(UrlStringCodecUtils.decodeURL(s1));
		
		s1 = "/adtest/1.gif?pss=538498274&pgn=4&page=http%3A//31.0.23.9/adtest/abc.htm%3Fq1%3D1234565%26bb%3Dssa2341%26cnstr%3D%25E4%25B8%25AD%25E6%2596%2587%25E6%2598%25AF%25E6%2580%258E%25E6%25A0%25B7%25E7%259A%2584&ref=http%3A//31.0.23.9/adtest/abc.htm%3Fq1%3D1234565%26bb%3Dssa2341%26cnstr%3D%25E4%25B8%25AD%25E6%2596%2587%25E6%2598%25AF%25E6%2580%258E%25E6%25A0%25B7%25E7%259A%2584&harpeId=0686516876eef3b64b9ac92330c234b9";
		System.out.println(UrlStringCodecUtils.decodeURL(s1));
	}
}
