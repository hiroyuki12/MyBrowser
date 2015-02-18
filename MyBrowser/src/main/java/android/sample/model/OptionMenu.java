package android.sample.model;

import android.sample.R;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

/**
 * Created by hiroyuki on 2/15/15.
 */
public class OptionMenu {

    // オプション・メニューを作成
    private static final int SUB_MENU_NEWS = 10;
    private static final int NIKKEI_NEWS_MENU_ID = 11;
    private static final int GOOGLE_NEWS_MENU_ID = 12;
    private static final int HOTENTRY_MENU_ID = 13;
    private static final int NIFTY_NEWS_MENU_ID = 14;
    private static final int KETAI_MENU_ID = 15;
    private static final int CLOUD_MENU_ID = 16;
    private static final int ITMEDIA_MENU_ID = 17;
    private static final int MYBROWSER_MENU_ID = 18;
    private static final int GOOGLE_MENU_ID = 20;
    private static final int SUB_MENU_BLOG = 30;
    private static final int ANDROID_DEV_BLOG_SEARCH = 31;
    private static final int NEXUS7_BLOG_SEARCH = 32;
    private static final int ANDROID_BLOG_SEARCH = 33;
    private static final int SUB_MENU_MANUAL = 40;
    private static final int GIT_MANUAL = 41;
    private static final int SENDTO_MENU_ID = 40;
    private static final int SUB_MENU_BOOKMARK = 50;
    private static final int ADD_BOOKMARK_MENU_ID = 51;
    private static final int VIEW_BOOKMARK_MENU_ID = 52;
    private static final int SETTING_MENU_ID = 60;
    private static final int FINISH_MENU_ID = 70;

    public Menu addSubMenu(Menu menu)
    {
        // ニュースサブメニュー
        SubMenu subMenuNews;

        subMenuNews = menu.addSubMenu(Menu.NONE, SUB_MENU_NEWS, 0, R.string.subMenu_news);
        //subMenuNews.setIcon(android.R.drawable.ic_menu_directions);

        // 日経
        subMenuNews.add(0, NIKKEI_NEWS_MENU_ID, 0, R.string.nikkei);
        // Googleニュース
        subMenuNews.add(0, GOOGLE_NEWS_MENU_ID, 1, R.string.google_news);
        // 人気エントリー
        subMenuNews.add(0, HOTENTRY_MENU_ID, 2, R.string.hotentry);
        // @niftyニュース
        subMenuNews.add(0, NIFTY_NEWS_MENU_ID, 3, R.string.nifty_news);
        // ケータイWatch
        subMenuNews.add(0, KETAI_MENU_ID, 4, R.string.ketai_news);
        // クラウドWatch
        subMenuNews.add(0, CLOUD_MENU_ID, 5, R.string.cloud_news);
        // ITmedia
        subMenuNews.add(0, ITMEDIA_MENU_ID, 6, R.string.it_media);
        // MyBrowser
        subMenuNews.add(0, MYBROWSER_MENU_ID, 7, R.string.my_browser);

        // Google
        menu.add(0, GOOGLE_MENU_ID, 1, R.string.google);

        // ブログ検索サブメニュー
        SubMenu subMenuBlog;
        subMenuBlog = menu.addSubMenu(Menu.NONE, SUB_MENU_BLOG, 2, R.string.subMenu_blog_search);
        //subMenuBlog.setIcon(android.R.drawable.ic_menu_directions);

        // android 開発
        subMenuBlog.add(0, ANDROID_DEV_BLOG_SEARCH, 0, R.string.android_dev_blog_seach);
        // Nexus7
        subMenuBlog.add(0, NEXUS7_BLOG_SEARCH, 0, R.string.nexus7_blog_seach);
        // Android
        subMenuBlog.add(0, ANDROID_BLOG_SEARCH, 0, R.string.android_blog_seach);

        // マニュアルサブメニュー
        //SubMenu subMenuManual;
        //subMenuManual = menu.addSubMenu(Menu.NONE, SUB_MENU_MANUAL, 3, R.string.subMenu_manual);
        //subMenuManual.setIcon(android.R.drawable.ic_menu_directions);

        // Gitマニュアル
        //subMenuManual.add(0, GIT_MANUAL, 0, R.string.git_manual);

        // 共有する
        menu.add(0, SENDTO_MENU_ID, 3, R.string.subMenu_sendto);

        // ブックマークサブメニュー
        SubMenu subMenuBookmark;
        subMenuBookmark = menu.addSubMenu(Menu.NONE, SUB_MENU_BOOKMARK, 4, R.string.subMenu_bookmark);
        //subMenuBookmark.setIcon(android.R.drawable.ic_menu_directions);

        //追加
        subMenuBookmark.add(Menu.NONE,  ADD_BOOKMARK_MENU_ID, 0, R.string.add_bookmark);
        //参照
        subMenuBookmark.add(Menu.NONE, VIEW_BOOKMARK_MENU_ID, 1, R.string.view_bookmark);

        // 設定
        //menu.add(0, SETTING_MENU_ID, 4, R.string.setting);

        // 閉じる
        menu.add(0, FINISH_MENU_ID, 8, R.string.close);

        return menu;
    }

