package com.tntran.tryhard.article.presenter;

import com.tntran.tryhard.article.repository.DataListener;
import com.tntran.tryhard.article.view.ListDocView;
import com.tntran.tryhard.article.repository.Repository;
import com.tntran.tryhard.model.Doc;

import java.util.List;

/**
 * Created by TTN on 6/21/2018.
 */

public class ListPresenter implements DocPresenter,DataListener {
    private ListDocView lView;
    private Repository repository;
    public  ListPresenter(ListDocView lView, Repository repository){
        this.lView= lView;
        this.repository = repository;
    }
    @Override
    public void getDocs() {
            repository.callAPI( this );


    }

    @Override
    public void onResponse(List<Doc> docs) {
        lView.showListMovies( docs );

    }

    @Override
    public void onError(String error) {

    }
//    @Override
//
//    public void callPageAPI(int page) {
//
//
//        repository.callPageAPI( this,page );
//
//    }


}
