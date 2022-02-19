package com.example.rolhelper.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rolhelper.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import classes.CharTemplate;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link charTemplatePrimaryEdit#newInstance} factory method to
 * create an instance of this fragment.
 */
public class charTemplatePrimaryEdit extends Fragment {

    private static final int RESULT_LOAD_IMG = 200;
    private CharTemplate ch;

    EditText levelTxtEdit;
    EditText psTxtEdit;
    EditText evaTxtEdit;
    EditText impTxtEdit;
    EditText punTxtEdit;
    EditText magTxtEdit;
    EditText fzaTxtEdit;
    EditText aglTxtEdit;
    EditText perTxtEdit;
    EditText carTxtEdit;
    EditText nameTxtEdit;
    EditText archetypeTxtEdit;
    EditText descriptionTxtEdit;
    ImageView charImage;
    Button imageButton;

    public CharTemplate getTemplate() {
        fillTemplate();
        return ch;
    }

    public void setTemplate(CharTemplate ch) {
        this.ch = ch;
    }

    public charTemplatePrimaryEdit() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment charTemplatePrimary.
     */

    public static charTemplatePrimaryEdit newInstance(CharTemplate ch) {
        charTemplatePrimaryEdit fragment = new charTemplatePrimaryEdit();
        fragment.setTemplate(ch);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


public void fillTemplate(){
    ch.setLevel(Integer.parseInt(levelTxtEdit.getText().toString()));
    ch.setPs(Integer.parseInt(psTxtEdit.getText().toString()));
    ch.setEva(Integer.parseInt(evaTxtEdit.getText().toString()));
    ch.setImp(Integer.parseInt(impTxtEdit.getText().toString()));
    ch.setPun(Integer.parseInt(punTxtEdit.getText().toString()));
    ch.setMag(Integer.parseInt(magTxtEdit.getText().toString()));
    ch.setFza(Integer.parseInt(fzaTxtEdit.getText().toString()));
    ch.setAgl(Integer.parseInt(aglTxtEdit.getText().toString()));
    ch.setPer(Integer.parseInt(perTxtEdit.getText().toString()));
    ch.setCar(Integer.parseInt(carTxtEdit.getText().toString()));
    ch.setName(nameTxtEdit.getText().toString());
    ch.setArchetype(archetypeTxtEdit.getText().toString());
    ch.setDescription(descriptionTxtEdit.getText().toString());

}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_char_template_primary_edit, container, false);
        levelTxtEdit = view.findViewById(R.id.levelTxtEdit);
        psTxtEdit = view.findViewById(R.id.psTxtEdit);
        evaTxtEdit = view.findViewById(R.id.evaTxtEdit);
        impTxtEdit = view.findViewById(R.id.impTxtEdit);
        punTxtEdit = view.findViewById(R.id.punTxtEdit);
        magTxtEdit = view.findViewById(R.id.magTxtEdit);
        fzaTxtEdit = view.findViewById(R.id.fzaTxtEdit);
        aglTxtEdit = view.findViewById(R.id.aglTxtEdit);
        perTxtEdit = view.findViewById(R.id.perTxtEdit);
        carTxtEdit = view.findViewById(R.id.carTxtEdit);
        nameTxtEdit = view.findViewById(R.id.nameTxtEdit);
        archetypeTxtEdit = view.findViewById(R.id.archetypeTxtEdit);
        descriptionTxtEdit = view.findViewById(R.id.descriptionTxtEdit);
        charImage = view.findViewById(R.id.charImage);
        imageButton = view.findViewById(R.id.imageEditButton);
        imageButton.setOnClickListener(v ->{
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                 photoPickerIntent.setType("image/*");
                 startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
                });
        displayTemplate();



        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode){
                case RESULT_LOAD_IMG:
                    Uri selectedImage = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                        charImage.setImageBitmap(bitmap);
                        ch.setImage(getBitmapAsByteArray(bitmap));
                    } catch (IOException e) {
                        Log.i("TAG", "Some exception " + e);
                    }
                    break;
            }
        }
    }

    private static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    private void displayTemplate() {
        levelTxtEdit.setText(String.valueOf(ch.getLevel()));
        psTxtEdit.setText(String.valueOf(ch.getPs()));
        evaTxtEdit.setText(String.valueOf(ch.getEva()));
        impTxtEdit.setText(String.valueOf(ch.getImp()));
        punTxtEdit.setText(String.valueOf(ch.getPun()));
        magTxtEdit.setText(String.valueOf(ch.getMag()));
        fzaTxtEdit.setText(String.valueOf(ch.getFza()));
        aglTxtEdit.setText(String.valueOf(ch.getAgl()));
        perTxtEdit.setText(String.valueOf(ch.getPer()));
        carTxtEdit.setText(String.valueOf(ch.getCar()));
        nameTxtEdit.setText(ch.getName());
        archetypeTxtEdit.setText(ch.getArchetype());
        descriptionTxtEdit.setText(ch.getDescription());
        byte[] imageByteArray = ch.getImage();
        if (imageByteArray!=null) {
            Bitmap image = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
            charImage.setImageBitmap(image);
        }
    }
}