    public String getUrl(MenuItem item)
    {
        String url = "";

        switch (item.getItemId()) {
            case NIKKEI_NEWS_MENU_ID :
                url = "http://www.nikkei.com/";
                break;
            case GOOGLE_NEWS_MENU_ID :
                url = "http://news.google.co.jp/";
                break;
            case HOTENTRY_MENU_ID :
                url = "http://hatebu.net/";
                break;
            case NIFTY_NEWS_MENU_ID :
                url = "http://news.nifty.com/";
                break;
            case KETAI_MENU_ID:
                url = "http://k-tai.impress.co.jp/";
                break;
            case CLOUD_MENU_ID:
                url = "http://cloud.watch.impress.co.jp/";
                break;
            case ITMEDIA_MENU_ID:
                url = "http://www.itmedia.co.jp/";
                break;
            case MYBROWSER_MENU_ID:
                url = "https://github.com/hiroyuki12/MyBrowser/commits/master";
                break;
            case GOOGLE_MENU_ID :
                url = "http://www.google.co.jp/";
                break;
            case ANDROID_DEV_BLOG_SEARCH :
                url = "https://www.google.co.jp/search?q=android+%E9%96%8B%E7%99%BA#q=android+%E9%96%8B%E7%99%BA&hl=ja&tbo=d&source=lnms&tbm=blg&sa=X&ei=e7ruULndN8eHkQXav4BQ&ved=0CBEQ_AUoAA&bav=on.2,or.r_gc.r_pw.r_qf.&bvm=bv.1357700187,d.dGI&fp=4a7b87b2fcf8f3fc&biw=1280&bih=687";
                break;
            case NEXUS7_BLOG_SEARCH :
                url = "https://www.google.co.jp/search?q=android+%E9%96%8B%E7%99%BA#hl=ja&gs_rn=1&gs_ri=serp&gs_is=1&pq=android%20%E9%96%8B%E7%99%BA&cp=4&gs_id=19&xhr=t&q=nexus7&es_nrs=true&pf=p&tbo=d&tbm=blg&sclient=psy-ab&oq=nexu&gs_l=&pbx=1&bav=on.2,or.r_gc.r_pw.r_qf.&fp=13d9d789849eb92e&biw=1280&bih=629";
                break;
            case ANDROID_BLOG_SEARCH :
                url = "https://www.google.co.jp/search?q=android+%E9%96%8B%E7%99%BA#hl=ja&tbo=d&tbm=blg&sclient=psy-ab&q=android&oq=android&gs_l=serp.3..0l8.414037.415041.4.415245.7.5.0.2.2.1.605.1488.1j1j1j0j1j1.5.0...0.0...1c.1j4.ZkHRPiMpKKI&pbx=1&bav=on.2,or.r_gc.r_pw.r_qf.&fp=13d9d789849eb92e&biw=1280&bih=623";
                break;
            case GIT_MANUAL :
                url = "http://cdn8.atwikiimg.com/git_jp/pub/git-manual-jp/Documentation/user-manual.html";
                break;

            default :
                break;
        }

        return url;
    }
}
