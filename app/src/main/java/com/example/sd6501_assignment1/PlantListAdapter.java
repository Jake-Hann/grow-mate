package com.example.sd6501_assignment1;

import android.content.Context;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;

public class PlantListAdapter extends AppCompatPreferenceActivity {

    public void setPlantListAdapter(ArrayList<HashMap<String, String>> arrayList, Context context,
                                    ListView listView) {

        ListAdapter plantListAdapter= new SimpleAdapter(context, arrayList, R.layout.layout_db_row,
                new String[] {"plantname", "datetimeplanted"},
                new int[] {R.id.txtViewDisplayPlantName, R.id.txtViewDisplayDateTimePlanted}
        );

        listView.setAdapter(plantListAdapter);
    }
}
