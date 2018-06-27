package cn.fxkoutlook.docking1688.common;

import java.util.Date;

import com.alibaba.ocean.rawsdk.ApiExecutor;
import com.alibaba.ocean.rawsdk.client.entity.AuthorizationToken;

/**
 * 获取权限accesstoken
 *
 * @author xingkai.fan
 * @create 2018 04 24
 */
public class AccessToken {

    private AuthorizationToken token;
    private ApiExecutor apiExecutor;
    private Date refreshTokenTimeout;
    private String refreshToken;
    private final int millis = 1000;
    private Long currentMills;

    /**
     * 判断accessToken是否过期
     *
     * @return accessToken
     */
    public Boolean isExpiredToken() {

        //判断access_token是否过期
        if ((token.getExpires_in() * millis) <= (System.currentTimeMillis() - currentMills)) {
            return true;
        }
        return false;
    }

    public void setAccessToken(AuthorizationToken token, ApiExecutor apiExecutor) {
        this.currentMills = System.currentTimeMillis();
        this.apiExecutor = apiExecutor;
        this.token = token;
        this.refreshTokenTimeout = token.getRefresh_token_timeout();
        this.refreshToken = token.getRefresh_token();
    }


    public ApiExecutor getApiExecutor() {
        return apiExecutor;
    }

    private void setApiExecutor(ApiExecutor apiExecutor) {
        this.apiExecutor = apiExecutor;
    }

    public AuthorizationToken getToken() {
        return token;
    }

    public void setToken(AuthorizationToken token) {
        this.token = token;
    }

    /**
     * 保存refreshTokenTimeout的值，因为刷新token后，这个时间就不存在了
     *
     * @return
     */
    public Date getRefreshTokenTimeout() {
        return refreshTokenTimeout;
    }

    /**
     * 获取用来刷新token的refreshToken，因为刷新token后，这个也不存在了。
     * @return
     */
    public String getRefreshToken(){
        return refreshToken;
    }

}