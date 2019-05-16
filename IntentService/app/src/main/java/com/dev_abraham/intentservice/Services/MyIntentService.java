package com.dev_abraham.intentService.Services;

import android.app.IntentService;
import android.content.Intent;

    public class MyIntentService extends IntentService {

        public static final String ACTION_PROGRESS =  "dev_abraham.action.PROGR";
        public static final String ACTION_END =       "dev_abraham.action.END";

        public MyIntentService() {
            super("MiIntentService");
        }

        @Override
        protected void onHandleIntent(Intent intent)
        {
            int loops = intent.getIntExtra("loops", 0);

            for(int i=1; i<=loops; i++) {
                sleep();
                Intent bcIntent = new Intent();
                bcIntent.setAction(ACTION_PROGRESS);
                bcIntent.putExtra("progress", i);
                sendBroadcast(bcIntent);
            }

            Intent bcIntent = new Intent();
            bcIntent.setAction(ACTION_END);
            sendBroadcast(bcIntent);
        }

        private void sleep()
        {
            try {
                Thread.sleep(100);
            } catch(InterruptedException e) {}
        }
    }


