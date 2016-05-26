package tech.sree.com.intent_services;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by ananth on 5/25/2016.
 */
public class MyService_Intent_Service extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyService_Intent_Service() {
        super("MyService_Intent_Service");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"IntentService - onStartCommand",Toast.LENGTH_LONG).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"IntentService - onDestroy",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Toast.makeText(this,"onHandleIntent",Toast.LENGTH_LONG).show();
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
        }
    }
}
