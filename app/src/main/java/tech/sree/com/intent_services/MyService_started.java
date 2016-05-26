package tech.sree.com.intent_services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by ananth on 5/25/2016.
 */
public class MyService_started extends Service {

    private class myThread  implements Runnable{
        int serviceId;
        myThread(int serviceId){
            this.serviceId = serviceId;
        }

        @Override
        public void run() {
            int i  =0 ;
            synchronized (this){
                while(i<10){
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
                stopSelf();
            }
        }
    }
    @Override
    public void onCreate() {
        Toast.makeText(getBaseContext(),"onCreate",Toast.LENGTH_LONG).show();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getBaseContext(),"onStartCommand",Toast.LENGTH_LONG).show();
        Thread thread =  new Thread( new myThread(startId));
        thread.start();

//        synchronized (this){
//            while(i<10){
//                try {
//                    Thread.sleep(1500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                i++;
//            }
//        stopSelf();
//        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(getBaseContext(),"onDestroy",Toast.LENGTH_LONG).show();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
