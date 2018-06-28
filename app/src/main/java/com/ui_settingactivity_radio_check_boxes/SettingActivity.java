package com.ui_settingactivity_radio_check_boxes;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


@SuppressWarnings("ALL")
public class SettingActivity extends AppCompatActivity implements View.OnClickListener{
    Button fontStyleBtn,fontcolorBtn,fontSizeBtn;
    TextView fontStyleTxt,fontColorTxt,fontSizeTxt;
    RadioGroup radioGroup;
    RadioButton radioButtonSize;
    SwitchCompat switchCompat,switchCompat2;
    FloatingActionButton fab;
    LinearLayout linearLayout;
InterstitialAd interstitialAd;
    @Override
    public void finish() {
        super.finish();
        Intent intent=new Intent(getApplicationContext(),TabsHeaderActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initIntertistialAds();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setTitle("Customization");
        fab = (FloatingActionButton) findViewById(R.id.fab);
        linearLayout=(LinearLayout)findViewById(R.id.change_color);

        initRadio();
        initSwitchCompact();
        linearLayout.setOnClickListener(this);

        fontStyleBtn=(Button)findViewById(R.id.id_font_style_btn);
        assert fontStyleBtn != null;
        fontStyleBtn.setOnClickListener(this);





        fontColorTxt=(TextView)findViewById(R.id.f_style_txt);
        fontColorTxt.setTypeface(new FontStyles().getFontStyle(getApplicationContext()));













        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAds();
                Intent intent=new Intent(getApplicationContext(),TabsHeaderActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
    public void initSwitchCompact(){
        switchCompat=(SwitchCompat)findViewById(R.id.switch_compat1);
        if(Utils.getItemAnimationBool(getApplicationContext())){
            switchCompat.setChecked(true);
            switchCompat.setText("Turn off Animation");
        }else {
            switchCompat.setText("Turn on Animation");
            switchCompat.setChecked(false);
        }


        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    // buttonView.setText("Disable Animation");
                    switchCompat.setChecked(true);
                    switchCompat.setText("Turn off Animation");
                    Utils.setItemAnimationBool(getApplicationContext(),true);
                    Snackbar.make(switchCompat,"Animation Started", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }else {
                    // buttonView.setText("Enable Animation");
                    switchCompat.setChecked(false);
                    switchCompat.setText("Turn on Animation");
                    Utils.setItemAnimationBool(getApplicationContext(),false);
                    Snackbar.make(fab,"Animation Stop", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                    compoundButton.setText("Turn on Animation");
                }

            }
        });



        switchCompat2=(SwitchCompat)findViewById(R.id.switch_compat2);

        if(Utils.getPageAnimationBool(getApplicationContext())){
            switchCompat2.setChecked(true);
            switchCompat2.setText("Turn off Text blocks Animation");
        }else {
            switchCompat2.setText("Turn on Text blocks Animation");
            switchCompat2.setChecked(false);
        }


        switchCompat2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    // buttonView.setText("Disable Animation");
                    compoundButton.setText("Turn off Text blocks Animation");
                    switchCompat2.setChecked(true);
                    Utils.setPageAnimationBool(getApplicationContext(),true);
                    Snackbar.make(switchCompat2,"Animation starts", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                    // switchCompat.setText("Turn off Text blocks Animation");
                }else {
                    // buttonView.setText("Enable Animation");
                    Utils.setPageAnimationBool(getApplicationContext(),false);
                    Snackbar.make(fab,"Animation Stop", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                    compoundButton.setText("Turn on Text blocks Animation");
                    switchCompat2.setChecked(true);

                }

            }
        });




    }
    void initIntertistialAds(){
        interstitialAd=new InterstitialAd(getApplicationContext());
        interstitialAd.setAdUnitId("ca-app-pub-9786215071824815/5415429289");
        AdRequest adRequest=new AdRequest.Builder().tagForChildDirectedTreatment(false).build();
        interstitialAd.loadAd(adRequest);
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                interstitialAd.show();
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
            interstitialAd=null;
                interstitialAd=new InterstitialAd(getApplicationContext());
            }
        });
    }

    void showAds(){
        if(interstitialAd.isLoaded()){
            interstitialAd.show();
        }
    }

    public void initRadio(){
        radioGroup=(RadioGroup)findViewById(R.id.radio_group_fsize);
        int checkedId= Utils.readCheckedSharedFontSize(getApplicationContext(),R.id.radioButton2);
        radioButtonSize=(RadioButton)findViewById(checkedId);
        if(radioButtonSize==null){
            radioButtonSize=(RadioButton)findViewById(R.id.radioButton2);

        }
        radioButtonSize.setChecked(true);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                showAds();
                int radioid=radioGroup.getCheckedRadioButtonId();
                radioButtonSize=(RadioButton)findViewById(radioid);
                Utils.saveSharedFontSize(getApplicationContext(),radioButtonSize.getText().toString());
                //  Toast.makeText(getApplicationContext(),radioButtonSize.getText().toString(),Toast.LENGTH_SHORT).show();
                Snackbar.make(fab,""+radioButtonSize.getText()+" font applied", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                Utils.saveCheckedSharedFontSize(getApplicationContext(),radioButtonSize.getId());
            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.id_font_style_btn:
                dispalyPopUpWindow(1,view);
                break;
            case R.id.change_color:
                dispalyPopUpWindow(1,view);
                break;



        }

    }



    public void dispalyPopUpWindow(int option ,View anchorView){
        LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4,linearLayout5;

        final PopupWindow popupWindow=new PopupWindow(getApplicationContext());
        View view= LayoutInflater.from(getApplicationContext()).inflate(R.layout.pop_up_menu_xml,null);
        if (Utils.getItemAnimationBool(getApplicationContext())) {
            AnimationXml.animateview2(view,getApplicationContext());

        }

        popupWindow.setContentView(view);

        popupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        popupWindow.showAsDropDown(anchorView,0,0);


        linearLayout1=(LinearLayout)view.findViewById(R.id.poup1);
        linearLayout2=(LinearLayout)view.findViewById(R.id.popup2);

        linearLayout4=(LinearLayout)view.findViewById(R.id.popup4);
        linearLayout5=(LinearLayout)view.findViewById(R.id.popup5);
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                fontColorTxt.setTextColor(getResources().getColor(R.color.dark_alice));
                Utils.saveSharedFontColor(getApplicationContext(),"1"); //alice
                fontColorTxt.setText("Drak Alice");
                Snackbar.make(fontColorTxt,"Color Changed (Drak Alice)", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                showAds();
            }
        });

        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();

                fontColorTxt.setTextColor(getResources().getColor(R.color.fb_txt_color));
                Utils.saveSharedFontColor(getApplicationContext(),"3"); //fb
                fontColorTxt.setText("Black");
                Snackbar.make(fontColorTxt,"Color Changed (Black)", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();


                fontColorTxt.setTextColor(getResources().getColor(R.color.blue_grey));
                fontColorTxt.setText("Blue Gray");
                Utils.saveSharedFontColor(getApplicationContext(),"2"); //blueGray
                Snackbar.make(fontColorTxt,"Color Changed (Blue Gray)", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        linearLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                fontColorTxt.setTextColor(getResources().getColor(R.color.dark_ruby));
                fontColorTxt.setText("Dark Ruby");
                Utils.saveSharedFontColor(getApplicationContext(),"4"); //d-ruby
                Snackbar.make(fontColorTxt,"Color Changed (Dark Ruby)", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



    }

}
