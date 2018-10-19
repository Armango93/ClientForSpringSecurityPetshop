package com.example.clientforspringsecuritypetshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.clientforspringsecuritypetshop.adapters.PetAdapter;
import com.example.clientforspringsecuritypetshop.managers.DataManager;
import com.example.clientforspringsecuritypetshop.model.Pet;

import java.util.ArrayList;
import java.util.List;

public class PetActivity extends AppCompatActivity {
    RecyclerView recyclerView = null;
    List<Pet> petList = new ArrayList<>();
    DataManager mDataManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);

        mDataManager = DataManager.getInstance();

//        Bundle arguments = getIntent().getExtras();
//        if(arguments!=null){
//            petList = arguments.getParcelable("petList");
            petList = mDataManager.getDaoSession().getPetDao().loadAll();
//        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PetAdapter(petList));
    }

}
