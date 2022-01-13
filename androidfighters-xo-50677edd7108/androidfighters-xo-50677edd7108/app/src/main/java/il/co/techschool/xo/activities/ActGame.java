package il.co.techschool.xo.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import il.co.techschool.xo.R;
import il.co.techschool.xo.common.AppXO;
import il.co.techschool.xo.common.GameStatuses;
import il.co.techschool.xo.entites.Game;
import il.co.techschool.xo.entites.Player;
import il.co.techschool.xo.events.EventArrayListGameUpdate;
import il.co.techschool.xo.firebase.AppXOFirebase;

import static java.lang.System.in;

/**
 * Created by rawad pc on 5/12/2017.
 */

<<<<<<< HEAD
public class ActGame extends AppCompatActivity
{
    public int Counter;
=======
public class ActGame extends AppCompatActivity {
>>>>>>> e814012535ad504ce3f3f0b87b6bdd16e0ebeabf
    private ImageButton mBtna1;
    private ImageButton mBtna2;
    private ImageButton mBtna3;
    private ImageButton mBtnb1;
    private ImageButton mBtnb2;
    private ImageButton mBtnb3;
    private ImageButton mBtnc1;
    private ImageButton mBtnc2;
    private ImageButton mBtnc3;
    private TextView mPlayer1;
    private TextView mPlayer2;
    private TextView mturnGameTime;
    private String turn;
    private int turn_count = 0;
    private ImageButton[] allbuttons = null;
    private Game mCurrentGame;
    private Context mContext;
    private Typeface mTypefaceAlgeria;

    private TextView mtxtGamePlayer1;
    private TextView mtxtGamePlayer2;

    private ImageView mImageViewWinVerticalRight;
    private ImageView mImageViewWinVerticalCenter;
    private ImageView mImageViewWinVerticalLeft;

    private ImageView mImageViewWinHorizontalTop;
    private ImageView mImageViewWinHorizontalCenter;
    private ImageView mImageViewWinHorizontalBottom;

    private ImageView mImageViewWinDiagonalTopLeft;
    private ImageView mImageViewWinDiagonalTopRight;

    private MediaPlayer Sound;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_game);


        mContext = this;

        AssetManager am = mContext.getApplicationContext().getAssets();
        mTypefaceAlgeria = Typeface.createFromAsset(am, "fonts/fontforte.ttf");
        initComponetns();

        iniCompontListener();

        Bundle bundle = getIntent().getExtras();

        if (bundle.getString("game_id") != null)
        {
            mCurrentGame = AppXO.APP_INSTANCE.getArrayListGames().findGameById(bundle.getString("game_id"));
        }

