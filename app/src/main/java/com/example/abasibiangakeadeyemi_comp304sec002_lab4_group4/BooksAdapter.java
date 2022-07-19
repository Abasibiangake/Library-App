package com.example.abasibiangakeadeyemi_comp304sec002_lab4_group4;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookHolder> {
    private List<Books> books=new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.book_items,parent,false);
        return new BookHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {
        Books currentBook=books.get(position);
        holder.bookAuthorTv.setText(currentBook.getAuthorName());
        holder.bookNameTv.setText(currentBook.getBookName());
        holder.bookQuantityTv.setText(String.valueOf(currentBook.getQuantity()));
        holder.bookCategoryTv.setText(currentBook.getCategory());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
    public void setBooks(List<Books> books){
        this.books=books;
        notifyDataSetChanged();
    }
    public Books getBookAt(int position){
        return books.get(position);
    }

    class BookHolder extends RecyclerView.ViewHolder{
        private TextView bookAuthorTv;
    private TextView bookNameTv;
    private TextView bookQuantityTv;
    private Button borrowBookBtn;
    private TextView bookCategoryTv;
        public BookHolder(View itemView) {
            super(itemView);

            bookAuthorTv=itemView.findViewById(R.id.tv_book_author);
        bookNameTv=itemView.findViewById(R.id.tv_book_name);
        bookQuantityTv=itemView.findViewById(R.id.tv_book_quantity);
        borrowBookBtn=itemView.findViewById(R.id.btn_borrow_book);
        bookCategoryTv=itemView.findViewById(R.id.tv_book_category);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=getAdapterPosition();
                if(listener!=null && position!=RecyclerView.NO_POSITION){
                    listener.onItemClick(books.get(position));
                }

            }
        });

    }
    }
public interface OnItemClickListener{
        void onItemClick(Books book);
}
public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;

}
}
