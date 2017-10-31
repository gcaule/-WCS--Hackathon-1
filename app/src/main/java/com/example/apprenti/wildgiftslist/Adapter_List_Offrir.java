package com.example.apprenti.wildgiftslist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by apprenti on 30/10/17.
 */

public class Adapter_List_Offrir extends BaseAdapter{
    private Activity activity;
    private List<WishModel> list_offrir;
    private LayoutInflater inflater;
    private WishModel wM = new WishModel();

    public Adapter_List_Offrir(Activity activity, List<WishModel> list_offrir) {
        this.activity = activity;
        this.list_offrir = list_offrir;
    }

    @Override
    public int getCount() {
        return list_offrir.size();
    }

    @Override
    public Object getItem(int i) {
        return list_offrir.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, final View convertView, ViewGroup parent) {
        inflater = (LayoutInflater)activity.getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.activity_list_offrir_item,null);

        ImageView imgOff = (ImageView) itemView.findViewById(R.id.imgOff);
        TextView nomOff = (TextView) itemView.findViewById(R.id.nomOff);
        TextView descOff = (TextView) itemView.findViewById(R.id.descOff);
       // TextView destinataire = (TextView) itemView.findViewById(R.id.linkOff);
        ImageView offeredGift = (ImageView) itemView.findViewById(R.id.offeredGift);

        nomOff.setText(list_offrir.get(i).getName());
        descOff.setText(list_offrir.get(i).getDescription());
        //destinataire.setText(list_offrir.get(i).getDestinataire());
        Glide.with(activity).load(list_offrir.get(i).getImage()).into(imgOff);

        return itemView;
    }
}
