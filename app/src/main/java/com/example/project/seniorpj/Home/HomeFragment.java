package com.example.project.seniorpj.Home;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.egco428.camera.CameraOneStepActivity;
import com.example.project.seniorpj.Food.DAO_FoodList.DAO_FoodList;
import com.example.project.seniorpj.Food.FoodListFragment;
import com.example.project.seniorpj.FoodListServiceAPI;
import com.example.project.seniorpj.GalleryFragment;
import com.example.project.seniorpj.R;
import com.example.project.seniorpj.RegisLogIn.LogInActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    List<String> namefood_today;
    List<String> namefood_yesterday;

    ListViewAdapter_Home_Today adapter_home_today;
    ListViewAdapter_Home_Yesterday adapter_home_yesterday;

    ListView lv_today;
    ListView lv_yesterday;
    TextView tv_sumEnergy_today;
    TextView tv_sumEnergy_yesterday;

    double sumEnergy_today=0;
    double sumEnergy_yesterday=0;

    Gson gson;
    Retrofit retrofit;

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        setHasOptionsMenu(true);

        lv_today = (ListView) v.findViewById(R.id.lv_today);
        lv_yesterday = (ListView) v.findViewById(R.id.lv_yesterday);
        tv_sumEnergy_today = (TextView) v.findViewById(R.id.tv_sumEnergy_today);
        tv_sumEnergy_yesterday = (TextView) v.findViewById(R.id.tv_sumEnergy_yesterday);

        namefood_today = new ArrayList<>();
        namefood_yesterday = new ArrayList<>();

        namefood_today.add("kaokaijiao&&&808&&&27.9&&&53.2&&&54.5");
        namefood_today.add("kaokamoo&&&501&&&20&&&20.6&&&58.7");
        namefood_today.add("kaokapaogai&&&69&&&24.2&&&14.8&&&59.9");
        namefood_today.add("kaoklukgapi&&&614&&&20.3&&&24.3&&&78.8");
//        namefood_today.add("kaomangai&&&619&&&10.9&&&28&&&80.9");

        namefood_yesterday.add("kaomhokgai&&&551&&&24.2&&&21.2&&&66.1");
        namefood_yesterday.add("kaomoodeang&&&418&&&19.7&&&8.6&&&65.4");
//        namefood_yesterday.add("padseeiu&&&680&&&22&&&34&&&71");
//        namefood_yesterday.add("padthai&&&580&&&19&&&30&&&58");
//        namefood_yesterday.add("radhnar&&&310&&&11.1&&&15.1&&&32.3");

        String _namefood[];
        for(int i=0; i<namefood_today.size(); i++)
        {
            _namefood = namefood_today.get(i).split("&&&");
            sumEnergy_today += Integer.parseInt(_namefood[1]);
        }

        tv_sumEnergy_today.setText(""+(int)sumEnergy_today);

        String __namefood[];
        for(int i=0; i<namefood_yesterday.size(); i++)
        {
            __namefood = namefood_yesterday.get(i).split("&&&");
            sumEnergy_yesterday += Integer.parseInt(__namefood[1]);
        }
        tv_sumEnergy_yesterday.setText(""+(int)sumEnergy_yesterday);

        adapter_home_today = new ListViewAdapter_Home_Today(namefood_today);
        adapter_home_yesterday = new ListViewAdapter_Home_Yesterday(namefood_yesterday);

        lv_today.setAdapter(adapter_home_today);
        lv_yesterday.setAdapter(adapter_home_yesterday);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.add_newdata_home, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.actioc_add:

                final Dialog dialog2 = new Dialog(getContext());
                dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog2.setContentView(R.layout.dialog_addnewdata);
                dialog2.setCancelable(true);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog2.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog2.show();
                dialog2.getWindow().setAttributes(lp);

                LinearLayout img_camera = (LinearLayout) dialog2.findViewById(R.id.img_camera_addnewdata);
                LinearLayout img_gallery = (LinearLayout) dialog2.findViewById(R.id.img_gallery_addnewdata);
                LinearLayout img_foodlist = (LinearLayout) dialog2.findViewById(R.id.img_foodlist_addnewdata);
                Button btn_cancel = (Button) dialog2.findViewById(R.id.btn_cancel_addnewdata);

                img_camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), CameraOneStepActivity.class);
                        startActivity(intent);
                    }
                });

                img_gallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Fragment fragment3 = new GalleryFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction3 = getFragmentManager().beginTransaction();
                        fragmentTransaction3.replace(R.id.frame, fragment3);
                        fragmentTransaction3.commit();
                        dialog2.dismiss();
                    }
                });

                img_foodlist.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Fragment fragment4 = new FoodListFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction4 = getFragmentManager().beginTransaction();
                        fragmentTransaction4.replace(R.id.frame, fragment4);
                        fragmentTransaction4.commit();
                        dialog2.dismiss();
                    }
                });

                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog2.dismiss();
                    }
                });
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
