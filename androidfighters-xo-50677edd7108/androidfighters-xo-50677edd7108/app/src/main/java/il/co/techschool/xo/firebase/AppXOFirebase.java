package il.co.techschool.xo.firebase;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import il.co.techschool.xo.common.AppXO;
import il.co.techschool.xo.entites.Game;
import il.co.techschool.xo.entites.Player;

/**
 * Created by danielszasz on 12/05/2017.
 */

public class AppXOFirebase {
    private static final String TAG = "AppXOFirebase";
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDBReferencePlayers;
    private DatabaseReference mDBReferenceGames;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser mCurrentUser;

    private static final AppXOFirebase ourInstance = new AppXOFirebase();

    public static AppXOFirebase getInstance() {
        return ourInstance;
    }


    private AppXOFirebase() {
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    mCurrentUser = user;
                    opendatabase();

                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }

        };

        mAuth.addAuthStateListener( mAuthListener);
    }

    private void opendatabase(){
        mDatabase = FirebaseDatabase.getInstance();

        AppXO.APP_INSTANCE.getArrayListPlayers().clear();

        AppXO.APP_INSTANCE.getArrayListGames().clear();

        mDBReferencePlayers = mDatabase.getReference("players");
        mDBReferencePlayers.addValueEventListener( OnValuePlayer);
        mDBReferencePlayers.addChildEventListener( OnChildPlayer);

        mDBReferenceGames = mDatabase.getReference("games");
        mDBReferenceGames.addValueEventListener( OnValueGame);
        mDBReferenceGames.addChildEventListener( OnChildGame);

        if ((mCurrentUser != null) && (mCurrentUser.getEmail().compareToIgnoreCase( "xo@xo.xo") == 0)) {
            fillDefaultValues();
        }
    }

    public FirebaseUser getCurrentUser(){
        return mCurrentUser;
    }

    private ValueEventListener OnValuePlayer = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot aDataSnapshot) {
            Log.d( TAG, "OnValuePlayer:onDataChange");
        }

        @Override
        public void onCancelled(DatabaseError aDatabaseError) {
            Log.d( TAG, "OnValuePlayer:onCancelled");
        }
    };

    private ValueEventListener OnValueGame = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot aDataSnapshot) {
            Log.d( TAG, "OnValueGame:onDataChange");
        }

        @Override
        public void onCancelled(DatabaseError aDatabaseError) {
            Log.d( TAG, "OnValueGame:onCancelled");
        }
    };

    private ChildEventListener OnChildPlayer = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot aDataSnapshot, String aS) {
            Log.d( TAG, "OnChildPlayer:onChildAdded");
            Player currentPlayer;

            currentPlayer = aDataSnapshot.getValue(Player.class);
            AppXO.APP_INSTANCE.getArrayListPlayers().add( currentPlayer);
        }

        @Override
        public void onChildChanged(DataSnapshot aDataSnapshot, String aS) {
            Log.d( TAG, "OnChildPlayer:onChildChanged");
            Player currentPlayer;

            currentPlayer = aDataSnapshot.getValue(Player.class);
            AppXO.APP_INSTANCE.getArrayListPlayers().updatePlayer( currentPlayer);
        }

        @Override
        public void onChildRemoved(DataSnapshot aDataSnapshot) {
            Log.d( TAG, "OnChildPlayer:onChildRemoved");

            Player currentPlayer;

            currentPlayer = aDataSnapshot.getValue(Player.class);
            AppXO.APP_INSTANCE.getArrayListPlayers().removePlayer(currentPlayer);
        }

        @Override
        public void onChildMoved(DataSnapshot aDataSnapshot, String aS) {
            Log.d( TAG, "OnChildPlayer:onChildMoved");
        }

        @Override
        public void onCancelled(DatabaseError aDatabaseError) {
            Log.d( TAG, "OnChildPlayer:onCancelled");
        }
    };




    private ChildEventListener OnChildGame = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot aDataSnapshot, String aS) {
            Log.d( TAG, "OnChildGame:onChildAdded");
            Game currentGame;

            currentGame = aDataSnapshot.getValue(Game.class);
            AppXO.APP_INSTANCE.getArrayListGames().add( currentGame);
        }

        @Override
        public void onChildChanged(DataSnapshot aDataSnapshot, String aS) {
            Log.d( TAG, "OnChildGame:onChildChanged");
            Game currentGame;

            currentGame = aDataSnapshot.getValue(Game.class);
            AppXO.APP_INSTANCE.getArrayListGames().updateGame( currentGame);
        }

        @Override
        public void onChildRemoved(DataSnapshot aDataSnapshot) {
            Log.d( TAG, "OnChildGame:onChildRemoved");

            Game currentGame;

            currentGame = aDataSnapshot.getValue(Game.class);
            AppXO.APP_INSTANCE.getArrayListGames().removeGame(currentGame);
        }

        @Override
        public void onChildMoved(DataSnapshot aDataSnapshot, String aS) {
            Log.d( TAG, "OnChildGame:onChildMoved");
        }

        @Override
        public void onCancelled(DatabaseError aDatabaseError) {
            Log.d( TAG, "OnChildGame:onCancelled");
        }
    };


    public void fillDefaults() {

        mAuth.signInWithEmailAndPassword( "xo@xo.xo", "x@x@x@").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> aTask) {
                Log.d( TAG, "signin - onComplete");
                if (aTask.isSuccessful()){
//                    mCurrentUser = aTask.getResult().getUser();
//
//                    opendatabase();
                }
            }
        });
    }

    public void updatePlayerStatus( String aPlayerUUID, Integer aStatusNew){
        mDBReferencePlayers.child( aPlayerUUID).child( "status").setValue( aStatusNew);
    }

    public void addPlayer(Player player){
        mDBReferencePlayers.child(player.getId()).setValue( player);
    }

    private void fillDefaultValues(){
        Player currentPlayer;

//        currentPlayer = new Player("HSl37YGYPkSc8E5M1o7SWKxuAuN2", "daniel", new Date(1972, 11, 6).getTime(),
//                0, 0, 0, 0, 0D, 0);
//        mDBReferencePlayers.child(currentPlayer.getId()).setValue( currentPlayer);
//        currentPlayer = new Player("W1jeRJ9Q7eRDvWMQYSz5KQsd4jv2", "daniel2", new Date(1972, 11, 7).getTime(),
//                0, 0, 0, 0, 0D, 0);
//        mDBReferencePlayers.child(currentPlayer.getId()).setValue( currentPlayer);
//        currentPlayer = new Player("ZRYUNTg8oHTnjsbzQjSEBzrSZ493", "daniel3", new Date(1972, 11, 8).getTime(),
//                0, 0, 0, 0, 0D, 0);
//        mDBReferencePlayers.child(currentPlayer.getId()).setValue( currentPlayer);
//        currentPlayer = new Player("bzswayn6WoZmjLRquMJhHXIAWxl2", "daniel4", new Date(1972, 11, 9).getTime(),
//                0, 0, 0, 0, 0D, 0);
//        mDBReferencePlayers.child(currentPlayer.getId()).setValue( currentPlayer);
//        currentPlayer = new Player("w6HPe8IJesRd1bx3PmZaS2cC0Yv1", "daniel5", new Date(1972, 11, 10).getTime(),
//                0, 0, 0, 0, 0D, 0);
//        mDBReferencePlayers.child(currentPlayer.getId()).setValue( currentPlayer);

    }


    public void addGame(Game mCurrentGame)
    {
        mDBReferenceGames.child(mCurrentGame.getId()).setValue(mCurrentGame);
    }
}
