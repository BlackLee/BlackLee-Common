package tester;

import net.blacklee.common.date.DateFormatter;
import net.blacklee.common.date.DateUtils;
import net.blacklee.common.net.UrlUtils;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author LiHuiRong
 * 2010-10-21 09:23:22
 */
@RunWith(value=Suite.class)
@SuiteClasses(value={UrlUtils.class, DateFormatter.class, DateUtils.class})
public class MainTester {

}
