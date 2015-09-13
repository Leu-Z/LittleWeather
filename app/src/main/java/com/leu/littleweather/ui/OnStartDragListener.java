package com.leu.littleweather.ui;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Leu on 2015/9/13.
 */
public interface OnStartDragListener {

    /**
     * Called when a view is requesting a start of a drag.
     *
     * @param viewHolder The holder of the view to drag.
     */
    void onStartDrag(RecyclerView.ViewHolder viewHolder);
}
