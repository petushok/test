package com.example.android.listviewscrolltrick;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.example.listviewscrolltrick.R;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Yanaa
 * Date: 18.09.13
 */
public class MapStickyView extends RelativeLayout {
    private RelativeLayout stickylayout;
    private MapView map;
    private Context mContext;

    public MapStickyView(Context context) {
        super(context);
        mContext = context;

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        stickylayout = (RelativeLayout) inflater.inflate(R.layout.stickylayout, null);
        LayoutParams lp = new LayoutParams(600, 600);
        stickylayout.setLayoutParams(lp);

        map = (MapView) stickylayout.findViewById(R.id.mapview);
        addView(stickylayout);
        createUI();
    }

    private void zoomDown() {
        if (map.getZoomLevel() == 0)
            map.getController().setZoom(1);
        map.getController().setZoom((int) (map.getZoomLevel() / 2));
        map.invalidate();
    }

    public void setMarker(float latitude, float longitude) {
        GeoPoint overlayPoint = new GeoPoint(latitude, longitude);

        ArrayList<OverlayItem> overlayItemArray = new ArrayList<OverlayItem>();
        overlayItemArray.add(new OverlayItem("0, 0", "0, 0", overlayPoint));

        map.getController().animateTo(overlayPoint);
        ItemizedIconOverlay<OverlayItem> iconOverlay = new ItemizedIconOverlay<OverlayItem>(
                mContext, overlayItemArray, null);
        map.getOverlays().add(iconOverlay);
        map.getController().setZoom(2);
        map.invalidate();
    }

    private void zoomUp() {
        if (map.getZoomLevel() == 0)
            map.getController().setZoom(1);
        map.getController().setZoom(map.getZoomLevel() * 2);

        map.invalidate();
    }

    private void createUI() {
        Button zoomUp = (Button) stickylayout.findViewById(R.id.btnzoomup);
        zoomUp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                zoomUp();
            }
        });

        Button zoomDown = (Button) stickylayout.findViewById(R.id.btnzoomdown);
        zoomDown.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                zoomDown();
            }
        });
    }
}
