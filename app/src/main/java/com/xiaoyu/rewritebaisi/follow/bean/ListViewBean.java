package com.xiaoyu.rewritebaisi.follow.bean;

import java.util.List;

/**
 * Created by yuxiaobai on 2017/1/6.
 */

public class ListViewBean {

    /**
     * info : {"count":8,"np":20}
     * total : 8
     * list : [{"count":59,"id":35,"name":"网红"},{"count":7,"id":36,"name":"精品"},{"count":16,"id":38,"name":"搞笑"},{"count":51,"id":37,"name":"创意"},{"count":29,"id":40,"name":"视频"},{"count":9,"id":39,"name":"原创"},{"count":18,"id":44,"name":"图片"},{"count":14,"id":33,"name":"工作室"}]
     * size : 8
     */

    private InfoBean info;
    private int total;
    private int size;
    private List<ListBean> list;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class InfoBean {
        /**
         * count : 8
         * np : 20
         */

        private int count;
        private int np;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getNp() {
            return np;
        }

        public void setNp(int np) {
            this.np = np;
        }
    }

    public static class ListBean {
        /**
         * count : 59
         * id : 35
         * name : 网红
         */

        private int count;
        private int id;
        private String name;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
