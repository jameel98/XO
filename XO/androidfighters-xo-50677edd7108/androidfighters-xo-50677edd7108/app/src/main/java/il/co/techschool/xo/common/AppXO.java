package il.co.techschool.xo.common;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import il.co.techschool.xo.arraylists.ArrayListGames;
import il.co.techschool.xo.arraylists.ArrayListPlayers;
import il.co.techschool.xo.entites.Player;
import il.co.techschool.xo.firebase.AppXOFirebase;

/**
 * Created by danielszasz on 12/05/2017.
 */

public class AppXO extends Application {

    public static AppXO APP_INSTANCE = null;

    private ArrayListPlayers mArrayListPlayers;
    private ArrayListGames mArrayListGames;

    @Override
    public void onCreate() {
        super.onCreate();

        APP_INSTANCE = this;

        registerActivityLifecycleCallbacks( new AppLifecycleTracker());

        mArrayListPlayers = new ArrayListPlayers();
        mArrayListGames = new ArrayListGames();
        AppXOFirebase.getInstance();
//Daniel - do NOT tuch this line !!!!
//        AppXOFirebase.getInstance().fillDefaults();
    }

    @Override
    public void onTerminate() {
        AppXOFirebase.getInstance().updatePlayerStatus( AppXOFirebase.getInstance().getCurrentUser().getUid(), 0);

        super.onTerminate();
    }

    public ArrayListPlayers getArrayListPlayers(){
        return mArrayListPlayers;
    }

    public ArrayListGames getArrayListGames() {
        return mArrayListGames;
    }


    public Player getCurrentPlayer(){
        return mArrayListPlayers.findPlayerById( AppXOFirebase.getInstance().getCurrentUser().getUid());
    }


    class AppLifecycleTracker implements ActivityLifecycleCallbacks {

        private int numStarted = 0;

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        }

        @Override
        public void onActivityStarted(Activity activity) {
            numStarted++;
        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {
            numStarted--;
            if (numStarted == 0){
                AppXOFirebase.getInstance().updatePlayerStatus( AppXOFirebase.getInstance().getCurrentUser().getUid(), 0);
            }
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }

//        override fun onActivityStarted(activity:Activity?) {
//            if (numStarted == 0) {
//                // app went to foreground
//            }
//            numStarted++
//        }
//
//        override fun onActivityStopped(activity: Activity?) {
//            numStarted--
//            if (numStarted == 0) {
//                // app went to background
//            }
//        }

    }
}










