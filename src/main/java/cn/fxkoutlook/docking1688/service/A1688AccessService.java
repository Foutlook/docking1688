package cn.fxkoutlook.docking1688.service;

/**
 * 权限处理
 * Created by Administrator on 2018/4/24.
 * @author xingkai.fan
 */

public interface A1688AccessService {
    /**
     * 获取Access权限
     * @return
     */
    void getOrderAccess(String code, String appKey, String secKey);
}