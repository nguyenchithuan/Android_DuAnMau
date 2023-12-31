package edu.poly.assigment_ph26023.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

import edu.poly.assigment_ph26023.R;
import edu.poly.assigment_ph26023.objects.Photo;

public class PhotoAdapter extends PagerAdapter {
    private Context context;
    private List<Photo> mListPhoto;

    public PhotoAdapter(Context context, List<Photo> mListPhoto) {
        this.context = context;
        this.mListPhoto = mListPhoto;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.layout_item_slide_show_anh, container, false);

        ImageView  imgPhoto = view.findViewById(R.id.img_slide_show);

        Photo photo = mListPhoto.get(position);

        if(photo != null) {
                // câu lệnh dùng thư viện glide để load ảnh
            Glide.with(context).load(photo.getResouredId()).into(imgPhoto);
        }

        // đặc biệt quan trọng
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        if(mListPhoto != null) {
            return mListPhoto.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
