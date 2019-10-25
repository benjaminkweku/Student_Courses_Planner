package my.benjamin.com.nar_drawer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import my.benjamin.com.nar_drawer.ui.DatabaseManager;



public class RegisterCourse extends AppCompatActivity {

    Iterator it_store_courses_names = set_store_courses_names.iterator();
    //setting button variable
    private TextView tv_program;
    private EditText et_program_entry;
    private Button btn_add;
    private Button btn_done;
    private EditText et_courses_entry;
    private HashMap<String, String> store_courses_names = new HashMap<>();
    Set set_store_courses_names = (Set) store_courses_names.entrySet();
    private HashMap<String, String> store_course_materials = new HashMap<>();
    private HashMap<String, String> store_pass_questions = new HashMap<>();
    private boolean db_flag = true;
    // Database
     DatabaseManager my_db; // an object for the DatabaseManager

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_course);
        tv_program = (TextView) findViewById(R.id.id_tv_program_name);
        et_program_entry = (EditText) findViewById(R.id.id_et_program_entry);
        et_courses_entry = (EditText) findViewById(R.id.id_et_courses_entry);
        btn_add = (Button) findViewById(R.id.id_btn_add_courses);
        btn_done = (Button) findViewById(R.id.id_btn_done);

        my_db = new DatabaseManager(this);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_courses_entry.getText().length() > 0) {
                    store_courses_names.put(my_db.generateCourse_id
                                    (et_courses_entry.getText().toString()),
                            et_courses_entry.getText().toString());
                    et_courses_entry.setText("");
                }else{
                    t_emptyFieldAlert();
                }

            }


        });

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_program_entry.getText().length() > 0) {
                    while (it_store_courses_names.hasNext()) {
                        Map.Entry map_entry = (Map.Entry) it_store_courses_names.next();
                        db_flag = my_db.insertIntoCourses_tbl((String) map_entry.getValue());
                        et_courses_entry.setText((String) map_entry.getValue());
                        et_program_entry.setError("error, ");
                    }
                    if (db_flag) {
                        t_successfulConnectStatus();

                    } else {
                        t_unsuccessfulConnectStatus();
                    }
                } else {
                    t_FieldEmpty();
                }
            }
        });
    }

    private void t_successfulConnectStatus() {
        Toast.makeText(this, "Info successfully added", Toast.LENGTH_SHORT).show();
    }

    private void t_unsuccessfulConnectStatus() {
        Toast.makeText(this, "not successful", Toast.LENGTH_SHORT).show();
    }

    private void t_FieldEmpty() {
        Toast.makeText(this, "program field cannot be empty", Toast.LENGTH_SHORT).show();

    }

    private void t_emptyFieldAlert() {
        Toast.makeText(this, "Empty Field", Toast.LENGTH_SHORT).show();
    }


}

//    checkProgramField();
//
//                if(et_courses.getText().toString() != "") {
//        boolean insert_status = my_db.insertIntoCourses_tbl(et_courses.getText().toString());
//        if (insert_status) {
//            et_courses.setText("");
//            t_successfulConnectStatus();
//        } else {
//            t_unsuccessfulConnectStatus();
//        }
//    }else{
//        t_emptyFieldAlert();
//    }
//}