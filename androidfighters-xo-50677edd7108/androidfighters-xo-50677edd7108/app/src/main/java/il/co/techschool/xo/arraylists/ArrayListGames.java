package il.co.techschool.xo.arraylists;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import il.co.techschool.xo.entites.Game;
import il.co.techschool.xo.events.EventArrayListGameGotInvitation;
import il.co.techschool.xo.events.EventArrayListGameUpdate;
import il.co.techschool.xo.firebase.AppXOFirebase;

/**
 * Created by danielszasz on 12/05/2017.
 */

public class ArrayListGames extends ArrayList<Game> {


    private static final long serialVersionUID = 1132984673562900167L;

    public Game findGameById(String aUid) {
        Game gameFound;
        int iIndex;
        int iSize;

        gameFound = null;

        iSize = size();

        for (iIndex = 0; iIndex < iSize; iIndex++){
            if (aUid.compareToIgnoreCase( get( iIndex).getId()) == 0){
                gameFound = get( iIndex);
                break;
            }
        }

        return gameFound;
    }

    @Override
    public boolean add(Game aGame) {
        boolean result;
        result = super.add(aGame);

        if (aGame.getPlayer2().compareTo( AppXOFirebase.getInstance().getCurrentUser().getUid()) == 0){
            EventBus.getDefault().post( new EventArrayListGameGotInvitation( aGame));
        }

        return result;
    }

    public Game updateGame(Game aGame) {
        Game currentGame;

        currentGame = findGameById(aGame.getId());

        if (currentGame != null){
            currentGame.updateFrom(aGame);
            EventBus.getDefault().post(new EventArrayListGameUpdate(currentGame));
            return currentGame;
        } else {
            return null;
        }
    }

    public void removeGame(Game aGame) {
        Game currentGame;

        currentGame = findGameById(aGame.getId());

        if (currentGame != null){
            remove( currentGame);
        }
    }


}
