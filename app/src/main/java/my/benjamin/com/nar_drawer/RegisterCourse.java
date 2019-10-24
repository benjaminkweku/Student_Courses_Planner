package my.benjamin.com.nar_drawer;

import androidx.appcompat.app.AppCompatActivity;

// import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import my.benjamin.com.nar_drawer.ui.DatabaseManager;

public class RegisterCourse extends AppCompatActivity {
    //setting button variable
    private TextView program;
    private EditText edit_input;
    private Button add;
    private EditText edit_course;
    private Button save;
    static boolean check = true;
    // Database
     DatabaseManager my_db; // an object for the DatabaseManager




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_course);
        program=(TextView)findViewById(R.id.downbar);
        edit_input=(EditText)findViewById(R.id.edt_save);
        add=(Button)findViewById(R.id.add);
        save=(Button)findViewById(R.id.save);
        edit_course=(EditText)findViewById(R.id.edt_link);
        my_db = new DatabaseManager(this);


        //set save onclicklistener
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                my_db.createCourses_tbl();
                if(my_db.insertProgrammeName(edit_course.getText().toString())){
                    unsuccessful_connect_status();
                }else{
                    successful_connect_status();
                }

            }
        });




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                while(check) {
                    if (program.getText() != edit_input.getText()) {
                        program.setText(edit_input.getText().toString());
                        check = false;

                    }
                }

            }
        });
    }

    public void successful_connect_status(){
        Toast.makeText(this,"added successfully",Toast.LENGTH_SHORT).show();;
    }

    public void unsuccessful_connect_status(){
        Toast.makeText(this, "not sucessful", Toast.LENGTH_SHORT).show();
    }
}
