package com.soomapps.screenmirroring.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.soomapps.screenmirroring.R;

public class BottomSheetFragment extends BottomSheetDialogFragment {
    public BottomSheetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // val view: View = inflater.inflate(R.layout.activity_remote, container, false)
        View view = inflater.inflate(R.layout.item_grid, container, false);
        LinearLayout fbBTN = view.findViewById(R.id.fbBTN);
        fbBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://www.facebook.com/soomapps"));
                startActivity(i);

                // https://www.instagram.com/soomapps/
                // https://www.facebook.com/soomapps
            }
        });


        LinearLayout instaBTN = view.findViewById(R.id.instaBTN);
        instaBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://www.instagram.com/soomapps/"));
                startActivity(i);

                // https://www.instagram.com/soomapps/
                // https://www.facebook.com/soomapps
            }
        });

        TextView cancelBTN = view.findViewById(R.id.cancelBTN);
        cancelBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }
}
