package cn.fxkoutlook.docking1688.common;

import java.util.TimerTask;

import com.alibaba.ocean.rawsdk.client.entity.AuthorizationToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * token判断定时器
 *
 * @author xingkai.fan
 * @create 2018 04 27
 */
public class TokenTimer extends TimerTask {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private AccessToken accessToken;

    public TokenTimer(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public void run() {
        if (accessToken.getToken() != null) {
            if (accessToken.getRefreshTokenTimeout().getTime() > System.currentTimeMillis()) {
                if (accessToken.isExpiredToken()) {
                    logger.info("开始刷新accesstoken:" + accessToken.getApiExecutor());
                    AuthorizationToken token = accessToken.getApiExecutor().refreshToken(accessToken.getRefreshToken());
                    accessToken.setToken(token);
                    logger.info("刷新accessToken 成功:" + accessToken.getToken());
                }
            } else {
                accessToken.setToken(null);
                logger.info("refreshToken过期，token置空...");
            }
        }
    }
}