package com.example.usuario.guia6;

import java.io.Serializable;

/**
 * Created by usuario on 3/3/18.
 */

public class Image implements Serializable {

    private String id;
    private String name;
    private String url;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
