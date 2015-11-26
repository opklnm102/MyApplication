package me.dong.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gv;

    Integer[] posterImg = {
            R.drawable.img_poster1, R.drawable.img_poster2, R.drawable.img_poster3, R.drawable.img_poster4};
    String[] posterTitle = {"신세계", "괴물", "하울링", "구름을 벗어난 달처럼"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gv = (GridView) findViewById(R.id.gridView);
        MyGridAdapter adapter = new MyGridAdapter(this);
        gv.setAdapter(adapter);
    }

    class MyGridAdapter extends BaseAdapter {

        Context mContext;
        ArrayList<Poster> posterArrayList;

        public MyGridAdapter(Context context) {
            mContext = context;
            posterArrayList = new ArrayList<>();

            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 4; j++) {
                    Poster poster = new Poster(posterTitle[j], posterImg[j]);
                    posterArrayList.add(poster);
                }
            }
        }

        @Override
        public int getCount() {
            return posterArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return posterArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_grid, parent, false);

            final Poster item = posterArrayList.get(position);

            ImageView ivPster = (ImageView) view.findViewById(R.id.imageView_poster);
            TextView tvTitle = (TextView) view.findViewById(R.id.textView_title);

            ivPster.setImageResource(item.getImgRes());
            tvTitle.setText(item.getTitle());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    View dialogView = (View) view.inflate(MainActivity.this, R.layout.dialog, null);

                    ImageView ivDialogPoster = (ImageView) dialogView.findViewById(R.id.imageView_poster);
                    ivDialogPoster.setImageResource(item.getImgRes());

                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle(item.getTitle())
                            .setIcon(R.drawable.ic_group_black_24dp)
                            .setView(dialogView)
                            .setNegativeButton("닫기", null)
                            .show();
                }
            });

            return view;
        }
    }
}
