package com.shopgun.android.verso;

import com.shopgun.android.utils.TextUtils;
import com.shopgun.android.zoomlayout.ZoomLayout;

import java.util.Arrays;
import java.util.Locale;

public class VersoTapInfo extends ZoomLayout.TapInfo {

    private static final String STRING_FORMAT = "VersoTapInfo[ position:%s, pageTapped:%s, pages:%s, absX:%.0f, absY:%.0f, relX:%.0f, relY:%.0f, percentX:%.2f, percentY:%.2f, contentClicked:%s ]";

    private final VersoPageViewFragment mFragment;
    private final int mPosition;
    private final int[] mPages;
    private final int mPageTapped;

    public VersoTapInfo(VersoTapInfo info) {
        this(info, info.mFragment);
    }

    protected VersoTapInfo(ZoomLayout.TapInfo info, VersoPageViewFragment fragment) {
        super(info);
        mFragment = fragment;
        mPosition = mFragment.mPosition;
        mPages = Arrays.copyOf(mFragment.mPages, mFragment.mPages.length);
        float pageWidth = 1f / (float) mPages.length;
        int pagePos = (int) Math.floor(getPercentX() / pageWidth);
        mPageTapped = mPages[pagePos];
    }

    public VersoPageViewFragment getFragment() {
        return mFragment;
    }

    public int getPosition() {
        return mPosition;
    }

    public int[] getPages() {
        return mPages;
    }

    public int getPageTapped() {
        return mPageTapped;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, STRING_FORMAT, mPosition, mPageTapped, TextUtils.join(",", mPages),
                getX(), getY(), getRelativeX(), getRelativeY(), getPercentX(), getPercentY(), isContentClicked());
    }
}