package com.example.sd6501_assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    Button btnAccessVegetableMenu;
    Button btnAccessMyPlants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Toolbar toolbar = findViewById(R.id.growMateToolbar);
        setSupportActionBar(toolbar);

        btnAccessVegetableMenu = findViewById(R.id.btnAccessVegetableMenu);
        btnAccessMyPlants = findViewById(R.id.btnAccessMyPlants);


        btnAccessVegetableMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                accessVegetableMenu();
            }
        });

        btnAccessMyPlants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                accessMyPlants();
            }
        });
    }

    private void accessVegetableMenu() {

        Intent accessVegetableMenu = new Intent(this, VegetableMenu.class);
        startActivity(accessVegetableMenu);
    }

    private void accessMyPlants() {

        Intent accessMyPlants = new Intent(this, MyPlants.class);
        startActivity(accessMyPlants);
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
