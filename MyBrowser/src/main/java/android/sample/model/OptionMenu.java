package android.sample.model;

import android.sample.R;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

/**
 * Created by hiroyuki on 2/15/15.
 */
public class OptionMenu {

    private static int index = 100;

    // オプション・メニューを作成
    private static final int SUB_MENU_NEWS = index++;
    private static final int NIKKEI_NEWS_MENU_ID = index++;
    private static final int GOOGLE_NEWS_MENU_ID = index++;
    private static final int HOTENTRY_MENU_ID = index++;
    private static final int NIFTY_NEWS_MENU_ID = index++;
    private static final int KETAI_MENU_ID = index++;
    private static final int CLOUD_MENU_ID = index++;
    private static final int ITMEDIA_MENU_ID = index++;
    private static final int ENGADGET_MENU_ID = index++;
    private static final int TECH_CRUNCH_MENU_ID = index++;
    private static final int GIGAZINE = index++;
    private static final int MYBROWSER_MENU_ID = index++;

    private static final int GOOGLE_MENU_ID = index++;

    private static final int NEXT_NEWS_MENU_ID = index++;

    private static final int SUB_MENU_BLOG = index++;
    private static final int ANDROID_DEV_BLOG_SEARCH = index++;
    private static final int NEXUS7_BLOG_SEARCH = index++;
    private static final int ANDROID_BLOG_SEARCH = index++;

    private static final int SUB_MENU_MANUAL = index++;
    private static final int GIT_MANUAL = index++;

    private static final int SENDTO_MENU_ID = 1;
    private static final int OTHER_BROWSER = 2;
    private static final int SHOW_SCALE = 3;

    private static final int SUB_MENU_OTHER = index++;
    private static final int NEW_TAB = 4;
//    private static final int SUB_MENU_BOOKMARK = index++;
    private static final int ADD_BOOKMARK_MENU_ID = 5;
    private static final int VIEW_BOOKMARK_MENU_ID = 6;
    private static final int SETTING_MENU_ID = 7;

    private static final int FINISH_MENU_ID = 8;

    public Menu addSubMenu(Menu menu)
    {
        // ニュースサブメニュー
        SubMenu subMenuNews;

        subMenuNews = menu.addSubMenu(Menu.NONE, SUB_MENU_NEWS, 0, R.string.subMenu_news);
        //subMenuNews.setIcon(android.R.drawable.ic_menu_directions);

        subMenuNews.add(0, NIKKEI_NEWS_MENU_ID, 0, R.string.nikkei);
        subMenuNews.add(0, GOOGLE_NEWS_MENU_ID, 1, R.string.google_news);
        subMenuNews.add(0, HOTENTRY_MENU_ID, 2, R.string.hotentry);
        subMenuNews.add(0, NIFTY_NEWS_MENU_ID, 3, R.string.nifty_news);
        subMenuNews.add(0, KETAI_MENU_ID, 4, R.string.ketai_news);
        subMenuNews.add(0, CLOUD_MENU_ID, 5, R.string.cloud_news);
        subMenuNews.add(0, ITMEDIA_MENU_ID, 6, R.string.it_media);
        subMenuNews.add(0, ENGADGET_MENU_ID, 7, R.string.engadget);
        subMenuNews.add(0, TECH_CRUNCH_MENU_ID, 8, R.string.tech_crunch);
        subMenuNews.add(0, GIGAZINE, 9, R.string.gigazine);
        subMenuNews.add(0, MYBROWSER_MENU_ID, 10, R.string.my_browser);

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

        // 次のニュース
        menu.add(0, NEXT_NEWS_MENU_ID, 3, R.string.next_news);

        // 共有する
        menu.add(0, SENDTO_MENU_ID, 4, R.string.subMenu_sendto);

        // その他サブメニュー
        SubMenu subMenuOther;
        subMenuOther = menu.addSubMenu(Menu.NONE, SUB_MENU_OTHER, 5, R.string.subMenu_other);


//        subMenuBookmark = menu.addSubMenu(Menu.NONE, SUB_MENU_BOOKMARK, 4, R.string.subMenu_bookmark);
        //subMenuBookmark.setIcon(android.R.drawable.ic_menu_directions);

        //新しいタブを開く
        subMenuOther.add(Menu.NONE,  NEW_TAB, 0, R.string.new_tab);
        // 別のブラウザで開く
        subMenuOther.add(Menu.NONE, OTHER_BROWSER, 1, R.string.other_browser);
        // 拡大率を表示
        subMenuOther.add(Menu.NONE, SHOW_SCALE, 2, R.string.show_scale);

        //追加
        subMenuOther.add(Menu.NONE,  ADD_BOOKMARK_MENU_ID,  3, R.string.add_bookmark);
        //参照
        subMenuOther.add(Menu.NONE, VIEW_BOOKMARK_MENU_ID, 4, R.string.view_bookmark);

        // 設定
        subMenuOther.add(Menu.NONE, SETTING_MENU_ID, 5, R.string.setting);

        // 閉じる
        menu.add(0, FINISH_MENU_ID, 8, R.string.close);

        return menu;
    }

