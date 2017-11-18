package com.example.jarigye.sqlliteapplication;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {
    DBHelper mydb;
    private Spinner spinner;
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DBHelper(this);

        // mydb.insertCourse();
        // mydb.insertStudent();
        // mydb.insertQuiz();

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        loadSpinnerData();

        tableLayout = (TableLayout) findViewById(R.id.tableLayout);

        //for (int i=0;i<5;i++){
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        TextView quiz_display_s1 = new TextView(this);
        quiz_display_s1.setText("Student_id");
        quiz_display_s1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tr.addView(quiz_display_s1);
        TextView quiz_display_q1 = new TextView(this);
        quiz_display_q1.setText("QUIZ 1");
        quiz_display_q1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tr.addView(quiz_display_q1);
        TextView quiz_display_q2 = new TextView(this);
        quiz_display_q2.setText("QUIZ 2");
        quiz_display_q2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tr.addView(quiz_display_q2);
        TextView quiz_display_q3 = new TextView(this);
        quiz_display_q3.setText("QUIZ 3");
        quiz_display_q3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tr.addView(quiz_display_q3);
        TextView quiz_display_q4 = new TextView(this);
        quiz_display_q4.setText("QUIZ 4");
        tr.addView(quiz_display_q4);
        quiz_display_q4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        TextView quiz_display_q5 = new TextView(this);
        quiz_display_q5.setText("QUIZ 5");
        quiz_display_q5.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tr.addView(quiz_display_q5);
        tableLayout.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
    }

    private void loadSpinnerData() {
        // database handler
        DBHelper db = new DBHelper(getApplicationContext());
        // Spinner Drop down elements
        List<String> courses = db.getAllCourses();
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, courses);
        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        DBHelper db = new DBHelper(getApplicationContext());
        // On selecting a spinner item
        String course = parent.getItemAtPosition(position).toString();
        int a = db.getId(course);
        ArrayList<String> array_list = new ArrayList();
        array_list = db.getAllMarks(a);
        if (array_list.size() != 0) {
            for (int i = 0; i < 4; i++) {
                TableRow tr = new TableRow(this);
                for (int j = 0; j < 6; j++) {
                    TextView a1 = new TextView(this);
                    a1.setText(array_list.get((i * 6) + j));
                    a1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                    tr.addView(a1);
                }
                tableLayout.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            }
        } else {

            // Showing selected spinner item
            Toast.makeText(parent.getContext(), "There is NO DATA FOR THIS COURSE ",
                    Toast.LENGTH_LONG).show();
        }

        ArrayList<String> list = new ArrayList();
        list=db.getMaximumScore(a);
         if (list.size() != 0) {
             TableRow tra = new TableRow(this);
             TextView a2 = new TextView(this);
             a2.setText("High Score");
             a2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
             tra.addView(a2);
            for(String x:list) {
                TextView ar = new TextView(this);
                ar.setText(x);
               ar.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
               tra.addView(ar);
            }
              tableLayout.addView(tra,new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT,TableLayout.LayoutParams.WRAP_CONTENT));
         }
        ArrayList<String> list1 = new ArrayList();
          list1=db.getMinScore(a);
           if (list1.size() != 0) {
               TableRow tri = new TableRow(this);
               TextView a3 = new TextView(this);
               a3.setText("Low Score");
               a3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
               tri.addView(a3);
               for (String y : list1) {
                   TextView ary = new TextView(this);
                   ary.setText(y);
                   ary.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                   tri.addView(ary);
               }
              tableLayout.addView(tri, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
           }

        ArrayList<String> list2 = new ArrayList();
        list2=db.getAvgScore(a);
        if (list2.size() != 0) {
           TableRow trp = new TableRow(this);
            TextView a4 = new TextView(this);
            a4.setText("Average");
            a4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
            trp.addView(a4);
            for (String y : list2) {
                TextView art = new TextView(this);
                art.setText(y);
                art.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                trp.addView(art);
            }

            tableLayout.addView(trp,new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT,TableLayout.LayoutParams.WRAP_CONTENT));
       }
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

}
