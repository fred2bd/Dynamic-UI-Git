package com.example.sabbirhossain.dynamicui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sabbirhossain on 7/18/16.
 */
public class PreviewListviewAdapter extends ArrayAdapter<Model> {

    private final Activity context;
    ArrayList<Model> arrayList;
    String TAG = "SelectedRecordAdapter";
    int aa;

    public PreviewListviewAdapter(Activity context, ArrayList<Model> arrayList) {
        super(context, R.layout.preview_item, arrayList);
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final LayoutInflater inflater = context.getLayoutInflater();
        final View rootView = inflater.inflate(R.layout.preview_item, null, true);
        Model model = arrayList.get(position);
        TextView label = (TextView) rootView.findViewById(R.id.txtLabel);
        TextView value = (TextView) rootView.findViewById(R.id.txtValue);
        label.setText(model.getLabel());
        value.setText(model.getValue());
        return rootView;
    }
}
