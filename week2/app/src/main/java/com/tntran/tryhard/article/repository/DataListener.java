package com.tntran.tryhard.article.repository;

/**
 * Created by TTN on 6/21/2018.
 */

import com.tntran.tryhard.model.Doc;

import java.util.List;



public interface DataListener {

    void onResponse(List<Doc> docList);



    void onError(String error);

}
