package com.example.sabbirhossain.dynamicui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.List;

public class ForumActivity extends Activity {

    String TAG = "ForumActivity";
    Button cancelBtn, nextBtn, previousBtn, previewBtn;
    List<Field> fields;
    public String type;
    public String label;
    public String hint;
    public int order;
    int page = 0;

    LinearLayout linearLayout;
    Model model;
    String jArray = "[{\"contentType\":\"text\",\"contentLabel\":\"name\",\"hints\":\"john\",\"order\":0},{\"contentType\":\"number\",\"contentLabel\":\"Phone Number\",\"hints\":0,\"order\":1},{\"contentType\":\"text\",\"contentLabel\":\"Address\",\"hints\":address,\"order\":3}]";
    JSONArray jsonArray;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        try {
            jsonArray = new JSONArray(jArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        linearLayout = (LinearLayout) findViewById(R.id.dynamicLayout);
        getDetails(page);

        cancelBtn = (Button) findViewById(R.id.btnCancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.removeAllViews();
                PreviewListviewAdapter listviewAdapter = new PreviewListviewAdapter(ForumActivity.this, AppConstance.arrayList);
                listviewAdapter.clear();
                listviewAdapter.notifyDataSetChanged();
                Intent intent = new Intent(ForumActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        previousBtn = (Button) findViewById(R.id.btnPrevious);
        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page--;
                linearLayout.removeAllViews();
                nextBtn.setVisibility(View.VISIBLE);
                if (page == 0) {
                    previousBtn.setVisibility(View.INVISIBLE);
                }
                getDetails(page);
            }
        });


        nextBtn = (Button) findViewById(R.id.btnNext);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page++;
                Log.e(TAG, "page " + page);
                if (page == 2) {
                    nextBtn.setVisibility(View.INVISIBLE);
                }
                linearLayout.removeAllViews();
                previousBtn.setVisibility(View.VISIBLE);
                getDetails(page);

            }
        });
        previewBtn = (Button) findViewById(R.id.btnPreview);
        previewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForumActivity.this, PreviewActivity.class);
                startActivity(intent);

            }
        });
    }

    private void createUi() {
        model = new Model();

        if (type.contains("text")) {
            TextView textView = new TextView(this);
            textView.setText(AppConstance.label);
            textView.setTextSize(20);
            EditText editText = new EditText(this);
            editText.setPadding(50, 0, 0, 0);
            editText.setHeight(150);
            editText.setBackground(getResources().getDrawable(R.drawable.background));
            editText.setHint(hint);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.e(TAG, "before value " + AppConstance.value);

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.e(TAG, "on text changed value " + AppConstance.value);

                }

                @Override
                public void afterTextChanged(Editable s) {
                    AppConstance.value = s.toString();
                    Log.e(TAG, "afterTextChanged value " + AppConstance.value);
                    model.setLabel(AppConstance.label);
                    model.setValue(AppConstance.value);

                }
            });
            AppConstance.arrayList.add(model);
            linearLayout.addView(textView);
            linearLayout.addView(editText);
        } else if (type.contains("number")) {
            TextView textView = new TextView(this);
            textView.setText(AppConstance.label);
            textView.setTextSize(20);
            EditText editText = new EditText(this);
            editText.setHeight(150);
            editText.setPadding(50, 0, 0, 0);
            editText.setBackground(getResources().getDrawable(R.drawable.background));
            editText.setHint(hint);
            // editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    AppConstance.value = s.toString();
                    Log.e(TAG, "afterTextChanged value " + AppConstance.value);
                    model.setLabel(AppConstance.label);
                    model.setValue(AppConstance.value);

                }
            });
            AppConstance.arrayList.add(model);
            linearLayout.addView(textView);
            linearLayout.addView(editText);
        }
    }

    private void getDetails(int page) {
        Log.e(TAG, "page" + page);
        JSONObject jsonObject2 = null;
        try {

            jsonObject2 = jsonArray.getJSONObject(page);
            type = jsonObject2.getString("contentType");
            AppConstance.label = jsonObject2.getString("contentLabel");
            order = jsonObject2.getInt("order");
            Log.e(TAG, "order " + order);
            hint = jsonObject2.getString("hints");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        createUi();

    }

}