    public String[] getUrl(MenuItem item, int newsIndex)
    {
        String[] urlList = {""
                , "http://www.nikkei.com/"
                , "http://hatebu.net/"
                , "http://news.nifty.com/"
                , "http://k-tai.impress.co.jp/"
                , "http://cloud.watch.impress.co.jp/"
                , "http://www.itmedia.co.jp/"
                , "http://japanese.engadget.com/"
                , "http://jp.techcrunch.com/"
                , "http://gigazine.net/"
                        };

        String[] url = new String[2];
        url[0] = "";
        url[1] = String.valueOf(newsIndex);

        if(item.getItemId() == NIKKEI_NEWS_MENU_ID) {
            url[0] = "http://www.nikkei.com/";
            newsIndex = 1;
        }
        else if(item.getItemId() == GOOGLE_NEWS_MENU_ID) {
            url[0] = "http://news.google.co.jp/";
        }
        else if(item.getItemId() == HOTENTRY_MENU_ID) {
            url[0] = "http://hatebu.net/";
            newsIndex = 2;
        }
        else if(item.getItemId() == NIFTY_NEWS_MENU_ID) {
            url[0] = "http://news.nifty.com/";
            newsIndex = 3;
        }
        else if(item.getItemId() == KETAI_MENU_ID) {
            url[0] = "http://k-tai.impress.co.jp/";
            newsIndex = 4;
        }
        else if(item.getItemId() == CLOUD_MENU_ID) {
            url[0] = "http://cloud.watch.impress.co.jp/";
            newsIndex = 5;
        }
        else if(item.getItemId() == ITMEDIA_MENU_ID) {
            url[0] = "http://www.itmedia.co.jp/";
            newsIndex = 6;
        }
        else if(item.getItemId() == ENGADGET_MENU_ID) {
            url[0] = "http://japanese.engadget.com/";
            newsIndex = 7;
        }
        else if(item.getItemId() == TECH_CRUNCH_MENU_ID) {
            url[0] = "http://jp.techcrunch.com/";
            newsIndex = 8;
        }
        else if(item.getItemId() == GIGAZINE) {
            url[0] = "http://gigazine.net/";
            newsIndex = 9;
        }
        else if(item.getItemId() == MYBROWSER_MENU_ID) {
            url[0] = "https://github.com/hiroyuki12/MyBrowser/commits/master";
        }
        else if(item.getItemId() == GOOGLE_MENU_ID) {
            url[0] = "http://www.google.co.jp/";
        }
        else if(item.getItemId() == NEXT_NEWS_MENU_ID) {
            if(newsIndex + 1 >= urlList.length) {
                newsIndex = 0;
            }

            newsIndex++;
            url[0] = urlList[newsIndex];// + "," + String.valueOf(newsIndex);
        }
        else if(item.getItemId() == ANDROID_DEV_BLOG_SEARCH) {
            url[0] = "https://www.google.co.jp/search?q=android+%E9%96%8B%E7%99%BA#q=android+%E9%96%8B%E7%99%BA&hl=ja&tbo=d&source=lnms&tbm=blg&sa=X&ei=e7ruULndN8eHkQXav4BQ&ved=0CBEQ_AUoAA&bav=on.2,or.r_gc.r_pw.r_qf.&bvm=bv.1357700187,d.dGI&fp=4a7b87b2fcf8f3fc&biw=1280&bih=687";
        }
        else if(item.getItemId() == NEXUS7_BLOG_SEARCH) {
            url[0]= "https://www.google.co.jp/search?q=android+%E9%96%8B%E7%99%BA#hl=ja&gs_rn=1&gs_ri=serp&gs_is=1&pq=android%20%E9%96%8B%E7%99%BA&cp=4&gs_id=19&xhr=t&q=nexus7&es_nrs=true&pf=p&tbo=d&tbm=blg&sclient=psy-ab&oq=nexu&gs_l=&pbx=1&bav=on.2,or.r_gc.r_pw.r_qf.&fp=13d9d789849eb92e&biw=1280&bih=629";
        }
        else if(item.getItemId() == ANDROID_BLOG_SEARCH) {
            url[0] = "https://www.google.co.jp/search?q=android+%E9%96%8B%E7%99%BA#hl=ja&tbo=d&tbm=blg&sclient=psy-ab&q=android&oq=android&gs_l=serp.3..0l8.414037.415041.4.415245.7.5.0.2.2.1.605.1488.1j1j1j0j1j1.5.0...0.0...1c.1j4.ZkHRPiMpKKI&pbx=1&bav=on.2,or.r_gc.r_pw.r_qf.&fp=13d9d789849eb92e&biw=1280&bih=623";
        }
        else if(item.getItemId() == GIT_MANUAL) {
            url[0] = "http://cdn8.atwikiimg.com/git_jp/pub/git-manual-jp/Documentation/user-manual.html";
        }

        if(url[0] != "") {
            url[1] = String.valueOf(newsIndex);
        }

        return url;
    }
}
