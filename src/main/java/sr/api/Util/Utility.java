package sr.api.Util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sercan on 10/02/16.
 */
public class Utility {

    public static String getServerBaseUrlFromRequest(HttpServletRequest request){
        StringBuffer requestURLs = request.getRequestURL();
        String req = requestURLs.toString();

        String[] rurl = req.split("/");
        String serverIp = rurl[2];
        serverIp = rurl[0] + "//" + rurl[2] + "/" + rurl[3];

        return serverIp;
    }

    public static String parseStackTrace(StackTraceElement element){
        StringBuilder sb = new StringBuilder();
        sb.append(element.getClassName());
        sb.append("-");
        sb.append(element.getMethodName());
        sb.append("-");
        sb.append(element.getLineNumber());
        return sb.toString();
    }
}
