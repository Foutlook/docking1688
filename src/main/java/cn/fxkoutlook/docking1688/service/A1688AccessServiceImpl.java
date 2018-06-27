package cn.fxkoutlook.docking1688.service;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cn.fxkoutlook.docking1688.common.AccessToken;
import cn.fxkoutlook.docking1688.common.SingletonAccessToken;
import cn.fxkoutlook.docking1688.common.SingletonTimer;
import cn.fxkoutlook.docking1688.common.TokenTimer;
import com.alibaba.ocean.rawsdk.ApiExecutor;
import com.alibaba.ocean.rawsdk.client.entity.AuthorizationToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 获取Access_token
 *
 * @author xingkai.fan
 * @create 2018 04 24
 */
@Service
public class A1688AccessServiceImpl implements A1688AccessService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void getOrderAccess(String code, String appKey, String secKey) {
        ApiExecutor apiExecutor = new ApiExecutor(appKey,secKey);
        AuthorizationToken token = apiExecutor.getToken(code);
        AccessToken accessToken = SingletonAccessToken.getAccessToken();
        accessToken.setAccessToken(token,apiExecutor);
        if (null!=SingletonTimer.getTokenTimer()){
            SingletonTimer.getTokenTimer().shutdown();
            SingletonTimer.setScheduledExecutorService(null);
            logger.info("线程池设为空...");
        }
        ScheduledExecutorService timer = SingletonTimer.getTokenTimer();
        logger.info("获取线程池对象："+timer);

        TokenTimer tokenTimer = new TokenTimer(accessToken);
        logger.info("运行指定定时线程");
        timer.scheduleAtFixedRate(tokenTimer,10L,10L,TimeUnit.HOURS);
    }
}