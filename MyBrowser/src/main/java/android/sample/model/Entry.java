package android.sample.model;

import android.content.Context;
import android.sample.R;

/**
 * Created by hiroyuki on 2/18/15.
 */
public class Entry {
    private String url = "";
    private String title = "";
    private int scale = 100;
    private boolean xlarge = false;      // xlarge true=タブレット、false=タブレットでない

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public int getScale() {
        return scale;
    }


    public boolean getXlarge() {
        return xlarge;
    }

    public void setUrl(String url) {
        this.url = null;
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = null;
        this.title = title;
    }

    public void setUrlTitle(String url, String title) {
        this.url = null;
        this.url = url;

        this.title = null;
        this.title = title;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public void setXlarge(Context context) {
        xlarge = context.getResources().getBoolean(R.bool.isTablet);
    }
}
