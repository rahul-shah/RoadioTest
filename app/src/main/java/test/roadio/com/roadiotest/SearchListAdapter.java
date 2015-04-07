package test.roadio.com.roadiotest;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class SearchListAdapter extends ArrayAdapter
{
    private Context context;
    private int layoutResourceId;
    public static boolean mIsUserLoggedIn = false;
    protected ImageLoader loader;
    private String mEventName;
    ArrayList<SearchItems> mEventsList = new ArrayList<SearchItems>();

    public SearchListAdapter(Context context, int layoutResourceId, ArrayList<SearchItems> eventList)
    {
        super(context, layoutResourceId,eventList);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        mEventsList.addAll(eventList);
        loader = VolleySingleton.getInstance(context).getImageLoader();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        NetworkImageView rowImage = null;
        ImageView arrowIcon = null;
        TextView rowText = null;
        TextView rowSubText = null;
        TextView starText = null;
        TextView forkText = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.events_list, parent, false);
        }

        rowImage = (NetworkImageView)row.findViewById(R.id.imgIcon);
        rowText = (TextView)row.findViewById(R.id.txtTitle);
        rowSubText = (TextView) row.findViewById(R.id.txtSubTitle);
        starText = (TextView) row.findViewById(R.id.starTitle);
        forkText = (TextView) row.findViewById(R.id.forkTitle);
        //arrowIcon = (ImageView)row.findViewById(R.id.arrowIcon);

        mEventName = new String(mEventsList.get(position).getFullName());
        rowImage.setImageUrl(mEventsList.get(position).getOwner().getAvatar(),loader);
        rowText.setText(mEventName);
        starText.setText(mEventsList.get(position).getWatchers_count());
        forkText.setText(mEventsList.get(position).getForks_count());
        rowSubText.setText(mEventsList.get(position).getDescription());
        return row;
    }
}
