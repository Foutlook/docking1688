package cn.fxkoutlook.docking1688.Param;


import java.util.Date;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

/**
 * 接收页面参数
 *
 * @author xingkai.fan
 * @create 2018 04 28
 */
@Data
@JsonNaming(SnakeCaseStrategy.class)
public class PurchaseOrderListParam {

    private String[] bizTypes;

    private Date createEndTime;

    private Date createStartTime;

    private Boolean isHis;

    private Date modifyEndTime;

    private Integer page;

    private Integer pageSize;

    private Date modifyStartTime;

    private String orderStatus;

    private String refundStatus;

    private String sellerMemberId;

    private Integer sellerRateStatus;

    private String tradeType;

    private String productName;

    private Boolean needBuyerAddressAndPhone;

    private Boolean needMemoInfo;

}