package com.example.myapplication;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class LiveMatchAdapter extends RecyclerView.Adapter<LiveMatchAdapter.LiveMatchViewHolder> {

    ArrayList<LiveMatchPOJO> liveMatches;
    Context context;
    int prevPos = -1;

    public LiveMatchAdapter(ArrayList<LiveMatchPOJO> liveMatches, Context context) {

        this.liveMatches = liveMatches;
        this.context = context;

    }

    public void updateData(ArrayList<LiveMatchPOJO> liveMatches) {

        this.liveMatches = liveMatches;
        notifyDataSetChanged();

    }

    @Override
    public LiveMatchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = li.inflate(R.layout.item_live_match, parent, false);

        return new LiveMatchViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(LiveMatchViewHolder holder, final int position) {


        LiveMatchPOJO.MatchDetail currItem = liveMatches.get(position).getMatchDetail();

        holder.tvTeam1.setText(currItem.getTeam_1());
        holder.tvTeam2.setText(currItem.getTeam_2());

        if (prevPos < position) {
            //downwards
            AnimUtil.animate(holder, true);
        }else{
            //upwards
            AnimUtil.animate(holder, false);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, CricketScoreActivity.class);
                i.putExtra("unique_id", liveMatches.get(position).getMatchDetail().getUnique_key());
                i.putExtra("matchStarted", liveMatches.get(position).getMatchDetail().getMatchStarted());
                //Toast.makeText(context, "Clicked Item "+ position, Toast.LENGTH_SHORT).show();
                context.startActivity(i);

            }
        });

        prevPos = position;


    }

    @Override
    public int getItemCount() {

        return liveMatches.size();
    }


    class LiveMatchViewHolder extends RecyclerView.ViewHolder {

        TextView tvTeam1, tvTeam2;
        View itemView;

        public LiveMatchViewHolder(View itemView) {

            super(itemView);
            this.itemView = itemView;
            tvTeam1 = (TextView) itemView.findViewById(R.id.tvTeam1);
            tvTeam2 = (TextView) itemView.findViewById(R.id.tvTeam2);

        }
    }
}
