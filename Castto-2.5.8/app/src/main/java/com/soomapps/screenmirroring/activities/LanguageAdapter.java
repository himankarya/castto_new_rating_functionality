package com.soomapps.screenmirroring.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.soomapps.screenmirroring.R;
import java.util.List;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.MyViewHolder> {

    private List<String> beanMoreAppList;
    Context context;
    String currentLanguage;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_text;
        public ImageView iv_app_name,iv_aero;

        public MyViewHolder(View view) {
            super(view);
            iv_app_name = (ImageView) view.findViewById(R.id.iv_app_name);
            iv_aero = (ImageView) view.findViewById(R.id.iv_aero);
            tv_text=(TextView)view.findViewById(R.id.tv_text);
        }
    }

    public LanguageAdapter(Context context, List<String> beanMoreAppList,String currentLanguage) {
        this.currentLanguage=currentLanguage;
        this.beanMoreAppList = beanMoreAppList;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_more_apps, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_text.setText(beanMoreAppList.get(position));
        if(currentLanguage.equalsIgnoreCase("ar")){
           holder.iv_aero.setBackgroundResource(R.drawable.ic_chevron_left_black_24dp);
        }else{
            holder.iv_aero.setBackgroundResource(R.drawable.ic_keyboard_arrow_right_black_24dp);
        }
        switch (position){
            case 0:
                holder.iv_app_name.setBackgroundResource(R.drawable.united_arab);
                break;
            case 1:
                holder.iv_app_name.setBackgroundResource(R.drawable.croatia);
                break;
            case 2:
                holder.iv_app_name.setBackgroundResource(R.drawable.czech_republic);
                break;
            case 3:
                holder.iv_app_name.setBackgroundResource(R.drawable.netherland);
                break;
            case 4:
                holder.iv_app_name.setBackgroundResource(R.drawable.english_icon);
                break;
            case 5:
                holder.iv_app_name.setBackgroundResource(R.drawable.philippines);
                break;
            case 6:
                holder.iv_app_name.setBackgroundResource(R.drawable.french_icon);
                break;
            case 7:
                holder.iv_app_name.setBackgroundResource(R.drawable.germany);
                break;
            case 8:
                holder.iv_app_name.setBackgroundResource(R.drawable.indonesia);
                break;
            case 9:
                holder.iv_app_name.setBackgroundResource(R.drawable.italy);
                break;
            case 10:
                holder.iv_app_name.setBackgroundResource(R.drawable.south_korea);
                break;
            case 11:
                holder.iv_app_name.setBackgroundResource(R.drawable.malay);
                break;
            case 12:
                holder.iv_app_name.setBackgroundResource(R.drawable.polland);
                break;
            case 13:
                holder.iv_app_name.setBackgroundResource(R.drawable.portuguese_icon);
                break;
            case 14:
                holder.iv_app_name.setBackgroundResource(R.drawable.russia);
                break;
            case 15:
                holder.iv_app_name.setBackgroundResource(R.drawable.serbia);
                break;
            case 16:
                holder.iv_app_name.setBackgroundResource(R.drawable.slovakia);
                break;
            case 17:
                holder.iv_app_name.setBackgroundResource(R.drawable.slovenia);
                break;
            case 18:
                holder.iv_app_name.setBackgroundResource(R.drawable.spanish_icon);
                break;
            case 19:
                holder.iv_app_name.setBackgroundResource(R.drawable.swedan);
                break;
            case 20:
                holder.iv_app_name.setBackgroundResource(R.drawable.thailand);
                break;
            case 21:
                holder.iv_app_name.setBackgroundResource(R.drawable.turkey);//dutch
                break;
            case 22:
                holder.iv_app_name.setBackgroundResource(R.drawable.vietnam);
                break;
        }

   }
    @Override
    public int getItemCount() {
        return beanMoreAppList.size();

    }
}