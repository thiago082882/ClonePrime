package com.example.primevideoclone;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.primevideoclone.adapter.BannerMoviesPagerAdapter;
import com.example.primevideoclone.adapter.MainRecyclerAdapter;
import com.example.primevideoclone.model.AllCategory;
import com.example.primevideoclone.model.BannerMovies;
import com.example.primevideoclone.retrofit.RetrofitClient;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {


    BannerMoviesPagerAdapter bannerMoviesPagerAdapter;
    TabLayout indicatorTab,categoryTab;
    ViewPager bannerMovieViewPager;
    List<BannerMovies> HomeBannerList;
    List<BannerMovies> tvShowBannerList;
    List<BannerMovies> movieBannerList;
    List<BannerMovies> kidsBannerList;
    Timer slideTimer;


MainRecyclerAdapter mainRecyclerAdapter;
RecyclerView recyclerView;
List<AllCategory>allCategoryList;
    NestedScrollView nestedScrollView;
AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        indicatorTab = findViewById(R.id.tab_indicator);
        categoryTab = findViewById(R.id.tabLayout);
        nestedScrollView= findViewById(R.id.nested_scroll);
        appBarLayout = findViewById(R.id.appbar);


        HomeBannerList=new ArrayList<>();
       tvShowBannerList=new ArrayList<>();
        movieBannerList=new ArrayList<>();
       kidsBannerList=new ArrayList<>();

        getBannerData();

        getAllMoviesData(1);


        categoryTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()){
                    case 1 :
                        setScrollDefaultState();
                        setBannerMoviesPagerAdapter(tvShowBannerList);
                        getAllMoviesData(2);

                        break;
                    case 2 :
                        setScrollDefaultState();
                        setBannerMoviesPagerAdapter(movieBannerList);
                        getAllMoviesData(3);

                        break;
                    case 3 :
                        setScrollDefaultState();
                        setBannerMoviesPagerAdapter(kidsBannerList);
                        getAllMoviesData(4);

                        break;
                    default:
                        setScrollDefaultState();
                        setBannerMoviesPagerAdapter(HomeBannerList);
                        getAllMoviesData(1);


                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        allCategoryList = new ArrayList<>();




    }



    private void setBannerMoviesPagerAdapter(List<BannerMovies>bannerMoviesList){

       bannerMovieViewPager = findViewById(R.id.banner_viewPager);
       bannerMoviesPagerAdapter = new BannerMoviesPagerAdapter(this,bannerMoviesList);
       bannerMovieViewPager.setAdapter(bannerMoviesPagerAdapter);
  indicatorTab.setupWithViewPager(bannerMovieViewPager);

// Passar a imagem a cada 2 segundo
        slideTimer = new Timer();
        slideTimer.scheduleAtFixedRate(new AutoSlider(),4000,6000);
        indicatorTab.setupWithViewPager(bannerMovieViewPager,true);

    }
    class AutoSlider extends TimerTask {
        @Override
        public  void run(){
            MainActivity.this.runOnUiThread(() ->{
                if(bannerMovieViewPager.getCurrentItem() <HomeBannerList.size() -1){


                    bannerMovieViewPager.setCurrentItem(bannerMovieViewPager.getCurrentItem()+1);
                }else{

                    bannerMovieViewPager.setCurrentItem(0);
                }
                    }

            );
        }
    }
    public  void setMainRecycler(List<AllCategory>allCategoryList){
        recyclerView = findViewById(R.id.main_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
         recyclerView.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(this,allCategoryList);
        recyclerView.setAdapter(mainRecyclerAdapter);
    }
private void setScrollDefaultState(){
        nestedScrollView.fullScroll(View.FOCUS_UP);
        nestedScrollView.scrollTo(0,0);
        appBarLayout.setExpanded(true);
}


private void getBannerData(){
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    compositeDisposable.add(RetrofitClient.getRetrofitClient().getAllBanners()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableObserver<List<BannerMovies>>() {
                @Override
                public void onNext(List<BannerMovies> bannerMovies) {
//                    Toast.makeText(getApplicationContext(), "hello"+bannerMovies, Toast.LENGTH_SHORT).show();
//                    Log.d("bannerData",""+bannerMovies);
                    for(int i=0;i<bannerMovies.size();i++){

                        if(bannerMovies.get(i).getBannerCategoryid().toString().equals("1")){
                            HomeBannerList.add(bannerMovies.get(i));

                        }else if(bannerMovies.get(i).getBannerCategoryid().toString().equals("2")){
                            tvShowBannerList.add(bannerMovies.get(i));
                        }else if(bannerMovies.get(i).getBannerCategoryid().toString().equals("3")){
                           movieBannerList.add(bannerMovies.get(i));
                        }else if(bannerMovies.get(i).getBannerCategoryid().toString().equals("4")){
                           kidsBannerList.add(bannerMovies.get(i));
                        }else{

                        }



                    }

                }

                @Override
                public void onError(Throwable e) {
                    Log.d("bannerData",""+e);
                }

                @Override
                public void onComplete() {

                    setBannerMoviesPagerAdapter(HomeBannerList);
                }
            })

    );
}

    private void getAllMoviesData(int categoryid){
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(RetrofitClient.getRetrofitClient().getAllCategoryMovie(categoryid)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<List<AllCategory>>() {
                            @Override
                            public void onNext(List<AllCategory>allCategoryList) {


                                setMainRecycler(allCategoryList);

                                }



                            @Override
                            public void onError(Throwable e) {
                                Log.d("bannerData",""+e);
                            }

                            @Override
                            public void onComplete() {


                            }
                        })

        );
    }


}