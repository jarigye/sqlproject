package com.example.jarigye.sqlliteapplication;

/**
 * Created by jarigye on 11/17/2017.
 */

public class StudentsModel {
        private String ID, firstName, lastName;
        public String getID() {
            return ID;
        }
        public void setID(String ID) {
            this.ID = ID;
        }
        public String getFirstName() {
            return firstName;
        }
        public String getLastName() {
            return lastName;
        }
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

}
