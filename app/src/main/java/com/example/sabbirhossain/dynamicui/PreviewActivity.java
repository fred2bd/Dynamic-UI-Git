package com.example.sabbirhossain.dynamicui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * Created by sabbirhossain on 7/14/16.
 */
public class PreviewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        ListView previewListview = (ListView) findViewById(R.id.previewList);
        PreviewListviewAdapter listviewAdapter = new PreviewListviewAdapter(PreviewActivity.this, AppConstance.arrayList);
        previewListview.setAdapter(listviewAdapter);


    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        PreviewListviewAdapter listviewAdapter = new PreviewListviewAdapter(PreviewActivity.this, AppConstance.arrayList);
//        listviewAdapter.clear();
//        listviewAdapter.notifyDataSetChanged();
//
//    }
}
