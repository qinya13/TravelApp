package com.narong.travelapp.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.narong.travelapp.Domain.ItemDomain;
import com.narong.travelapp.R;
import com.narong.travelapp.databinding.ActivityTicketBinding;

public class TicketActivity extends BaseActivity {
    ActivityTicketBinding binding;
    private ItemDomain object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityTicketBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        setVariable();

    }

    private void setVariable() {
        Glide.with(TicketActivity.this)
                .load(object.getPic())
                .into(binding.pic);
        Glide.with(TicketActivity.this)
                .load(object.getTourGuidePic())
                .into(binding.profile);

        binding.backBtn.setOnClickListener(view -> finish());
        binding.titleTxt.setText(object.getTitle());
        binding.tourGuideTxt.setText(object.getDateTour());
        binding.titleTxt.setText(object.getTimeTour());
        binding.tourGuideNameTxt.setText(object.getTourGuideName());

        binding.callBtn.setOnClickListener(view -> {
            Intent sendIntent=new Intent(Intent.ACTION_VIEW);
            sendIntent.setData(Uri.parse("sms"+object.getTourGuidePhone()));
            sendIntent.putExtra("sms_body", "type your message");
            startActivity(sendIntent);
        });

        binding.messageBtn.setOnClickListener(view -> {
            String phone= object.getTourGuidePhone();
            Intent intent=new Intent(Intent.ACTION_DIAL,Uri.fromParts("tel",phone, null));
            startActivity(intent );
        });
    }

    private void getIntentExtra() {
        object= (ItemDomain) getIntent().getSerializableExtra("object");
    }
}