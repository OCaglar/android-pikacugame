package com.onur.pikacu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    TextView timetxt, besttxt, skortxt,sonuc;
    ImageView imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9;
    Random random = new Random();
    int rastgele = random.nextInt(9);
    int skor=1, counter=0,sayac = 0;
    androidx.gridlayout.widget.GridLayout layout;
    ImageView[] gorseller;

    Handler handler = new Handler();
    Runnable runnable;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        timetxt = findViewById(R.id.timetxt);
        skortxt = findViewById(R.id.skortxt);
        besttxt = findViewById(R.id.besttxt);
        imageView = findViewById(R.id.imageView);
        imageView2 =findViewById(R.id.imageView2);
        imageView3 =findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 =findViewById(R.id.imageView8);
        imageView9 =findViewById(R.id.imageView9);
        layout =findViewById(R.id.layout);
        sonuc = findViewById(R.id.sonuc);
        System.out.println(rastgele);
        SharedPreferences skortut = this.getPreferences(Context.MODE_PRIVATE);
        int bestskor = skortut.getInt("yuksekSkor",0);
        besttxt.setText("En iyi skor: "+bestskor);
//        aydinlatma(rastgele);
        gorseller = new ImageView[]{imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};
        gorselgizle();
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                counter++;
                timetxt.setText(counter+"/10sn");
            }

            @Override
            public void onFinish() {
                timetxt.setText("Zaman doldu");
                handler.removeCallbacks(runnable);
                layout.setVisibility(View.INVISIBLE);

                sonuc.setText("Oyun bitti...");
                SharedPreferences skortut = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
                int bestskorr = skortut.getInt("yuksekSkor",0);
                if ( skor>bestskorr){
                    SharedPreferences.Editor editor =skortut.edit();
                    editor.putInt("yuksekSkor",skor);
                    editor.apply();
                }
                AlertDialog.Builder mesaj = new AlertDialog.Builder(MainActivity.this);
                mesaj.setTitle("Yeniden Başla");
                mesaj.setMessage("Tekrar oynamak ister misiniz?");
                mesaj.setCancelable(false);
                mesaj.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent bastanbasla = getIntent();
                        finish();
                        startActivity(bastanbasla);
                    }
                });
                mesaj.setNeutralButton("İptal Et", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Oyun bitti...",Toast.LENGTH_SHORT).show();
                    }
                });
                mesaj.setNegativeButton("Çıkış Yap", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(1);
                    }
                }).show();



            }
        }.start();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public int aydinlatma(int rastgele) {
        System.out.println("class: "+rastgele);
        if (rastgele == 0) {
//            rastgele = random.nextInt(9);
            imageView.setVisibility(View.VISIBLE);
            return rastgele;
        } else if (rastgele == 1) {
//            rastgele = random.nextInt(9);
            imageView2.setVisibility(View.VISIBLE);
            return rastgele;
        } else if (rastgele == 2) {
//            rastgele = random.nextInt(9);
            imageView3.setVisibility(View.VISIBLE);
            return rastgele;
        } else if (rastgele == 3) {
//            rastgele = random.nextInt(9);
            imageView4.setVisibility(View.VISIBLE);
            return rastgele;
        } else if (rastgele == 4) {
//            rastgele = random.nextInt(9);
            imageView5.setVisibility(View.VISIBLE);
            return rastgele;
        } else if (rastgele == 5) {
//            rastgele = random.nextInt(9);
            imageView6.setVisibility(View.VISIBLE);
            return rastgele;
        } else if (rastgele == 6) {
//            rastgele = random.nextInt(9);
            imageView7.setVisibility(View.VISIBLE);
            return rastgele;
        } else if (rastgele == 7) {
//            rastgele = random.nextInt(9);
            imageView8.setVisibility(View.VISIBLE);
            return rastgele;
        } else if (rastgele == 8) {
//            rastgele = random.nextInt(9);
            imageView9.setVisibility(View.VISIBLE);
            return rastgele;
        }
        return (0);
    }
    public void gorselgizle (){
         runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView resim : gorseller) {
                    resim.setVisibility(View.INVISIBLE);}

                    int i = random.nextInt(9);
                    gorseller[i].

                            setVisibility(View.VISIBLE);
                    handler.postDelayed(runnable,850);
                }

        };
        handler.post(runnable);
    }

    public void kontrol (View view){
        gorselgizle();
        rastgele = random.nextInt(9);
//        aydinlatma(rastgele);

        sonuc.setText("1 puan kazandın...");
        skortxt.setText("Skorunuz: "+(skor++));

    }

}