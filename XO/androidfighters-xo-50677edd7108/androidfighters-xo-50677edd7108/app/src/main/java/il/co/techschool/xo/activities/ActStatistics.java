package il.co.techschool.xo.activities;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import il.co.techschool.xo.R;

/**
 * Created by feras on 5/12/2017.
 */

public class ActStatistics extends AppCompatActivity
{
    private Context mContext;
    private Typeface mTypefaceAlgeria;
    private EditText mEditTextname;
    private EditText mEditTextDraw;
    private EditText mEditTextGamesNumber;
    private EditText mEditTextWon;
    private EditText mEditTextLost;
    private TextView mTextViewGamesNumber;
    private TextView mTextViewWon;
    private TextView mTextViewDraw;
    private TextView mTextViewLost;
    private TextView mTextViewname;
    private TextView mTextViewStatistic;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_statistic);

        mContext = this;

        AssetManager am = mContext.getApplicationContext().getAssets();
        mTypefaceAlgeria = Typeface.createFromAsset(am, "fonts/fontforte.ttf");

        InitComponent();

    }

    private void InitComponent()
    {
        mEditTextDraw = (EditText)findViewById(R.id.et_Draw);
        mEditTextWon = (EditText)findViewById(R.id.et_Won);
        mEditTextLost = (EditText)findViewById(R.id.et_Lost);
        mEditTextname = (EditText) findViewById(R.id.et_Name);
        mEditTextGamesNumber = (EditText)findViewById(R.id.et_GamesNumber);
        mTextViewname = (TextView) findViewById(R.id.txt_Name);
        mTextViewname.setTypeface( mTypefaceAlgeria);
        mTextViewGamesNumber = (TextView) findViewById(R.id.txt_GamesNumber);
        mTextViewGamesNumber.setTypeface( mTypefaceAlgeria);
        mTextViewWon = (TextView)findViewById(R.id.txt_Won);
        mTextViewWon.setTypeface( mTypefaceAlgeria);
        mTextViewLost = (TextView)findViewById(R.id.txt_Lost);
        mTextViewLost.setTypeface( mTypefaceAlgeria);
        mTextViewDraw = (TextView)findViewById(R.id.txt_Draw);
        mTextViewDraw.setTypeface( mTypefaceAlgeria);
        mTextViewStatistic = (TextView)findViewById(R.id.txt_Statistic);
        mTextViewStatistic.setTypeface( mTypefaceAlgeria);
    }
    

}
