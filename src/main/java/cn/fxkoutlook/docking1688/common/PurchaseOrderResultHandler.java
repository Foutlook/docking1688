package cn.fxkoutlook.docking1688.common;


import lombok.Data;

import java.io.Serializable;

/**
 * 订单结果处理
 *
 * @author xingkai.fan
 * @create 2018 04 28
 */
@Data
public class PurchaseOrderResultHandler implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long timestamp;
    /**
     * 状态
     */
    private Integer status;
    private String error;
    private String path;
    private String message;
    /**
     * 相应中的对象
     */
    private Object data;

    public PurchaseOrderResultHandler() {

    }

    public PurchaseOrderResultHandler(Long timestamp, Integer status, String error, String path, String message, Object
            data) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
        this.message = message;
        this.data = data;
    }

    public PurchaseOrderResultHandler(Object data) {
        this.status = 200;
        this.message = "OK";
        this.error = null;
        this.data = data;
    }




}