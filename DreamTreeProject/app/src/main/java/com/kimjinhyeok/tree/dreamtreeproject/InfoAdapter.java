package com.kimjinhyeok.tree.dreamtreeproject;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Tacademy on 2016-09-30.
 */
public class InfoAdapter extends FragmentStatePagerAdapter {
    Context context;
    int _numOfTabs;

    public InfoAdapter(Context context, FragmentManager fm, int numOfTabs) {
        super(fm);
        this._numOfTabs = numOfTabs;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Info1Fragment tab1 = new Info1Fragment(); // Fragment 는 알아서 만들자
                return tab1;
            case 1:
                Info2Fragment tab2 = new Info2Fragment();
                return tab2;
            case 2:
                Info3Fragment tab3 = new Info3Fragment();
                return tab3;
            case 3:
                Info4Fragment tab4 = new Info4Fragment();
                return tab4;
            case 4:
                Info5Fragment tab5 = new Info5Fragment();
                return tab5;



            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return _numOfTabs;
    }
//    Drawable myDrawable;
//    String title;
//    @Override
//    public CharSequence getPageTitle(int position) {
//        SpannableStringBuilder sb;
//        ImageSpan span;
//        switch (position) {
//        case 0:
//            myDrawable = context.getResources().getDrawable(R.drawable.icon01);
//            sb = new SpannableStringBuilder("  Page1"); // space added before text for convenience
//
//            myDrawable.setBounds(0, 0, myDrawable.getIntrinsicWidth(), myDrawable.getIntrinsicHeight());
//            span = new ImageSpan(myDrawable, ImageSpan.ALIGN_BASELINE);
//            sb.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//            return sb;
//        case 1:
//            myDrawable = context.getResources().getDrawable(R.drawable.icon02);
//            sb = new SpannableStringBuilder("  Page2"); // space added before text for convenience
//
//            myDrawable.setBounds(0, 0, myDrawable.getIntrinsicWidth(), myDrawable.getIntrinsicHeight());
//            span = new ImageSpan(myDrawable, ImageSpan.ALIGN_BASELINE);
//            sb.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//            return sb;
//        case 2:
//            myDrawable = context.getResources().getDrawable(R.drawable.icon03);
//            sb = new SpannableStringBuilder("  Page3"); // space added before text for convenience
//
//            myDrawable.setBounds(0, 0, myDrawable.getIntrinsicWidth(), myDrawable.getIntrinsicHeight());
//            span = new ImageSpan(myDrawable, ImageSpan.ALIGN_BASELINE);
//            sb.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//            return sb;
//        default:
//            return null;
//        }
//    }
}