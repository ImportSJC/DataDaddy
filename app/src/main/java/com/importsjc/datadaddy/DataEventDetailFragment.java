package com.importsjc.datadaddy;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.importsjc.datadaddy.Modules.DataPoint;
import com.importsjc.datadaddy.Modules.Template;
import com.importsjc.datadaddy.dummy.DummyContent;

/**
 * A fragment representing a single DataEvent detail screen.
 * This fragment is either contained in a {@link DataEventListActivity}
 * in two-pane mode (on tablets) or a {@link DataEventDetailActivity}
 * on handsets.
 */
public class DataEventDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private static DataPoint mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DataEventDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            DummyContent.DummyItem tmpItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
            mItem = tmpItem != null ? tmpItem : mItem;

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.content);
            }
        }*/

        Activity activity = this.getActivity();
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle(((DataPoint)
                    ((Template) MainActivity.templateList.get(MainActivity.currentTemplateIndex))
                            .getDataPointList().get(MainActivity.currentDataPointIndex)).getName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dataevent_detail, container, false);

        // Show the dummy content as text in a TextView.
//        if (mItem != null) {
        ((TextView) rootView.findViewById(R.id.dataevent_detail)).setText(((DataPoint)
                ((Template) MainActivity.templateList.get(MainActivity.currentTemplateIndex))
                        .getDataPointList().get(MainActivity.currentDataPointIndex)).toString());
//        }

        return rootView;
    }
}
