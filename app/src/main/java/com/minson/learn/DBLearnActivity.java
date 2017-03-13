package com.minson.learn;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class DBLearnActivity extends AppCompatActivity implements View.OnClickListener{

    private Button createDatabase;
    private Button queryButton;
    private Button addButton;
    private EditText bookName;
    private EditText bookAuthor;
    private EditText bookPrice;
    private EditText bookPages;
    private EditText bookPress;
    private EditText showBooks;
    private List<Book> books = null;
    private StringBuilder content = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dblearn_layout);

        //创建数据库
        createDatabase = (Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(this);

        //添加数据
        addButton = (Button) findViewById(R.id.addBook);
        bookName = (EditText) findViewById(R.id.bookName);
        bookAuthor = (EditText) findViewById(R.id.bookAuthor);
        bookPrice = (EditText) findViewById(R.id.bookPrice);
        bookPages = (EditText) findViewById(R.id.bookPages);
        bookPress = (EditText) findViewById(R.id.bookPress);

        addButton.setOnClickListener(this);

        //显示data
        queryButton = (Button) findViewById(R.id.queryData);
        showBooks = (EditText) findViewById(R.id.showBooks);
        queryButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.create_database:
                Connector.getDatabase();
                Log.d("DBLearnActivity", "create Book success");
                break;
            case R.id.addBook:
                String bn = bookName.getText().toString();
                String ba = bookAuthor.getText().toString();
                String bp = bookPrice.getText().toString();
                String bps = bookPages.getText().toString();
                String ppr = bookPress.getText().toString();
                if(bn.isEmpty() || bp.isEmpty() || ba.isEmpty() || bps.isEmpty() || ppr.isEmpty())
                {
                    Toast.makeText(DBLearnActivity.this, "please fill the data correctly", Toast.LENGTH_SHORT).show();
                } else {
                    Book book = new Book();
                    book.setName(bn);
                    book.setAuthor(ba);
                    book.setPages(Integer.parseInt(bps));
                    book.setPrice(Double.parseDouble(bp));
                    book.setPress(ppr);
                    book.save();
                    Log.d("DBLearnActivity", "book save success");
                }
                break;
            case R.id.queryData:
                books = DataSupport.findAll(Book.class);
                for(Book b : books)
                {
                    Log.d("DBLearnActivity", "BookName is " + b.getName());
                    content.append(b.getName() + "--");
                }
                showBooks.setText(content.toString());
                break;
            default:
                break;
        }
    }
}
