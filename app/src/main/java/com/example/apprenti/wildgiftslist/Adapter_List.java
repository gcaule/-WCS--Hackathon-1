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

public class Adapter_List extends BaseAdapter {
    private Activity activity;
    private List<WishModel> list_Gifts;
    private LayoutInflater inflater;
    private WishModel wM = new WishModel();

    public Adapter_List(Activity activity, List<WishModel> list_Gifts) {
        this.activity = activity;
        this.list_Gifts = list_Gifts;
    }

    @Override
    public int getCount() {
        return list_Gifts.size();
    }

    @Override
    public Object getItem(int i) {
        return list_Gifts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        inflater = (LayoutInflater)activity.getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.activity_list_wish_item,null);

        ImageView imgObj = (ImageView) itemView.findViewById(R.id.imgObj);
        TextView nomObj = (TextView) itemView.findViewById(R.id.nomObj);
        TextView descObj = (TextView) itemView.findViewById(R.id.descObj);
        TextView linkObj = (TextView) itemView.findViewById(R.id.linkObj);

        nomObj.setText(list_Gifts.get(i).getName());
        descObj.setText(list_Gifts.get(i).getDescription());
        linkObj.setText(list_Gifts.get(i).getLink());
        Glide.with(activity).load(list_Gifts.get(i).getImage()).into(imgObj);

        return itemView;
    }
}
