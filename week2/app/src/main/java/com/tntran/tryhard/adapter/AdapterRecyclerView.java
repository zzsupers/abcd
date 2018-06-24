package com.tntran.tryhard.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tntran.tryhard.R;
import com.tntran.tryhard.model.Doc;
import com.tntran.tryhard.utils.RetrofitUtil;

import java.util.List;

/**
 * Created by TTN on 6/20/2018.
 */

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.ViewHolder> {



    private List<Doc> data;

    private Context context;
    private ItemClickListener itemClickListener;



    /**

     * Constructs a new MovieRecyclerAdapter with a context

     *

     * @param ctx

     */

    public AdapterRecyclerView(Context ctx) {
        this.context = ctx;

    }

    public void setData(List<Doc> data){
        this.data = data;
        //data.clear();
        //data.addAll( doc );
        notifyDataSetChanged();
    }
    public void clearData(){
        //this.data = data;
        data.clear();
        //data.addAll( doc );
        notifyDataSetChanged();
    }


    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View itemView = layoutInflater.inflate(R.layout.row_item, parent, false);

        return new ViewHolder(itemView);

    }



    @Override

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            Doc doc = data.get(position);
            if (doc!=null){
                holder.main.setText( doc.getHeadline().getMain());
                if (doc.getMultimedia().size()!= 0){
                    Glide.with(context).load( RetrofitUtil.BASE_URL_IMAGE + doc.getMultimedia().get(0).getUrl())

                    .into(holder.poster);
                }
                else {
                    Glide.with(context).load("https://hinhanhdephd.com/wp-content/uploads/2017/06/anh-girl-xinh-de-thuong-nhat-nam-2017-10.jpg")

                    .into(holder.poster);
                }
            }



    }



    @Override

    public int getItemCount() {

        return data.size();

    }





    /**

     * List row members

     */

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView main;


        public ImageView poster;



        public ViewHolder(View itemView) {

            super(itemView);
            main =itemView.findViewById( R.id.main );
            poster = itemView.findViewById( R.id.thumbnail );
            itemView.setOnClickListener( this );

        }


        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick( data.get( getAdapterPosition() ));
        }
    }
    public void setItemClickListener(ItemClickListener itemClickListener) {

        this.itemClickListener = itemClickListener;

    }
    public interface ItemClickListener {

        void onItemClick(Doc doc);

    }



}
