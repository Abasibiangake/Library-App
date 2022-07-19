package com.example.abasibiangakeadeyemi_comp304sec002_lab4_group4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddEditbookActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "abasiadeyemi.fiction.library.EXTRA_ID";
    public static final String EXTRA_FICTION_TITLE = "abasiadeyemi.fiction.library.EXTRA_TITLE";
    public static final String EXTRA_FICTION_DESCRIPTION = "abasiadeyemi.fiction.library.EXTRA_DESCRIPTION";
    public static final String EXTRA_FICTION_CATEGORY = "abasiadeyemi.fiction.library.EXTRA_CATEGORY";
    public static final String EXTRA_FICTION_AUTHOR = "abasiadeyemi.fiction.library.EXTRA_AUTHOR";
    public static final String EXTRA_FICTION_QUANTITY = "abasiadeyemi.fiction.library.EXTRA_QUANTITY";
    private EditText editTextTitle, editTextCategory, editTextAuthor, editTextDescription, editTextQuantity;
    private Button saveBookButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_editbook);
        editTextTitle = findViewById(R.id.book_title);
        editTextCategory = findViewById(R.id.book_category);
        editTextAuthor = findViewById(R.id.book_author);
        editTextDescription = findViewById(R.id.book_description);
        editTextQuantity = findViewById(R.id.book_quantity);
        saveBookButton = findViewById(R.id.add_book_button);
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Book");
            editTextTitle.setText(intent.getStringExtra(EXTRA_FICTION_TITLE));
            editTextQuantity.setText(String.valueOf(intent.getIntExtra(EXTRA_FICTION_QUANTITY, 1)));
            //setText(String.valueOf(intent.getStringExtra(EXTRA_FICTION_QUANTITY)));
            editTextCategory.setText(intent.getStringExtra(EXTRA_FICTION_CATEGORY));
            editTextAuthor.setText(intent.getStringExtra(EXTRA_FICTION_AUTHOR));
            editTextDescription.setText(intent.getStringExtra(EXTRA_FICTION_DESCRIPTION));
        }
    }

//    public void intentHelper(String categoryName, String category,  String author, int quantity,
//                             String description, String title
//                             ){
//        if (category.equals(categoryName)) {
//
//            Intent intent = new Intent();
//            intent.putExtra(EXTRA_FICTION_AUTHOR, author);
//            intent.putExtra(EXTRA_FICTION_QUANTITY, quantity);
//            intent.putExtra(EXTRA_FICTION_CATEGORY, category);
//            intent.putExtra(EXTRA_FICTION_DESCRIPTION, description);
//            intent.putExtra(EXTRA_FICTION_TITLE, title);
//            int id = getIntent().getIntExtra(EXTRA_ID, -1);
//            if (id != -1) {
//                intent.putExtra(EXTRA_ID, id);
//            }
//            setResult(RESULT_OK, intent);
//            finish();
//        }
//    }

    public void saveBook(View view) {
        String title = editTextTitle.getText().toString();
        String category = editTextCategory.getText().toString();

        String author = editTextAuthor.getText().toString();
        String description = editTextDescription.getText().toString();
        int quantity = Integer.valueOf(editTextQuantity.getText().toString());
        if (title.trim().isEmpty() || category.trim().isEmpty() || author.trim().isEmpty() || description.trim().isEmpty() || quantity < 1) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }
            Intent intent = new Intent();
            intent.putExtra(EXTRA_FICTION_AUTHOR, author);
            intent.putExtra(EXTRA_FICTION_QUANTITY, quantity);
            intent.putExtra(EXTRA_FICTION_CATEGORY, category);
            intent.putExtra(EXTRA_FICTION_DESCRIPTION, description);
            intent.putExtra(EXTRA_FICTION_TITLE, title);
            int id = getIntent().getIntExtra(EXTRA_ID, -1);
            if (id != -1) {
                intent.putExtra(EXTRA_ID, id);
            }
            setResult(RESULT_OK, intent);
            finish();
    }
}
