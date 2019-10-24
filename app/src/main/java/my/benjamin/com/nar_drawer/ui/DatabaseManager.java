package my.benjamin.com.nar_drawer.ui;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.util.Log;

public class DatabaseManager extends SQLiteOpenHelper {
    private final static int DBVERSION = 1;
    // for the main table
    private final static String DBNAME = "COURSE_PLANNER";
    private final static String MAIN_TBL = "main_tbl";
    private final static String PROGRAMME = "programme_name";
    private final static String ABOUT_DEVS = "about_devs";
    private final static String HELP = "help";
    private final static String CONTACTS = "devs_contact";

    //courses tables for the list of courses
    private final static String COURSES_TBL = "courses_tbl";
    private final static String COURSES_ID = "courses_id";
    private final static String COURSES_NAME = "courses_name";

    // for courses materials like pdf
    private final static String COURSES_MATERIALS_TBL = "courses_materials_tbl";
    private final static String COURSE_MATERIAL_LINK = "material_link";

    // for courses past questions materials
    private final static String COURSES_PAST_Q_TBL = "past_questions_tbl";
    private final static String COURSES_PAST_Q_LINK = "past_questions_link";

    private String user_course_name;
    private String material_link;
    private String past_questions_link;



    public DatabaseManager(Context context){
        super(context, DBNAME, null, DBVERSION);
    }

    // mutator and accessor functions
    private String getUserCourseName(){ return user_course_name;}

    public void setUserCourseName(String x){ user_course_name = x;}

    public void setMaterial_link(String material_link) { this.material_link = material_link; }

    private String getMaterial_link() { return material_link; }

    private String getPast_questions_link() { return past_questions_link; }

    public void setPast_questions_link(String past_questions_link) {
        this.past_questions_link = past_questions_link; }

    @Override
    public  void onCreate(SQLiteDatabase db) {
        db.execSQL( "create table " + MAIN_TBL
                + "(" + PROGRAMME + " varchar(60), " + ABOUT_DEVS + " varchar(255), " +
                HELP + " varchar(255), " + CONTACTS + " varchar(255) " + ")" );
        Log.e("DATABASE OPERATION", "Database created / opened.....");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("");
        onCreate(db);
    }

    public boolean insertProgrammeName(String programme_name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PROGRAMME, programme_name);
        long status = db.insert(MAIN_TBL, null, contentValues);
        if (status == -1){
            return false;
        } else
            return true;
    }
    /*
    *  creates the table and then next method simultaneous to it name
    *  insert into the table
    *
    *
    * */

    public void createCourses_tbl(SQLiteDatabase db){
        db.execSQL(
                "create table " + COURSES_TBL + "(" + COURSES_ID + " varchar(10) primary key, " +
                        COURSES_NAME + " varchar(255))"
        );
    }

    public boolean inserIntoCourses_tbl(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COURSES_ID, generateCourseName(getUserCourseName()).toString());
        contentValues.put(COURSES_NAME, user_course_name);
        long status = db.insert(COURSES_TBL,null, contentValues);
        // check if db insert was successful
        if (status == -1){
            return false;
        } else
            return true;
    }



    public void createCoursesMaterials_tbl(SQLiteDatabase db){
        db.execSQL(
                "create table " + COURSES_MATERIALS_TBL + "(" + COURSES_ID + " varchar(10) primary key,"
                        + COURSE_MATERIAL_LINK + " varchar(255)" + ")"
        );
    }

    public boolean insertCourseMaterials_tbl(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COURSES_ID, generateCourseName(getUserCourseName()).toString());
        contentValues.put(COURSE_MATERIAL_LINK, getMaterial_link());
        long status = db.insert(COURSES_MATERIALS_TBL, null, contentValues);
        // check if db insert was successful
        if (status == -1){
            return false;
        } else
            return true;
    }

    public void createPassQuestions_tbl(SQLiteDatabase db){
        db.execSQL(
                "create table " +  COURSES_PAST_Q_TBL + "(" + COURSES_ID + " varchar(10) primary key," +
                        COURSES_PAST_Q_LINK + " varchar(255)" + ")"
        );
    }

    public boolean insertPastQuestions_tbl(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COURSES_ID, generateCourseName(getUserCourseName()).toString());
        contentValues.put(COURSE_MATERIAL_LINK, getPast_questions_link());
        long status = db.insert(COURSES_PAST_Q_TBL, null, contentValues);
        if (status == -1){
            return false;
        } else
            return true;
    }

    private StringBuilder generateCourseName(String course_name){
        StringBuilder course_id = new StringBuilder();
        course_id.append(course_name.charAt(0));    //+= String.valueOf(course_name.charAt(0));
        course_id.append(course_name.charAt(1));    //+= String.valueOf(course_name.charAt(1));
        for(int i = 0; i < course_name.length(); i++ ){
            if(course_name.charAt( i ) == ' '){
                 course_id.append(course_name.charAt(i + 1)); //+= String.valueOf(course_name.charAt(i + 1));
            }
        }
        return course_id;
    }




}
