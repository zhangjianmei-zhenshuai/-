package zjm.com.xiangmu.data.bean;

import java.util.List;

public class CommentBean {

    /**
     * result : [{"commodityId":10,"content":"","createTime":1551818602000,"headPic":"http://172.17.8.100/images/small/head_pic/2019-03-06/20190306201017.jpg","image":"http://172.17.8.100/images/small/comment_pic/2019-03-05/9387020190305144322.jpg","nickName":"222","userId":600},{"commodityId":10,"content":"","createTime":1551733451000,"headPic":"http://172.17.8.100/images/small/head_pic/2019-03-06/20190306201017.jpg","image":"http://172.17.8.100/images/small/comment_pic/2019-03-04/5819020190304150411.jpg","nickName":"222","userId":600},{"commodityId":10,"content":"","createTime":1551663513000,"headPic":"http://172.17.8.100/images/small/head_pic/2019-03-06/20190306201017.jpg","image":"http://172.17.8.100/images/small/comment_pic/2019-03-03/9450620190303193833.jpg","nickName":"222","userId":600},{"commodityId":10,"content":"","createTime":1551663485000,"headPic":"http://172.17.8.100/images/small/head_pic/2019-03-06/20190306201017.jpg","image":"http://172.17.8.100/images/small/comment_pic/2019-03-03/2017320190303193805.jpg","nickName":"222","userId":600},{"commodityId":10,"content":"","createTime":1551658804000,"headPic":"http://172.17.8.100/images/small/head_pic/2019-03-06/20190306201017.jpg","image":"http://172.17.8.100/images/small/comment_pic/2019-03-03/9085420190303182004.jpg","nickName":"222","userId":600},{"commodityId":10,"content":"111","createTime":1551656716000,"headPic":"http://172.17.8.100/images/small/head_pic/2019-03-06/20190306201017.jpg","image":"","nickName":"222","userId":600}]
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
         * commodityId : 10
         * content :
         * createTime : 1551818602000
         * headPic : http://172.17.8.100/images/small/head_pic/2019-03-06/20190306201017.jpg
         * image : http://172.17.8.100/images/small/comment_pic/2019-03-05/9387020190305144322.jpg
         * nickName : 222
         * userId : 600
         */

        private int commodityId;
        private String content;
        private long createTime;
        private String headPic;
        private String image;
        private String nickName;
        private int userId;

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

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
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
    }
}
