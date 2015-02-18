package android.sample.model;

import android.sample.Constants;

/**
 * Created by hiroyuki on 2/15/15.
 */
public class Scale {

    private int scale;

    public int getScale(boolean xlarge, String url) {
        if(xlarge) {
            if (url.startsWith("http://www.nikkei.com/") || url.startsWith("http://k-tai.impress.co.jp/")
                    || url.startsWith("http://cloud.watch.impress.co.jp/") || url.startsWith("http://k-tai.impress.co.jp/")
                    || url.startsWith("http://www.itmedia.co.jp/") || url.startsWith("https://www.google.co.jp/")
                    || url.startsWith("http://www.yahoo.co.jp/") || url.startsWith("https://github.com/")
                    )     //nikkeiは150%表示
            {
                scale = Constants.TABLET_SCALE_BIG;  //タブレットは130%で表示
            }
            else if(url.startsWith("http://anond.hatelabo.jp/"))
            {
                scale = Constants.TABLET_SCALE_MIDDLE;
            }
            else  //nikkei以外は100%表示
            {
                scale = Constants.TABLET_SCALE_NORMAL;  //タブレットは100%で表示
            }
        }
        else
        {
            scale = Constants.SMART_PHONE_SCALE_BIG;  //SmartPhoneは200%で表示
        }

        return scale;
    }
}
