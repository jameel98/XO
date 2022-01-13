package il.co.techschool.xo.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import il.co.techschool.xo.R;
import il.co.techschool.xo.adapters.AdapterPlayers;
import il.co.techschool.xo.common.AppXO;
import il.co.techschool.xo.common.GameStatuses;
import il.co.techschool.xo.entites.Game;
import il.co.techschool.xo.entites.Player;
import il.co.techschool.xo.events.EventArrayListGameGotInvitation;
import il.co.techschool.xo.events.EventArrayListGameUpdate;
import il.co.techschool.xo.events.EventArrayListPlayersChange;
import il.co.techschool.xo.firebase.AppXOFirebase;

/**
 * Created by Admin on 5/12/2017.
 */

public class ActListOfPlayers extends AppCompatActivity {

    private ListView mListViewPlayers;
    private Context mContext;
    private Typeface mTypefaceAlgeria;
    private AdapterPlayers mAdapterPlayers;

    private TextView mTxtPlayerName;
    private TextView mtxtPlayerGamesCount;
    private TextView mtxtPlayerWin;
    private TextView mtxtPlayerLoss;
    private TextView mtxtPlayerDraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        AssetManager am = mContext.getApplicationContext().getAssets();
        mTypefaceAlgeria = Typeface.createFromAsset(am, "fonts/fontforte.ttf");

        initComponents();

        mAdapterPlayers = new AdapterPlayers(this, AppXO.APP_INSTANCE.getArrayListPlayers());
        mListViewPlayers.setAdapter(mAdapterPlayers);


    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }


    void initComponents() {
        setContentView(R.layout.list_of_players);

        mTxtPlayerName = (TextView) findViewById(R.id.mtxtItmPlyName);
        mTxtPlayerName.setTypeface(mTypefaceAlgeria);
        mtxtPlayerGamesCount = (TextView) findViewById(R.id.mtxtItmGamesNumber);
        mtxtPlayerGamesCount.setTypeface(mTypefaceAlgeria);
        mtxtPlayerWin = (TextView) findViewById(R.id.mtxtItmPLyWon);
        mtxtPlayerWin.setTypeface(mTypefaceAlgeria);
        mtxtPlayerLoss = (TextView) findViewById(R.id.mtxtItmPlyLoss);
        mtxtPlayerLoss.setTypeface(mTypefaceAlgeria);
        mtxtPlayerDraw = (TextView) findViewById(R.id.mtxtItmPlyDraw);
        mtxtPlayerDraw.setTypeface(mTypefaceAlgeria);

        mListViewPlayers = (ListView) findViewById(R.id.lstLstPlyList);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventListPlayers(EventArrayListPlayersChange event) {
        mAdapterPlayers.updateListPlayers(AppXO.APP_INSTANCE.getArrayListPlayers());
    }

    ;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventListPlayers(EventArrayListGameGotInvitation event) {
        Game game;

        game = event.getGame();


        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(R.string.Game_Invite);

        // Set up the input
        final TextView input = new TextView(mContext);
        Player player;

        player = AppXO.APP_INSTANCE.getArrayListPlayers().findPlayerById(game.getPlayer1());
        if (player != null) {
            input.setText(player.getName() + getString(R.string.msg_game_invitation_rejected));
            // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            builder.setView(input);


            // Set up the buttons
            builder.setPositiveButton(R.string.Accept, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    game.setStatus(GameStatuses.GS_ACCEPTED);

                    AppXOFirebase.getInstance().addGame(game);
                    dialog.dismiss();

                }
            });

            builder.setNegativeButton(R.string.Disagree, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    game.setStatus(GameStatuses.GS_REJECTED);
                    AppXOFirebase.getInstance().addGame(game);

                    dialog.dismiss();
                }
            });
            builder.show();
        }
    }

    ;


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventListPlayers(EventArrayListGameUpdate event) {
        Game game;

        game = event.getGame();

        switch (game.getStatus()){
            case GS_ACCEPTED:
                Intent i = new Intent(ActListOfPlayers.this, ActGame.class);
                i.putExtra("game_id",game.getId());
                startActivity(i);
                break;



            case GS_REJECTED:
                Toast.makeText( mContext, getString(R.string.msg_game_invitation_rejected), Toast.LENGTH_LONG).show();
                break;
        }

    }


}
