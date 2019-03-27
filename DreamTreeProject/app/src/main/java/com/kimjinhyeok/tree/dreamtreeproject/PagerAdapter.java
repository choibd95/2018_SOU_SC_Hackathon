package com.kimjinhyeok.tree.dreamtreeproject;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.inputmethod.InputMethodManager;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by Tacademy on 2016-09-30.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    Context context;
    int _numOfTabs;
    InputMethodManager imm;
    public PagerAdapter(Context context, FragmentManager fm, int numOfTabs) {
        super(fm);
        this._numOfTabs = numOfTabs;
        this.context = context;
      imm = (InputMethodManager)context.getSystemService(INPUT_METHOD_SERVICE);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ThirdFragment tab1 = new ThirdFragment(); // Fragment 는 알아서 만들자
                if(NameSeach.editText != null)
                {
                    imm.hideSoftInputFromWindow(NameSeach.editText.getWindowToken(),0);
                }

                return tab1;
            case 1:
                AreaFragment tab2 = new AreaFragment();
                if(NameSeach.editText != null)
                {
                    NameSeach.editText.setFocusable(true);
                }


                return tab2;
            case 2:
                NameSeach tab3 = new NameSeach();
                if(NameSeach.editText != null)
                {
                    NameSeach.editText.setFocusable(true);
                }
                return tab3;

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