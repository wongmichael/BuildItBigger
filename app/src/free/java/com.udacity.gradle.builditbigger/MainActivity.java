package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.jokes.Joker;


public class MainActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;
    public String joke;
    public ProgressBar spinner;
    //public TextView instructionsTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.progressBar1);
        //instructionsTv = findViewById(R.id.instructions_text_view);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build()
        );
        mInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build()); //load next interstitial
                Intent intent = new Intent(MainActivity.this, com.example.mylibrary.MainActivity.class);
                intent.putExtra(com.example.mylibrary.MainActivity.JOKE_KEY,joke);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        //Joker joker = new Joker();
        //Toast.makeText(this, "derp", Toast.LENGTH_SHORT).show();
        //Toast.makeText(this,joker.getJoke(),Toast.LENGTH_SHORT).show();
        //launchLibraryActivity(view);
        spinner.setVisibility(View.VISIBLE);
        //new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "Manfred"));
        new EndpointsAsyncTask(new EndpointsAsyncTask.EndpointsCallback() {
            @Override
            public void onTaskFinished(String results) {
                spinner.setVisibility(View.GONE);
                if(results!=null){
                    joke=results;
                    Log.d("onTaskFinished",joke);
                    showInterstitialAd();
                } else{
                    //instructionsTv.append("\n No joke found.");
                    Toast.makeText(getApplicationContext(),"No joke found.",Toast.LENGTH_LONG);
                    Log.d("telljoke","no data found");
                }
            }
        }).execute(new Pair<Context, String>(this,"Manfred"));
        //showInterstitialAd();
    }

    public void showInterstitialAd(){
        if(mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }else{
            Log.d("showInterstitial","the interstitial wasn't loaded yet..");
            mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build()); //load next interstitial
            mInterstitialAd.show();
        }
    }

    public void launchLibraryActivity(View view){
        Intent intent = new Intent(this, com.example.mylibrary.MainActivity.class);
        intent.putExtra(com.example.mylibrary.MainActivity.JOKE_KEY,new Joker().getJoke());
        startActivity(intent);
        //Toast.makeText(this,"test test",Toast.LENGTH_LONG).show();
    }


}
