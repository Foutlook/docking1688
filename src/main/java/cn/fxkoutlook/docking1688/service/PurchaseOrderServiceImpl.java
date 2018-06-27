package cn.fxkoutlook.docking1688.service;

import cn.fxkoutlook.docking1688.Param.PurchaseOrderListParam;
import cn.fxkoutlook.docking1688.common.AccessToken;
import cn.fxkoutlook.docking1688.common.PurchaseOrderResultHandler;
import cn.fxkoutlook.docking1688.common.SingletonAccessToken;
import com.alibaba.ocean.rawsdk.common.SDKResult;
import com.alibaba.trade.param.AlibabaTradeGetBuyerOrderListParam;
import com.alibaba.trade.param.AlibabaTradeGetBuyerOrderListResult;
import com.alibaba.trade.param.AlibabaTradeGetBuyerViewParam;
import com.alibaba.trade.param.AlibabaTradeGetBuyerViewResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 获取1688订单
 *
 * @author xingkai.fan
 * @create 2018 04 23
 */
@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取1688订单详情
     *
     * @return
     */
    @Override
    public PurchaseOrderResultHandler getOrderDetails(Long orderId) {
        AlibabaTradeGetBuyerViewParam buyerViewParam = new AlibabaTradeGetBuyerViewParam();
        buyerViewParam.setWebSite("1688");
        buyerViewParam.setOrderId(orderId);
        if (SingletonAccessToken.getAccessToken().getToken() != null) {
            AccessToken accessToken = SingletonAccessToken.getAccessToken();
            SDKResult<AlibabaTradeGetBuyerViewResult> resultExecute = accessToken.getApiExecutor().execute
                    (buyerViewParam, accessToken.getToken().getAccess_token());
            AlibabaTradeGetBuyerViewResult buyerViewResult = resultExecute.getResult();
            logger.info("正确获取1688订单详情:" + resultExecute.getResult());
            if (buyerViewResult.getResult()!=null){
                return new PurchaseOrderResultHandler(buyerViewResult.getResult());
            }
            return new PurchaseOrderResultHandler(System.currentTimeMillis(),404,buyerViewResult.getErrorCode(),
                    "/1688/purchase_orders/", buyerViewResult.getErrorMessage(),buyerViewResult.getResult());
        }
        logger.info("refreshToken授权过期...返回null");
        return new PurchaseOrderResultHandler(System.currentTimeMillis(),401,"unauthorized",
                "/1688/purchase_orders/", "1688授权过期,请重新授权...",null);
    }

    /**
     * 获取1688订单列表
     *
     * @param listParam
     * @return
     */
    @Override
    public PurchaseOrderResultHandler getOrderList(PurchaseOrderListParam listParam) {
        logger.info("开始1688获取订单列表...");
        AlibabaTradeGetBuyerOrderListParam buyerOrderListParam = new AlibabaTradeGetBuyerOrderListParam();
        BeanUtils.copyProperties(listParam, buyerOrderListParam);
        buyerOrderListParam.setPage(listParam.getPage() == null ? 1 : listParam.getPage());
        buyerOrderListParam.setPageSize(listParam.getPageSize() == null ? 20 : listParam.getPageSize());
        buyerOrderListParam.setNeedBuyerAddressAndPhone(listParam.getNeedBuyerAddressAndPhone() == null ? false :
                listParam.getNeedBuyerAddressAndPhone());
        buyerOrderListParam.setNeedMemoInfo(listParam.getNeedMemoInfo() == null ? false : listParam.getNeedMemoInfo());
        if (SingletonAccessToken.getAccessToken().getToken() != null) {
            AccessToken accessToken = SingletonAccessToken.getAccessToken();
            SDKResult<AlibabaTradeGetBuyerOrderListResult> resultOrderList = accessToken.getApiExecutor().execute
                    (buyerOrderListParam, accessToken.getToken().getAccess_token());
            AlibabaTradeGetBuyerOrderListResult buyerOrderListResult = resultOrderList.getResult();
            if (buyerOrderListResult.getResult()!=null){
                logger.info("获取1688订单列表成功..." + buyerOrderListResult.getResult());
                return new PurchaseOrderResultHandler(buyerOrderListResult.getResult());
            }
            logger.info("获取1688订单列表出错...");
            return new PurchaseOrderResultHandler(System.currentTimeMillis(),404,buyerOrderListResult.getErrorCode(),
                    "/1688/purchase_orders/", buyerOrderListResult.getErrorMessage(),buyerOrderListResult.getResult());
        }
        logger.info("refreshToken过期，返回null");
        return new PurchaseOrderResultHandler(System.currentTimeMillis(),401,"unauthorized",
                "/1688/purchase_orders/", "1688授权过期,请重新授权...",null);
    }
}