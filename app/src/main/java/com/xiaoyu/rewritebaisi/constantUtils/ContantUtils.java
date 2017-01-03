package com.xiaoyu.rewritebaisi.constantUtils;

/**
 * Created by yuxiaobai on 2016/12/29.
 */

public class ContantUtils {
    /**
     * 精华的全部URL
     */

    //    精华和新帖的Tab标题地址都在一个地址里面
    public static final String ESSENCE_TAB_URL = "http://s.budejie.com/public/list-appbar/budejie-android-6.6.3/?market=b-vivo&ver=6.6.3&visiting=19951053&os=5.1.1&appname=baisibudejie&client=android&udid=862607030468895&mac=20%3A5d%3A47%3A37%3A06%3A38\n";
    //      精华的推荐
    public static final String ESSENCE = "http://s.budejie.com/topic/list/jingxuan/1/budejie-android-6.6.3/0-20.json";
    //   精华的视频
    public static final String ESSENCE_VIDEO = "http://s.budejie.com/topic/list/jingxuan/41/budejie-android-6.6.3/0-20.json";
    //    精华的图片
    public static final String ESSENCE_PICTURE = "http://s.budejie.com/topic/list/jingxuan/10/budejie-android-6.6.3/0-20.json";
    //    精华的段子、
    public static final String ESSENCE_FIELD = "http://s.budejie.com/topic/tag-topic/64/hot/budejie-android-6.6.3/0-20.json";
    //     精华的排行
    public static final String ESSENCE_RANKING = "http://s.budejie.com/topic/list/remen/1/budejie-android-6.6.3/0-20.json";
    //    精华社会
    public static final String ESSENCE_sociology = "http://s.budejie.com/topic/tag-topic/473/hot/budejie-android-6.6.3/0-20.json";
    //     精华美女
    public static final String ESSENCE_BEAUTY = "http://s.budejie.com/topic/tag-topic/117/hot/budejie-android-6.6.3/0-20.json";
    //   精华冷知识
    public static final String ESSENCE_KNOW = "http://s.budejie.com/topic/tag-topic/3176/hot/budejie-android-6.6.3/0-20.json";
    //    精华游戏
    public static final String ESSENCE_GAME = "http://s.budejie.com/topic/tag-topic/164/hot/budejie-android-6.6.3/0-20.json";
    //     精华网红
    public static final String ESSENCE_NET_HOT = "http://s.budejie.com/topic/tag-topic/3096/hot/budejie-android-6.6.3/0-20.json";
    //     精华界面
    public static final String ESSENCE_DETIAL = "http://c.api.budejie.com/topic/comment_list/22860808/0/budejie-android-6.6.3/0-20.json";
    /**
     * 新帖的全部URL
     */

//    精华和新帖的Tab标题地址都在一个地址里面在最上边
    //      新帖的全部
    public static final String NEWSPOST_ALL = "http://s.budejie.com/topic/list/zuixin/1/budejie-android-6.6.3/0-20.json";
    //   新帖的视频
    public static final String NEWSPOST_VIDEO = "http://s.budejie.com/topic/list/zuixin/41/budejie-android-6.6.3/0-20.json";
    //    新帖的土图片
    public static final String NEWSPOST_PICTURE = "http://s.budejie.com/topic/list/zuixin/10/budejie-android-6.6.3/0-20.json";
    //    新帖的段子、
    public static final String NEWSPOST_FIELD = "http://s.budejie.com/topic/list/zuixin/29/budejie-android-6.6.3/0-20.json";

    //     新帖美女
    public static final String NEWSPOST_BEAUTY = "http://s.budejie.com/topic/tag-topic/117/new/budejie-android-6.6.3/0-20.json";
    //   新帖冷知识
    public static final String NEWSPOST_KNOW = "http://s.budejie.com/topic/tag-topic/3176/new/budejie-android-6.6.3/0-20.json";
    //    新帖游戏
    public static final String NEWSPOST_GAME = "http://s.budejie.com/topic/tag-topic/164/new/budejie-android-6.6.3/0-20.json";
    //    新帖声音
    public static final String NEWSPOST_VOICE = "http://s.budejie.com/topic/list/zuixin/31/budejie-android-6.6.3/0-20.json";
    //     新帖网红
    public static final String NEWSPOST_NET_HOT = "http://s.budejie.com/topic/tag-topic/3096/new/budejie-android-6.6.3/0-20.json";
    //     详情界面
    public static final String NEWSPOST_DETIAL = "http://c.api.budejie.com/topic/comment_list/22860808/0/budejie-android-6.6.3/0-20.json";
/**
 * 我的URL
 * 中间的网形格数据
 */
    public  static final String MINE_GRID  ="http://s.budejie.com/op/square2/budejie-android-6.6.3/tencentyingyongbao/0-\n"+"\n" + "100.json\n";
//     内容贡献榜
    public static  final  String MINE_CONTRIBUTE = "http://m.budejie.com/user/credit.html?\n" +
        "\n" +
        "from=singlemessage&isappinstalled=0&ver=6.6.3&client=android&market=tencentyingy\n" +
        "\n" +
        "ongbao&udid=null&appname=baisibudejie&mac=5c:f7:c3:51:77:14&time=1482932498863&r\n" +
        "\n" +
        "untimecan=1\n";
//    排行榜
    public  static  final  String MINE_CHART = "http://s.budejie.com/topic/top-topic/1/day/forward/budejie-android-6.6.3/0-\n" +
        "\n" +
        "20.json\n";
//       新闻头条
    public  static  final  String MINE_NEWS = "http://m.9wuli.com/sdkweb/?\n" +
        "\n" +
        "appId=dfb3405cd7e43be149e1c6679e4e189d&appSecret=35925174bc62732bbbc82f1a1a571ed\n" +
        "\n" +
        "e&navstyle=a&ver=6.6.3&client=android&market=tencentyingyongbao&udid=null&appnam\n" +
        "\n" +
        "e=baisibudejie&mac=5c:f7:c3:51:77:14&time=1482932368803&runtimecan=1\n";
//       推荐订阅
    public  static final String MINE_GROOM = "http://d.api.budejie.com/tag/subscribe/budejie-android-6.6.3.json";
//            推荐订阅的数据
    public static final String MINE_GROOM_DATA ="http://s.budejie.com/topic/tag-topic/42835/hot/budejie-android-6.6.3/0-20.json";


    /**
     * 详情页面的数据
     */
    public  static  final String DETIAL_BASE = "http://c.api.budejie.com/topic/comment_list/";


    public  static  final String FOOT_URL = "/0/budejie-android-6.6.3/0-20.json";

    /**
     * 各个详情界面
     */
    public  static  final String TUIJIAN = "http://c.api.budejie.com/topic/comment_list/22958696/0/budejie-android-6.6.3/0-20.json";
}
