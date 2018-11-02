package ch.com.constructionhunt.ui.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import ch.com.constructionhunt.utils.DetailClickListener;

/**
 * Created by jurgen on 10/10/2018.
 */


public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    private int mCurrentPosition;
    private DetailClickListener mdetailClickListener;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    protected abstract void clear();

    public void onBind(int position) {
        mCurrentPosition = position;
        clear();
    }
    public void onBind(int position,DetailClickListener detailClickListener) {
        mCurrentPosition = position;
        mdetailClickListener = detailClickListener;
        clear();
    }

    public int getCurrentPosition() {
        return mCurrentPosition;
    }
}
