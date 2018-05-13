package com.example.kevin.myapplication.Activity;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.kevin.myapplication.R;
import com.example.kevin.myapplication.base.BaseActivity;
import com.example.kevin.myapplication.fragments.FourthFragment;
import com.example.kevin.myapplication.fragments.SecondFragment;
import com.example.kevin.myapplication.fragments.StudyFragment;
import com.example.kevin.myapplication.fragments.ThirdFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(this,"add_item button clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remo_item:
                Toast.makeText(this,"remo_item button clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.more_item:
                Toast.makeText(this,"more_item button clicked",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, ViewActivity.class));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onInitView() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();


        ViewPager viewPager = (ViewPager)findViewById(R.id.viewPage);
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onInitData() {

    }

    private class MainAdapter extends FragmentPagerAdapter {

        List<Fragment> fragments = new ArrayList<>();

        public MainAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            fragments.add(new StudyFragment());
            fragments.add(new SecondFragment());
            fragments.add(new ThirdFragment());
            fragments.add(new FourthFragment());
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
