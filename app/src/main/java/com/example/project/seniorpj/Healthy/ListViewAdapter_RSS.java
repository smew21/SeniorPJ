package com.example.project.seniorpj.Healthy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.project.seniorpj.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Smew on 22/9/2560.
 */

public class ListViewAdapter_RSS extends BaseAdapter implements Filterable {

    List<String> foodName = new ArrayList<>();
    TextView tvrss;
    List<String> mStringFilterList = null;
    ValueFilter valueFilter;

    public ListViewAdapter_RSS(List<String> foodName) {
        this.foodName = foodName;
        mStringFilterList = foodName;
    }

    @Override
    public int getCount() {
        return foodName.size();
    }

    @Override
    public Object getItem(int position) {
        return foodName.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater mInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.item_listview_rss, parent, false);

        tvrss = (TextView) convertView.findViewById(R.id.tv_rss);
        tvrss.setText(foodName.get(position));

        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<String> filterList = new ArrayList<>();
            if (constraint != null && constraint.length() > 0) {
                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if (mStringFilterList.get(i).toLowerCase().contains(constraint.toString()))
                        filterList.add(mStringFilterList.get(i));
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            foodName = (List<String>) results.values;
            notifyDataSetChanged();
        }
    }
}
