package com.example.actividad4_parte01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private String str="Inicial";
private ShareActionProvider share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("nuevo titulo");
        getSupportActionBar().setIcon(R.drawable.ic_baseline_apps_24);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(onSearch());

        MenuItem shareItem = menu.findItem(R.id.action_share);
        share = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        share.setShareIntent(configuraIntent());
        return true;
    }
    private Intent configuraIntent(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/*");
        intent.putExtra(Intent.EXTRA_TEXT,str);
        return intent;
    }
    private SearchView.OnQueryTextListener onSearch(){
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                str= s;
                share.setShareIntent(configuraIntent());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        };
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      if (item.getItemId() == R.id.action_search) {
          Toast.makeText(getApplicationContext(), "Menu Search", Toast.LENGTH_SHORT).show();
          return true;
      } else if (item.getItemId() == R.id.action_refresh) {
          Toast.makeText(getApplicationContext(), "Menu Refresh", Toast.LENGTH_SHORT).show();
          return true;
      } else if (item.getItemId() == R.id.action_settings) {
          Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
          startActivity(intent);
          return true;
      }
      return super.onOptionsItemSelected(item);
  }
  }