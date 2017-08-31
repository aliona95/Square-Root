package com.example.squareroot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class SquareRootActivity extends AppCompatActivity {

    EditText editText;
    float number;
    float root;
    Button button;
    TextView textView;

    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_square_root);

        ////////////////////////////////////////////////////////////////////////////////////////////
        MobileAds.initialize(this, "ca-app-pub-9305604647969151~2402231208");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        //AdRequest adRequest = new AdRequest.Builder().addTestDevice("53E85F858990D78F0B00A510AE711E4F").build();

        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-9305604647969151/4320272944");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                finish();
            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////

        editText = (EditText) findViewById(R.id.editText2);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView8);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (String.valueOf(editText.getText().toString()).equals("")) {
                    number = 0;
                } else {
                    number = Float.parseFloat(String.valueOf(editText.getText().toString()));
                }
                root = (float) (Math.sqrt(number));
                textView.setText(String.valueOf(root));
            }
        } ;

        button.setOnClickListener(onClickListener);
    }

    public void showInterastial(){
        if(mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }else{
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        showInterastial();

    }
}
