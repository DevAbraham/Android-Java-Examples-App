package com.dev_abraham.intentService.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dev_abraham.intentService.Services.MyIntentService;
import com.dev_abraham.intentService.R;

public class MainActivity extends AppCompatActivity {

    TextView tvPorcentaje;
    Button btnStarService;
    ProgressBar pbService;
    Boolean service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvPorcentaje = findViewById(R.id.tvPorcentaje);
        btnStarService = findViewById(R.id.btnService);
        pbService = findViewById(R.id.progressBar);
        service=false;


        btnStarService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (service){
                    Toast.makeText(MainActivity.this, "Service run!!", Toast.LENGTH_SHORT).show();

                }else {
                    pbService.setProgress(0);
                    tvPorcentaje.setText(0 + " % ");
                    Intent intent = new Intent(MainActivity.this, MyIntentService.class);
                    intent.putExtra("loops", 100);
                    startService(intent);
                    service = true;
                }
            }
        });


        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MyIntentService.ACTION_PROGRESS);
        intentFilter.addAction(MyIntentService.ACTION_END);


        //Si no se registra no recibe el broadcast
        ProgressReceiver progressReceiver = new ProgressReceiver();
        registerReceiver(progressReceiver, intentFilter);

    }




    public class ProgressReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(MyIntentService.ACTION_PROGRESS)) {
                int prog = intent.getIntExtra("progress", 0);
                pbService.setProgress(prog);
                tvPorcentaje.setText(prog + " % ");
            }
            else if(intent.getAction().equals(MyIntentService.ACTION_END)) {
                Toast.makeText(MainActivity.this, "Service Finish", Toast.LENGTH_SHORT).show();
                service=false;
            }
        }
    }


}
