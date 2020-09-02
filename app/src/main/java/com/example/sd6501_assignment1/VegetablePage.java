package com.example.sd6501_assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.example.sd6501_assignment1.VegetableMenu.plantToDisplay;

public class VegetablePage extends AppCompatActivity {

    Button btnDisplayPlantDatesFragment, btnDisplayGrowingInfoFragment,
           btnAccessMyPlants;
    TextView germinationDate, transplantDate, harvestDate, growingInfoDisplayWhen,
             growingInfoDisplayWhere, getGrowingInfoDisplayHow, getGrowingInfoDisplayCare,
             vegetableHeading;

    RelativeLayout plantDatesFragment, growingInfoFragment, btnCalculateDates, btnAddPlantToDb;
    CalculateDates calculateDate;

    boolean plantDatesFragmentCollapsed = true;
    boolean growingInfoFragmentCollapsed = true;

    SpringOnion springOnion = new SpringOnion();
    Cucumber cucumber = new Cucumber();
    Cabbage cabbage = new Cabbage();
    Tomato tomato = new Tomato();
    Lettuce lettuce = new Lettuce();
    VioletCauliflower violetCauliflower = new VioletCauliflower();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetable_page);

        Toolbar toolbar = findViewById(R.id.growMateToolbar);
        setSupportActionBar(toolbar);

        btnCalculateDates = findViewById(R.id.plantTodayBtnLayout);
        btnAddPlantToDb = findViewById(R.id.addToMyPlantsLayout);
        btnDisplayGrowingInfoFragment = findViewById(R.id.btnDisplayGrowingInfoFragment);
        btnDisplayPlantDatesFragment = findViewById(R.id.btnDisplayPlantDateFragment);
        btnAccessMyPlants = findViewById(R.id.btnAccessMyPlants);

        germinationDate = findViewById(R.id.txtViewGerminationDate);
        transplantDate = findViewById(R.id.txtViewTransplantDate);
        harvestDate = findViewById(R.id.txtViewHarvestDate);
        growingInfoDisplayWhen = findViewById(R.id.txtViewGrowingInfoDisplayWhen);
        growingInfoDisplayWhere = findViewById(R.id.txtViewGrowingInfoDisplayWhere);
        getGrowingInfoDisplayHow = findViewById(R.id.txtViewGrowingInfoDisplayHow);
        getGrowingInfoDisplayCare = findViewById(R.id.txtViewGrowingInfoDisplayCare);
        vegetableHeading = findViewById(R.id.vegetableHeading);

        plantDatesFragment = findViewById(R.id.plantDatesFragment);
        growingInfoFragment = findViewById(R.id.growingInfoFragment);

        // Get current screen width
        final int fragmentWidth = MainActivity.width;

        //Collapse fragments when page is opened
        collapseFragment(plantDatesFragment);
        collapseFragment(growingInfoFragment);

        btnDisplayPlantDatesFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // If plant_dates_fragment is collapsed when btn is clicked, expand the fragment
                if (plantDatesFragmentCollapsed) {
                    plantDatesFragmentCollapsed = false;
                    expandFragment(fragmentWidth, plantDatesFragment);


                    // Else collapse the fragment
                }else {
                    plantDatesFragmentCollapsed = true;
                    collapseFragment(plantDatesFragment);
                }
            }
        });

        // Collapse and expand growing_info_fragment
        btnDisplayGrowingInfoFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // If growing_info_fragment is collapsed when btn is clicked, expand the fragment
                if (growingInfoFragmentCollapsed) {
                    growingInfoFragmentCollapsed = false;
                    expandFragment(fragmentWidth, growingInfoFragment);

                    // Else collapse the fragment
                }else {
                    growingInfoFragmentCollapsed = true;
                    collapseFragment(growingInfoFragment);
                }
            }
        });

        if (plantToDisplay == 0) {

            vegetableHeading.setText(springOnion.getName());

            // Get spring onion growing info and display in the growing info fragment
            growingInfoDisplayWhen.setText(springOnion.getGrowingInformationWhen());
            growingInfoDisplayWhere.setText(springOnion.getGrowingInformationWhere());
            getGrowingInfoDisplayHow.setText(springOnion.getGrowingInformationHow());
            getGrowingInfoDisplayCare.setText(springOnion.getGrowingInformationCare());

            // Calculate and display plant dates
            btnCalculateDates.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    calculateDate = new CalculateDates();

                    springOnion.setGerminationDate(calculateDate.calculateDate(
                            springOnion.getGerminationDays()));
                    germinationDate.setText(springOnion.getGerminationDate());

                    springOnion.setTransplantDate(calculateDate.calculateDate(
                            springOnion.getTransplantDays()));
                    transplantDate.setText(springOnion.getTransplantDate());

                    springOnion.setHarvestDate(calculateDate.calculateDate(
                            springOnion.getHarvestDays()));
                    harvestDate.setText(springOnion.getHarvestDate());
                }
            });

            // Add plant info to db when icon is clicked
            btnAddPlantToDb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Prompt user to click plant today to get dates before adding the plant
                    if( springOnion.getGerminationDate().equals("")) {
                        Toast.makeText(getApplicationContext(),"Please click Plant Today " +
                                        "to get dates first...", Toast.LENGTH_LONG).show();

                    }else {
                        MyPlantsDbHandler dbHandler = new MyPlantsDbHandler(
                                VegetablePage.this);

                        // Get current date and time
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                                "dd-MM-yyyy, HH:mm");
                        String currentDateTime = simpleDateFormat.format(
                                Calendar.getInstance().getTime());

                        // Add plant record to db
                        dbHandler.insertPlantDetails(
                                springOnion.getName(),
                                currentDateTime,
                                springOnion.getGerminationDate(),
                                springOnion.getTransplantDate(),
                                springOnion.getHarvestDate()
                        );

                        Toast.makeText(getApplicationContext(),"Spring Onion added to " +
                                        "My Plants...", Toast.LENGTH_LONG).show();
                    }
                }
            });

        } else if(plantToDisplay == 1) {

            vegetableHeading.setText(cucumber.getName());

            // Get cucumber onion growing info and display in the growing info fragment
            growingInfoDisplayWhen.setText(cucumber.getGrowingInformationWhen());
            growingInfoDisplayWhere.setText(cucumber.getGrowingInformationWhere());
            getGrowingInfoDisplayHow.setText(cucumber.getGrowingInformationHow());
            getGrowingInfoDisplayCare.setText(cucumber.getGrowingInformationCare());

            // Calculate and display plant dates
            btnCalculateDates.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    calculateDate = new CalculateDates();

                    cucumber.setGerminationDate(calculateDate.calculateDate(
                            cucumber.getGerminationDays()));
                    germinationDate.setText(cucumber.getGerminationDate());

                    cucumber.setTransplantDate(calculateDate.calculateDate(
                            cucumber.getTransplantDays()));
                    transplantDate.setText(cucumber.getTransplantDate());

                    cucumber.setHarvestDate(calculateDate.calculateDate(
                            cucumber.getHarvestDays()));
                    harvestDate.setText(cucumber.getHarvestDate());
                }
            });

            // Add plant info to db when icon is clicked
            btnAddPlantToDb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Prompt user to click plant today to get dates before adding the plant
                    if( cucumber.getGerminationDate().equals("")) {
                        Toast.makeText(getApplicationContext(),"Please click Plant Today to " +
                                        "get dates first...", Toast.LENGTH_LONG).show();

                    }else {
                        MyPlantsDbHandler dbHandler = new MyPlantsDbHandler(
                                VegetablePage.this);

                        // Get current date and time
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                                "dd-MM-yyyy, HH:mm");
                        String currentDateTime = simpleDateFormat.format(
                                Calendar.getInstance().getTime());

                        // Add plant record to db
                        dbHandler.insertPlantDetails(
                                cucumber.getName(),
                                currentDateTime,
                                cucumber.getGerminationDate(),
                                cucumber.getTransplantDate(),
                                cucumber.getHarvestDate()
                        );

                        Toast.makeText(getApplicationContext(),"Cucumber added to " +
                                        "My Plants...", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }else if(plantToDisplay == 2) {

            vegetableHeading.setText(cabbage.getName());

            // Get cucumber onion growing info and display in the growing info fragment
            growingInfoDisplayWhen.setText(cabbage.getGrowingInformationWhen());
            growingInfoDisplayWhere.setText(cabbage.getGrowingInformationWhere());
            getGrowingInfoDisplayHow.setText(cabbage.getGrowingInformationHow());
            getGrowingInfoDisplayCare.setText(cabbage.getGrowingInformationCare());

            // Calculate and display plant dates
            btnCalculateDates.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    calculateDate = new CalculateDates();

                    cabbage.setGerminationDate(calculateDate.calculateDate(
                            cabbage.getGerminationDays()));
                    germinationDate.setText(cabbage.getGerminationDate());

                    cabbage.setTransplantDate(calculateDate.calculateDate(
                            cabbage.getTransplantDays()));
                    transplantDate.setText(cabbage.getTransplantDate());

                    cabbage.setHarvestDate(calculateDate.calculateDate(
                            cabbage.getHarvestDays()));
                    harvestDate.setText(cabbage.getHarvestDate());
                }
            });

            // Add plant info to db when icon is clicked
            btnAddPlantToDb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Prompt user to click plant today to get dates before adding the plant
                    if( cabbage.getGerminationDate().equals("")) {
                        Toast.makeText(getApplicationContext(),"Please click Plant Today to " +
                                "get dates first...", Toast.LENGTH_LONG).show();

                    }else {
                        MyPlantsDbHandler dbHandler = new MyPlantsDbHandler(
                                VegetablePage.this);

                        // Get current date and time
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                                "dd-MM-yyyy, HH:mm");
                        String currentDateTime = simpleDateFormat.format(
                                Calendar.getInstance().getTime());

                        // Add plant record to db
                        dbHandler.insertPlantDetails(
                                cabbage.getName(),
                                currentDateTime,
                                cabbage.getGerminationDate(),
                                cabbage.getTransplantDate(),
                                cabbage.getHarvestDate()
                        );

                        Toast.makeText(getApplicationContext(),"Cabbage added to " +
                                "My Plants...", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }else if(plantToDisplay == 3) {

            vegetableHeading.setText(tomato.getName());

            // Get cucumber onion growing info and display in the growing info fragment
            growingInfoDisplayWhen.setText(tomato.getGrowingInformationWhen());
            growingInfoDisplayWhere.setText(tomato.getGrowingInformationWhere());
            getGrowingInfoDisplayHow.setText(tomato.getGrowingInformationHow());
            getGrowingInfoDisplayCare.setText(tomato.getGrowingInformationCare());

            // Calculate and display plant dates
            btnCalculateDates.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    calculateDate = new CalculateDates();

                    tomato.setGerminationDate(calculateDate.calculateDate(
                            tomato.getGerminationDays()));
                    germinationDate.setText(tomato.getGerminationDate());

                    tomato.setTransplantDate(calculateDate.calculateDate(
                            tomato.getTransplantDays()));
                    transplantDate.setText(tomato.getTransplantDate());

                    tomato.setHarvestDate(calculateDate.calculateDate(
                            tomato.getHarvestDays()));
                    harvestDate.setText(tomato.getHarvestDate());
                }
            });

            // Add plant info to db when icon is clicked
            btnAddPlantToDb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Prompt user to click plant today to get dates before adding the plant
                    if (tomato.getGerminationDate().equals("")) {
                        Toast.makeText(getApplicationContext(), "Please click Plant Today to " +
                                "get dates first...", Toast.LENGTH_LONG).show();

                    } else {
                        MyPlantsDbHandler dbHandler = new MyPlantsDbHandler(
                                VegetablePage.this);

                        // Get current date and time
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                                "dd-MM-yyyy, HH:mm");
                        String currentDateTime = simpleDateFormat.format(
                                Calendar.getInstance().getTime());

                        // Add plant record to db
                        dbHandler.insertPlantDetails(
                                tomato.getName(),
                                currentDateTime,
                                tomato.getGerminationDate(),
                                tomato.getTransplantDate(),
                                tomato.getHarvestDate()
                        );

                        Toast.makeText(getApplicationContext(), "Tomato added to " +
                                "My Plants...", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }else if(plantToDisplay == 4) {

            vegetableHeading.setText(lettuce.getName());

            // Get cucumber onion growing info and display in the growing info fragment
            growingInfoDisplayWhen.setText(lettuce.getGrowingInformationWhen());
            growingInfoDisplayWhere.setText(lettuce.getGrowingInformationWhere());
            getGrowingInfoDisplayHow.setText(lettuce.getGrowingInformationHow());
            getGrowingInfoDisplayCare.setText(lettuce.getGrowingInformationCare());

            // Calculate and display plant dates
            btnCalculateDates.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    calculateDate = new CalculateDates();

                    lettuce.setGerminationDate(calculateDate.calculateDate(
                            lettuce.getGerminationDays()));
                    germinationDate.setText(lettuce.getGerminationDate());

                    lettuce.setTransplantDate(calculateDate.calculateDate(
                            lettuce.getTransplantDays()));
                    transplantDate.setText(lettuce.getTransplantDate());

                    lettuce.setHarvestDate(calculateDate.calculateDate(
                            lettuce.getHarvestDays()));
                    harvestDate.setText(lettuce.getHarvestDate());
                }
            });

            // Add plant info to db when icon is clicked
            btnAddPlantToDb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Prompt user to click plant today to get dates before adding the plant
                    if (lettuce.getGerminationDate().equals("")) {
                        Toast.makeText(getApplicationContext(), "Please click Plant Today to " +
                                "get dates first...", Toast.LENGTH_LONG).show();

                    } else {
                        MyPlantsDbHandler dbHandler = new MyPlantsDbHandler(
                                VegetablePage.this);

                        // Get current date and time
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                                "dd-MM-yyyy, HH:mm");
                        String currentDateTime = simpleDateFormat.format(
                                Calendar.getInstance().getTime());

                        // Add plant record to db
                        dbHandler.insertPlantDetails(
                                lettuce.getName(),
                                currentDateTime,
                                lettuce.getGerminationDate(),
                                lettuce.getTransplantDate(),
                                lettuce.getHarvestDate()
                        );

                        Toast.makeText(getApplicationContext(), "Lettuce added to " +
                                "My Plants...", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }else if (plantToDisplay == 5) {

            vegetableHeading.setText(violetCauliflower.getName());

            // Get cucumber onion growing info and display in the growing info fragment
            growingInfoDisplayWhen.setText(violetCauliflower.getGrowingInformationWhen());
            growingInfoDisplayWhere.setText(violetCauliflower.getGrowingInformationWhere());
            getGrowingInfoDisplayHow.setText(violetCauliflower.getGrowingInformationHow());
            getGrowingInfoDisplayCare.setText(violetCauliflower.getGrowingInformationCare());

            // Calculate and display plant dates
            btnCalculateDates.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    calculateDate = new CalculateDates();

                    violetCauliflower.setGerminationDate(calculateDate.calculateDate(
                            violetCauliflower.getGerminationDays()));
                    germinationDate.setText(violetCauliflower.getGerminationDate());

                    violetCauliflower.setTransplantDate(calculateDate.calculateDate(
                            violetCauliflower.getTransplantDays()));
                    transplantDate.setText(violetCauliflower.getTransplantDate());

                    violetCauliflower.setHarvestDate(calculateDate.calculateDate(
                            violetCauliflower.getHarvestDays()));
                    harvestDate.setText(violetCauliflower.getHarvestDate());
                }
            });

            // Add plant info to db when icon is clicked
            btnAddPlantToDb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Prompt user to click plant today to get dates before adding the plant
                    if (violetCauliflower.getGerminationDate().equals("")) {
                        Toast.makeText(getApplicationContext(), "Please click Plant Today to " +
                                "get dates first...", Toast.LENGTH_LONG).show();

                    } else {
                        MyPlantsDbHandler dbHandler = new MyPlantsDbHandler(
                                VegetablePage.this);

                        // Get current date and time
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                                "dd-MM-yyyy, HH:mm");
                        String currentDateTime = simpleDateFormat.format(
                                Calendar.getInstance().getTime());

                        // Add plant record to db
                        dbHandler.insertPlantDetails(
                                violetCauliflower.getName(),
                                currentDateTime,
                                violetCauliflower.getGerminationDate(),
                                violetCauliflower.getTransplantDate(),
                                violetCauliflower.getHarvestDate()
                        );

                        Toast.makeText(getApplicationContext(), "Violet Cauliflower added to "
                                + "My Plants...", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        btnAccessMyPlants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                accessMyPlants();
            }
        });
    }

    private void collapseFragment(RelativeLayout fragment) {

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)
        fragment.getLayoutParams();
        layoutParams.height = 0;
        layoutParams.width = 0;
        fragment.setLayoutParams(layoutParams);
    }

    private void expandFragment(int fragmentWidth, RelativeLayout fragment) {

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)
        fragment.getLayoutParams();
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = fragmentWidth;
        fragment.setLayoutParams(layoutParams);
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