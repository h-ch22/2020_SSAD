package kr.ac.jbnu.se.ssad_group6.listView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import kr.ac.jbnu.se.ssad_group6.Item;
import kr.ac.jbnu.se.ssad_group6.MainActivity;
import kr.ac.jbnu.se.ssad_group6.R;

import static android.content.ContentValues.TAG;

public class UserListActivity extends Activity {
    private ListView mlistView;
    private ItemAdapter mAdapter;
    private List<Item> visited;
    private FirebaseFirestore db;

    private String[] dateValues;

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        fab = findViewById(R.id.fab);

        if (getIntent().getStringExtra("intentDate") != null) {
            dateValues = getIntent().getStringExtra("intentDate").split(",");

            for (String s : dateValues) {
                Log.d(TAG, "----- date : " + s);
            }

            if (dateValues.length <= 3) {
                finish();
            }

        } else {
            finish();
        }

        String inputData = dateValues[0] + "-" + dateValues[1] + "-" + dateValues[2] + "-" + dateValues[3] + "-" + dateValues[4];
        String inputTime = dateValues[3];

        Log.d(TAG, "-----DATA--" + inputData);

        visited = new ArrayList<>();

        mlistView = (ListView) findViewById(R.id.todoListView);
        mAdapter = new ItemAdapter(visited);
        mlistView.setAdapter(mAdapter);

        loadData(inputData, inputTime);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });


    }

    @Override
    public void onBackPressed() {

    }

    private void loadData(String inputData, String inputTime){
        db = FirebaseFirestore.getInstance();
        db.collection("movement2")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot qds : task.getResult()) {
                                db.collection("movement2")
                                        .document(qds.getId())
                                        .collection("yejin25@gmail.com")
                                        .orderBy("time")
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                                        Log.d(TAG, "----- " + document.getId());

                                                        Map<String, Object> maps = document.getData();

                                                        String makeTimes = (String.valueOf(maps.get("date")) + " " + String.valueOf(maps.get("time")));
                                                        makeTimes = makeTimes.replaceAll(". ", "-");
                                                        makeTimes = makeTimes.replaceAll(":", "-");

                                                        String onlyTime = makeTimes.substring(11,13);

                                                        Log.d(TAG, "-----DATA" + onlyTime);

                                                        SimpleDateFormat transDateForm = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
                                                        Date transDate;
                                                        Date transInput;

                                                        try {
                                                            transDate = transDateForm.parse(makeTimes);
                                                            transInput = transDateForm.parse(inputData);

                                                        } catch (ParseException e) {
                                                            transDate = Calendar.getInstance().getTime();
                                                            transInput = Calendar.getInstance().getTime();
                                                        }

                                                        int cmp = transDate.compareTo(transInput);

                                                        if( cmp > 0){
                                                            if(Integer.parseInt(onlyTime) - Integer.parseInt(inputTime) > 0)
                                                                visited.add(new Item(String.valueOf(maps.get("storeName")), transDate));
                                                            mAdapter.notifyDataSetChanged();
                                                        }

                                                        for (String key : maps.keySet()) {
                                                            Log.d(TAG, "----- key : " + key + " data : " + maps.get(key));
                                                        }

                                                    }

                                                }
                                            }
                                        });
                            }
                        }
                    }
                });
    }
}
