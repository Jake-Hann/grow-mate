package com.example.sd6501_assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MyPlants extends AppCompatActivity {

    Button btnAccessMainMenu;
    ListView plantList;
    TextView displayMessage;
    ArrayList<HashMap<String, String>> plantArray;

    MyPlantsDbHandler dbHandler;
    PlantListAdapter plantListAdapter;

    static int plantPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plants);

        Toolbar toolbar = findViewById(R.id.growMateToolbar);
        setSupportActionBar(toolbar);

        btnAccessMainMenu = findViewById(R.id.btnAccessMainMenu);
        plantList = findViewById(R.id.plantList);
        displayMessage = findViewById(R.id.txtViewEmptyListMessage);

        plantListAdapter = new PlantListAdapter();

        dbHandler = new MyPlantsDbHandler(this);
        plantArray = dbHandler.getPlants();

        // Display message if plant list is empty
        if (plantArray.isEmpty()) {
            displayMessage.setVisibility(View.VISIBLE);

        } else
            displayMessage.setVisibility(View.INVISIBLE);

        // Display the plants contained in the db in a list view
        plantListAdapter.setPlantListAdapter(plantArray, MyPlants.this, plantList);

        // Open the view plant details page when list view item is clicked
        plantList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                           int position, long id) {

                plantPosition = position;
                accessViewPlantDetails();
            }
        });

        btnAccessMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                accessMainMenu();
            }
        });
    }

    private void accessViewPlantDetails() {

        Intent accessViewPlantDetails = new Intent(MyPlants.this, ViewPlantRecord.class);
        startActivity(accessViewPlantDetails);
    }

    private void accessMainMenu() {

        Intent  accessMainMenu = new Intent(this, MainMenu.class);
        startActivity(accessMainMenu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_login:
                Intent accessLogin = new Intent(this, MainActivity.class);
                startActivity(accessLogin);
                break;

            case R.id.action_main_menu:
                Intent  accessMainMenu = new Intent(this, MainMenu.class);
                startActivity(accessMainMenu);
                break;

            case R.id.action_vegetable_menu:
                Intent  accessVegetableMenu = new Intent(this, VegetableMenu.class);
                startActivity(accessVegetableMenu);
                break;

            case R.id.action_my_plants:
                Intent accessMyPlants = new Intent(this, MyPlants.class);
                startActivity(accessMyPlants);
                break;

            case R.id.action_settings:
                Intent settings = new Intent(this, SettingsActivity.class);
                startActivity(settings);
                break;

            default:
        }

        return super.onOptionsItemSelected(item);
    }
}







