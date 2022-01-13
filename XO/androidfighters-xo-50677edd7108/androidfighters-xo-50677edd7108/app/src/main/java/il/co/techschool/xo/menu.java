package il.co.techschool.xo;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by jihad on 19/05/2017.
 */

public class menu extends AppCompatActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_act_main, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.mSingle_Player) {

        }

        if (id == R.id.mMulti_player) {

        }

        if (id == R.id.mNew_game) {
            Toast.makeText(getApplicationContext(),"Good luck",Toast.LENGTH_LONG).show();

        }

        if (id == R.id.mSave_game) {
          Toast.makeText(getApplicationContext(),"Game Saved Successfully",Toast.LENGTH_LONG).show();
        }

        if (id == R.id.mLoad_game) {
            Toast.makeText(getApplicationContext(),"Game Loaded Successfully",Toast.LENGTH_LONG).show();

        }

        if (id == R.id.mRestart_game) {
            Toast.makeText(getApplicationContext(),"Game Restarted Successfully",Toast.LENGTH_LONG).show();

        }

        if (id == R.id.mExist) {
            Toast.makeText(getApplicationContext(),"Are you sure you want to exit",Toast.LENGTH_LONG).show();

        }
        return super.onOptionsItemSelected(item);

    }

}