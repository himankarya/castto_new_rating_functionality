
package com.soomapps.screenmirroring.activities;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.soomapps.screenmirroring.BuildConfig;
import com.soomapps.screenmirroring.R;


public class Not_Really_Activity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    private Button submit_btn;
    private TextView text_doesnot_work, text_cannot_find_tv, text_poor_connection, text_too_many_ads, text_other;
    private EditText et_more_detail;
    private ImageView back;
    boolean is_type_Visible_does_work, isIs_type_Visible_tv, isIs_type_Visible_poor, isIs_type_Visible_ads, isIs_type_Visible_others;
    private String doesnot_work , cannot_find_tv , poor_connection , too_many_ads , other;
    int is_type;
    String mailto;
    private Handler handler;
    private boolean tvSelected = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.not_really_screen);

        getSupportActionBar().hide();


        submit_btn = (Button) findViewById(R.id.submit_btn);
        back = (ImageView) findViewById(R.id.back);
        text_doesnot_work = (TextView) findViewById(R.id.text_doesnot_work);
        text_cannot_find_tv = (TextView) findViewById(R.id.text_cannot_find_tv);
        text_poor_connection = (TextView) findViewById(R.id.text_poor_connection);
        text_too_many_ads = (TextView) findViewById(R.id.text_too_many_ads);
        text_other = (TextView) findViewById(R.id.text_other);
        et_more_detail = (EditText) findViewById(R.id.et_more_detail);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Not_Really_Activity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        text_doesnot_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!is_type_Visible_does_work) {
                   doesnot_work= text_doesnot_work.getText().toString();
                    text_doesnot_work.setBackgroundResource(R.drawable.not_really_bg_enabled);
                    text_doesnot_work.setTextColor(Color.parseColor("#FFFFFF"));
                    submit_btn.setBackgroundResource(R.drawable.rating_button_bg);
                    submit_btn.setTextColor(Color.parseColor("#FFFFFF"));
                    submit_btn.setTypeface(Typeface.DEFAULT_BOLD);
                    is_type = 1;
                    is_type_Visible_does_work = true;
                    submit_btn.setEnabled(true);
                    submit_btn.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onClick(View view) {
                            sendEmail();
                        }
                    });
                } else {
                    text_doesnot_work.setBackgroundResource(R.drawable.not_really_bg);
                    text_doesnot_work.setTextColor(Color.parseColor("#000000"));
                    submit_btn.setBackgroundResource(R.drawable.submit_btn_disable);
                    submit_btn.setTextColor(Color.parseColor("#000000"));
                    is_type = 0;
                    is_type_Visible_does_work = false;
                    submit_btn.setEnabled(false);
                }
            }
        });

        text_cannot_find_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isIs_type_Visible_tv) {
                    cannot_find_tv = text_cannot_find_tv.getText().toString();
                    text_cannot_find_tv.setBackgroundResource(R.drawable.not_really_bg_enabled);
                    text_cannot_find_tv.setTextColor(Color.parseColor("#FFFFFF"));
                    submit_btn.setBackgroundResource(R.drawable.rating_button_bg);
                    submit_btn.setTextColor(Color.parseColor("#FFFFFF"));
                    submit_btn.setTypeface(Typeface.DEFAULT_BOLD);
                    is_type = 1;
                    isIs_type_Visible_tv = true;
                    submit_btn.setEnabled(true);
                    submit_btn.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onClick(View view) {
                           sendEmail();
                        }
                    });
                } else {
                    text_cannot_find_tv.setBackgroundResource(R.drawable.not_really_bg);
                    text_cannot_find_tv.setTextColor(Color.parseColor("#000000"));
                    submit_btn.setBackgroundResource(R.drawable.submit_btn_disable);
                    submit_btn.setTextColor(Color.parseColor("#000000"));
                    is_type = 0;
                    isIs_type_Visible_tv = false;
                    submit_btn.setEnabled(false);
                }


            }
        });


        text_poor_connection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isIs_type_Visible_poor) {
                    poor_connection = text_poor_connection.getText().toString();
                    text_poor_connection.setBackgroundResource(R.drawable.not_really_bg_enabled);
                    text_poor_connection.setTextColor(Color.parseColor("#FFFFFF"));
                    submit_btn.setBackgroundResource(R.drawable.rating_button_bg);
                    submit_btn.setTextColor(Color.parseColor("#FFFFFF"));
                    submit_btn.setTypeface(Typeface.DEFAULT_BOLD);
                    is_type = 1;
                    isIs_type_Visible_poor = true;
                    submit_btn.setEnabled(true);
                    submit_btn.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onClick(View view) {
                          sendEmail();
                        }
                    });
                } else {
                    text_poor_connection.setBackgroundResource(R.drawable.not_really_bg);
                    text_poor_connection.setTextColor(Color.parseColor("#000000"));
                    submit_btn.setBackgroundResource(R.drawable.submit_btn_disable);
                    submit_btn.setTextColor(Color.parseColor("#000000"));
                    is_type = 0;
                    isIs_type_Visible_poor = false;
                    submit_btn.setEnabled(false);
                }
            }
        });


        text_too_many_ads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isIs_type_Visible_ads) {
                   // too_many_ads = text_too_many_ads.getText().toString();
                    text_too_many_ads.setBackgroundResource(R.drawable.not_really_bg_enabled);
                    text_too_many_ads.setTextColor(Color.parseColor("#FFFFFF"));
                    submit_btn.setBackgroundResource(R.drawable.rating_button_bg);
                    submit_btn.setTextColor(Color.parseColor("#FFFFFF"));
                    submit_btn.setTypeface(Typeface.DEFAULT_BOLD);
                    is_type = 1;
                    isIs_type_Visible_ads = true;
                    submit_btn.setEnabled(true);
                    submit_btn.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onClick(View view) {
                         sendEmail();
                        }
                    });
                } else {
                    text_too_many_ads.setBackgroundResource(R.drawable.not_really_bg);
                    text_too_many_ads.setTextColor(Color.parseColor("#000000"));
                    submit_btn.setBackgroundResource(R.drawable.submit_btn_disable);
                    submit_btn.setTextColor(Color.parseColor("#000000"));
                    is_type = 0;
                    isIs_type_Visible_ads = false;
                    submit_btn.setEnabled(false);
                }

            }
        });


        text_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isIs_type_Visible_others) {
                   // other= text_other.getText().toString();
                    text_other.setBackgroundResource(R.drawable.not_really_bg_enabled);
                    text_other.setTextColor(Color.parseColor("#FFFFFF"));
                    submit_btn.setBackgroundResource(R.drawable.rating_button_bg);
                    submit_btn.setTextColor(Color.parseColor("#FFFFFF"));
                    submit_btn.setTypeface(Typeface.DEFAULT_BOLD);
                    is_type = 1;
                    isIs_type_Visible_others = true;
                    submit_btn.setEnabled(true);
                    submit_btn.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onClick(View view) {
                           sendEmail();
                        }
                    });
                } else {
                    text_other.setBackgroundResource(R.drawable.not_really_bg);
                    text_other.setTextColor(Color.parseColor("#000000"));
                    submit_btn.setBackgroundResource(R.drawable.submit_btn_disable);
                    submit_btn.setTextColor(Color.parseColor("#000000"));
                    is_type = 0;
                    isIs_type_Visible_others = false;
                    submit_btn.setEnabled(false);
                }
            }
        });

        et_more_detail.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().trim().length()==0){
                    submit_btn.setBackgroundResource(R.drawable.submit_btn_disable);
                    submit_btn.setTextColor(Color.parseColor("#000000"));
                    submit_btn.setEnabled(false);

                } else {
                    submit_btn.setBackgroundResource(R.drawable.rating_button_bg);
                    submit_btn.setTextColor(Color.parseColor("#FFFFFF"));
                    submit_btn.setTypeface(Typeface.DEFAULT_BOLD);
                    submit_btn.setEnabled(true);
                    submit_btn.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onClick(View view) {
                            sendEmail();
                        }
                    });
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("IntentReset")
    protected void sendEmail() {

        if (text_doesnot_work.isClickable() &&text_cannot_find_tv.isClickable()
                 &&text_poor_connection.isClickable() &&text_too_many_ads.isClickable() &&text_other.isClickable()) {

            mailto = "mailto:contact@soomapps.com" +
                    "?cc=" + "" +
                    "&subject=" + Uri.encode("Castto - Feedback") +
                    "&body=" + Uri.encode("Type: " + doesnot_work + ", " +cannot_find_tv + ", " + poor_connection + ", "+ too_many_ads + ", "+ other);

       }
        else{
            mailto = "mailto:contact@soomapps.com" +
                    "?cc=" + "" +
                    "&subject=" + Uri.encode("Castto - Feedback") +
                    "&body=" + Uri.encode("Type: " ) ;

        }

//        mailto = "mailto:contact@soomapps.com" +
//                "?cc=" + "" +
//                "&subject=" + Uri.encode("Castto - Feedback") +
//                "&body=" + Uri.encode("Type: ");

        String deviceInfo = "\nSystem info: (";
        deviceInfo += "App v" + BuildConfig.VERSION_NAME;
        deviceInfo += (", Android V: " + Build.VERSION.RELEASE + ")");
        String moredetail = et_more_detail.getText().toString();
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse(mailto + "\n" + moredetail + deviceInfo));

        try {
            startActivity(emailIntent);
            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    showBottomSheetDialog();
                }
            }, 1000);
        } catch (ActivityNotFoundException e) {
            //TODO: Handle case where no email app is available
        }

    }

        private void showBottomSheetDialog() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.thanks_popup);
        Button ok_button_feedback = bottomSheetDialog.findViewById(R.id.ok_button_feedback);
        bottomSheetDialog.show();
        bottomSheetDialog.setCancelable(false);


        ok_button_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Not_Really_Activity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i =  new Intent(Not_Really_Activity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

}
