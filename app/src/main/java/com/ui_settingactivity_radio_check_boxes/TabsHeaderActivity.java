package com.ui_settingactivity_radio_check_boxes;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("ALL")
public class TabsHeaderActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggleRestroDistances;
    ImageView khImg, htabHeaderImg;
    TextView khText;
    boolean isUserFirstTime;
    public static final String PREF_USER_FIRST_TIME = "user_first_time";
    protected Application appUncaughtException;
   

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appUncaughtException= getApplication();
        isUserFirstTime = Boolean.valueOf(Utils.readSharedSetting(TabsHeaderActivity.this, PREF_USER_FIRST_TIME, "true"));

        Intent introIntent = new Intent(TabsHeaderActivity.this, PagerActivity.class);
        introIntent.putExtra(PREF_USER_FIRST_TIME, isUserFirstTime);

       // if (isUserFirstTime)
        //    startActivity(introIntent);


        setContentView(R.layout.activity_tabs_header);
        Bundle extras=new Bundle();
        extras.putString("max_ad_content_rating","MA");
      







        final Toolbar toolbar = (Toolbar) findViewById(R.id.htab_toolbar);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlout_restro_latilongi);
        navigationView=(NavigationView)findViewById(R.id.navigation_view);
        khImg=(ImageView)findViewById(R.id.imgicon);
        htabHeaderImg=(ImageView)findViewById(R.id.htab_header);
        khText=(TextView)findViewById(R.id.texthub);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.htab_viewpager);
        setupViewPager(viewPager);
            viewPager.setPageTransformer(true,new ZoomOutPageTransformer());
        


        TabLayout tabLayout = (TabLayout) findViewById(R.id.htab_tabs);
        tabLayout.setupWithViewPager(viewPager);

        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.htab_collapse_toolbar);
        collapsingToolbarLayout.setTitleEnabled(false);

        actionBarDrawerToggleRestroDistances=new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.open,R.string.close);
        actionBarDrawerToggleRestroDistances.syncState(); // it will display hand barger manu , without wich it was not displaye
        // only back arrow will display;
        drawerLayout.setDrawerListener(actionBarDrawerToggleRestroDistances);

      //  ImageView header = (ImageView) findViewById(R.id.header);
        if (Utils.getItemAnimationBool(getApplicationContext())) {

            new AnimationXml(getApplicationContext()).animateCollapsingview(htabHeaderImg);

        }





        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.header);

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @SuppressWarnings("ResourceType")
            @Override
            public void onGenerated(Palette palette) {

                int vibrantColor = palette.getVibrantColor(R.color.primary_500);
                int vibrantDarkColor = palette.getDarkVibrantColor(R.color.primary_700);
                collapsingToolbarLayout.setContentScrimColor(vibrantColor);
                collapsingToolbarLayout.setStatusBarScrimColor(vibrantDarkColor);
            }
        });
        khImg.setImageResource(R.drawable.histry4);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());

                switch (tab.getPosition()) {
                    case 0:
                        khImg.setImageResource(0);
                        htabHeaderImg.setImageResource(R.drawable.kash1);
                        khImg.setImageResource(0);
                        khImg.setImageResource(R.drawable.histry1);
                        khText.setText("History of Kashmir Conflict");
                        //showAds();
                        break;
                    case 1:
                        khImg.setImageResource(0);
                        htabHeaderImg.setImageResource(R.drawable.kash2);
                        khImg.setImageResource(0);
                        khImg.setImageResource(R.drawable.histry2);
                        khText.setText("Wars on Kashmir:Courtesy Telegraph");
                        //showAds();
                        break;
                    case 2:
                        khImg.setImageResource(0);
                        htabHeaderImg.setImageResource(R.drawable.kash4);
                        khImg.setImageResource(0);
                        khImg.setImageResource(R.drawable.histry3);
                        khText.setText("India-Pakistan:Problems");
                        //showAds();
                        break;
                    case 3:
                        khImg.setImageResource(0);
                        htabHeaderImg.setImageResource(R.drawable.kash2);
                        khImg.setImageResource(0);
                        khImg.setImageResource(R.drawable.histry4);
                        khText.setText("Nuclear Tests:India-Pakistan");
                        break;
                    case 4:
                        showToast("One");
                        khImg.setImageResource(0);
                        htabHeaderImg.setImageResource(R.drawable.kash1);
                        khImg.setImageResource(0);
                        khImg.setImageResource(R.drawable.histry1);
                        khText.setText("Kashmir Pre-1947:L. N. Dhar");
                        //showAds();
                        break;
                    case 5:
                        //  showToast("Two");
                        htabHeaderImg.setImageResource(0);
                        htabHeaderImg.setImageResource(R.drawable.kash4);
                        khImg.setImageResource(0);
                        khImg.setImageResource(R.drawable.histry2);
                        khText.setText("Kashmir Pre-1947");

//showAds();
                        break;

                    case 6:
                        showToast("Two");
                        htabHeaderImg.setImageResource(0);
                        htabHeaderImg.setImageResource(R.drawable.kash2);
                        khImg.setImageResource(0);
                        khImg.setImageResource(R.drawable.histry3);
                        khText.setText(" Kashmir Pre-1947");
                        //showAds();
                        break;
                    case 7:
                        htabHeaderImg.setImageResource(0);
                        htabHeaderImg.setImageResource(R.drawable.kash1);
                        khImg.setImageResource(0);
                        khImg.setImageResource(R.drawable.histry4);
                        khText.setText("Kashmir Pre-1947");

//showAds();

                        break;
                    case 8:
                        htabHeaderImg.setImageResource(0);
                        htabHeaderImg.setImageResource(R.drawable.kash2);
                        khImg.setImageResource(R.drawable.histry1);
                        khText.setText("Kashmir Pre-1947");
//showAds();
                        break;
                    case 9:
                        htabHeaderImg.setImageResource(0);
                        htabHeaderImg.setImageResource(R.drawable.kash4);
                        khImg.setImageResource(0);
                        khImg.setImageResource(R.drawable.histry2);
                        khText.setText("Kashmir Pre-1947.");

//showAds();

                        break;
                    case 10:
                        htabHeaderImg.setImageResource(0);
                        htabHeaderImg.setImageResource(R.drawable.kash1);
                        khImg.setImageResource(0);
                        khImg.setImageResource(R.drawable.histry3);
                        khText.setText("Kashmir Pre-1947");

//showAds();

                        break;
                    case 11:
                        htabHeaderImg.setImageResource(0);
                        htabHeaderImg.setImageResource(R.drawable.kash2);
                        khImg.setImageResource(0);
                        khImg.setImageResource(R.drawable.histry4);
                        khText.setText("Kashmir Pre-1947");
//showAds();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawers();
//showAds();
                switch (item.getItemId()) {
                    case R.id.feedback:
                        Intent intentfeed=new Intent(Intent.ACTION_VIEW);
                        intentfeed.setData(Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName()));
//showAds();
                        startActivity(intentfeed);
                        break;
                    case R.id.contactus:
                        Intent intentcontact=new Intent(getApplicationContext(),SendEmailActivity.class);
                        intentcontact.putExtra("mail-contact","contact");
                        startActivity(intentcontact);
                        break;

                    case R.id.aboutus:
                        View view=LayoutInflater.from(getApplicationContext()).inflate(R.layout.about,null);
                        AlertDialog.Builder builder=new AlertDialog.Builder(TabsHeaderActivity.this);
                       Animation animation=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.ordersended);
                        view.setAnimation(animation);
                        animation.start();
                       builder.setView(view);

                        //builder.setPositiveButton("OK", null);

                        Dialog dialog=builder.create();

                        dialog.show();
                        break;

                }
                return true;
            }
        });
    }




    void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
