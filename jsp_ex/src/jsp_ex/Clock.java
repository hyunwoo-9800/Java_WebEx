/**
 * 
 */
package jsp_ex;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author develop
 *
 */
public class Clock {
	
	public String now() {
		
		SimpleDateFormat format = new SimpleDateFormat("HH시 mm분 ss초");
		return format.format(new Date());
		
	}

}
