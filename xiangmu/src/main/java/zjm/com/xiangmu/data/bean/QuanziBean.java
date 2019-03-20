package zjm.com.xiangmu.data.bean;

import java.util.List;

public class QuanziBean {

    /**
     * result : [{"commodityId":1,"content":"123","createTime":1551280989000,"greatNum":0,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":554,"image":"","nickName":"N7_74Am6","userId":32,"whetherGreat":2},{"commodityId":1,"content":"美美哒","createTime":1551280984000,"greatNum":0,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":553,"image":"","nickName":"N7_74Am6","userId":32,"whetherGreat":2},{"commodityId":1,"content":"那有声么岁月静好，只是有人为你负重前行！","createTime":1551280922000,"greatNum":0,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":552,"image":"","nickName":"N7_74Am6","userId":32,"whetherGreat":2},{"commodityId":1,"content":"范某某！！！","createTime":1551234351000,"greatNum":1,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":549,"image":"","nickName":"HA_DM5T9","userId":11,"whetherGreat":2},{"commodityId":1,"content":"哎呀呀","createTime":1551219506000,"greatNum":3,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":547,"image":"","nickName":"@范某某","userId":871,"whetherGreat":2}]
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
         * commodityId : 1
         * content : 123
         * createTime : 1551280989000
         * greatNum : 0
         * headPic : http://172.17.8.100/images/small/default/user.jpg
         * id : 554
         * image :
         * nickName : N7_74Am6
         * userId : 32
         * whetherGreat : 2
         */

        private int commodityId;
        private String content;
        private long createTime;
        private int greatNum;
        private String headPic;
        private int id;
        private String image;
        private String nickName;
        private int userId;
        private int whetherGreat;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(int greatNum) {
            this.greatNum = greatNum;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getWhetherGreat() {
            return whetherGreat;
        }

        public void setWhetherGreat(int whetherGreat) {
            this.whetherGreat = whetherGreat;
        }
    }
}
