package com.example.jarigye.sqlliteapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jarigye on 11/17/2017.
 */
class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SQLITASSIGN.db";
    public static final String COURSE_TABLE_NAME = "courses";
    public static final String COURSE_COLUMN_ID = "id";
    public static final String COURSE_COLUMN_NAME = "name";
    public static final String STUDENTS_TABLE_NAME = "students";
    public static final String STUDENTS_COLUMN_ID = "id";
    public static final String STUDENTS_UNIQUE_NO = "unique_no";
   // public static final String STUDENTS_COLUMN_NAME2 = "second_name";
    public static final String QUIZ_TABLE_NAME = "quiz";
    public static final String QUIZ_COLUMN_ID = "id";
    public static final String QUIZ_COLUMN_NAME = "name";
    public static final String MARKS_TABLE_NAME = "marks";
    public static final String MARKS_COLUMN_ID = "id";
    public static final String MARKS_COLUMN_NAME = "mark";
    public static final String COURSE_MARKS = "coursemarks";
    public static final String QUIZ_COURSE = "quizcourse";
    public static final String QUIZ_STUDENT = "quizstudent";


    private HashMap hp;
    private SQLiteDatabase database;
    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override public void onCreate(SQLiteDatabase db) {
//        // TODO Auto-generated method stub
        db.execSQL(
                "create table " + COURSE_TABLE_NAME + " ( " + COURSE_COLUMN_ID + "   INTEGER PRIMARY KEY," + COURSE_COLUMN_NAME + "   VARCHAR);");
        db.execSQL(
                "create table " + STUDENTS_TABLE_NAME + " ( " + STUDENTS_COLUMN_ID + "  INTEGER PRIMARY KEY ," + STUDENTS_UNIQUE_NO + "  INTEGER);");
        db.execSQL(
                "create table " + QUIZ_TABLE_NAME + " ( " + QUIZ_COLUMN_ID + "   INTEGER PRIMARY KEY ," + QUIZ_COLUMN_NAME + "   VARCHAR,"
                        + QUIZ_STUDENT + "   INTEGER,"
                        + QUIZ_COURSE + "    INTEGER,"
                        + MARKS_COLUMN_NAME + "   FLOAT,"
                        + " FOREIGN KEY (" + QUIZ_STUDENT + ") REFERENCES " + STUDENTS_TABLE_NAME + "(" + STUDENTS_COLUMN_ID + ")" +","+
                       " FOREIGN KEY (" + QUIZ_COURSE + ") REFERENCES " + COURSE_TABLE_NAME + "(" + COURSE_COLUMN_ID + "));");

   }


    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.setForeignKeyConstraintsEnabled (true);
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS courses");
       db.execSQL("DROP TABLE IF EXISTS students");
       db.execSQL("DROP TABLE IF EXISTS quiz");
        onCreate(db);
    }

    public void insertCourse() {
        database = this.getReadableDatabase();
        database.beginTransaction();
        ArrayList<String> x= new ArrayList();
        x.add("Programming 101");
        x.add("Programming 102");
        for(String text : x) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COURSE_COLUMN_NAME, text);
            database.insert(COURSE_TABLE_NAME, null, contentValues);
        }
        database.setTransactionSuccessful();
        database.endTransaction();
        database.close();
    }

    public void insertStudent() {
        database = this.getReadableDatabase();
        database.beginTransaction();
       ArrayList<String> arrList = new ArrayList<>();
        arrList.add("1234");
        arrList.add("1032");
        arrList.add("5466");
        arrList.add("5622");
        arrList.add("1002");
        for(String text : arrList)
        {
            ContentValues contentValues = new ContentValues();
            contentValues.put(STUDENTS_UNIQUE_NO, text);
            database.insert(STUDENTS_TABLE_NAME, null, contentValues);
        }
        database.setTransactionSuccessful();
        database.endTransaction();
        database.close();
    }

    public void insertQuiz() {
        database = this.getReadableDatabase();
        database.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put(QUIZ_COLUMN_NAME, "Q1");
            contentValues.put(QUIZ_STUDENT, "1");
            contentValues.put(QUIZ_COURSE, "1");
            contentValues.put(MARKS_COLUMN_NAME, "52");
        database.insert(QUIZ_TABLE_NAME, null, contentValues);
        database.setTransactionSuccessful();
        database.endTransaction();
        database.close();
    }


    public void updateMarksRecord(MarksModel mark) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MARKS_COLUMN_NAME, mark.getMark());
        database.update(MARKS_TABLE_NAME, contentValues, MARKS_COLUMN_ID + " = ?", new String[]{mark.getID()});
        database.close();
    }

    public void deleteRecord(MarksModel contact) {
        database = this.getReadableDatabase();
        database.delete(MARKS_TABLE_NAME, MARKS_COLUMN_ID + " = ?", new String[]{contact.getID()});
        database.close();
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor res1 =  db.rawQuery( "select * from marks where id="+id+"", null );
        Cursor res =  db.rawQuery( "select * from courses", null );
        return res;
    }


    public ArrayList<String> getRows(){
        String query =  " SELECT  id,name, quizstudent ,(select unique_no from students where id=quizstudent) as unique_no, mark FROM quiz where quizcourse =1";
        SQLiteDatabase db = getWritableDatabase();

        Cursor  cursor = db.rawQuery(query,null);
        ArrayList<String> list= new ArrayList<String>();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String name = cursor.getString(cursor.getColumnIndex("id"));
                list.add(name);
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return list;
    }
    public List<String> getAllCourses(){
        List<String> courses = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + COURSE_TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                courses.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return courses;
    }
    public int getId(String course){
        int id = 0;
        String h =course;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res1 =  db.rawQuery( "select id from courses where name='"+h+"'", null );
        res1.moveToFirst();
        id=res1.getInt(0);
        return id;
    }
    public ArrayList<String> getAllStudents(String course) {
        
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  id  FROM courses where name like " + course;

        Cursor res1 =  db.rawQuery(selectQuery, null );
        
        Cursor res =  db.rawQuery( "SELECT  (select unique_no from students where id=quizstudent)as unique_no ,*FROM quiz where quizstudent=1", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex("unique_no")));
            array_list.add(res.getString(res.getColumnIndex("name")));
            array_list.add(res.getString(res.getColumnIndex("mark")));
            res.moveToNext();
        }
        return array_list;
    }
}