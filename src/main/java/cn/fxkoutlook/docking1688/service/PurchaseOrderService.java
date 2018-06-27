package cn.fxkoutlook.docking1688.service;


import cn.fxkoutlook.docking1688.Param.PurchaseOrderListParam;
import cn.fxkoutlook.docking1688.common.PurchaseOrderResultHandler;

/**
 * Created by Administrator on 2018/4/23.
 * 订单处理
 * @author xingkai.fan
 */
public interface PurchaseOrderService {

    /**
     * 获取1688订单详情
     * @param orderId
     * @return
     */
    PurchaseOrderResultHandler getOrderDetails(Long orderId);
    /**
     * 获取1688订单列表
     * @param listParam
     * @return
     */
    PurchaseOrderResultHandler getOrderList(PurchaseOrderListParam listParam);
}