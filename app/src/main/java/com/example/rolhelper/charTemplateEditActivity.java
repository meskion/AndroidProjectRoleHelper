package com.example.rolhelper;

import android.os.Build;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rolhelper.ui.charTemplateNotesEdit;
import com.example.rolhelper.ui.charTemplatePrimaryEdit;

import java.util.Optional;

import classes.CharTemplate;
import database.CharTemplateRepository;

public class charTemplateEditActivity extends AppCompatActivity {

    FrameLayout tabFrame1, tabFrame4;
    ImageButton saveButton, deleteButton, backbutton;
    TabHost tabHost;
    CharTemplate editingTemplate;

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_template_edit);
        tabHost = findViewById(R.id.tbEditView);
        tabFrame1 = findViewById(R.id.frameEdit1);
        tabFrame4 = findViewById(R.id.frameEdit2);
        saveButton = findViewById(R.id.confirmButton);
        deleteButton = findViewById(R.id.deleteButton);
        backbutton = findViewById(R.id.backButton);

        int charId = getIntent().getIntExtra("templateId",-1);
        CharTemplate ch = CharTemplateRepository.getInstance(this).findById(charId);
        if (ch == null) {
            newTemplate();
            Toast.makeText(this, "New char", Toast.LENGTH_SHORT).show();
        } else {
            editingTemplate = ch;

        }


        charTemplatePrimaryEdit frag1 = charTemplatePrimaryEdit.newInstance(editingTemplate);
        charTemplateNotesEdit frag4 = charTemplateNotesEdit.newInstance(editingTemplate);

        getFragmentManager().beginTransaction().add(R.id.frameEdit1, frag1).commit();
        getFragmentManager().beginTransaction().add(R.id.frameEdit2, frag4).commit();

        tabHost.setup();
        TabHost.TabSpec spec = tabHost.newTabSpec("info");
        spec.setContent(R.id.tabEdit1);
        spec.setIndicator("Info");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Notes");
        spec.setContent(R.id.tabEdit2);
        spec.setIndicator("Notes");

        tabHost.addTab(spec);

        saveButton.setOnClickListener(view -> {
            editingTemplate = frag1.getTemplate();

            editingTemplate.setNotes(frag4.getTemplate().getNotes());
            CharTemplateRepository.getInstance(this).save(editingTemplate);
            finish();
        });

        backbutton.setOnClickListener(view -> {
            finish();
        });
        deleteButton.setOnClickListener(view -> {
            CharTemplateRepository.getInstance(this).delete(editingTemplate.getId());
            setResult(RESULT_OK,null);
            finish();


        });


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void newTemplate() {
        editingTemplate = new CharTemplate();
        Optional<Integer> maxId = CharTemplateRepository.getInstance(this).findAll().keySet().stream().max(Integer::compareTo);
        if (maxId.isPresent())
            editingTemplate.setId(maxId.get() + 1);
        else editingTemplate.setId(1);
    }


}
