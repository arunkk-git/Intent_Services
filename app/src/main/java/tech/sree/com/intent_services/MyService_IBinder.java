package tech.sree.com.intent_services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by ananth on 5/25/2016.
 */
public class MyService_IBinder extends Service {

    private final Binder iBinder =  new LocalService();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        Toast.makeText(getApplicationContext(),"onStartCommand",Toast.LENGTH_LONG).show();
//        return START_NOT_STICKY;
//    }

    public class LocalService extends Binder{
        MyService_IBinder getMyService(){
            return MyService_IBinder.this;
        }
    }

    public String getFirstMsg(){

        Thread thread =  new Thread(new myThread());
        thread.start();
        return "This is First Message From Binder : "+System.currentTimeMillis();
    }


    public String getSecondMsg(){
        Thread thread =  new Thread(new myThread());
        thread.start();
        return "This is Second Message From Binder : "+System.currentTimeMillis();
    }

    class myThread implements Runnable{
        @Override
        public void run() {
            int i = 0;
            synchronized (this){
                while (i < 10){
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
            }
        }
    }

}
