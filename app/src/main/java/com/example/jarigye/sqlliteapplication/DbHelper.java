package com.example.jarigye.sqlliteapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jarigye on 11/17/2017.
 */
class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Students.db";
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
    public static final String STUDENT_MARKS = "studentmarks";
    public static final String QUIZ_MARKS = "quizmarks";


    private HashMap hp;
    private SQLiteDatabase database;
    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table " + COURSE_TABLE_NAME + " ( " + COURSE_COLUMN_ID + " INTEGER PRIMARY KEY," + COURSE_COLUMN_NAME + "VARCHAR);");
        db.execSQL(
                "create table " + STUDENTS_TABLE_NAME + " ( " + STUDENTS_COLUMN_ID + " INTEGER PRIMARY KEY ," + STUDENTS_UNIQUE_NO + "INTEGER);");
        db.execSQL(
                "create table " + QUIZ_TABLE_NAME + " ( " + QUIZ_COLUMN_ID + " INTEGER PRIMARY KEY ," + QUIZ_COLUMN_NAME + "VARCHAR);");
        db.execSQL(
                "create table "
                        + MARKS_TABLE_NAME + "(" + MARKS_COLUMN_ID + " INTEGER PRIMARY KEY ,"
                        + MARKS_COLUMN_NAME + " FLOAT,"
                        + COURSE_MARKS + " INTEGER,"
                        + STUDENT_MARKS + "  INTEGER,"
                        + QUIZ_MARKS + " INTEGER,"
                        +" FOREIGN KEY (" + COURSE_MARKS + ") REFERENCES " + COURSE_TABLE_NAME + "(" + COURSE_COLUMN_ID + ")" +","+
                        " FOREIGN KEY (" + STUDENT_MARKS + ") REFERENCES " + STUDENTS_TABLE_NAME + "(" + STUDENTS_COLUMN_ID + ")" +","+
                        " FOREIGN KEY (" + QUIZ_MARKS + ") REFERENCES " + QUIZ_TABLE_NAME + "(" + QUIZ_COLUMN_ID + "));");


    }


    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
           // db.execSQL("PRAGMA foreign_keys= on;");
            db.setForeignKeyConstraintsEnabled (true);
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS courses");
        db.execSQL("DROP TABLE IF EXISTS students");
        db.execSQL("DROP TABLE IF EXISTS quiz");
        db.execSQL("DROP TABLE IF EXISTS marks");
        onCreate(db);
    }

    public void insertCourse() {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COURSE_COLUMN_NAME, "JAVA");
        contentValues.put(COURSE_COLUMN_NAME, "DATA");
        database.insert(COURSE_TABLE_NAME, null, contentValues);
        database.close();
    }
    public void insertQuiz() {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COURSE_COLUMN_NAME, "Q1");
        contentValues.put(COURSE_COLUMN_NAME, "Q2");
        contentValues.put(COURSE_COLUMN_NAME, "Q3");
        contentValues.put(COURSE_COLUMN_NAME, "Q4");
        contentValues.put(COURSE_COLUMN_NAME, "Q5");
        database.insert(QUIZ_TABLE_NAME, null, contentValues);
        database.close();
    }
    public void insertStudent() {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(STUDENTS_UNIQUE_NO, "1234");
        contentValues.put(STUDENTS_UNIQUE_NO, "1032");
        contentValues.put(STUDENTS_UNIQUE_NO, "1782");
        contentValues.put(STUDENTS_UNIQUE_NO, "2314");
        database.insert(STUDENTS_TABLE_NAME, null, contentValues);
        database.close();
    }
//    public void insertMark(MarksModel mark) {
//        database = this.getReadableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(MARKS_COLUMN_NAME, mark.getMark());
//        contentValues.put(MARKS_COLUMN_NAME, mark.getQuiz());
//        contentValues.put(MARKS_COLUMN_NAME, mark.getCourse());
//        contentValues.put(MARKS_COLUMN_NAME, mark.getStudent());
//        database.insert(MARKS_TABLE_NAME, null, contentValues);
//        database.close();
//    }
    public void insertMark() {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MARKS_COLUMN_NAME, "52");
        contentValues.put(COURSE_MARKS, "1");
        contentValues.put(STUDENT_MARKS, "1");
        contentValues.put(QUIZ_MARKS, "1");
        contentValues.put(MARKS_COLUMN_NAME, "56");
        contentValues.put(COURSE_MARKS, "1");
        contentValues.put(STUDENT_MARKS, "1");
        contentValues.put(QUIZ_MARKS, "2");
        contentValues.put(MARKS_COLUMN_NAME, "66");
        contentValues.put(COURSE_MARKS, "1");
        contentValues.put(STUDENT_MARKS, "1");
        contentValues.put(QUIZ_MARKS, "3");
        contentValues.put(MARKS_COLUMN_NAME, "89");
        contentValues.put(COURSE_MARKS, "1");
        contentValues.put(STUDENT_MARKS, "1");
        contentValues.put(QUIZ_MARKS, "4");
        contentValues.put(MARKS_COLUMN_NAME, "70");
        contentValues.put(COURSE_MARKS, "1");
        contentValues.put(STUDENT_MARKS, "1");
        contentValues.put(QUIZ_MARKS, "5");
        database.insert(MARKS_TABLE_NAME, null, contentValues);
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
        Cursor res =  db.rawQuery( "select * from marks where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, MARKS_TABLE_NAME);
        return numRows;
    }

    public ArrayList<String> getAllStudents() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from students", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(STUDENTS_UNIQUE_NO)));
            res.moveToNext();
        }
        return array_list;
    }
}