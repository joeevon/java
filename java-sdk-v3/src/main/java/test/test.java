package test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by joee on 2017/9/27.
 */

public class test {
    private static Log log = LogFactory.getLog(test.class);

    public static void main(String[] args) throws Exception {
        long l = System.currentTimeMillis() + 15 * 60 * 1000;
        java.sql.Date date = new java.sql.Date(l);
        System.out.println(date.getYear() + 1900);//年
        System.out.println(date.getMonth() + 1);//月
        System.out.println(date.getDate());//日
        java.sql.Timestamp time = new java.sql.Timestamp(l);
        System.out.println(time.getHours());//时
        System.out.println(time.getMinutes());//分
        System.out.println(time.getSeconds());//秒
    }
}
