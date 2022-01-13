package il.co.techschool.xo.events;

import il.co.techschool.xo.entites.Game;

/**
 * Created by danielszasz on 30/06/2017.
 */

public class EventArrayListGameGotInvitation {
    private Game mGame;

    public EventArrayListGameGotInvitation(Game aGame) {
        mGame = aGame;
    }

    public Game getGame() {
        return mGame;
    }
}
