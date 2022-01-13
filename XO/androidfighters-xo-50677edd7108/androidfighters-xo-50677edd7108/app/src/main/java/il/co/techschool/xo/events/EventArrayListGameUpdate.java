package il.co.techschool.xo.events;

import il.co.techschool.xo.entites.Game;

/**
 * Created by danielszasz on 30/06/2017.
 */

public class EventArrayListGameUpdate {
    private Game mGame;

    public EventArrayListGameUpdate(Game aGame) {
        mGame = aGame;
    }

    public Game getGame() {
        return mGame;
    }
}
