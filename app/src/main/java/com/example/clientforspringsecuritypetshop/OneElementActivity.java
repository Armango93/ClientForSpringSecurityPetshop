package com.example.clientforspringsecuritypetshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.clientforspringsecuritypetshop.model.Pet;

public class OneElementActivity extends AppCompatActivity {
    TextView elementId;
    TextView elementName;
    TextView elementAge;
    TextView elementUserId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_element);

        elementId = findViewById(R.id.elementId);
        elementName = findViewById(R.id.elementName);
        elementAge = findViewById(R.id.elementAge);
        elementUserId = findViewById(R.id.elementUserId);

        Bundle savesBundle = getIntent().getExtras();
        if(savesBundle!=null){
//            elementId.setText(String.valueOf(savesBundle.get("id")));
//            elementId.setText(String.valueOf(savesBundle.get("name")));
//            elementId.setText(String.valueOf(savesBundle.get("age")));
//            elementId.setText(String.valueOf(savesBundle.get("userId")));
            Pet savedPet = savesBundle.getParcelable("task");
            elementId.setText("ID: " + String.valueOf(savedPet.getId()));
            elementName.setText("Name: " + String.valueOf(savedPet.getName()));
            elementAge.setText("Age: " + String.valueOf(savedPet.getAge()));
            elementUserId.setText("UserId: " + String.valueOf(savedPet.getUserId()));
        }
    }
}
