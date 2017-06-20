package services;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by RRabata on 12/16/2016.
 */
public class LogUtil {
    public static String getStackTraceString(Exception e){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}
