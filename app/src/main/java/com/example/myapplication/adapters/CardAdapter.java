package com.example.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.clases.LoteryCard;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolderCard> {

    private ArrayList<LoteryCard> myCard = new ArrayList<>();
    HashMap<Integer, ViewHolderCard> holderlist;

    public CardAdapter(ArrayList<LoteryCard> card) {
        this.myCard = card;
        holderlist = new HashMap<>();
    }

    @NonNull
    @Override
    public ViewHolderCard onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_cards, parent, false);
        return new ViewHolderCard(v);
    }

    public CardAdapter.ViewHolderCard getViewByPosition(int position) {
        return holderlist.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCard holder, int position) {
        holder.imgcard.setImageResource(myCard.get(position).getPicture());
        if (!holderlist.containsKey(position)) {
            holderlist.put(position, holder);
        }
    }

    @Override
    public int getItemCount() {
        return myCard.size();
    }

    public class ViewHolderCard extends RecyclerView.ViewHolder {

        ImageView imgcard;

        public ViewHolderCard(@NonNull View itemView) {
            super(itemView);
            imgcard = itemView.findViewById(R.id.imgcard);
        }
    }
}
