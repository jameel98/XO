package il.co.techschool.xo.arraylists;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import il.co.techschool.xo.entites.Player;
import il.co.techschool.xo.events.EventArrayListPlayersChange;

/**
 * Created by feras on 5/12/2017.
 */

public class ArrayListPlayers extends ArrayList<Player>
{


    public Player findPlayerById(String aUid) {
        Player playerFound;
        int iIndex;
        int iSize;

        playerFound = null;

        iSize = size();

        for (iIndex = 0; iIndex < iSize; iIndex++){
            if (aUid.compareToIgnoreCase( get( iIndex).getId()) == 0){
                playerFound = get( iIndex);
                break;
            }
        }

        return playerFound;
    }

    public Player updatePlayer(Player aPlayer) {
        Player currentPlayer;

        currentPlayer = findPlayerById(aPlayer.getId());

        if (currentPlayer != null){
            currentPlayer.updateFrom(aPlayer);
            EventBus.getDefault().post(new EventArrayListPlayersChange());
            return currentPlayer;
        } else {
            return null;
        }
    }

    public void removePlayer(Player aPlayer) {
        Player currentPlayer;

        currentPlayer = findPlayerById(aPlayer.getId());

        if (currentPlayer != null){
            remove( currentPlayer);
            EventBus.getDefault().post(new EventArrayListPlayersChange());
        }
    }

    @Override
    public boolean add(Player aPlayer) {
        boolean result;
        result = super.add(aPlayer);
        EventBus.getDefault().post(new EventArrayListPlayersChange());
        return result;
    }
}
