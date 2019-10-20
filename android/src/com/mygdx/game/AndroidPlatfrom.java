package com.mygdx.game;
import android.app.Activity;
import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
import android.net.*;
import com.mygdx.game.*;
public class AndroidPlatfrom implements NativePlatform{
    private Activity context;
    public AndroidPlatfrom(Activity context)
    {
        this.context = context;
    }

   private boolean haveNetwork()
   {
       boolean have_WIFI = false;
       boolean have_MobileData = false;

       ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
       NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
       for(NetworkInfo info:networkInfos)
       {
            if(info.getTypeName().equalsIgnoreCase("WIFI"))
            {
                if(info.isConnected())
                {
                    have_WIFI = true;
                }
            }
           if(info.getTypeName().equalsIgnoreCase("MOBILE"))
           {
               if(info.isConnected())
               {
                   have_MobileData = true;
               }
           }
       }
       return have_MobileData||have_WIFI;
   }


    @Override
    public void CheckConnection() {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(haveNetwork())
                {
                    Toast.makeText(context,"NETWORK IS AVAILABLE",Toast.LENGTH_LONG).show();
                }
                else if(!haveNetwork())
                {
                    Toast.makeText(context,"NO NETWORK IS AVAILABLE",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
