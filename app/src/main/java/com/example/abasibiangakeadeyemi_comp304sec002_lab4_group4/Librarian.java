package com.example.abasibiangakeadeyemi_comp304sec002_lab4_group4;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Librarian {
    @PrimaryKey(autoGenerate = true)
    private int librarianId;
    private String firstName;
    private String lastName;
    private String password;

    public Librarian(int librarianId, String firstName, String lastName, String password) {
        this.librarianId = librarianId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public int getLibrarianId() {
        return librarianId;
    }
    public void setLibrarianId(int id) {
        this.librarianId = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String fName) {
        this.firstName = fName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lName) {
        this.lastName = lName;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String pass) {
        this.password = pass;
    }
}
