package com.leu.littleweather.ui;

/**
 * Created by Leu on 2015/9/13.
 */
public interface ItemTouchHelperAdapter {
    void onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
