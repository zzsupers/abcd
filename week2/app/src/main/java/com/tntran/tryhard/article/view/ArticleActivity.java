package com.tntran.tryhard.article.view;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tntran.tryhard.R;
import com.tntran.tryhard.adapter.AdapterRecyclerView;
import com.tntran.tryhard.article.presenter.DocPresenter;
import com.tntran.tryhard.article.presenter.ListPresenter;
import com.tntran.tryhard.article.repository.DocRepository;
import com.tntran.tryhard.article.repository.Repository;
import com.tntran.tryhard.dialog.DialogActivity;
import com.tntran.tryhard.model.Doc;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ArticleActivity extends AppCompatActivity implements ListDocView {

    AdapterRecyclerView adapter;
    List<Doc> docList ;
    DocPresenter presenter;
    ProgressBar progressBar;
    int requestCode = 100;
    EndlessScrollListener endlessScrollListener;

    @BindView( R.id.toolbar ) Toolbar toolbar;
    @BindView( R.id.my_recycler_view ) RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        ButterKnife.bind( this );
       // Toolbar toolbar = findViewById( R.id.toolbar );
        //recyclerView = findViewById( R.id.my_recycler_view );
        //swipeRefreshLayout = findViewById( R.id.swipeRefresh );

        setSupportActionBar( toolbar );
        setUpListView();
        Repository repository = new DocRepository();
        presenter = new ListPresenter( this,repository );
        presenter.getDocs();


    }


    @Override
    public void showListMovies(List<Doc> docs) {
        adapter.setData(docs);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu_main, menu );
        return super.onCreateOptionsMenu( menu );
    }
    public void clickCompose(MenuItem mi){
        Toast.makeText( this,"abcxyz",Toast.LENGTH_LONG ).show();
    }
    public  void clickProfile(MenuItem mi){
        Intent intent = new Intent( ArticleActivity.this, DialogActivity.class );
        startActivity(intent);
    }

    private void setUpListView() {
         docList = new ArrayList<>();
         RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( this );
         recyclerView.setLayoutManager( layoutManager );
         adapter = new AdapterRecyclerView( ArticleActivity.this );
         adapter.setData( docList );
         this.recyclerView.setAdapter( adapter );
         this.recyclerView.setLayoutManager( new StaggeredGridLayoutManager( 3, StaggeredGridLayoutManager.VERTICAL ) );
        adapter.setItemClickListener( new AdapterRecyclerView.ItemClickListener() {
            @Override
            public void onItemClick(Doc doc) {
                // Use a CustomTabsIntent.Builder to configure CustomTabsIntent.
                //String url = "https://www.codepath.com/";
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                //builder.setToolbarColor( ContextCompat.getColor(ArticleActivity.this, R.color.blue));
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, doc.getWebUrl());
                PendingIntent pendingIntent = PendingIntent.getActivity(ArticleActivity.this,
                        requestCode,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.home);
                builder.setActionButton(bitmap, "Share Link", pendingIntent, true);

                CustomTabsIntent customTabsIntent = builder.build();

                // and launch the desired Url with CustomTabsIntent.launchUrl()

                customTabsIntent.launchUrl(ArticleActivity.this, Uri.parse(doc.getWebUrl()));
            }
        } );
//        endlessScrollListener = new EndlessScrollListener(layoutManager) {
//
//            @Override
//
//            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
//
//                presenter.
//
//            }
//
//        };
//
//        recyclerView.addOnScrollListener( endlessScrollListener );
//
//    }
    }

}


