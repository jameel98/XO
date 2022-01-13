package il.co.techschool.xo.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import il.co.techschool.xo.arraylists.ArrayListPlayers;

/**
 * Created by feras on 5/12/2017.
 */

public class AdapterStatistic extends BaseAdapter
{
    private ArrayListPlayers mArrListPlayers;

    public AdapterStatistic( ArrayListPlayers ArrayListPlayers){
        mArrListPlayers = ArrayListPlayers;
    }

    @Override
    public int getCount()
    {
        return mArrListPlayers.size();
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        return null;
    }
}
