package com.kshrd.jsoup;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kshrd.jsoup.listener.MyClickDetail;
import com.kshrd.jsoup.listener.MyclickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by LIER on 7/10/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    private Context context;
    List<Movie> movieList;
    MyclickListener myclickListener;
    MyClickDetail myClickDetail;


    public MovieAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_recyclerview_item,parent,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie movie=movieList.get(position);
        holder.title.setText(movie.getTitle().toString());
        if(movie.getLinktrailer().equals("")){
            holder.trailer.setText("");
//            holder.trailer.setEnabled(false);
        }else if(!movie.getLinktrailer().equals("")){
            holder.trailer.setText("Play Trailer");
        }
        if(movie.getTitle().length()>25){
            holder.title.setTextSize(15);

        }else {
            holder.title.setTextSize(20);

        }
        holder.date.setText(movie.getDate());
        Log.e("oooooadapter",movie.getPosterUrl());
              /* Bitmap bitImg=getBitmapFromURL(movie.getPosterUrl());
        holder.imageView.setImageBitmap(bitImg);*/
        Picasso.with(context)
                .load("http://"+movie.getPosterUrl())
                .noFade()
                .placeholder(R.drawable.ic_android_black_24dp)
                .error(R.drawable.ic_android_black_24dp)
                .into(holder.imageView);



    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
//        super.onAttachedToRecyclerView(recyclerView);
        myclickListener= (MyclickListener) recyclerView.getContext();
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        TextView title;
        TextView date;
        TextView trailer;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.ivThumbnail);
            title= (TextView) itemView.findViewById(R.id.tvTitle);
            date= (TextView) itemView.findViewById(R.id.date);
            trailer= (TextView) itemView.findViewById(R.id.trailer);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            TextView textView= (TextView) v.findViewById(R.id.trailer);
            ImageView imageView= (ImageView) v.findViewById(R.id.ivThumbnail);

            myclickListener.onClicked(getAdapterPosition(), v);
            myclickListener.onDetailclick(getAdapterPosition(), v);
//            myClickDetail.onClickDetail(getAdapterPosition(),v);
            Log.e("oooooclick","work");

        }
    }
}
