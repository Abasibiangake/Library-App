package com.example.abasibiangakeadeyemi_comp304sec002_lab4_group4;



import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "booksTable")
public class Books {
    @PrimaryKey(autoGenerate = true)
    private int bookId;
    private String bookName;
    private String authorName;
    private String bookDescription;
    private String category;
    private int quantity;

    public Books(String bookName, String authorName, String bookDescription, String category, int quantity) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookDescription = bookDescription;
        this.category = category;
        this.quantity = quantity;
    }



    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

