package il.co.techschool.xo.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ValueEventListener;

import il.co.techschool.xo.R;
import il.co.techschool.xo.entites.Player;
import il.co.techschool.xo.firebase.AppXOFirebase;


/**
 * Created by rameh_000 on 12/05/2017.
 */

public class ActLogin extends AppCompatActivity {

    private static final String TAG = "ActLogin";
    private Context mContext;
    private EditText txtLogin;
    private EditText txtEmail;
    private EditText txtPass;
    private CheckBox cbShowPass;
    private Button btnLogin;
    private Button btnCreate;
    private Button btnForget;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Typeface mTypefaceAlgeria;
    private TextView txtGameTitle;
    private TextView txtTitleLogin;
    private String m_Text = "";
    private MediaPlayer Sound;
    private MediaPlayer Soundback;
    private CheckBox saveLoginCheckBox;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ringtone in background...
        Sound = MediaPlayer.create(this, R.raw.thozi_click);

        Soundback = MediaPlayer.create(this, R.raw.jermany_background);
       // Soundback.setLooping(true);
        Soundback.start();


        mContext = this;

        AssetManager am = mContext.getApplicationContext().getAssets();
        mTypefaceAlgeria = Typeface.createFromAsset(am, "fonts/fontforte.ttf");

        setContentView(R.layout.act_login);
        mAuth = FirebaseAuth.getInstance();

        InitilizeComponents();

        InitilizeComponentslistners();
    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    void InitilizeComponents() {
        txtGameTitle = (TextView) findViewById(R.id.txtLoginGameTitle);
        txtGameTitle.setTypeface(mTypefaceAlgeria);
        txtTitleLogin = (TextView) findViewById(R.id.txtLoginLogin);
        txtTitleLogin.setTypeface(mTypefaceAlgeria);
        txtEmail = (EditText) findViewById(R.id.txtLoginEmail);
        txtPass = (EditText) findViewById(R.id.txtLoginPassword);
        btnLogin = (Button) findViewById(R.id.btnLoginLogin);
        btnLogin.setTypeface(mTypefaceAlgeria);
        btnCreate = (Button) findViewById(R.id.btnLoginCreate);
        btnCreate.setTypeface(mTypefaceAlgeria);
        btnForget = (Button) findViewById(R.id.btnLoginForgotPassword);
        btnForget.setTypeface(mTypefaceAlgeria);
        cbShowPass = (CheckBox) findViewById(R.id.chkLoginShowPassword);
        cbShowPass.setTypeface(mTypefaceAlgeria);

        saveLoginCheckBox = (CheckBox) findViewById(R.id.saveLoginCheckBox);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

    }

    void InitilizeComponentslistners() {

        btnLogin.setOnClickListener(onClickListener);
        btnCreate.setOnClickListener(onClickListener);
        btnForget.setOnClickListener(onClickListener);

        // add onCheckedListener on checkbox
        // when user clicks on this checkbox, this is the handler.

        cbShowPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // checkbox status is changed from uncheck to checked.
                if (!isChecked) {
                    // show password
                    txtPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    txtPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }

        });

        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            txtEmail.setText(loginPreferences.getString("username", ""));
            txtPass.setText(loginPreferences.getString("password", ""));
            saveLoginCheckBox.setChecked(true);
        }


        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Save data
            if (v == btnLogin) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                imm.hideSoftInputFromWindow(txtEmail.getWindowToken(), 0);

                username = txtEmail.getText().toString();
                password = txtPass.getText().toString();

                if (saveLoginCheckBox.isChecked()) {
                    loginPrefsEditor.putBoolean("saveLogin", true);
                    loginPrefsEditor.putString("username", username);
                    loginPrefsEditor.putString("password", password);
                    loginPrefsEditor.commit();
                } else {
                    loginPrefsEditor.clear();
                    loginPrefsEditor.commit();
                }
            }


            String email;
            String password;
            email = txtEmail.getText().toString();
            password = txtPass.getText().toString();


            switch (v.getId()) {
                case R.id.btnLoginForgotPassword:
                    //go to the login activity
                    Intent i = new Intent(ActLogin.this, ActForgetPassword.class);
                    startActivity(i);
                    break;

                case R.id.btnLoginCreate:

                    if (email.isEmpty()) {
                        Toast.makeText(mContext, getString(R.string.error_email_empty), Toast.LENGTH_LONG).show();
                        return;


                    }


                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(ActLogin.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {
                                        if (!task.isSuccessful()) {
                                            try {
                                                throw task.getException();
                                            } catch (FirebaseAuthWeakPasswordException e) {
                                                txtPass.setError(getString(R.string.error_weak_password));
                                                txtPass.requestFocus();
                                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                                txtEmail.setError(getString(R.string.error_invalid_email));
                                                txtEmail.requestFocus();
                                            } catch (FirebaseAuthUserCollisionException e) {
                                                txtEmail.setError(getString(R.string.error_user_exists));
                                                txtEmail.requestFocus();
                                            } catch (Exception e) {
                                                Log.e(TAG, e.getMessage());
                                            }
                                        }
                                        Toast.makeText(ActLogin.this, R.string.auth_failed, Toast.LENGTH_SHORT).show();
                                    } else {

                                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                                        builder.setTitle(R.string.Title);

                                        // Set up the input
                                        final EditText input = new EditText(mContext);
                                        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                                        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                                        builder.setView(input);

                                        // Set up the buttons
                                        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                m_Text = input.getText().toString();
                                                Player playerNew;

                                                playerNew = new Player(task.getResult().getUser().getUid(),
                                                        m_Text, 0L, 0, 0, 0, 0, 0D, 0);
                                                AppXOFirebase.getInstance().addPlayer(playerNew);

                                            }
                                        });
                                        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }
                                        });
                                        builder.show();


//
                                    }

                                    // ...
                                }
                            });
                    break;
                case R.id.btnLoginLogin:
                    Sound.start();


                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(ActLogin.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {
                                        Log.w(TAG, "signInWithEmail:failed", task.getException());
                                        Toast.makeText(ActLogin.this, R.string.auth_failed,
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        AppXOFirebase.getInstance().updatePlayerStatus(task.getResult().getUser().getUid(), 1);

                                        Intent intentListOfPlayers;
                                        intentListOfPlayers = new Intent(mContext, ActListOfPlayers.class);
                                        startActivity(intentListOfPlayers);
                                    }

                                    // ...
                                }
                            });
                    break;
            }


        }


    };


}
