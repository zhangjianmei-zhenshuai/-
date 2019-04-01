package zjm.com.xiangmu.data.bean;

import java.util.List;

public class Cart_Bean {
    /**
     * result : [{"commodityId":19,"commodityName":"环球 时尚拼色街拍百搭小白鞋 韩版原宿ulzzang板鞋 女休闲鞋","count":1,"pic":"http://mobile.bwstudent.com/images/small/commodity/nx/bx/2/1.jpg","price":78},{"commodityId":7,"commodityName":"蓝色之恋","count":1,"pic":"http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/5/1.jpg","price":29},{"commodityId":19,"commodityName":"环球 时尚拼色街拍百搭小白鞋 韩版原宿ulzzang板鞋 女休闲鞋","count":1,"pic":"http://mobile.bwstudent.com/images/small/commodity/nx/bx/2/1.jpg","price":78},{"commodityId":14,"commodityName":"美诺MENOW面部刷","count":1,"pic":"http://mobile.bwstudent.com/images/small/commodity/mzhf/mzgj/4/1.jpg","price":47},{"commodityId":29,"commodityName":"秋冬新款平底毛毛豆豆鞋加绒单鞋一脚蹬懒人鞋休闲","count":1,"pic":"http://mobile.bwstudent.com/images/small/commodity/nx/ddx/5/1.jpg","price":278},{"commodityId":7,"commodityName":"蓝色之恋","count":1,"pic":"http://mobile.bwstudent.com/images/small/commodity/mzhf/cz/5/1.jpg","price":29},{"commodityId":17,"commodityName":"化妆镜","count":1,"pic":"http://mobile.bwstudent.com/images/small/commodity/mzhf/mzgj/7/1.jpg","price":31},{"commodityId":17,"commodityName":"化妆镜","count":1,"pic":"http://mobile.bwstudent.com/images/small/commodity/mzhf/mzgj/7/1.jpg","price":31},{"commodityId":29,"commodityName":"秋冬新款平底毛毛豆豆鞋加绒单鞋一脚蹬懒人鞋休闲","count":1,"pic":"http://mobile.bwstudent.com/images/small/commodity/nx/ddx/5/1.jpg","price":278},{"commodityId":23,"commodityName":"小白鞋 女款 时尚百搭休闲板鞋","count":1,"pic":"http://mobile.bwstudent.com/images/small/commodity/nx/bx/6/1.jpg","price":139},{"commodityId":17,"commodityName":"化妆镜","count":1,"pic":"http://mobile.bwstudent.com/images/small/commodity/mzhf/mzgj/7/1.jpg","price":31},{"commodityId":136,"commodityName":"板鞋休闲鞋男男士休闲运动鞋男士鞋子休闲皮鞋男士休闲鞋男鞋","count":1,"pic":"http://mobile.bwstudent.com/images/small/commodity/nx/nbx/2/1.jpg","price":99}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commodityId : 19
         * commodityName : 环球 时尚拼色街拍百搭小白鞋 韩版原宿ulzzang板鞋 女休闲鞋
         * count : 1
         * pic : http://mobile.bwstudent.com/images/small/commodity/nx/bx/2/1.jpg
         * price : 78
         */


        private int num=1;
        private int commodityId;
        private String commodityName;
        private int count;
        private String pic;
        private int price;
        private boolean my_checkbox;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public ResultBean(boolean my_checkbox) {
            this.my_checkbox = my_checkbox;
        }

        public boolean isMy_checkbox() {
            return my_checkbox;
        }

        public void setMy_checkbox(boolean my_checkbox) {
            this.my_checkbox = my_checkbox;
        }

        public boolean getMy_checkbox(boolean my_checkbox) {
            return my_checkbox=my_checkbox;
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

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
