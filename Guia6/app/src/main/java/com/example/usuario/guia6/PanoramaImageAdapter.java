package com.example.usuario.guia6;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by usuario on 3/3/18.
 */

public class PanoramaImageAdapter extends RecyclerView.Adapter<PanoramaImageAdapter.ViewHolder>{

    Context context;
    List<Image> panoramicImageList;

    public PanoramaImageAdapter(Context context, List<Image> panoramicImageList){
        this.context = context;
        this.panoramicImageList = panoramicImageList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.item_recycler_panoramic_image, parent, false);
        return new ViewHolder(item);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Image panoramicImage = this.panoramicImageList.get(position);
        Picasso.with(context).load(MainActivity.IMAGE_BASE_URL + panoramicImage.getUrl()).into(holder.panoramicpreview);
        holder.panoramicpreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, PanoramicActivity.class);

                intent.putExtra("serializedimage", panoramicImage);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return panoramicImageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.panoramicpreview)
        ImageView panoramicpreview;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

