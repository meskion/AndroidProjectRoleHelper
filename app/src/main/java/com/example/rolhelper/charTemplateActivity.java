package com.example.rolhelper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TabHost;

import com.example.rolhelper.ui.charTemplateNotes;
import com.example.rolhelper.ui.charTemplatePrimary;

import classes.CharTemplate;
import database.CharTemplateRepository;

public class charTemplateActivity extends AppCompatActivity {

    TabHost tabHost;
    CharTemplate template;
    FrameLayout tabFrame1, tabFrame4;
    ImageButton editButton, backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_template);

        tabHost = findViewById(R.id.tbView);
        tabFrame1 = findViewById(R.id.frame1);
        tabFrame4 = findViewById(R.id.frame2);
        editButton = findViewById(R.id.editButton);
        backButton = findViewById(R.id.backButton);

        int charId = getIntent().getIntExtra("templateId",-1);
        template =  CharTemplateRepository.getInstance(this).findById(charId);
        charTemplatePrimary frag1 = charTemplatePrimary.newInstance(template);
        charTemplateNotes frag4 = charTemplateNotes.newInstance(template);

        getFragmentManager().beginTransaction().add(R.id.frame1,frag1).commit();
       getFragmentManager().beginTransaction().add(R.id.frame2,frag4).commit();

        tabHost.setup();
        TabHost.TabSpec spec = tabHost.newTabSpec("info");
        spec.setContent(R.id.tab1);
        spec.setIndicator("info");
        tabHost.addTab(spec);



        spec = tabHost.newTabSpec("notes");
        spec.setContent(R.id.tab2);
        spec.setIndicator("notes");

        tabHost.addTab(spec);

        editButton.setOnClickListener(view -> {
            Intent next = new Intent(this, charTemplateEditActivity.class);
            next.putExtra("templateId", template.getId());
            startActivityForResult(next, 1);
        });

        backButton.setOnClickListener(view -> {
            finish();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK)
                this.finish();

        }
    }
}