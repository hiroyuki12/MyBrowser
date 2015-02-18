package android.sample.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.sample.R;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class BookmarkActivity extends Activity {

    private WebView webView;
    private String bookmarkFileName = "bookmark.txt";
    private ListView listView = null;
    private String bookmarks;
    private String deleteUrl;
    private int deletePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);   //既存のコード
        requestWindowFeature(Window.FEATURE_NO_TITLE);  //タイトルバーを非表示にする
        setContentView(R.layout.activity_main);  //既存のコード
        // Webビューを非表示
        webView = (WebView) findViewById(R.id.webview);
        webView.setVisibility(View.GONE);
        // プログレスバーを非表示
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        progressBar.setVisibility(View.GONE);

        // ブックマークファイルを読み込みリストビューにセット
        Read();

        //リスト項目がクリックされた時の処理
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                String item = (String) listView.getItemAtPosition(position);  // title, url
                String[] url = item.split(",");
                if(url.length > 1)
                {
                    // 返すデータ(Intent&Bundle)の作成
                    Intent data = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("key.StringData", url[1]);
                    bundle.putInt("key.intData", 123456789);
                    data.putExtras(bundle);
                    // setResult() で bundle を載せた送るIntent dataをセットする
                    // 第一引数は…Activity.RESULT_OK,Activity.RESULT_CANCELED など
                    setResult(RESULT_OK, data);
                }
                // finish() で終わらせてIntent data を送る
                finish();
            }
        });

        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //リスト項目が選択された時の処理
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                String item = (String) listView.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), item + " selected",
                        Toast.LENGTH_LONG).show();
            }

            //リスト項目がなにも選択されていない時の処理
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "no item selected",
                        Toast.LENGTH_LONG).show();
            }
        });

        // コンテキストメニューのためにリストビューを登録
        registerForContextMenu(listView);
    }

    // ブックマークファイルを読み込みリストビューにセット
    public void Read()
    {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = openFileInput(bookmarkFileName);
            byte[] readBytes = new byte[fileInputStream.available()];
            fileInputStream.read(readBytes);
            String readString = new String(readBytes);
            bookmarks = readString;
            Log.v("readString", readString);

            // XMLで定義したandroid:idの値を指定してListViewを取得します。
            listView = (ListView) findViewById(R.id.listView1);
            // ListViewに表示する要素を保持するアダプタを生成します。
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1);
            // 要素を追加
            String[] bookmark = bookmarks.split("\n");
            for( String data : bookmark ) {
                arrayAdapter.add(data);
            }
            // 選択行の色を変えるように設定
            //listView.setSelector(new PaintDrawable(Color.LTGRAY));
            // アダプタを設定
            listView.setAdapter(arrayAdapter);
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        finally {
            if (fileInputStream != null) {
                try{
                    fileInputStream.close();
                }
                catch(Exception ex) {
                }
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        AdapterContextMenuInfo adapterinfo = (AdapterContextMenuInfo)menuInfo;
        ListView listView = (ListView)v;

        deleteUrl = (String)listView.getItemAtPosition(adapterinfo.position);
        deletePosition = adapterinfo.position;
        // コンテキストメニューを作る
        menu.setHeaderTitle(deleteUrl);  //ListViewのURL
        //menu.add(R.string.context_delete);
        menu.add("削除");

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle()=="削除")
        {
            Delete();

            Toast.makeText(getApplicationContext(), "削除: " + deleteUrl,
                    Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }

    public void Delete()
    {
        // 改行で分解
        String[] bookmark = bookmarks.split("\n");

        FileOutputStream fileOutputStream = null;

        try {
            int i=0;
            //削除したブックマーク以外をファイルに書き込む
            for( String data : bookmark )
            {
                if(i != deletePosition)
                {
                    if(i==0 || (deletePosition==0 && i==1))
                    {
                        fileOutputStream = openFileOutput(bookmarkFileName, MODE_PRIVATE);
                        String writeString = data + "\n";
                        fileOutputStream.write(writeString.getBytes());
                    }
                    else
                    {
                        fileOutputStream = openFileOutput(bookmarkFileName, MODE_APPEND);  //追記モード
                        String writeString = data + "\n";
                        fileOutputStream.write(writeString.getBytes());
                    }
                }
                i++;
            }

            //削除した後のブックマークファイルを読み込む
            Read();
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

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        //if(mLongPressed){
        // 長押しされた時の処理
        //}else if(!mLongPressed){
        // 単押しされた時の処理
        if(keyCode == KeyEvent.KEYCODE_BACK){
            // 第一引数は…Activity.RESULT_OK,
            // Activity.RESULT_CANCELED など
            setResult(RESULT_CANCELED, null);
            // 戻るキーが押された時の処理
            finish(); // 戻るキーが長押しされたらブックマーク画面を終了
        }
        //}

        return super.onKeyUp(keyCode, event);
    }
}
