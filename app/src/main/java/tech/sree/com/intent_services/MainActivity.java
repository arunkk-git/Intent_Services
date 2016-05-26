package tech.sree.com.intent_services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button ;
    MyService_IBinder myService_iBinder = null;
    boolean isBind =  false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.startSrv);
    }
    public void process(View V){
        Intent intent;
        switch (V.getId()) {
            case R.id.startSrv:
                intent =  new Intent(this,MyService_started.class);
                startService(intent);
                break;
            case R.id.stop:
                intent =  new Intent(this,MyService_started.class);
                stopService(intent);
                break;
            case R.id.intentSrv:
                intent =  new Intent(this,MyService_started.class);
                startService(intent);
                break;
            case R.id.binderSrv:
                intent =  new Intent(this,MyService_IBinder.class);
        // startService(intent);
                bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.msgONE:
                if (isBind && myService_iBinder != null) {
                    String msg = myService_iBinder.getFirstMsg();
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                }else
                Toast.makeText(getApplicationContext(),"First Bind the service",Toast.LENGTH_LONG).show();

                break;

            case R.id.msgTWO:
                if (isBind && myService_iBinder != null) {
                    String msg = myService_iBinder.getSecondMsg();
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                }else
                    Toast.makeText(getApplicationContext(),"First Bind the service",Toast.LENGTH_LONG).show();

                break;

            default:
        }

    }
    ServiceConnection serviceConnection =  new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService_IBinder.LocalService localService = (MyService_IBinder.LocalService) service;
            myService_iBinder = localService.getMyService();
            isBind = true ;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myService_iBinder = null;
            isBind = false ;
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        if (isBind)
        unbindService(serviceConnection);
        myService_iBinder = null;
        isBind = false ;

    }
}