        initData();

    }


    @Override
    public void onStart()
    {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop()
    {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_act_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        int id = item.getItemId();

        if (id == R.id.mSingle_Player)
        {

        }

        if (id == R.id.mMulti_player)
        {

        }

        if (id == R.id.mNew_game)
        {
            Toast.makeText(getApplicationContext(), "Good luck", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, ActGame.class); //change it to your main class
            //the following 2 tags are for clearing the backStack and start fresh
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            finish();
            startActivity(i);

        }

        if (id == R.id.mSave_game)
        {
            Toast.makeText(getApplicationContext(), "Game Saved Successfully", Toast.LENGTH_LONG).show();

            // SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
            // SharedPreferences.Editor editor = prefs.edit();
            // editor.putInt("key", score);
            // editor.commit();

            //    SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
            //   int score = prefs.getInt("key", 0); //0 is the default value
        }

        if (id == R.id.mLoad_game)
        {
            Toast.makeText(getApplicationContext(), "Game Loaded Successfully", Toast.LENGTH_LONG).show();
        }


        if (id == R.id.mRestart_game)
        {
            Toast.makeText(getApplicationContext(), "Game Restarted Successfully", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, ActGame.class); //change it to your main class
            //the following 1 tags are for clearing the backStack and start fresh
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            finish();
            startActivity(i);
        }

        if (id == R.id.mExist)
        {
            Toast.makeText(getApplicationContext(), "Are you sure you want to exit", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, ActListOfPlayers.class); //change it to your main class
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            finish();
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);

    }

    private void initComponetns()
    {
        mBtna1 = (ImageButton) findViewById(R.id.btna1);
        mBtna2 = (ImageButton) findViewById(R.id.btna2);
        mBtna3 = (ImageButton) findViewById(R.id.btna3);
        mBtnb1 = (ImageButton) findViewById(R.id.btnb1);
        mBtnb2 = (ImageButton) findViewById(R.id.btnb2);
        mBtnb3 = (ImageButton) findViewById(R.id.btnb3);
        mBtnc1 = (ImageButton) findViewById(R.id.btnc1);
        mBtnc2 = (ImageButton) findViewById(R.id.btnc2);
        mBtnc3 = (ImageButton) findViewById(R.id.btnc3);

        mtxtGamePlayer1 = (TextView) findViewById(R.id.txtGamePlayer1);
        mtxtGamePlayer1.setTypeface(mTypefaceAlgeria);
        mtxtGamePlayer2 = (TextView) findViewById(R.id.txtGamePlayer2);
        mtxtGamePlayer2.setTypeface(mTypefaceAlgeria);
        mPlayer1 = (TextView) findViewById(R.id.txtGameNamePlayer1);
        mPlayer2 = (TextView) findViewById(R.id.txtGameNamePlayer2);
        mturnGameTime = (TextView) findViewById(R.id.turnGameTime);
        allbuttons = new ImageButton[]{mBtna1, mBtna2, mBtna3, mBtnb1, mBtnb2, mBtnb3, mBtnc1, mBtnc2, mBtnc3};

        mImageViewWinVerticalRight = (ImageView)findViewById(R.id.imgGameWinDownRight);
        mImageViewWinVerticalCenter = (ImageView)findViewById(R.id.imgGameWinDownCenter);
        mImageViewWinVerticalLeft = (ImageView)findViewById(R.id.imgGameWinDownLeft);

        mImageViewWinHorizontalTop = (ImageView)findViewById(R.id.imgGameWinHorizontal1);
        mImageViewWinHorizontalCenter = (ImageView)findViewById(R.id.imgGameWinHorizontal2);
        mImageViewWinHorizontalBottom = (ImageView)findViewById(R.id.imgGameWinHorizontal3);

        mImageViewWinDiagonalTopLeft = (ImageView)findViewById(R.id.imgGameWinUpDown);
        mImageViewWinDiagonalTopRight = (ImageView)findViewById(R.id.imgGameWinDownUp);

        mImageViewWinDiagonalTopLeft.setVisibility(View.GONE);
        mImageViewWinDiagonalTopRight.setVisibility(View.GONE);
        mImageViewWinVerticalRight.setVisibility(View.GONE);
        mImageViewWinVerticalCenter.setVisibility(View.GONE);
        mImageViewWinVerticalLeft.setVisibility(View.GONE);
        mImageViewWinHorizontalTop.setVisibility(View.GONE);
        mImageViewWinHorizontalCenter.setVisibility(View.GONE);
        mImageViewWinHorizontalBottom.setVisibility(View.GONE);
    }

    private void iniCompontListener()
    {
        mBtna1.setOnClickListener(OnClickButton);
        mBtna2.setOnClickListener(OnClickButton);
        mBtna3.setOnClickListener(OnClickButton);
        mBtnb1.setOnClickListener(OnClickButton);
        mBtnb2.setOnClickListener(OnClickButton);
        mBtnb3.setOnClickListener(OnClickButton);
        mBtnc1.setOnClickListener(OnClickButton);
        mBtnc2.setOnClickListener(OnClickButton);
        mBtnc3.setOnClickListener(OnClickButton);
    }

    private View.OnClickListener OnClickButton = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            buttonClicked((ImageButton) view);
        }
    };


    public void buttonClicked(ImageButton buttonClicked)
    {

<<<<<<< HEAD
        if ((mCurrentGame.getTurn() == null) ||
                (mCurrentGame.getTurn().compareTo(AppXOFirebase.getInstance().getCurrentUser().getUid()) != 0))
        {
=======
        if ((mCurrentGame.getTurn() == null) || (mCurrentGame.getTurn().compareTo(AppXOFirebase.getInstance().getCurrentUser().getUid()) != 0)) {
>>>>>>> e814012535ad504ce3f3f0b87b6bdd16e0ebeabf
            return;
        }

        if (mCurrentGame.getTurn().compareTo(mCurrentGame.getPlayer1()) == 0)
        {

            buttonClicked.setImageResource(R.drawable.x_design);
            buttonClicked.setTag("x");
        } else
        {
            buttonClicked.setImageResource(R.drawable.o_design);
            buttonClicked.setTag("o");
        }

        switch (buttonClicked.getId())
        {
            case R.id.btna1:
                mCurrentGame.setA1((String) buttonClicked.getTag());
                break;
            case R.id.btna2:
                mCurrentGame.setA2((String) buttonClicked.getTag());
                break;
            case R.id.btna3:
                mCurrentGame.setA3((String) buttonClicked.getTag());
                break;
            case R.id.btnb1:
                mCurrentGame.setB1((String) buttonClicked.getTag());
                break;
            case R.id.btnb2:
                mCurrentGame.setB2((String) buttonClicked.getTag());
                break;
            case R.id.btnb3:
                mCurrentGame.setB3((String) buttonClicked.getTag());
                break;
            case R.id.btnc1:
                mCurrentGame.setC1((String) buttonClicked.getTag());
                break;
            case R.id.btnc2:
                mCurrentGame.setC2((String) buttonClicked.getTag());
                break;
            case R.id.btnc3:
                mCurrentGame.setC3((String) buttonClicked.getTag());
                break;
        }

<<<<<<< HEAD

        if (mCurrentGame.getTurn().compareTo(mCurrentGame.getPlayer1()) == 0)
        {
=======
        if (mCurrentGame.getTurn().compareTo(mCurrentGame.getPlayer1()) == 0) {
>>>>>>> e814012535ad504ce3f3f0b87b6bdd16e0ebeabf
            mCurrentGame.setTurn(mCurrentGame.getPlayer2());
        } else
        {
            mCurrentGame.setTurn(mCurrentGame.getPlayer1());
        }

        AppXOFirebase.getInstance().addGame(mCurrentGame);
        turn_count++;
        buttonClicked.setClickable(false);
        checkForWinner();
    }


    private void checkForWinner() {

        int there_is_a_winner = 0;

        String sa1;
        String sa2;
        String sa3;
        String sb1;
        String sb2;
        String sb3;
        String sc1;
        String sc2;
        String sc3;

        sa1 = (String) mBtna1.getTag();
        sa2 = (String) mBtna2.getTag();
        sa3 = (String) mBtna3.getTag();
        sb1 = (String) mBtnb1.getTag();
        sb2 = (String) mBtnb2.getTag();
        sb3 = (String) mBtnb3.getTag();
        sc1 = (String) mBtnc1.getTag();
        sc2 = (String) mBtnc2.getTag();
        sc3 = (String) mBtnc3.getTag();

        // horizontal:
<<<<<<< HEAD
        if ((sa1 != null) && (sa2 != null) && (sa3 != null))
        {
            if ((sa1.compareTo(sa2) == 0) && sa2.compareTo(sa3) == 0)
            {
                there_is_a_winner = R.id.imgGameWinDownLeft;
            }
        }
        if ((sb1 != null) && (sb2 != null) && (sb3 != null))
        {
            if ((sb1.compareTo(sb2) == 0) && sb2.compareTo(sb3) == 0)
            {
                there_is_a_winner = R.id.imgGameWinDownCenter;
            }
        }
        if ((sc1 != null) && (sc2 != null) && (sc3 != null))
        {
            if ((sc1.compareTo(sc2) == 0) && sc2.compareTo(sc3) == 0)
            {
=======
        if ((sa1 != null) && (sa2 != null) && (sa3 != null)){
            if ((sa1.compareTo(sa2) == 0) && sa2.compareTo(sa3) == 0) {
                there_is_a_winner = R.id.imgGameWinDownLeft;
            }
        }
        if ((sb1 != null) && (sb2 != null) && (sb3 != null)){
            if ((sb1.compareTo(sb2) == 0) && sb2.compareTo(sb3) == 0) {
                there_is_a_winner = R.id.imgGameWinDownCenter;
            }
        }
        if ((sc1 != null) && (sc2 != null) && (sc3 != null)){
            if ((sc1.compareTo(sc2) == 0) && sc2.compareTo(sc3) == 0) {
>>>>>>> e814012535ad504ce3f3f0b87b6bdd16e0ebeabf
                there_is_a_winner = R.id.imgGameWinDownRight;
            }
        }

        // vertical:
<<<<<<< HEAD
        if ((sa1 != null) && (sb1 != null) && (sc1 != null))
        {
            if ((sa1.compareTo(sb1) == 0) && sb1.compareTo(sc1) == 0)
            {
=======
        if ((sa1 != null) && (sb1 != null) && (sc1 != null)){
            if ((sa1.compareTo(sb1) == 0) && sb1.compareTo(sc1) == 0) {
>>>>>>> e814012535ad504ce3f3f0b87b6bdd16e0ebeabf
                there_is_a_winner = R.id.imgGameWinHorizontal1;
            }
        }
        if ((sa2 != null) && (sb2 != null) && (sc2 != null)) {
            if ((sa2.compareTo(sb2) == 0) && sb2.compareTo(sc2) == 0) {
                there_is_a_winner = R.id.imgGameWinHorizontal2;
            }
        }
        if ((sa3 != null) && (sb3 != null) && (sc3 != null)) {
            if ((sa3.compareTo(sb3) == 0) && sb3.compareTo(sc3) == 0) {
                there_is_a_winner = R.id.imgGameWinHorizontal3;
            }
        }

        // diagonal:
        if ((sa1 != null) && (sb2 != null) && (sc3 != null)) {
            if ((sa1.compareTo(sb2) == 0) && sb2.compareTo(sc3) == 0) {
                there_is_a_winner = R.id.imgGameWinUpDown;
            }
        }
        if ((sc1 != null) && (sb2 != null) && (sa3 != null)) {
            if ((sc1.compareTo(sb2) == 0) && sb2.compareTo(sa3) == 0) {
                there_is_a_winner = R.id.imgGameWinDownUp;
            }
        }

        if (there_is_a_winner != 0) {
            if (mCurrentGame.getTurn().compareTo(mCurrentGame.getPlayer1()) == 0) {

<<<<<<< HEAD
            Player player = AppXO.APP_INSTANCE.getArrayListPlayers().findPlayerById(mCurrentGame.getPlayer1());
            if (player != null)
            {
                player.setNumberOfGamesWin(player.getNumberOfGamesWin() + 1);
            }
            Player player2 = AppXO.APP_INSTANCE.getArrayListPlayers().findPlayerById(mCurrentGame.getPlayer2());
            if (player2 != null)
            {
                player2.setNumberOfGamesLoss(player2.getNumberOfGamesLoss() + 1);
            } else
                message("X wins");

            player = AppXO.APP_INSTANCE.getArrayListPlayers().findPlayerById(mCurrentGame.getPlayer1());
            if (player != null)
            {
                player.setNumberOfGamesLoss(player.getNumberOfGamesLoss() + 1);
            }
            player2 = AppXO.APP_INSTANCE.getArrayListPlayers().findPlayerById(mCurrentGame.getPlayer2());
            if (player2 != null)
            {
                player2.setNumberOfGamesWin(player2.getNumberOfGamesWin() + 1);
            }

=======
                Sound = MediaPlayer.create(this, R.raw.short_triumphal);
                Sound.start();
                message("O wins");
            }
            else {
                Sound = MediaPlayer.create(this, R.raw.short_triumphal);
                Sound.start();

                message("X wins");
            }
>>>>>>> e814012535ad504ce3f3f0b87b6bdd16e0ebeabf

            mImageViewWinDiagonalTopLeft.setVisibility(View.INVISIBLE);
            mImageViewWinDiagonalTopRight.setVisibility(View.INVISIBLE);
            mImageViewWinVerticalRight.setVisibility(View.INVISIBLE);
            mImageViewWinVerticalCenter.setVisibility(View.INVISIBLE);
            mImageViewWinVerticalLeft.setVisibility(View.INVISIBLE);
            mImageViewWinHorizontalTop.setVisibility(View.INVISIBLE);
            mImageViewWinHorizontalCenter.setVisibility(View.INVISIBLE);
            mImageViewWinHorizontalBottom.setVisibility(View.INVISIBLE);

            View view = findViewById(there_is_a_winner);
            view.setVisibility(View.VISIBLE);
<<<<<<< HEAD
            //           enableOrDisable(false);
=======
            enableOrDisable(false);
>>>>>>> e814012535ad504ce3f3f0b87b6bdd16e0ebeabf
        } else if ((sa1 != null) && (sa2 != null) && (sa3 != null) && (sb1 != null) && (sb2 != null) && (sb3 != null) && (sc1 != null) && (sc2 != null) && (sc3 != null))
            message("Draw!");
      

<<<<<<< HEAD
        Player player = AppXO.APP_INSTANCE.getArrayListPlayers().findPlayerById(mCurrentGame.getPlayer1());
        if (player != null)
        {
            player.setNumberOfGamesDraw(player.getNumberOfGamesDraw() + 1);
        }
        Player player2 = AppXO.APP_INSTANCE.getArrayListPlayers().findPlayerById(mCurrentGame.getPlayer2());
        if (player2 != null)
        {
            player2.setNumberOfGamesDraw(player2.getNumberOfGamesDraw() + 1);
        }
=======
>>>>>>> e814012535ad504ce3f3f0b87b6bdd16e0ebeabf

    }

    private void message(String text)
    {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    private void enableOrDisable(boolean enable) {
        for (ImageButton buttonClicked : allbuttons) {
//            buttonClicked.setText("");
            buttonClicked.setClickable(enable);
            if (enable) {
                buttonClicked.setBackgroundColor(Color.LTGRAY);
            }
        }
    }

    private void initData()
    {
        Player player = AppXO.APP_INSTANCE.getArrayListPlayers().findPlayerById(mCurrentGame.getPlayer1());
        if (player != null)
        {
            mtxtGamePlayer1.setText(player.getName());
        }
        player = AppXO.APP_INSTANCE.getArrayListPlayers().findPlayerById(mCurrentGame.getPlayer2());

        if (player != null)
        {
            mtxtGamePlayer2.setText(player.getName());
        }

//        if (AppXOFirebase.getInstance().getCurrentUser().getUid().compareTo( mCurrentGame.getPlayer1()) == 0){
//            mCurrentGame.setTurn( mCurrentGame.getPlayer1());
//            mCurrentGame.setStatus(GameStatuses.GS_INGAME);
//
//            AppXOFirebase.getInstance().addGame( mCurrentGame);
//        }

        mCurrentGame.startGame();
        AppXOFirebase.getInstance().addGame(mCurrentGame);


    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventListPlayers(EventArrayListGameUpdate event)
    {
        Game game;

        game = event.getGame();

        if (game.getId().compareTo(mCurrentGame.getId()) == 0)
        {
            mCurrentGame.updateFrom(game);

            updateScreen();
        }
    }

    private void updateScreen()
    {
        updateGameButton(mCurrentGame.getA1(), mBtna1);
        updateGameButton(mCurrentGame.getA2(), mBtna2);
        updateGameButton(mCurrentGame.getA3(), mBtna3);
        updateGameButton(mCurrentGame.getB1(), mBtnb1);
        updateGameButton(mCurrentGame.getB2(), mBtnb2);
        updateGameButton(mCurrentGame.getB3(), mBtnb3);
        updateGameButton(mCurrentGame.getC1(), mBtnc1);
        updateGameButton(mCurrentGame.getC2(), mBtnc2);
        updateGameButton(mCurrentGame.getC3(), mBtnc3);
        checkForWinner();
    }

<<<<<<< HEAD
    private void updateGameButton(String aStringValue, ImageButton aImageButton)
    {
        if ((aStringValue != null) && (aStringValue.length() > 0))
        {
            //get clicked button and set it invisible
            aImageButton.setClickable(false);
            if (aStringValue.compareTo("x") == 0)
            {
                aImageButton.setImageResource(R.drawable.x_design);

            } else
            {
=======
    private void updateGameButton(String aStringValue, ImageButton aImageButton) {
        if ((aStringValue != null) && (aStringValue.length() > 0)) {
            if (aStringValue.compareTo("x") == 0) {
                aImageButton.setImageResource(R.drawable.x_design);
            } else {
>>>>>>> e814012535ad504ce3f3f0b87b6bdd16e0ebeabf
                aImageButton.setImageResource(R.drawable.o_design);
            }

            aImageButton.setTag(aStringValue);
<<<<<<< HEAD
            
=======
>>>>>>> e814012535ad504ce3f3f0b87b6bdd16e0ebeabf
        }
        //checkForWinner();
    }

<<<<<<< HEAD

    private View.OnClickListener TimerOnStart = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {

            if (mtxtGameTime == v)
                new CountDownTimer(1000, 1000)
                {
                    public void onTick(long MillisUntilFinished)
                    {
                        mtxtGameTime.setText("" + Integer.valueOf(Counter));
                        Counter--;
                    }

                    public void onFinish()
                    {
                        Counter = 0;
                    }
                }.start();


        }
    };
}
=======
}
>>>>>>> e814012535ad504ce3f3f0b87b6bdd16e0ebeabf
