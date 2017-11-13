package com.example.project.seniorpj.Healthy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.project.seniorpj.R;

import java.util.ArrayList;
import java.util.List;

public class HealthListActivity extends Fragment {

    ListView lv_rss;
    //    private String finalUrl = "http://tutorialspoint.com/android/sampleXML.xml";
    private String finalUrl = "http://rssfeeds.sanook.com/rss/feeds/sanook/health.index.xml";
    private HandleXML obj;
    ListViewAdapter_RSS adapter;
    List<String> str;
    List<String> str_link;

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_health_list, container, false);

        lv_rss = (ListView) v.findViewById(R.id.lv_rss);

        str = new ArrayList<>();
        str_link = new ArrayList<>();

        str.clear();
        str_link.clear();

        obj = new HandleXML(finalUrl);
        obj.fetchXML();

        while (obj.parsingComplete)
//        {
            Log.e("eiei", "onClick: " + obj.getTitle());
        str.add(obj.getTitle());
        str_link.add(obj.getLink());
//        }

        adapter = new ListViewAdapter_RSS(str);
        lv_rss.setAdapter(adapter);

        lv_rss.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), Second.class);
                String title = (String) lv_rss.getItemAtPosition(position);
                intent.putExtra("title", title);
                intent.putExtra("link", str_link.get(position));
                Log.e("eiei_first", "onItemClick: " + str_link.get(position));
                startActivity(intent);
            }
        });
        return v;
    }
}