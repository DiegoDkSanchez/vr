package com.example.usuario.guia6;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    public static final String END_POINT = "http://34.211.243.185:81/api/v1/panoramaimages";
    public static final String IMAGE_BASE_URL = "http://34.211.243.185:81";


    @BindView(R.id.content_refresher)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.panorama_recycler_view)
    RecyclerView panoramaRecyclerView;

    List<Image> images;
    final Gson gson = new Gson();

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        queue = Volley.newRequestQueue(this);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                networkRequest();
            }
        });
        refreshLayout.setRefreshing(true);
        networkRequest();



    }

    private void networkRequest() {
        Log.d("UDBDEBUG","Making Request");

        JsonArrayRequest jsonArrayRequest = new  JsonArrayRequest
                (Request.Method.GET, END_POINT, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        images = gson.fromJson(response.toString(), new TypeToken<List<Image>>(){}.getType());
                        loadData();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        panoramaRecyclerView.setLayoutManager(new GridLayoutManager(this,3));

        queue.add(jsonArrayRequest);
    }


    private void loadData() {
        Log.d("UDBDEBUG","Loading data" + images.size());
        PanoramaImageAdapter panoramaImageRecyclerAdapter = new PanoramaImageAdapter(this, images);
        panoramaRecyclerView.setAdapter(panoramaImageRecyclerAdapter);
        refreshLayout.setRefreshing(false);
    }
}


