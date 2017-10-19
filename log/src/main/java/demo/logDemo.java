package demo;

import org.apache.log4j.Logger;

public class logDemo {
    private static Logger loger = Logger.getLogger(logDemo.class);

    public static void main(String[] args) throws Exception {
        loger.debug("This is debug message.");
        loger.info("This is info message.");
        loger.error("This is error message.");
    }
}
