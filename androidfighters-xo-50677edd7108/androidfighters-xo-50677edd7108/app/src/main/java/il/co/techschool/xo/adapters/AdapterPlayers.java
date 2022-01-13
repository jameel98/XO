package il.co.techschool.xo.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import il.co.techschool.xo.arraylists.ArrayListPlayers;
import il.co.techschool.xo.common.GameStatuses;
import il.co.techschool.xo.entites.Game;
import il.co.techschool.xo.entites.Player;
import il.co.techschool.xo.R;
import il.co.techschool.xo.firebase.AppXOFirebase;

/**
 * Created by Admin on 5/12/2017.
 */

public class AdapterPlayers extends BaseAdapter
{

    private Context mContext;
    private ArrayListPlayers mArrayListPlayers;

    public AdapterPlayers(Context aContext, ArrayListPlayers aArrayListPlayers)
    {
        mArrayListPlayers = aArrayListPlayers;
        mContext = aContext;
    }

    @Override
    public int getCount()
    {
        return mArrayListPlayers.size();
    }

    @Override
    public Object getItem(int position)
    {
        return mArrayListPlayers.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View resultView;
        LayoutInflater layoutInflater;
        Player currentPlayer;
        LinearLayout lolinPlayerRow;
        TextView txtPlayerName;
        TextView txtPlayerGamesCount;
        TextView txtPlayerWin;
        TextView txtPlayerLoss;
        TextView txtPlayerDraw;

        if (convertView == null)
        {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            resultView = layoutInflater.inflate(R.layout.lo_item_player, null);
        } else
        {
            resultView = convertView;
        }

        lolinPlayerRow = (LinearLayout) resultView.findViewById(R.id.lolinItmPlyRow);
        txtPlayerName = (TextView) resultView.findViewById(R.id.tvItmPlyName);
        txtPlayerGamesCount = (TextView) resultView.findViewById(R.id.tvItmPlyGamesNumber);
        txtPlayerWin = (TextView) resultView.findViewById(R.id.tvItmPlyWon);
        txtPlayerLoss = (TextView) resultView.findViewById(R.id.tvItmPlyLost);
        txtPlayerDraw = (TextView) resultView.findViewById(R.id.tvItmPlyDraw);

        currentPlayer = mArrayListPlayers.get(position);
        lolinPlayerRow.setTag(R.id.id_player,currentPlayer.getId());
        txtPlayerName.setText(currentPlayer.getName());
        txtPlayerGamesCount.setText(String.valueOf(currentPlayer.getNumberOfGamesCount()));
        txtPlayerWin.setText(String.valueOf(currentPlayer.getNumberOfGamesWin()));
        txtPlayerLoss.setText(String.valueOf(currentPlayer.getNumberOfGamesLoss()));
        txtPlayerDraw.setText(String.valueOf(currentPlayer.getNumberOfGamesDraw()));

        if (currentPlayer.getId().compareTo(AppXOFirebase.getInstance().getCurrentUser().getUid()) == 0)
        {
            lolinPlayerRow.setBackgroundColor(Color.RED);
        } else
        {

            if (currentPlayer.getStatus().intValue() == 1){
                lolinPlayerRow.setBackgroundColor(Color.GREEN);
            } else {
                lolinPlayerRow.setBackgroundColor(Color.LTGRAY);
            }

            lolinPlayerRow.setOnClickListener(
                    new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            Intent intent;
                            String suid;
                            suid = (String)v.getTag(R.id.id_player);

                            createGame(suid);
                        }
                    });
        }

        return resultView;

    }


    public void updateListPlayers(ArrayListPlayers aArrayListPlayers) {
        mArrayListPlayers = aArrayListPlayers;
        notifyDataSetChanged();
    }
    private void createGame(String aUid){

        Game mCurrentGame = new Game(AppXOFirebase.getInstance().getCurrentUser().getUid(), aUid);
        mCurrentGame.setStatus(GameStatuses.GS_INVITATION);
        AppXOFirebase.getInstance().addGame(mCurrentGame);
    }
}


