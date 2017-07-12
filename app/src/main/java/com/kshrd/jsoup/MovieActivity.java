package com.kshrd.jsoup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kshrd.jsoup.listener.Callback;
import com.kshrd.jsoup.listener.MyclickListener;

import java.util.ArrayList;
import java.util.List;

public class MovieActivity extends AppCompatActivity implements Callback,MyclickListener {
    private ProgressDialog dialog;
    private ListView listView;
    private List<Movie> movieList;
//    private ArrayAdapter<Movie> adapter;
    RecyclerView recyclerView;
    MovieAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        movieList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.rvmovies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter=new MovieAdapter(this,movieList);
        recyclerView.setAdapter(adapter);

        /*listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1,  movieList
        );
        listView.setAdapter(adapter);*/

//        listView.setOnItemClickListener((AdapterView.OnItemClickListener) this);

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
    public void onClicked(int position, View v) {
        final Movie movie=movieList.get(position);
        Log.e("ooo1",movie.getLinktrailer());
        TextView trailer= (TextView) v.findViewById(R.id.trailer);
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(movie.getLinktrailer()));
        startActivity(intent);
        trailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }



    @Override
    public void onDetailclick(int position, View v) {
        final Movie movie=movieList.get(position);
        ImageView imageView= (ImageView) v.findViewById(R.id.ivThumbnail);
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(movie.getLink()));
        startActivity(intent);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
