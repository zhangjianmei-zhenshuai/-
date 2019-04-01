package zjm.com.xiangmu.data.bean;

import java.util.List;

/*
* 查询订单
* */
public class Find_Bean {

    /**
     * orderList : [{"detailList":[{"commentStatus":1,"commodityCount":1,"commodityId":23,"commodityName":"小白鞋 女款 时尚百搭休闲板鞋","commodityPic":"http://mobile.bwstudent.com/images/small/commodity/nx/bx/6/1.jpg,http://mobile.bwstudent.com/images/small/commodity/nx/bx/6/2.jpg,http://mobile.bwstudent.com/images/small/commodity/nx/bx/6/3.jpg,http://mobile.bwstudent.com/images/small/commodity/nx/bx/6/4.jpg,http://mobile.bwstudent.com/images/small/commodity/nx/bx/6/5.jpg","commodityPrice":139,"orderDetailId":4178}],"expressCompName":"京东快递","expressSn":"1001","orderId":"20190331213835668488","orderStatus":2,"payAmount":139,"payMethod":1,"userId":488},{"detailList":[{"commentStatus":1,"commodityCount":1,"commodityId":23,"commodityName":"小白鞋 女款 时尚百搭休闲板鞋","commodityPic":"http://mobile.bwstudent.com/images/small/commodity/nx/bx/6/1.jpg,http://mobile.bwstudent.com/images/small/commodity/nx/bx/6/2.jpg,http://mobile.bwstudent.com/images/small/commodity/nx/bx/6/3.jpg,http://mobile.bwstudent.com/images/small/commodity/nx/bx/6/4.jpg,http://mobile.bwstudent.com/images/small/commodity/nx/bx/6/5.jpg","commodityPrice":139,"orderDetailId":4176}],"expressCompName":"京东快递","expressSn":"1001","orderId":"20190331213556225488","orderStatus":2,"payAmount":139,"payMethod":1,"userId":488},{"detailList":[{"commentStatus":1,"commodityCount":1,"commodityId":27,"commodityName":"休闲马衔扣保暖绒里棉鞋懒人鞋毛毛鞋平底女雪地靴女短靴子豆豆鞋女鞋","commodityPic":"http://mobile.bwstudent.com/images/small/commodity/nx/ddx/3/1.jpg,http://mobile.bwstudent.com/images/small/commodity/nx/ddx/3/2.jpg,http://mobile.bwstudent.com/images/small/commodity/nx/ddx/3/3.jpg,http://mobile.bwstudent.com/images/small/commodity/nx/ddx/3/4.jpg,http://mobile.bwstudent.com/images/small/commodity/nx/ddx/3/5.jpg","commodityPrice":139,"orderDetailId":4173}],"expressCompName":"京东快递","expressSn":"1001","orderId":"20190331212413365488","orderStatus":1,"payAmount":139,"payMethod":1,"userId":488},{"detailList":[{"commentStatus":1,"commodityCount":1,"commodityId":29,"commodityName":"秋冬新款平底毛毛豆豆鞋加绒单鞋一脚蹬懒人鞋休闲","commodityPic":"http://mobile.bwstudent.com/images/small/commodity/nx/ddx/5/1.jpg,http://mobile.bwstudent.com/images/small/commodity/nx/ddx/5/2.jpg,http://mobile.bwstudent.com/images/small/commodity/nx/ddx/5/3.jpg,http://mobile.bwstudent.com/images/small/commodity/nx/ddx/5/4.jpg,http://mobile.bwstudent.com/images/small/commodity/nx/ddx/5/5.jpg","commodityPrice":278,"orderDetailId":4004}],"expressCompName":"京东快递","expressSn":"1001","orderId":"20190331140302148488","orderStatus":2,"payAmount":278,"payMethod":1,"userId":488},{"detailList":[{"commentStatus":1,"commodityCount":1,"commodityId":29,"commodityName":"秋冬新款平底毛毛豆豆鞋加绒单鞋一脚蹬懒人鞋休闲","commodityPic":"http://mobile.bwstudent.com/images/small/commodity/nx/ddx/5/1.jpg,http://mobile.bwstudent.com/images/small/commodity/nx/ddx/5/2.jpg,http://mobile.bwstudent.com/images/small/commodity/nx/ddx/5/3.jpg,http://mobile.bwstudent.com/images/small/commodity/nx/ddx/5/4.jpg,http://mobile.bwstudent.com/images/small/commodity/nx/ddx/5/5.jpg","commodityPrice":278,"orderDetailId":3998}],"expressCompName":"京东快递","expressSn":"1001","orderId":"20190331122231537488","orderStatus":1,"payAmount":278,"payMethod":1,"userId":488}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<OrderListBean> orderList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderListBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    public static class OrderListBean {
        /**
         * detailList : [{"commentStatus":1,"commodityCount":1,"commodityId":23,"commodityName":"小白鞋 女款 时尚百搭休闲板鞋","commodityPic":"http://mobile.bwstudent.com/images/small/commodity/nx/bx/6/1.jpg,http://mobile.bwstudent.com/images/small/commodity/nx/bx/6/2.jpg,http://mobile.bwstudent.com/images/small/commodity/nx/bx/6/3.jpg,http://mobile.bwstudent.com/images/small/commodity/nx/bx/6/4.jpg,http://mobile.bwstudent.com/images/small/commodity/nx/bx/6/5.jpg","commodityPrice":139,"orderDetailId":4178}]
         * expressCompName : 京东快递
         * expressSn : 1001
         * orderId : 20190331213835668488
         * orderStatus : 2
         * payAmount : 139
         * payMethod : 1
         * userId : 488
         */

