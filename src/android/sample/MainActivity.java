package android.sample;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity{

	private WebView webView;
	private String currentUrl;
	private String currentTitle;
	private String bookmarkFileName = "bookmark.txt";
		
	@SuppressLint("SetJavaScriptEnabled")
	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);   //既存のコード
		requestWindowFeature(Window.FEATURE_NO_TITLE);  //タイトルバーを非表示にする
		setContentView(R.layout.activity_main);  //既存のコード
		// Webビューの作成
		webView = (WebView) findViewById(R.id.webview);
		webView.setVerticalScrollbarOverlay(true);
		webView.setWebViewClient(new WebViewClient()  //リンクをタップしたときに標準ブラウザを起動させない
		{
			@Override
			public void onPageStarted(final WebView view, final String url, final Bitmap favicon) {
				//プログレスバーを表示(10%)
				ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar1);
				progressBar.setProgress(10);
				progressBar.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				//プログレスバーを非表示
				ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar1);
				progressBar.setVisibility(View.GONE);
				//ページのURL・タイトルを取得
				currentUrl = url;
				currentTitle = view.getTitle();
			}
			
			@Override
			public boolean shouldOverrideUrlLoading(WebView webView, String url)
			{
				//googlePlayを別アプリで開く
				if(url.startsWith("https://play.google.com/"))
				{
					Uri uri = Uri.parse(url);
					Intent intent = new Intent(Intent.ACTION_VIEW, uri);
					startActivity(intent);
					return true;
				}
		        
				return false;
			}
		});
		
		webView.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress) {
				//プログレスバー表示を更新
				ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar1);
				if(progress >= 100)
				{
					progressBar.setVisibility(View.GONE);
				}
				else
				{
					progressBar.setVisibility(View.VISIBLE);
					progressBar.setProgress(progress);
				}
			}
		});

		webView.setOnLongClickListener(new OnLongClickListener() {
		    @Override
		    public boolean onLongClick(View v) {
		        WebView webView = (WebView) v;
		        WebView.HitTestResult hr = webView.getHitTestResult();
		        String url = hr.getExtra();
		        showToast(url);
		        return false;
		    }
		});
		
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		Boolean bJavascripOn = pref.getBoolean("javascriptOn",true);

		WebSettings settings = webView.getSettings();
		settings.setSupportMultipleWindows(false);  //trueではGoogleニュースのリンクが開けない
		settings.setLoadsImagesAutomatically(true);
		//settings.setSupportZoom(true);
		//settings.setLightTouchEnabled(true);
		//settings.setBuiltInZoomControls(true);  // 読み込んだWebページをWebView上で拡大・縮小（ピンチイン・アウト）可能に
		settings.setJavaScriptEnabled(bJavascripOn);  //javascript有効化
		
		Bundle extras=getIntent().getExtras();
		if (extras!=null) {
			String sKey1 = extras.getString("url");
			webView.loadUrl(sKey1);
		}
		else if (Intent.ACTION_VIEW.equals(getIntent().getAction()) ){
		    //暗黙的インテント経由で起動された時の処理
			String url = getIntent().getDataString();
			webView.loadUrl(url);
		}
		else
		{
			webView.loadUrl("http://www.nikkei.com/");
		}
	}
	
	//戻るボタンで前のページに戻る
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		//戻るボタンで前のページに戻る
		if(keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
			webView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	// オプション・メニューを作成
	private static final int SUB_MENU_NEWS = 0;
		private static final int NIKKEI_NEWS_MENU_ID = 1;
		private static final int GOOGLE_NEWS_MENU_ID = 2;
		private static final int HOTENTRY_MENU_ID = 3;
	private static final int GOOGLE_MENU_ID = 4;
	private static final int SUB_MENU_BLOG = 5;
		private static final int ANDROID_DEV_BLOG_SEARCH = 6;
		private static final int NEXUS7_BLOG_SEARCH = 7;
		private static final int ANDROID_BLOG_SEARCH = 8;
	private static final int SUB_MENU_MANUAL = 9;
		private static final int GIT_MANUAL = 10;
	private static final int SUB_MENU_BOOKMARK = 11;
		private static final int ADD_BOOKMARK_MENU_ID = 12;
		private static final int VIEW_BOOKMARK_MENU_ID = 13;
	private static final int SETTING_MENU_ID = 14;
	private static final int FINISH_MENU_ID = 15;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		// ニュースサブメニュー
		SubMenu subMenuNews;
		subMenuNews = menu.addSubMenu(Menu.NONE, SUB_MENU_NEWS, 0, R.string.subMenu_news);
		subMenuNews.setIcon(android.R.drawable.ic_menu_directions);
		
			// 日経
			subMenuNews.add(0, NIKKEI_NEWS_MENU_ID, 0, R.string.nikkei);
			// Googleニュース
			subMenuNews.add(0, GOOGLE_NEWS_MENU_ID, 1, R.string.google_news);
			// 人気エントリー
			subMenuNews.add(0, HOTENTRY_MENU_ID, 2, R.string.hotentry);
			
		// Google
		menu.add(0, GOOGLE_MENU_ID, 1, R.string.google);
		
		// ブログ検索サブメニュー
		SubMenu subMenuBlog;
		subMenuBlog = menu.addSubMenu(Menu.NONE, SUB_MENU_BLOG, 2, R.string.subMenu_blog_search);
		subMenuBlog.setIcon(android.R.drawable.ic_menu_directions);
		
			// android 開発
			subMenuBlog.add(0, ANDROID_DEV_BLOG_SEARCH, 0, R.string.android_dev_blog_seach);
			// Nexus7
			subMenuBlog.add(0, NEXUS7_BLOG_SEARCH, 0, R.string.nexus7_blog_seach);
			// Android
			subMenuBlog.add(0, ANDROID_BLOG_SEARCH, 0, R.string.android_blog_seach);

		// マニュアルサブメニュー
		SubMenu subMenuManual;
		subMenuManual = menu.addSubMenu(Menu.NONE, SUB_MENU_MANUAL, 3, R.string.subMenu_manual);
		subMenuManual.setIcon(android.R.drawable.ic_menu_directions);

			// Gitマニュアル
			subMenuManual.add(0, GIT_MANUAL, 0, R.string.git_manual);
		
		// ブックマークサブメニュー
		SubMenu subMenuBookmark;
		subMenuBookmark = menu.addSubMenu(Menu.NONE, SUB_MENU_BOOKMARK, 4, R.string.subMenu_bookmark);
		subMenuBookmark.setIcon(android.R.drawable.ic_menu_directions);
		
			//追加
			subMenuBookmark.add(Menu.NONE,  ADD_BOOKMARK_MENU_ID, 0, R.string.add_bookmark);
			//参照
			subMenuBookmark.add(Menu.NONE, VIEW_BOOKMARK_MENU_ID, 1, R.string.view_bookmark);
		
		// 設定
		menu.add(0, SETTING_MENU_ID, 4, R.string.setting);
		
		// 終了
		menu.add(0, FINISH_MENU_ID, 8, R.string.finish);
	
		return true;
	}
	
	// メニューを選択した時の処理
	public boolean onMenuItemSelected(int featureId,MenuItem item){
		switch (item.getItemId()) {
		case NIKKEI_NEWS_MENU_ID :
			webView.loadUrl("http://www.nikkei.com/");
			return true;
		case GOOGLE_NEWS_MENU_ID :
			webView.loadUrl("http://news.google.co.jp/");
			return true;
		case HOTENTRY_MENU_ID :
			webView.loadUrl("http://slx.heteml.jp/hatebu/sp");
			return true;
		case GOOGLE_MENU_ID :
			{
				Intent intentNew = new Intent(getApplicationContext(), MainActivity.class);
				intentNew.putExtra("url","http://www.google.co.jp" );
				startActivity(intentNew);
			}
			return true;
		case ANDROID_DEV_BLOG_SEARCH :
			webView.loadUrl("https://www.google.co.jp/search?q=android+%E9%96%8B%E7%99%BA#q=android+%E9%96%8B%E7%99%BA&hl=ja&tbo=d&source=lnms&tbm=blg&sa=X&ei=e7ruULndN8eHkQXav4BQ&ved=0CBEQ_AUoAA&bav=on.2,or.r_gc.r_pw.r_qf.&bvm=bv.1357700187,d.dGI&fp=4a7b87b2fcf8f3fc&biw=1280&bih=687");
			return true;
		case NEXUS7_BLOG_SEARCH :
			webView.loadUrl("https://www.google.co.jp/search?q=android+%E9%96%8B%E7%99%BA#hl=ja&gs_rn=1&gs_ri=serp&gs_is=1&pq=android%20%E9%96%8B%E7%99%BA&cp=4&gs_id=19&xhr=t&q=nexus7&es_nrs=true&pf=p&tbo=d&tbm=blg&sclient=psy-ab&oq=nexu&gs_l=&pbx=1&bav=on.2,or.r_gc.r_pw.r_qf.&fp=13d9d789849eb92e&biw=1280&bih=629");	
			return true;
		case ANDROID_BLOG_SEARCH :
			webView.loadUrl("https://www.google.co.jp/search?q=android+%E9%96%8B%E7%99%BA#hl=ja&tbo=d&tbm=blg&sclient=psy-ab&q=android&oq=android&gs_l=serp.3..0l8.414037.415041.4.415245.7.5.0.2.2.1.605.1488.1j1j1j0j1j1.5.0...0.0...1c.1j4.ZkHRPiMpKKI&pbx=1&bav=on.2,or.r_gc.r_pw.r_qf.&fp=13d9d789849eb92e&biw=1280&bih=623");	
			return true;
		case GIT_MANUAL :
			webView.loadUrl("http://cdn8.atwikiimg.com/git_jp/pub/git-manual-jp/Documentation/user-manual.html");	
			return true;
		case ADD_BOOKMARK_MENU_ID :
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
			// アラートダイアログのタイトルを設定します
			alertDialogBuilder.setTitle("ブックマーク追加");
			// アラートダイアログのメッセージを設定します
			//alertDialogBuilder.setMessage(currentUrl);
			alertDialogBuilder.setMessage(currentTitle);
			// アラートダイアログの肯定ボタンがクリックされた時に呼び出されるコールバックリスナーを登録します
			alertDialogBuilder.setPositiveButton("追加",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// Write
						{
							FileOutputStream fileOutputStream = null;
							try {
								fileOutputStream = openFileOutput(bookmarkFileName, MODE_APPEND);  //追記モード
								//FileOutputStream fileOutputStream = openFileOutput(bookmarkFileName, MODE_PRIVATE); 
								String writeString = currentTitle + "," + currentUrl +  "\n";
								fileOutputStream.write(writeString.getBytes());
							} catch (FileNotFoundException e) {
							} catch (IOException e) {
							}
							finally {
								if (fileOutputStream != null) {
									try{
										fileOutputStream.close();
									}
									catch(Exception ex) {
									}
								}
							}
						}
						
						Toast.makeText(getApplicationContext(),"追加:" + currentUrl, Toast.LENGTH_LONG).show();
					}
				}
			);
			// アラートダイアログの否定ボタンがクリックされた時に呼び出されるコールバックリスナーを登録します
			alertDialogBuilder.setNegativeButton("キャンセル",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					}
				}
			);
			// アラートダイアログのキャンセルが可能かどうかを設定します
			alertDialogBuilder.setCancelable(true);
			AlertDialog alertDialog = alertDialogBuilder.create();
			// アラートダイアログを表示します
			alertDialog.show();
			
			return true;
		case VIEW_BOOKMARK_MENU_ID :
			// 起動先アクティビティからデータを返してもらいたい場合は
			// Activity#startActivityForResult(intent, requestCode) を使う
			// 第二引数の requestCode は 下の onActivityResult の
			// 第一引数に渡される値で、条件分岐のための数値。
			// requestCode が 0 未満の場合は startActivity(intent) と等価
			Intent intent =new Intent(this, BookmarkActivity.class);
			int requestCode = 123;
			startActivityForResult(intent, requestCode);
			return true;
		case SETTING_MENU_ID :
			//設定画面を表示
			startActivity(new Intent(getApplicationContext(), SettingActivity.class));
			return true;
		case FINISH_MENU_ID :
			//終了ボタンが押されたとき
			webView.clearCache(true); // キャッシュのクリア
			webView.clearHistory(); // 履歴のクリア
			finish();  //終了
			return true;
		default :
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}
	
	// ブックマーク画面から戻ってきた時の処理
	// startActivityForResult で起動させたアクティビティが
	// finish() により破棄されたときにコールされる
	// requestCode : startActivityForResult の第二引数で指定した値が渡される
	// resultCode : 起動先のActivity.setResult の第一引数が渡される
	// Intent data : 起動先Activityから送られてくる Intent
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		switch (requestCode) {
		case 123:
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			webView.loadUrl(bundle.getString("key.StringData"));
		} else if (resultCode == RESULT_CANCELED) {
			
		}
		break;
		
		default:
			break;
		}
	}
	
	// 画面の向きを切り替えた時に画面を保持する
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}
	
	// トースト表示
	public void showToast(String Message)
	{
		Toast.makeText(getApplicationContext(),Message, Toast.LENGTH_LONG).show();	
	}
}
