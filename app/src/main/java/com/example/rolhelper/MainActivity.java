package com.example.rolhelper;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import classes.CharTemplate;
import database.CharTemplateRepository;

public class MainActivity extends AppCompatActivity {

    LinearLayout templates;
    Button newChar;
    ArrayAdapter<Integer> adp;
    Map<Integer,String> chars;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        templates = findViewById(R.id.charList);
        newChar = findViewById(R.id.newCharButton);

        newChar.setOnClickListener(view -> {


            //llevar a plantilla nueva
            Intent next = new Intent(this, charTemplateEditActivity.class);
     //       next.putExtra("template", null);
            startActivity(next);
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onResume() {
        super.onResume();
        chars = CharTemplateRepository.getInstance(this).findAll();
        templates.removeAllViewsInLayout();
        //Toast.makeText(this, "num chars: " + String.valueOf(chars.size()), Toast.LENGTH_SHORT).show();
        for (Integer k : chars.keySet()){
            Button tv = new Button(this);
            tv.setTextSize(15);

            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tv.setText(String.valueOf(k) + " - " + chars.get(k));
            Toast.makeText(this, String.valueOf(k), Toast.LENGTH_SHORT).show();
            tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            templates.addView(tv);
            tv.setOnClickListener(view -> {
                Intent next = new Intent(this, charTemplateActivity.class);
                CharTemplate ch =  CharTemplateRepository.getInstance(this).findById(k);
                Toast.makeText(this, ch.getName(), Toast.LENGTH_SHORT).show();
                next.putExtra("template",ch);
                startActivity(next);
            });

        }


    }
}