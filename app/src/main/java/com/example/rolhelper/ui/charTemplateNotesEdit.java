package com.example.rolhelper.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rolhelper.R;

import classes.CharTemplate;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link charTemplateNotesEdit#newInstance} factory method to
 * create an instance of this fragment.
 */
public class charTemplateNotesEdit extends Fragment {

    private CharTemplate ch;
    EditText notesTxtEdit;

    public CharTemplate getTemplate() {
        ch.setNotes(notesTxtEdit.getText().toString());
        return ch;
    }

    public void setTemplate(CharTemplate ch) {
        this.ch = ch;
    }

    public charTemplateNotesEdit() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment charTemplatePrimary.
     */

    public static charTemplateNotesEdit newInstance(CharTemplate ch) {
        charTemplateNotesEdit fragment = new charTemplateNotesEdit();
        fragment.setTemplate(ch);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_char_template_notes_edit, container, false);

        notesTxtEdit = view.findViewById(R.id.notesTxtEdit);
        notesTxtEdit.setText(ch.getNotes());

        return view;
    }
}