package com.example.tocuhnclean;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Onboarding extends AppCompatActivity {

    public static final int max = 3;
    Button login,register;

    public String[] judul_array ={"Selamat Datang di TouchNclean!",
            "Pilih Waktu",
            "Tunggu Hasil !"};

    public String[] deskripsi_array = {
            "Pilih Layanan Profesional TouchnClean sesuai kebutuhan kamu"
            , "Atur jadwal dan waktu  kapan-pun kamu mau"
            , "Pembersihan oleh Team TouchnClean memberikan Pelayanan  yang maksimal"
    };

    public  int[] gambar_array = {
            R.drawable.pict1
            ,R.drawable.pict2
            ,R.drawable.pict3
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        Komponen();
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Onboarding.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Onboarding.this, Register.class);
                startActivity(intent);
            }
        });

    }



    public class MyViewPagerAdapter extends PagerAdapter
    {
        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.item_onboarding, container, false);
            ((TextView) view.findViewById(R.id.judul)).setText(judul_array[position]);
            ((TextView) view.findViewById(R.id.deskripsi)).setText(deskripsi_array[position]);
            ((ImageView) view.findViewById(R.id.gambar)).setImageResource(gambar_array[position]);
            container.addView(view);

            return view;

        }

        @Override
        public int getCount() {
            return judul_array.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }

    }


    public void Komponen()
    {
        ViewPager viewPager = findViewById(R.id.viewPager);

        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                progressDot(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(myViewPagerAdapter);
        progressDot(0);


    }

    public void progressDot(int index)
    {
        LinearLayout dotsLayout = findViewById(R.id.layoutDots);
        ImageView[] dots = new ImageView[max];

        dotsLayout.removeAllViews();
        for (int i = 0;i<dots.length;i++)
        {
            dots[i] = new ImageView(this);
            int width_heigh =15;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_heigh,width_heigh));
            params.setMargins(10,10,10,10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.dot);
            dots[i].setColorFilter(getResources().getColor(R.color.teal_200), PorterDuff.Mode.SRC_IN);
            dotsLayout.addView(dots[i]);
        }

        dots[index].setImageResource(R.drawable.dot);
        dots[index].setColorFilter(getResources().getColor(R.color.biru), PorterDuff.Mode.SRC_IN);




    }
}