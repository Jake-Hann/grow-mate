package com.example.sd6501_assignment1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.sd6501_assignment1.MyPlants.plantPosition;

public class ViewPlantRecord extends AppCompatActivity {

    TextView plantDetailsHeading;
    EditText germinationDate, transplantDate, harvestDate;
    Button btnUpdate, btnDelete, btnAccessMyPlants;

    public ArrayList<HashMap<String, String>> plantArray;
    public HashMap<String,String> plant;

    MyPlantsDbHandler dbHandler;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_plant_record);

        plantDetailsHeading = findViewById(R.id.txtViewPlantDetailsHeading);
        germinationDate = findViewById(R.id.editTxtGerminationDate);
        transplantDate = findViewById(R.id.editTxtTransplantDate);
        harvestDate = findViewById(R.id.editTxtHarvestDate);
        btnAccessMyPlants = findViewById(R.id.btnAccessMyPlants);
        btnUpdate = findViewById(R.id.btnUpdatePlantDetails);
        btnDelete = findViewById(R.id.btnDeletePlant);

        // Get plant array, extract the selected plant and display its dates
        dbHandler = new MyPlantsDbHandler(ViewPlantRecord.this);
        plantArray = dbHandler.getPlants();
        plant = plantArray.get(plantPosition);
        displayPlantInfo(plant);

        germinationDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                germinationDate.setFocusableInTouchMode(true);
                return false;
            }
        });

        transplantDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                transplantDate.setFocusableInTouchMode(true);
                return false;
            }
        });

        harvestDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                harvestDate.setFocusableInTouchMode(true);
                return false;
            }
        });

        // Call confirm delete dialog
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConfirmPlantDeleteDialog confirmDeleteDialog = new ConfirmPlantDeleteDialog();
                confirmDeleteDialog.show(getSupportFragmentManager(),
                        "ConfirmPlantDeleteDialog");

            }
        });

        // Update plant details on btn click
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hideEditTextCursors(germinationDate, transplantDate, harvestDate);

                // Call method to update db
                dbHandler.updatePlantDetails(
                        Integer.valueOf(plant.get("id")),
                        germinationDate.getText().toString(),
                        transplantDate.getText().toString(),
                        harvestDate.getText().toString()
                );

                // Refresh the plant array, get the updated plant and re-display info
                dbHandler = new MyPlantsDbHandler(ViewPlantRecord.this);
                plantArray = dbHandler.getPlants();
                HashMap<String,String> refreshedPlant;
                refreshedPlant = plantArray.get(plantPosition);
                displayPlantInfo(refreshedPlant);

                Toast.makeText(ViewPlantRecord.this, "Plant details updated!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnAccessMyPlants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                accessMyPlants();
            }
        });
    }

    private void hideEditTextCursors(EditText germinationDate, EditText transplantDate,
                                     EditText harvestDate) {

        germinationDate.setFocusable(false);
        transplantDate.setFocusable(false);
        harvestDate.setFocusable(false);
    }

    private void displayPlantInfo(HashMap<String,String> plant) {

        plantDetailsHeading.setText(plant.get("plantname"));
        germinationDate.setText(plant.get("germinationdate"));
        transplantDate.setText(plant.get("transplantdate"));
        harvestDate.setText(plant.get("harvestdate"));
    }

    private void accessMyPlants() {

        Intent accessMyPlants = new Intent(ViewPlantRecord.this,
                MyPlants.class);
        startActivity(accessMyPlants);
    }
}


