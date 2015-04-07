package test.roadio.com.roadiotest;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;

import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity
{
    private SearchView mSearchView;
    private LinearLayout mBackgroundLayout;
    private LinearLayout mListView;
    private ListView mSearchResultList;
    private ProgressDialog mProgressDialog;
    private LinearLayout mSearchErrorLayout;
    private ArrayList<RepositoryObject> mRepositoriesList = new ArrayList<RepositoryObject>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT == 21)
        {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.parseColor("#00B2FF"));
        }

        //get custome views
        getCustomViews();

        getSupportActionBar().hide();
    }

    public void getCustomViews()
    {
        mBackgroundLayout = (LinearLayout) findViewById(R.id.search_icon_background);
        mListView = (LinearLayout) findViewById(R.id.ll_listview);
        mSearchView = (SearchView) findViewById(R.id.searchview);
        mSearchResultList = (ListView) findViewById(R.id.list_view);
        mSearchErrorLayout = (LinearLayout) findViewById(R.id.search_error_background);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                /*Intent tempIntent = new Intent(MainActivity.this,SearchResultsActivity.class);
                startActivity(tempIntent);*/

                getRepositories(query);
                //mSearchResultList
                mBackgroundLayout.setVisibility(View.GONE);
                mSearchErrorLayout.setVisibility(View.GONE);
                mListView.setVisibility(View.VISIBLE);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                return false;
            }
        });

        mSearchView.setOnCloseListener(new SearchView.OnCloseListener()
        {
            @Override
            public boolean onClose()
            {
                mBackgroundLayout.setVisibility(View.VISIBLE);
                mListView.setVisibility(View.GONE);
                return false;
            }
        });
    }

    public void getRepositories(String query)
    {
        final String url = "https://api.github.com/search/repositories?q=" + query;
        final RequestQueue queue = VolleySingleton.getInstance(this).getRequestQueue();
        Cache.Entry entry = queue.getCache().get(url);
        if (entry != null && entry.data != null)
        {
            processMapDataResponse(new String(entry.data));
            return;
        }
        if (mProgressDialog == null) mProgressDialog = getLoadingDialog(this);
        if (!mProgressDialog.isShowing()) mProgressDialog.show();
        StringRequest request = new StringRequest(url,new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {

                if (mProgressDialog.isShowing()) mProgressDialog.dismiss();
                Cache.Entry newEntry = new Cache.Entry();
                newEntry.data = response.getBytes();
                queue.getCache().put(url, newEntry);
                processMapDataResponse(response);
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                if (mProgressDialog.isShowing()) mProgressDialog.dismiss();
                mSearchErrorLayout.setVisibility(View.VISIBLE);
                mBackgroundLayout.setVisibility(View.GONE);
                mListView.setVisibility(View.GONE);
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 0, 0));
        queue.add(request);
    }

    public void processMapDataResponse(String apiResponse)
    {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        JsonParser parser = new JsonParser();

        JsonObject responseObject = parser.parse(apiResponse.toString()).getAsJsonObject();
        //for(JsonElement tempContact: responseObject)
        //{
            RepositoryObject personFromAPI = gson.fromJson(responseObject,RepositoryObject.class);
            //mRepositoriesList.add(personFromAPI);
        //}
        if(personFromAPI.getTotal_count() == 0)
        {
            mSearchErrorLayout.setVisibility(View.VISIBLE);
            mBackgroundLayout.setVisibility(View.GONE);
            mListView.setVisibility(View.GONE);
        }
        else {
            mSearchResultList.setAdapter(new SearchListAdapter(MainActivity.this, android.R.layout.simple_list_item_1, personFromAPI.getItems()));
        }
    }

    public static ProgressDialog getLoadingDialog(final Context context)
    {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        return progressDialog;
    }
}
