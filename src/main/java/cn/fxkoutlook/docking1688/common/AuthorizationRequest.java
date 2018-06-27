package cn.fxkoutlook.docking1688.common;

import org.apache.tomcat.util.http.parser.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用户授权
 *
 * @author xingkai.fan
 * @create 2018 04 20
 */
public class AuthorizationRequest {
    private static Logger log = LoggerFactory.getLogger(Authorization.class);

    private static String requestUrl = "https://auth.1688" +
            ".com/oauth/authorize?client_id=appKey&site=1688&redirect_uri=YOUR_REDIRECT_URL&state=YOUR_PARM";

    public static String verifyAuth(String appKey, String redirect_url,String orderUrl) {
        String reqUrl = requestUrl.replace("appKey", appKey).replace("YOUR_REDIRECT_URL", redirect_url).replace
                ("YOUR_PARM",orderUrl);
        return reqUrl;
    }
}