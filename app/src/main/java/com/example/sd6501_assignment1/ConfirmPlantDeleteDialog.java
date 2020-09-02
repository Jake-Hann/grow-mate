package com.example.sd6501_assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.sd6501_assignment1.MyPlants.plantPosition;

public class ConfirmPlantDeleteDialog extends DialogFragment {

    ImageButton btnConfirmDelete, btnCancelDelete;

    public ArrayList<HashMap<String, String>> plantArray;
    public HashMap<String,String> plant;

    MyPlantsDbHandler dbHandler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(
            R.layout.layout_confirm_plant_delete_dialog,
            container,
            false
        );

        btnConfirmDelete= view.findViewById(R.id.btnConfirmDelete);
        btnCancelDelete = view.findViewById(R.id.btnCancelDelete);

        dbHandler = new MyPlantsDbHandler(getContext());
        plantArray = dbHandler.getPlants();
        plant = plantArray.get(plantPosition);

        btnConfirmDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbHandler.deletePlant(Integer.valueOf(plant.get("id")));
                accessMyPlants();
                dismiss();
            }
        });

        btnCancelDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });

        return  view;
    }

    private void accessMyPlants() {

        Intent accessMyPlants = new Intent(getContext(), MyPlants.class);
        startActivity(accessMyPlants);
    }
}
