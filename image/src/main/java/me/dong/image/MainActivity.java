package me.dong.image;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvSelect, tvLike;
    Switch switchAgree;
    RadioGroup radioGroup;
    RadioButton rbJellyBean, rbKitkat, rbLollipop;
    Button btnExit, btnFirst;
    ImageView ivPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("애완동물 사진 보기");

        tvSelect = (TextView) findViewById(R.id.textView_select);
        tvLike = (TextView) findViewById(R.id.textView_like);
        switchAgree = (Switch) findViewById(R.id.switch_agree);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        rbJellyBean = (RadioButton) findViewById(R.id.radioButton_jellybean);
        rbKitkat = (RadioButton) findViewById(R.id.radioButton_kitkat);
        rbLollipop = (RadioButton) findViewById(R.id.radioButton_lollipop);
        ivPet = (ImageView) findViewById(R.id.imageView_pet);
        btnExit = (Button) findViewById(R.id.button_exit);
        btnFirst = (Button) findViewById(R.id.button_forthefirsttime);

        switchAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switchAgree.isChecked() == true) {
                    tvLike.setVisibility(View.VISIBLE);
                    radioGroup.setVisibility(View.VISIBLE);
                    ivPet.setVisibility(View.VISIBLE);
                    btnExit.setVisibility(View.VISIBLE);
                    btnFirst.setVisibility(View.VISIBLE);
                } else {
                    tvLike.setVisibility(View.INVISIBLE);
                    radioGroup.setVisibility(View.INVISIBLE);
                    ivPet.setVisibility(View.INVISIBLE);
                    btnExit.setVisibility(View.INVISIBLE);
                    btnFirst.setVisibility(View.INVISIBLE);
                }
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (group.getCheckedRadioButtonId()) {
                    case R.id.radioButton_jellybean:
                        ivPet.setImageResource(R.drawable.img_jelly_bean);
                        break;
                    case R.id.radioButton_kitkat:
                        ivPet.setImageResource(R.drawable.img_kitkat);
                        break;
                    case R.id.radioButton_lollipop:
                        ivPet.setImageResource(R.drawable.img_lollipop);
                        break;
                }
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
    }
}
