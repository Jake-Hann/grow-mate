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
import android.widget.GridView;
import android.widget.Toast;

public class VegetableMenu extends AppCompatActivity {

    GridView vegetableGrid;

    public static int plantToDisplay = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetable_menu);

        Toolbar toolbar = findViewById(R.id.growMateToolbar);
        setSupportActionBar(toolbar);

        vegetableGrid = findViewById(R.id.vegetableGrid);

        vegetableGrid.setAdapter(new VegetableImageAdapter((this)));

        vegetableGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    accessVegetablePage();
                    plantToDisplay = 0;
                }
                else if (position == 1) {
                    accessVegetablePage();
                    plantToDisplay = 1;
                }
                else if (position == 2) {
                    accessVegetablePage();
                    plantToDisplay = 2;
                }
                else if (position == 3) {
                    accessVegetablePage();
                    plantToDisplay = 3;
                }
                else if (position == 4) {
                    accessVegetablePage();
                    plantToDisplay = 4;
                }
                else if (position == 5) {
                    accessVegetablePage();
                    plantToDisplay = 5;
                }
            }
        });
    }

    private void accessVegetablePage() {

        Intent accessSpringOnionPage = new Intent(this, VegetablePage.class);
        startActivity(accessSpringOnionPage);
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
                Intent  accessLogin = new Intent(this, MainActivity.class);
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