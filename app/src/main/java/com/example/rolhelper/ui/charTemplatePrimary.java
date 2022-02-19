package com.example.rolhelper.ui;

import static java.lang.Math.ceil;
import static java.lang.Math.floor;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rolhelper.R;

import classes.CharTemplate;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link charTemplatePrimary#newInstance} factory method to
 * create an instance of this fragment.
 */
public class charTemplatePrimary extends Fragment {

    private CharTemplate ch;

    TextView levelTxt;
    TextView psTxt;
    TextView currentPsTxt;
    TextView evaTxt;
    TextView impTxt;
    TextView punTxt;
    TextView magTxt;
    TextView fzaTxt;
    TextView aglTxt;
    TextView perTxt;
    TextView carTxt;
    TextView nameTxt;
    TextView archetypeTxt;
    TextView descriptionTxt;
    ImageButton psUp, psDown;
    ImageView charImage;
    TextView DiceTxt;
    SoundPool sp;
    int sound;

    public CharTemplate getTemplate() {
        return ch;
    }

    public void setTemplate(CharTemplate ch) {
        this.ch = ch;
    }

    public charTemplatePrimary() {
        // Required empty public constructor
    }

    public void displayTemplate() {
        levelTxt.setText(String.valueOf(ch.getLevel()));
        psTxt.setText(String.valueOf(ch.getPs()));
        evaTxt.setText(String.valueOf(ch.getEva()));
        impTxt.setText(String.valueOf(ch.getImp()));
        punTxt.setText(String.valueOf(ch.getPun()));
        magTxt.setText(String.valueOf(ch.getMag()));
        fzaTxt.setText(String.valueOf(ch.getFza()));
        aglTxt.setText(String.valueOf(ch.getAgl()));
        perTxt.setText(String.valueOf(ch.getPer()));
        carTxt.setText(String.valueOf(ch.getCar()));
        nameTxt.setText(ch.getName());
        archetypeTxt.setText(ch.getArchetype());
        descriptionTxt.setText(ch.getDescription());
        currentPsTxt.setText((String.valueOf(ch.getPs())));
        byte[] imageByteArray = ch.getImage();
        if (imageByteArray!=null) {
            Bitmap image = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
            charImage.setImageBitmap(image);
        }
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment charTemplatePrimary.
     */

    public static charTemplatePrimary newInstance(CharTemplate ch) {
        charTemplatePrimary fragment = new charTemplatePrimary();
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

        View view = inflater.inflate(R.layout.fragment_char_template_primary, container, false);

        sp = new SoundPool(1, AudioManager.STREAM_MUSIC,1);
        sound = sp.load(view.getContext(), R.raw.diceroll, 1);
        levelTxt = view.findViewById(R.id.levelTxt);
        psTxt = view.findViewById(R.id.psTxt);
        evaTxt = view.findViewById(R.id.evaTxt);
        impTxt = view.findViewById(R.id.impTxt);
        punTxt = view.findViewById(R.id.punTxt);
        magTxt = view.findViewById(R.id.magTxt);
        fzaTxt = view.findViewById(R.id.fzaTxt);
        aglTxt = view.findViewById(R.id.aglTxt);
        perTxt = view.findViewById(R.id.perTxt);
        carTxt = view.findViewById(R.id.carTxt);
        nameTxt = view.findViewById(R.id.nameTxt);
        archetypeTxt = view.findViewById(R.id.archetypeTxt);
        descriptionTxt = view.findViewById(R.id.descriptionTxt);
        currentPsTxt = view.findViewById(R.id.psCurrentTxt);
        psUp = view.findViewById(R.id.psUpButton);
        psDown = view.findViewById(R.id.psDownbutton);
        DiceTxt = view.findViewById(R.id.diceTxt);
        charImage = view.findViewById(R.id.charImage);
        displayTemplate();

        psUp.setOnClickListener(v -> {
                    int oldPs = Integer.parseInt(currentPsTxt.getText().toString());
                    String newPs = String.valueOf(oldPs + 1);
                    currentPsTxt.setText(newPs);
                }
        );

        psDown.setOnClickListener(v -> {
                    int oldPs = Integer.parseInt(currentPsTxt.getText().toString());
                    String newPs = String.valueOf(oldPs - 1);
                    currentPsTxt.setText(newPs);
                }
        );

        impTxt.setOnClickListener(this::rollAtt);
        punTxt.setOnClickListener(this::rollAtt);
        magTxt.setOnClickListener(this::rollAtt);
        fzaTxt.setOnClickListener(this::rollAtt);
        aglTxt.setOnClickListener(this::rollAtt);
        perTxt.setOnClickListener(this::rollAtt);
        carTxt.setOnClickListener(this::rollAtt);

        return view;
    }

    public void rollAtt(View v) {
        if (v instanceof TextView) {
            TextView att = (TextView) v;
            int attNum = Integer.parseInt(att.getText().toString());
            int rand = (int) ceil(Math.random() * 10);
            int resNum = attNum + rand;
            sp.play(sound , 1,1, 1, 0, 0);
            String res = attNum + " + " + rand + "= " + resNum;
            DiceTxt.setText(String.valueOf(res));
        }
    }
}