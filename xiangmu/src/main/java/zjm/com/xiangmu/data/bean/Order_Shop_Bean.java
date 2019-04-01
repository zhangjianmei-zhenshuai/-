package zjm.com.xiangmu.data.bean;

import java.io.Serializable;

/*
* 确定订单的商品bean
* */
public class Order_Shop_Bean implements Serializable {

    private String pic;//图片
    private String commodityName;//商品名称
    private String price;//价格
    

    public Order_Shop_Bean(String pic, String commodityName, String price) {
        this.pic = pic;
        this.commodityName = commodityName;
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
