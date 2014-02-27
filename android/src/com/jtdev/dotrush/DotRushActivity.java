/* Copyright (C) JT-Dev 2014
 * Created, authored and designed by Cyphar.
 * This project is *not* open source (unfortunately).
 */

package com.jtdev.dotrush;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.*;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import com.jtdev.dotrush.managers.AdManager;
import com.jtdev.dotrush.db.ScoreDatabaseManager;
import com.jtdev.dotrush.managers.IScoreManager;
import com.jtdev.dotrush.managers.PseudoScoreManager;
import com.jtdev.dotrush.managers.ScoreManager;
import com.jtdev.dotrush.utils.Logger;

public class DotRushActivity extends AndroidApplication implements AdManager {
    private Logger logger;
    private AdView adView;

    private final int SHOW_ADS = 1;
    private final int HIDE_ADS = 0;

    protected Handler adHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case SHOW_ADS:
                    logger.log("showing ads");
                    adView.setEnabled(true);
                    adView.setVisibility(View.VISIBLE);
                    adView.loadAd(generateRequest());
                    break;
                case HIDE_ADS:
                    logger.log("hiding ads");
                    adView.setEnabled(false);
                    adView.setVisibility(View.GONE);
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useAccelerometer = false;
        cfg.useCompass = false;
        cfg.useGL20 = false;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        hideUI();

        logger = new Logger(this);
        IScoreManager scoreManager = null;

        try {
            scoreManager = new ScoreManager(new ScoreDatabaseManager(this.getBaseContext()));
        } catch (Exception e) {
            logger.warn("could not connect to score database");
            logger.warn("db err: " + e.getMessage());

            for(StackTraceElement ste: e.getStackTrace())
                logger.warn("ste: " + ste.toString());

            scoreManager = new PseudoScoreManager();
        }

        RelativeLayout layout = new RelativeLayout(this);
        View gameView = initializeForView(new DotRush(this, scoreManager), cfg);
        layout.addView(gameView);

        adView = new AdView(this);
        adView.setAdUnitId("a15301e94e5eb65");
        adView.setAdSize(AdSize.SMART_BANNER);

        RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        adView.loadAd(generateRequest());
        layout.addView(adView, adParams);

        setContentView(layout);
    }

    @Override
    public void setVisibility(boolean visible) {
        adHandler.sendEmptyMessage(visible ? SHOW_ADS : HIDE_ADS);
    }

    @TargetApi(11)
    private void hideUI() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
    }

    private AdRequest generateRequest() {
        Bundle bundle = new Bundle();
        bundle.putString("color_bg", "000000");
        bundle.putString("color_bg_top", "000000");
        bundle.putString("color_border", "000000");
        bundle.putString("color_link", "aaaaff");
        bundle.putString("color_text", "fafafa");
        bundle.putString("color_url", "ccccff");

        AdMobExtras extras = new AdMobExtras(bundle);
        AdRequest adRequest = new AdRequest.Builder()
                .addNetworkExtras(extras)
                .build();

        return adRequest;
    }
}