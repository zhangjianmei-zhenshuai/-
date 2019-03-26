package zjm.com.xiangmu.data.bean;

import java.util.List;

public class AddressBean {

    /**
     * result : [{"address":"安徽省-安庆市-枞阳县122","createTime":1553193543000,"id":453,"phone":"13777777777","realName":"张健美梅","userId":488,"whetherDefault":2,"zipCode":"246000"},{"address":"安徽省-安庆市-枞阳县","createTime":1553195246000,"id":454,"phone":"13666666666","realName":"阿斯顿","userId":488,"whetherDefault":2,"zipCode":"246000"},{"address":"北京市-北京市-海淀区上第七届","createTime":1553205560000,"id":461,"phone":"13717861302","realName":"张建没","userId":488,"whetherDefault":2,"zipCode":"100000"},{"address":"河北省-邯郸市-武安市","createTime":1553286786000,"id":469,"phone":"13717861302","realName":"张建梅","userId":488,"whetherDefault":2,"zipCode":"056000"}]
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
         * address : 安徽省-安庆市-枞阳县122
         * createTime : 1553193543000
         * id : 453
         * phone : 13777777777
         * realName : 张健美梅
         * userId : 488
         * whetherDefault : 2
         * zipCode : 246000
         */

        private String address;
        private long createTime;
        private int id;
        private String phone;
        private String realName;
        private int userId;
        private int whetherDefault;
        private String zipCode;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getWhetherDefault() {
            return whetherDefault;
        }

        public void setWhetherDefault(int whetherDefault) {
            this.whetherDefault = whetherDefault;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }
    }
}
