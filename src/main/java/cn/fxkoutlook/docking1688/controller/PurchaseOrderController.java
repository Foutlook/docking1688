package cn.fxkoutlook.docking1688.controller;


import cn.fxkoutlook.docking1688.Param.PurchaseOrderListParam;
import cn.fxkoutlook.docking1688.common.PurchaseOrderResultHandler;
import cn.fxkoutlook.docking1688.service.PurchaseOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 1688订单查询
 *
 * @author xingkai.fan
 * @create 2018 04 24
 */
@RestController
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 1688订单详情查询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/analytics/1688/purchase_orders/{id}")
    public PurchaseOrderResultHandler orderDetails(@PathVariable Long id) {
        PurchaseOrderResultHandler resultHandler = purchaseOrderService.getOrderDetails(id);
        logger.info("获取1688订单详情成功..." + resultHandler.getData());
        return resultHandler;
    }

    /**
     * 1688订单列表查询
     *
     * @param listParam
     * @return
     */
    @GetMapping(value = "/analytics/1688/purchase_orders")
    public PurchaseOrderResultHandler orderList(PurchaseOrderListParam listParam) {
        PurchaseOrderResultHandler resultHandler = purchaseOrderService.getOrderList(listParam);
        logger.info("获取1688订单列表成功..." + resultHandler.getData());
        return resultHandler;
    }


}