package cn.fxkoutlook.docking1688.common;

/**
 * 生成单例AccessToken
 *
 * @author xingkai.fan
 * @create 2018 04 25
 */
public class SingletonAccessToken {

    private volatile static AccessToken accessToken;

    public static AccessToken getAccessToken(){
        if(accessToken==null){
            synchronized (AccessToken.class){
                if (accessToken==null){
                    accessToken = new AccessToken();
                }
            }
        }
        return accessToken;
    }
}