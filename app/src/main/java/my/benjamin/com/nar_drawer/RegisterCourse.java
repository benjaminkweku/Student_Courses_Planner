package my.benjamin.com.nar_drawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterCourse extends AppCompatActivity {
    //setting button variable
    private TextView program;
    private EditText edit_input;
    private Button add;
    static boolean check = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_course);
        program=(TextView)findViewById(R.id.downbar);
        edit_input=(EditText)findViewById(R.id.edt_save);
        add=(Button)findViewById(R.id.add);


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
}
