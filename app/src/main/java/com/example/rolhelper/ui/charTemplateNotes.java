package com.example.rolhelper.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rolhelper.R;

import classes.CharTemplate;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link charTemplateNotes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class charTemplateNotes extends Fragment {

    private CharTemplate ch;
    private TextView notesTxt;

    public CharTemplate getTemplate() {
        return ch;
    }

    public void setTemplate(CharTemplate ch) {
        this.ch = ch;
    }

    public charTemplateNotes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment charTemplatePrimary.
     */

    public static charTemplateNotes newInstance(CharTemplate ch) {
        charTemplateNotes fragment = new charTemplateNotes();
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

        View view = inflater.inflate(R.layout.fragment_char_template_notes, container, false);
       notesTxt = view.findViewById(R.id.notesTxt);
       notesTxt.setText(ch.getNotes());


        return view;
    }
}