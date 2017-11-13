package com.example.project.seniorpj.Food;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project.seniorpj.R;

/**
 * Created by Smew on 22/9/2560.
 */

public class DetailFoodActivity_FoodList extends AppCompatActivity {

    ImageView img;
    TextView tv_namefood;
    TextView tv_energy;
    TextView tv_protein;
    TextView tv_lipid;
    TextView tv_carbs;
    Toolbar toolbar;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailfood_foodlist);

        mContext = this;

        img = (ImageView) findViewById(R.id.img_detailfood_foodlist);
        tv_namefood = (TextView) findViewById(R.id.tv_namefood_detailfood_foodlist);
        tv_energy = (TextView) findViewById(R.id.tv_energy_foodlist);
        tv_protein = (TextView) findViewById(R.id.tv_protein_foodlist);
        tv_lipid = (TextView) findViewById(R.id.tv_lipid_foodlist);
        tv_carbs = (TextView) findViewById(R.id.tv_carbs_foodlist);
        toolbar = (Toolbar) findViewById(R.id.toolbar_foodlist);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.icon_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        String namefood = intent.getStringExtra("namefood");
        String _namefood[];
        _namefood = namefood.split("&&&");
        tv_namefood.setText(_namefood[0]);
        tv_energy.setText(_namefood[1]);
        tv_protein.setText(_namefood[2]);
        tv_lipid.setText(_namefood[3]);
        tv_carbs.setText(_namefood[4]);

        if (_namefood[0].equals("kaokaijiao"))
        {
            img.setImageResource(R.mipmap.kaokaijiao);
        }
        if (_namefood[0].equals("kaokamoo"))
        {
            img.setImageResource(R.mipmap.kaokamoo);
        }
        if (_namefood[0].equals("kaokapaogai"))
        {
            img.setImageResource(R.mipmap.kaokapaogai);
        }
        if (_namefood[0].equals("kaoklukgapi"))
        {
            img.setImageResource(R.mipmap.kaoklukgapi);
        }
        if (_namefood[0].equals("kaomangai"))
        {
            img.setImageResource(R.mipmap.kaomangai);
        }
        if (_namefood[0].equals("kaomhokgai"))
        {
            img.setImageResource(R.mipmap.kaomhokgai);
        }
        if (_namefood[0].equals("kaomoodeang"))
        {
            img.setImageResource(R.mipmap.kaomoodeang);
        }
        if (_namefood[0].equals("padseeiu"))
        {
            img.setImageResource(R.mipmap.padseeiu);
        }
        if (_namefood[0].equals("padthai"))
        {
            img.setImageResource(R.mipmap.padthai);
        }
        if (_namefood[0].equals("radhnar"))
        {
            img.setImageResource(R.mipmap.radhnar);
        }


    }

}
