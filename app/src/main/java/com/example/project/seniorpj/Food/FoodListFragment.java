package com.example.project.seniorpj.Food;

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
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.project.seniorpj.Food.DAO_FoodList.DAO_FoodList;
import com.example.project.seniorpj.FoodListServiceAPI;
import com.example.project.seniorpj.R;
import com.example.project.seniorpj.Result.ResultActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Smew on 22/9/2560.
 */

public class FoodListFragment extends Fragment {

    ListViewAdapter adapter;
    ListView lv_foodList;

    Gson gson;
    Retrofit retrofit;

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_foodlist, container, false);

        gson = new GsonBuilder()
                .create();

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request.Builder requestBuilder = original.newBuilder()
                        .method(original.method(), original.body());

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl(FoodListServiceAPI.base_url)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        FoodListServiceAPI git = retrofit.create(FoodListServiceAPI.class);
        Call<DAO_FoodList> call = git.getfoodlist(".json");
        Log.e("Url", call.request().url().toString());

        final ProgressDialog dialog = ProgressDialog.show(getContext(), "",
                "Loading. Please wait...", true);

        call.enqueue(new Callback<DAO_FoodList>() {
            @Override
            public void onResponse(Call<DAO_FoodList> call, Response<DAO_FoodList> response) {
                int statusCode = response.code();

                if (response.body() == null) {
                    dialog.dismiss();
                    Log.e("eiei","null");
                }
                if (response.body() != null) {
                    dialog.dismiss();
                    DAO_FoodList data = response.body();
                    Log.e("eiei",data.toString());
                }
            }
            @Override
            public void onFailure(Call<DAO_FoodList> call, Throwable t) {
            }
        });

        lv_foodList = (ListView) v.findViewById(R.id.lv_foodlist);

        List<String> str = new ArrayList<>();
        str.add("kaokaijiao&&&808&&&27.9&&&53.2&&&54.5");
        str.add("kaokamoo&&&501&&&20&&&20.6&&&58.7");
        str.add("kaokapaogai&&&69&&&24.2&&&14.8&&&59.9");
        str.add("kaoklukgapi&&&614&&&20.3&&&24.3&&&78.8");
        str.add("kaomangai&&&619&&&10.9&&&28&&&80.9");
        str.add("kaomhokgai&&&551&&&24.2&&&21.2&&&66.1");
        str.add("kaomoodeang&&&418&&&19.7&&&8.6&&&65.4");
        str.add("padseeiu&&&680&&&22&&&34&&&71");
        str.add("padthai&&&580&&&19&&&30&&&58");
        str.add("radhnar&&&310&&&11.1&&&15.1&&&32.3");
//
        adapter = new ListViewAdapter(str);
        lv_foodList.setAdapter(adapter);

        lv_foodList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                Intent intent = new Intent(getContext(), DetailFoodActivity_FoodList.class);
//                String namefood = (String) lv_foodList.getItemAtPosition(position);
//                intent.putExtra("namefood", namefood);
//                startActivity(intent);
                Intent intent = new Intent(getContext(), ResultActivity.class);
                String namefood = (String) lv_foodList.getItemAtPosition(position);
                intent.putExtra("namefood", namefood);
                startActivity(intent);
            }
        });

//        mHelper = new DBHelper(getContext());
//        mDb = mHelper.getWritableDatabase();
//        mHelper.onUpgrade(mDb, 1, 1);
//        mCursor = mDb.rawQuery("SELECT " + DBHelper.COL_NAMEFOOD + ", " + DBHelper.COL_ENERGY
//                + ", " + DBHelper.COL_PROTEIN + "," + DBHelper.COL_LIPID + "," + DBHelper.COL_CARBOHYDRATE +
//                " FROM " + DBHelper.TABLE_NAME, null);
//
//        ArrayList<String> dirArray = new ArrayList<String>();
//        mCursor.moveToFirst();
//
//        while (!mCursor.isAfterLast()) {
//            dirArray.add(mCursor.getString(mCursor.getColumnIndex(DBHelper.COL_NAMEFOOD)) + "\n"
//                    + mCursor.getString(mCursor.getColumnIndex(DBHelper.COL_ENERGY)) + "\n"
//                    + mCursor.getString(mCursor.getColumnIndex(DBHelper.COL_PROTEIN)) + "\n"
//                    + mCursor.getString(mCursor.getColumnIndex(DBHelper.COL_LIPID)) + "\n"
//                    + mCursor.getString(mCursor.getColumnIndex(DBHelper.COL_CARBOHYDRATE)) + "\n"
//            );
//            mCursor.moveToNext();
//        }
//
//        Log.d("eiei", ""+dirArray);
//
//        ArrayAdapter<String> adapterDir = new ArrayAdapter<String>(getContext()
//                , android.R.layout.simple_list_item_1, dirArray);
//        lv_foodList.setAdapter(adapterDir);

//        adapter = new ListViewAdapter(dirArray);
//        lv_foodList.setAdapter(adapter);

        return v;
    }

//    public void onPause() {
//        super.onPause();
//        mHelper.close();
//        mDb.close();
//    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_search:
//                Toast.makeText(getActivity().getApplicationContext(), "action_search", Toast.LENGTH_SHORT).show();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) MenuItemCompat.getActionView(searchItem);
        if (searchView != null) {
            searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
            searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    adapter.getFilter().filter(newText);
                    return true;
                }
            });
        }
    }
}