        private String expressCompName;
        private String expressSn;
        private String orderId;
        private int orderStatus;
        private int payAmount;
        private int payMethod;
        private int userId;
        private List<DetailListBean> detailList;

        public String getExpressCompName() {
            return expressCompName;
        }

        public void setExpressCompName(String expressCompName) {
            this.expressCompName = expressCompName;
        }

        public String getExpressSn() {
            return expressSn;
        }

        public void setExpressSn(String expressSn) {
            this.expressSn = expressSn;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public int getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(int payAmount) {
            this.payAmount = payAmount;
        }

        public int getPayMethod() {
            return payMethod;
        }

        public void setPayMethod(int payMethod) {
            this.payMethod = payMethod;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public List<DetailListBean> getDetailList() {
            return detailList;
        }

        public void setDetailList(List<DetailListBean> detailList) {
            this.detailList = detailList;
        }

        public static class DetailListBean {
            /**
             * commentStatus : 1
             * commodityCount : 1
             * commodityId : 23
             * commodityName : 小白鞋 女款 时尚百搭休闲板鞋
             * commodityPic : http://mobile.bwstudent.com/images/small/commodity/nx/bx/6/1.jpg,http://mobile.bwstudent.com/images/small/commodity/nx/bx/6/2.jpg,http://mobile.bwstudent.com/images/small/commodity/nx/bx/6/3.jpg,http://mobile.bwstudent.com/images/small/commodity/nx/bx/6/4.jpg,http://mobile.bwstudent.com/images/small/commodity/nx/bx/6/5.jpg
             * commodityPrice : 139
             * orderDetailId : 4178
             */

            private int commentStatus;
            private int commodityCount;
            private int commodityId;
            private String commodityName;
            private String commodityPic;
            private int commodityPrice;
            private int orderDetailId;

            public int getCommentStatus() {
                return commentStatus;
            }

            public void setCommentStatus(int commentStatus) {
                this.commentStatus = commentStatus;
            }

            public int getCommodityCount() {
                return commodityCount;
            }

            public void setCommodityCount(int commodityCount) {
                this.commodityCount = commodityCount;
            }

            public int getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(int commodityId) {
                this.commodityId = commodityId;
            }

            public String getCommodityName() {
                return commodityName;
            }

            public void setCommodityName(String commodityName) {
                this.commodityName = commodityName;
            }

            public String getCommodityPic() {
                return commodityPic;
            }

            public void setCommodityPic(String commodityPic) {
                this.commodityPic = commodityPic;
            }

            public int getCommodityPrice() {
                return commodityPrice;
            }

            public void setCommodityPrice(int commodityPrice) {
                this.commodityPrice = commodityPrice;
            }

            public int getOrderDetailId() {
                return orderDetailId;
            }

            public void setOrderDetailId(int orderDetailId) {
                this.orderDetailId = orderDetailId;
            }
        }
    }
}