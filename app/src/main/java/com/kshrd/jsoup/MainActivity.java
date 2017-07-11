package com.kshrd.jsoup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.kshrd.jsoup.listener.Callback;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Callback, AdapterView.OnItemClickListener {

    private ProgressDialog dialog;
    private ListView listView;
    private List<Movie> movieList;
    private ArrayAdapter<Movie> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1,  movieList
        );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");

        new CinemaParser(this).execute();

    }

    @Override
    public void onPreExecute() {
        dialog.show();
    }

    @Override
    public void onPostExecute(List<Movie> movieList) {
        this.movieList.addAll(movieList);
        adapter.notifyDataSetChanged();
        dialog.dismiss();
    }

    @Override
    public void onError() {
        dialog.dismiss();
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(movieList.get(i).getLink()));
        startActivity(intent);
    }
}
