package il.co.techschool.xo.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import il.co.techschool.xo.R;

/**
 * Created by taimaaa on 12/05/2017.
 */

public class ActForgetPassword extends AppCompatActivity
{
    private Button mSendEmail;
    private EditText mEditTextEmail;
    private TextView mPhraseResetPassword;
    private TextView mPhraseEnterYourEmail;
    private FirebaseAuth mAuth;
    private Context mContext;
    private Typeface mTypefaceAlgeria;


    

//    private FirebaseAuth.AuthStateListener mAuthListener;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mContext = this;
        AssetManager am = mContext.getApplicationContext().getAssets();
        mTypefaceAlgeria = Typeface.createFromAsset(am, "fonts/fontforte.ttf");

        initComponents();
    }

    @Override
    protected void onResume() {
        super.onResume();

        initComponentListeners();

        mAuth = FirebaseAuth.getInstance();
    }

    private void initComponents(){
        setContentView(R.layout.act_forget_password);

        mEditTextEmail = (EditText)findViewById(R.id.edtForgPswdEmail);
        mSendEmail = (Button) findViewById(R.id.btnForgPswdSendEmail);
        mPhraseEnterYourEmail = (TextView) findViewById(R.id.TextView_instructions1);
        mPhraseEnterYourEmail.setTypeface( mTypefaceAlgeria);
        mPhraseResetPassword = (TextView) findViewById(R.id.TextView_Instruction2);
        mPhraseResetPassword.setTypeface( mTypefaceAlgeria);
    }

    private void initComponentListeners(){
        mSendEmail.setOnClickListener(OnClickSendMessage);
    }

    private View.OnClickListener OnClickSendMessage = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            doSendEmail(view);
        }
    };

    private void doSendEmail(View aView)
    {
        switch (aView.getId())
        {
            case R.id.btnForgPswdSendEmail:
            {
                mAuth.sendPasswordResetEmail(mEditTextEmail.getText().toString().trim());

                Context context = getApplicationContext();
                CharSequence text = "You'r Password was sent to you'r E-mail";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context,text,duration);
                toast.show();

                //Go to the login activity when clicking on the SendPassword button
                Intent i = new Intent(ActForgetPassword.this,ActLogin.class);
                startActivity(i);
                toast.show();
                break;
            }
        }
    }
}
