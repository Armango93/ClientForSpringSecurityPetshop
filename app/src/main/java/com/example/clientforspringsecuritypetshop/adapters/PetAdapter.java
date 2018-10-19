package com.example.clientforspringsecuritypetshop.adapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.clientforspringsecuritypetshop.OneElementActivity;
import com.example.clientforspringsecuritypetshop.R;
import com.example.clientforspringsecuritypetshop.model.Pet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder> {
    private List<Pet> petList = new ArrayList<>();

    public PetAdapter(List<Pet> petList) {
        this.petList = petList;
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.element_list_layout, viewGroup, false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder petViewHolder, int position) {
        petViewHolder.id.setText("ID: "+ String.valueOf(petList.get(position).getId()));
        petViewHolder.name.setText("Name: " + petList.get(position).getName());
        petViewHolder.age.setText("Age: " + String.valueOf(petList.get(position).getAge()));
        petViewHolder.userId.setText("UserId: " + String.valueOf(petList.get(position).getUserId()));
    }

    @Override
    public int getItemCount() {
        return petList.size();
    }

    public class PetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView id;
        TextView name;
        TextView age;
        TextView userId;

        public PetViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            userId = itemView.findViewById(R.id.userId);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), OneElementActivity.class);
            intent.putExtra("task", petList.get(getAdapterPosition()));
//            intent.putExtra("id", id.getText());
//            intent.putExtra("name", name.getText());
//            intent.putExtra("age", age.getText());
//            intent.putExtra("userId", userId.getText());
            view.getContext().startActivity(intent);

        }
    }
}
