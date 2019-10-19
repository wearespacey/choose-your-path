package com.keusmar.cslabs_hackathon.Models;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.keusmar.cslabs_hackathon.R;

import java.util.ArrayList;

public class ActionRecapAdapter extends BaseAdapter {

    private static ArrayList<Answer> searchArrayList;

    private LayoutInflater mInflater;

    public ActionRecapAdapter(Context context, ArrayList<Answer> results) {
        searchArrayList = results;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return searchArrayList.size();
    }

    public Object getItem(int position) {
        return searchArrayList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.recap_custom_row, null);
            holder = new ViewHolder();

            holder.actionEffectNeg = convertView.findViewById(R.id.actionEffectNeg);
            holder.actionName = convertView.findViewById(R.id.actionName);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.actionName.setText(searchArrayList.get(position).getAction().getContent());
        ArrayList<String> cats = new ArrayList<>();
        String effNeg = "Effet negatif sur ";
        ArrayList<Impact> imps = searchArrayList.get(position).getAction().getImpacts();
        for (Impact imp:imps) {
            effNeg += (imp.equals(imps.get(0)) ? "" : ", ") + imp.getCaracteristic();
        }
        holder.actionEffectNeg.setText(effNeg);

        return convertView;
    }

    static class ViewHolder {
        TextView actionName;
        TextView actionEffectNeg;
    }
}
