package com.canvasseries;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.canvasseries.searchview.MySearchView;
import com.canvasseries.searchview.ViewController;

public class SearchActivity extends AppCompatActivity {

    private MySearchView vMySearchView;
    private ViewController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        vMySearchView = findViewById(R.id.mySearchView);

        mController = new ViewController();
        mController.setSearchView(vMySearchView);
    }

    public void open(View view) {
        mController.open();
    }

    public void close(View view) {
        mController.close();
    }
}