int ripple_material_light_color=getResources().getColor(R.color.ripple_material_light);
        adapter.addFrag(new Frag0(ripple_material_light_color),"brief history");
        adapter.addFrag(new Frag01(ripple_material_light_color),"Wars");
        adapter.addFrag(new Frag02(ripple_material_light_color),"problems");
        adapter.addFrag(new Frag03(ripple_material_light_color),"nuclear powers");
        adapter.addFrag(new HrInterviewFragment(ripple_material_light_color), "before 1947");
        adapter.addFrag(new EveryDayScienceFragment(ripple_material_light_color), "Pre-Historic Times");

        adapter.addFrag(new IssbNotesFragment(ripple_material_light_color), "Hindu Period");
        adapter.addFrag(new ComputerScienceFragment(ripple_material_light_color), "An Assessment of Hindu Rule");

        adapter.addFrag(new GeneralKnowledgeFragment(ripple_material_light_color), "Muslim Period");
        adapter.addFrag(new DummyFragment(ripple_material_light_color), "Moghul Period");
        adapter.addFrag(new AndroidInterviewFrag(ripple_material_light_color), "Dogra.Period : 1846 1957 ");

        adapter.addFrag(new StylesAndXmlFrag(ripple_material_light_color),"Sikh Rule: 1819-46");

        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_settings:
                Intent intent=new Intent(getApplicationContext(),SettingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(R.anim.move_left,R.anim.rotate);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    public static class Frag0 extends Fragment {
        int color;
        SimpleRecyclerAdapter adapter;
        public Frag0(int color) {
           this.color=color;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view=inflater.inflate(R.layout.dummy_fragment,container,false);

            final FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.dummyfrag_bg);
            frameLayout.setBackgroundColor(color);

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dummyfrag_scrollableview);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);

            List<String> list = new ArrayList<String>();
            Collections.addAll(list, new String[]{"sss","sss"});
            List<String> heading = new ArrayList<String>(); //not use




            adapter = new SimpleRecyclerAdapter(getActivity(),list,heading);
            recyclerView.setAdapter(adapter);

            return view;
        }
    }

    public static class Frag01 extends Fragment {
        int color;
        SimpleRecyclerAdapter adapter;
        public Frag01(int color) {
            this.color=color;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view=inflater.inflate(R.layout.dummy_fragment,container,false);

            final FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.dummyfrag_bg);
            frameLayout.setBackgroundColor(color);

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dummyfrag_scrollableview);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);

            List<String> list = new ArrayList<String>();
            Collections.addAll(list, new String[]{"sss","sss"});
            List<String> heading = new ArrayList<String>(); //not use




            adapter = new SimpleRecyclerAdapter(getActivity(),list,heading);
            recyclerView.setAdapter(adapter);

            return view;
        }
    }


    public static class Frag02 extends Fragment {
        int color;
        SimpleRecyclerAdapter adapter;
        public Frag02(int color) {
            this.color=color;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view=inflater.inflate(R.layout.dummy_fragment,container,false);

            final FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.dummyfrag_bg);
            frameLayout.setBackgroundColor(color);

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dummyfrag_scrollableview);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);

            List<String> list = new ArrayList<String>();
            Collections.addAll(list, new String[]{"aaa","sdsd","sddsd"});
            List<String> heading = new ArrayList<String>(); //not use




            adapter = new SimpleRecyclerAdapter(getActivity(),list,heading);
            recyclerView.setAdapter(adapter);

            return view;
        }
    }





    public static class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.VersionViewHolder> {
        List<String> versionModels;
        List<String> heading;
        Boolean isHomeList = false;

        public static List<String> homeActivitiesList = new ArrayList<String>();
        public static List<String> homeActivitiesSubList = new ArrayList<String>();
        Context context;
        OnItemClickListener clickListener;
        int previousPosition=0;


        public void setHomeActivitiesList(Context context) {
            //   String[] listArray = context.getResources().getStringArray(R.array.home_activities);
            //   String[] subTitleArray = context.getResources().getStringArray(R.array.home_activities_subtitle);
            //  for (int i = 0; i < listArray.length; ++i) {
            //     homeActivitiesList.add(listArray[i]);
            //     homeActivitiesSubList.add(subTitleArray[i]);
            // }
        }

        public SimpleRecyclerAdapter(Context context) {
            isHomeList = true;
            this.context = context;
            setHomeActivitiesList(context);
        }


        public SimpleRecyclerAdapter(Context context,List<String> versionModels,List<String> heading) {
            isHomeList = false;
            this.versionModels = versionModels;
            this.heading=heading;
            this.context = context;
        }

        @Override
        public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerlist_item, viewGroup, false);
            VersionViewHolder viewHolder = new VersionViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(VersionViewHolder versionViewHolder, int i) {
            int fontColor;
            float fontSize;
            Typeface typeface=Typeface.createFromAsset(context.getApplicationContext().getAssets(),"Roboto-Light.ttf");
            versionViewHolder.subTitle.setTypeface(typeface);
            versionViewHolder.subTitle.setTextSize(new FontStyles().getFontSize(context));
            versionViewHolder.subTitle.setTextColor(new FontStyles().getFontColor(context));
            versionViewHolder.subTitle.setTextSize(new FontStyles().getFontSize(context));



            //  versionViewHolder.title.setTypeface(typeface);

            if (isHomeList) {
                versionViewHolder.title.setText(homeActivitiesList.get(i));
                versionViewHolder.subTitle.setText(homeActivitiesSubList.get(i));
            } else {
                // versionViewHolder.title.setText(versionModels.get(i));
                //  versionViewHolder.subTitle.setText(Html.fromHtml(versionModels.get(i)));
                try {

                    // versionViewHolder.title.setText(heading.get(i));

                    versionViewHolder.subTitle.setText(versionModels.get(i));
                    if(Utils.getPageAnimationBool(context)){
                        if(previousPosition>i){
                            AnimationUtils.Animate(versionViewHolder,true);
                        }else {
                            AnimationUtils.Animate(versionViewHolder,false);
                        }
                        previousPosition=i;
                    }


                }catch (Exception e){
                    e.printStackTrace();
                }

                //  versionViewHolder.webView.loadData(String.format("        %s        ",versionModels.get(i)),"text/html","utf-8");

            }
        }

        public Typeface getFontStyle(Context context){
            Typeface typeface=Typeface.createFromAsset(context.getApplicationContext().getAssets(),"Roboto-Regular.ttf");
            String option=  Utils.readSharedFontStyle(context,"fontStyle","3");
            switch (option){
                case "1":
                    typeface=Typeface.createFromAsset(context.getApplicationContext().getAssets(),"Roboto-Regular.ttf");
                    break;
                case "2":
                    typeface=Typeface.createFromAsset(context.getApplicationContext().getAssets(),"Roboto-Light.ttf");
                    break;
                case "3":
                    typeface=Typeface.createFromAsset(context.getApplicationContext().getAssets(),"Roboto-Thin.ttf");
                    break;
            }



            return typeface;
        }

        public float getFontSize(Context context){
            String option=  Utils.readSharedFontSize(context,"fontSize","2");
            float fontSize=16f;
            switch (option){
                case "1":
                    fontSize=14f;
                    break;
                case "2":
                    fontSize=16f;
                    break;
                case "3":
                    fontSize=18f;
                    break;
            }



            return fontSize;
        }


        @Override
        public int getItemCount() {
            if (isHomeList)
                return homeActivitiesList == null ? 0 : homeActivitiesList.size();
            else
                return versionModels == null ? 0 : versionModels.size();
        }


        class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            CardView cardItemLayout;
            TextView title;
            TextView subTitle;
            WebView webView;

            public VersionViewHolder(View itemView) {
                super(itemView);

                cardItemLayout = (CardView) itemView.findViewById(R.id.cardlist_item);
                title = (TextView) itemView.findViewById(R.id.listitem_name);
                subTitle = (TextView) itemView.findViewById(R.id.listitem_subname);

                if (isHomeList) {
                    itemView.setOnClickListener(this);
                } else {
                    title.setVisibility(View.GONE);
                }

            }

            @Override
            public void onClick(View v) {
                clickListener.onItemClick(v, getPosition());
            }
        }

        public interface OnItemClickListener {
            void onItemClick(View view, int position);
        }

        public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

    }


}
