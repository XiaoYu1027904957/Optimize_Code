package com.xiaoyu.rewritebaisi.essence.bean;

/**
 * Created by yuxiaobai on 2017/1/7.
 */

public class UserBean {

    /**
     * msg : 操作成功
     * code : 0
     * data : {"username":"","introduction_own":"这家伙很懒，神马都木有写有木有！！！","profile_image_large":"http://wimg.spriteapp.cn/profile/","fans_count":"0","introduction":"这家伙很懒，神马都木有写有木有！！！","follow_count":"0","profile_image":"http://wimg.spriteapp.cn/profile/","background_image":"","jie_v":"0","sex":"n","v_desc":"","is_vip":false,"praise_count":"0","sina_v":"0","background_image_own":"","id":"","level":1}
     */

    private String msg;
    private int code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * username :
         * introduction_own : 这家伙很懒，神马都木有写有木有！！！
         * profile_image_large : http://wimg.spriteapp.cn/profile/
         * fans_count : 0
         * introduction : 这家伙很懒，神马都木有写有木有！！！
         * follow_count : 0
         * profile_image : http://wimg.spriteapp.cn/profile/
         * background_image :
         * jie_v : 0
         * sex : n
         * v_desc :
         * is_vip : false
         * praise_count : 0
         * sina_v : 0
         * background_image_own :
         * id :
         * level : 1
         */

        private String username;
        private String introduction_own;
        private String profile_image_large;
        private String fans_count;
        private String introduction;
        private String follow_count;
        private String profile_image;
        private String background_image;
        private String jie_v;
        private String sex;
        private String v_desc;
        private boolean is_vip;
        private String praise_count;
        private String sina_v;
        private String background_image_own;
        private String id;
        private int level;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getIntroduction_own() {
            return introduction_own;
        }

        public void setIntroduction_own(String introduction_own) {
            this.introduction_own = introduction_own;
        }

        public String getProfile_image_large() {
            return profile_image_large;
        }

        public void setProfile_image_large(String profile_image_large) {
            this.profile_image_large = profile_image_large;
        }

        public String getFans_count() {
            return fans_count;
        }

        public void setFans_count(String fans_count) {
            this.fans_count = fans_count;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getFollow_count() {
            return follow_count;
        }

        public void setFollow_count(String follow_count) {
            this.follow_count = follow_count;
        }

        public String getProfile_image() {
            return profile_image;
        }

        public void setProfile_image(String profile_image) {
            this.profile_image = profile_image;
        }

        public String getBackground_image() {
            return background_image;
        }

        public void setBackground_image(String background_image) {
            this.background_image = background_image;
        }

        public String getJie_v() {
            return jie_v;
        }

        public void setJie_v(String jie_v) {
            this.jie_v = jie_v;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getV_desc() {
            return v_desc;
        }

        public void setV_desc(String v_desc) {
            this.v_desc = v_desc;
        }

        public boolean isIs_vip() {
            return is_vip;
        }

        public void setIs_vip(boolean is_vip) {
            this.is_vip = is_vip;
        }

        public String getPraise_count() {
            return praise_count;
        }

        public void setPraise_count(String praise_count) {
            this.praise_count = praise_count;
        }

        public String getSina_v() {
            return sina_v;
        }

        public void setSina_v(String sina_v) {
            this.sina_v = sina_v;
        }

        public String getBackground_image_own() {
            return background_image_own;
        }

        public void setBackground_image_own(String background_image_own) {
            this.background_image_own = background_image_own;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }
    }
}
