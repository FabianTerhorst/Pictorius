package de.fterhorst.pictoriusvertretungsplan.adapter;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

public class SyncedScrollListener implements OnScrollListener{
    int offset;
    int oldVisibleItem = -1;
    int currentHeight;
    int prevHeight;
    private View mSyncedView;


    public SyncedScrollListener(View syncedView){

        if(syncedView == null){
            throw new IllegalArgumentException("syncedView is null");
        }

        mSyncedView = syncedView;
    }

    public void onScroll(AbsListView view, int firstVisibleItem,
            int visibleItemCount, int totalItemCount) {

        int[] location = new int[2];

        if(visibleItemCount == 0){
            return;
        }

        if(oldVisibleItem != firstVisibleItem){

            if(oldVisibleItem < firstVisibleItem){
                prevHeight = currentHeight;
                currentHeight = view.getChildAt(0).getHeight();

                offset += prevHeight;

            }else{
                currentHeight = view.getChildAt(0).getHeight();

                View prevView;
                if((prevView = view.getChildAt(firstVisibleItem - 1)) != null){
                    prevHeight = prevView.getHeight();
                }else{
                    prevHeight = 0;
                }

                offset -= currentHeight;
            }

            oldVisibleItem = firstVisibleItem;
        }

        view.getLocationOnScreen(location);
        int listContainerPosition = location[1];

        view.getChildAt(0).getLocationOnScreen(location);
        int currentLocation = location[1];

        int blah = listContainerPosition - currentLocation + offset;

        mSyncedView.scrollTo(0, blah);

    }

    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // TODO Auto-generated method stub

    }
}