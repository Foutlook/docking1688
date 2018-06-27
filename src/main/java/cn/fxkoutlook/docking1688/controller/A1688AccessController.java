package cn.fxkoutlook.docking1688.controller;

import cn.fxkoutlook.docking1688.common.AuthorizationRequest;
import cn.fxkoutlook.docking1688.service.A1688AccessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

/**
 * 1688采购订单详情对接
 *
 * @author Administrator
 * @create 2018 04 20
 */
@Controller
public class A1688AccessController {

    @Value("${APPKEY}")
    private String APPKEY;
    @Value("${SECKEY}")
    private String SECKEY;
    @Value("${REDIRECT_URL}")
    private String REDIRECT_URL;
    @Value("${ORDER_URL}")
    private String ORDER_URL;
    @Autowired
    private A1688AccessService orderAccessService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取code
     * @return
     */
    @GetMapping(value = "/analytics/1688/auth")
    public String userAuthorization() {
        //对参数进行处理
        Base64.Encoder encoder = Base64.getEncoder();
        String encodeToString = encoder.encodeToString(ORDER_URL.getBytes());
        String requestUrl = AuthorizationRequest.verifyAuth(APPKEY, REDIRECT_URL,encodeToString);
        if(logger.isInfoEnabled()){
            logger.info("Get permission to jump to the specified path:"+requestUrl);
        }
        return "redirect:" + requestUrl;
    }

    /**
     * 对获得的code进行处理
     * @param request
     * @return
     */
    @GetMapping(value = "/analytics/1688/access")
    public String authCode(HttpServletRequest request) {
        String code = request.getParameter("code");
        orderAccessService.getOrderAccess(code,APPKEY,SECKEY);
        String state = request.getParameter("state");
        if (state.equals("")){

            logger.info("Redirect path is null,can't jump!");
            return null;
        }
        Base64.Decoder decoder = Base64.getDecoder();

        String url = new String(decoder.decode(state));
        if (logger.isInfoEnabled()){
            logger.info("Redirect path:"+url);
        }
        return "redirect:"+url;
    }
}