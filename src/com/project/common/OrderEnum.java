/*
 * Copyright 2011 ems.com All right reserved. This software is the confidential and proprietary information of
 * ems.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with ems.com.
 */
package com.project.common;

/**
 * 类OrderMethodCodeEnum.java的实现描述：TODO 类实现描述
 * 
 * @author user 2011-5-11 下午04:17:15
 */
public class OrderEnum {

    public enum MethodCode {
        GEN_SUB_ORDER("sub_order", "生成子订单") {

            public String getName() {
                return this.getValue();
            }
        },
        GEN_SUB_SPLIT_ORDER("sub_split_order", "拆分子订单") {
            
            public String getName() {
                return this.getValue();
            }
        },
        GEN_EX_SUB_ORDER("ex_sub_order", "生成手工子订单") {
            
            public String getName() {
                return this.getValue();
            }
        },
        GEN_EX_SUB_SPLIT_ORDER("ex_sub_split_order", "拆分手工子订单") {
            
            public String getName() {
                return this.getValue();
            }
        },
        PAID_ORDER("turnPaid", "订单支付成功") {
            
            public String getName() {
                return this.getValue();
            }
        },
        SAVE_REFUND_ORDER("saveApply", "申请退款") {

            public String getName() {
                return this.getValue();
            }
        },
        SET_INVALID("setInvalid", "设置商品无效") {

            public String getName() {
                return this.getValue();
            }
        },
        CANCEL_ORDER("turnCancel", "取消订单") {

            public String getName() {
                return this.getValue();
            }
        },
        PAY_OFFLINE("batchCancelCoupon", "财务线下支付") {

            public String getName() {
                return this.getValue();
            }
        },
        CANCEL_SPECIAL_ORDER("cancelSpecialOrder", "客服取消") {

            public String getName() {
                return this.getValue();
            }
        },
        ACTIVE_ORDER("activeOrder", "激活订单") {

            public String getName() {
                return this.getValue();
            }
        },
        SALE_REFUND_REFUSE("mountOrder", "售后退款拒绝") {

            public String getName() {
                return this.getValue();
            }
        },
        
        FINISH_SALE("mountOrder", "售后完成") {

            public String getName() {
                return this.getValue();
            }
        },
        CHOOSE_LOGISTIC("updateLogictis", "更新物流公司") {

            public String getName() {
                return this.getValue();
            }
        },
        UN_LOCK_ORDER("unLockOrder", "解锁订单") {

            public String getName() {
                return this.getValue();
            }
        },
        LOCK_ORDER("lock_order", "锁定订单") {

            public String getName() {
                return this.getValue();
            }
        },
        SET_NORMAL_ORDER("setNormalOrder", "置为正常订单") {

            public String getName() {
                return this.getValue();
            }
        },
        TURN_EXCEPTION("turnException", "系统置为异常订单") {

            public String getName() {
                return this.getValue();
            }
        },
        TURN_SYSTEM_CHECK_SUCCESS("turnSystemCheckSuccess", "系统审核") {

            public String getName() {
                return this.getValue();
            }
        },
        TURNCHECK_REVIEW("turnCheckReview", "人工审核") {

            public String getName() {
                return this.getValue();
            }
        },
        TURN_CHECK_NOT_REVIEW("turnCheckNotReview", "拒绝订单") {

            public String getName() {
                return this.getValue();
            }
        },
        SET_EXCE_ORDER("setExceOrder", "置为异常订单") {

            public String getName() {
                return this.getValue();
            }
        },
        REFUND_STATUS_BY_ORDERNO("editRefundStatusByOrderNo", "订单退款") {

            public String getName() {
                return this.getValue();
            }
        },
        CONFIRM_REFUND("turnRefund", "确认退款") {

            public String getName() {
                return this.getValue();
            }
        },
        CREATE_REMARK("createRemark", "添加备注") {

            public String getName() {
                return this.getValue();
            }
        },
        FINISH_ORDER("finishOrder", "订单完成") {

            public String getName() {
                return this.getValue();
            }
        },
        EDIT_CONSIGNEEINFO("editConsigneeInfo", "修改收货人信息") {

            public String getName() {
                return this.getValue();
            }
        },
        EDIT_ORDER_PRODUCT("editOrderProduct", "修改商品信息") {

            public String getName() {
                return this.getValue();
            }
        },
        SAVE_APPLY("saveApply", "申请退款") {

            public String getName() {
                return this.getValue();
            }
        },
        UPDATE_ORDER("turnUpdateOrder", "修改支付方式") {

            public String getName() {
                return this.getValue();
            }
        },
        ORDER_OUTSTORE("orderOutstore", "订单已出库") {

            public String getName() {
                return this.getValue();
            }
        },
        ORDER_CHECK_UP("orderCheckUp", "订单检货中") {

            public String getName() {
                return this.getValue();
            }
        },
        INSERT_SALE("insertSaleApplyBill", "申请售后") {

            public String getName() {
                return this.getValue();
            }
        },
        TURN_SALE_REFUND_SUCCESS("turnSaleReundSuccess", "订单售后退款") {

            public String getName() {
                return this.getValue();
            }
        },
        TURN_SALE_SUPPLY_SUCCESS("turnSaleSupplySuccess", "订单售后补款") {

            public String getName() {
                return this.getValue();
            }
        },
        TURN_SALE_REPAIR_SUCCESS("turnRepairByFinance", "订单售后返修补款") {

            public String getName() {
                return this.getValue();
            }
        },
        TURN_SALE_REFUND_FAIL("turnSaleReundFail", "订单售后退款拒绝") {

            public String getName() {
                return this.getValue();
            }
        },
        TURN_SALE_SUPPLY_FAIL("turnSaleSupplyFail", "订单售后补款拒绝") {

            public String getName() {
                return this.getValue();
            }
        },
        TURN_OTHER_REFUND_SUCCESS("turnRefundOtherSuccess", "订单其他退款成功") {

            public String getName() {
                return this.getValue();
            }
        },
        TURN_OTHER_REFUND_FAIL("turnRefundOtherFail", "订单其他退款失败") {

            public String getName() {
                return this.getValue();
            }
        },
        UMOUNT_ORDER("umountOrder", "解挂订单") {

            public String getName() {
                return this.getValue();
            }
        };

        private final String key;
        private final String value;

        MethodCode(String key, String value) {

            this.key = key;
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public String getKey() {
            return this.key;
        }

        public abstract String getName();
    }

    public enum LogType {
    	
    	/**  订单操作日志*/
        ORDER_OPERATE_LOG(1) {
            public String getName() {
                return "订单操作日志";
            }
        },
        /**  订单售后日志*/
        ORDER_SAL_LOG(2) {
            public String getName() {
                return "订单售后日志";
            }
        },
        /**  订单退款申请日志*/
        ORDER_REFUND_LOG(3) {
            public String getName() {
                return "订单退款申请日志";
            }
        },
        /**  订单正常流程日志*/
        ORDER_NORMAL_LOG(4) {
            public String getName() {
                return "订单正常流程日志";
            }
        },
        /**  订单系统调度日*/
        ORDER_SYSTEM_LOG(5) {
            public String getName() {
                return "订单系统调度日志";
            }
        },
        /** 订单接口调用日志*/
        ORDER_CALL_LOG(6) {
            public String getName() {
                return "订单接口调用日志";
            }
        };

        private final int value;

        LogType(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public abstract String getName();

    }

    public static void main(String[] args) {
        System.out.println(MethodCode.PAID_ORDER.name());
    }

}
