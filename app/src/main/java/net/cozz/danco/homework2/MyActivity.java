package net.cozz.danco.homework2;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MyActivity extends Activity {
    private CapitalsDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        final String[] capitals = getResources().getStringArray(R.array.capitals);

        datasource = new CapitalsDataSource(this);
        try {
            datasource.open();
            int i = 0;
            for (String state : getResources().getStringArray(R.array.states)) {
                datasource.addCapital(state, capitals[i++]);
            }
            List <Capital> capitalsList = datasource.getCapitals();
            Log.d("", capitalsList.toString());
        } catch (SQLException e) {
            Log.d("MyActivity", "unable to open datasource");
        }


        final String[] states = getResources().getStringArray(R.array.states);
        final ListView listView = (ListView) findViewById(R.id.listview);

        final ArrayList statesList = new ArrayList<String>(Arrays.asList(states));

        final OptimizedCustomAdapter adapter =
                new OptimizedCustomAdapter(this, states);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
        /*
            WebView webView = (WebView) findViewById(R.id.activity_my_web_view);
            webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url)
            {
                webView.loadUrl("javascript:callMe(\""+data_val+"\")");

            }
            });

         */
                Toast.makeText(getApplicationContext(),
                        "Clicked on " + states[position] + ", capital = " +
                                datasource.getCapital(states[position]), Toast.LENGTH_SHORT)
                        .show();

                Intent i = new Intent(Intent.ACTION_WEB_SEARCH);
                String term = "capital:" + states[position];
                i.putExtra(SearchManager.QUERY, term);
                startActivity(i);
            }
        });

    }


    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent(intent);
    }


    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            // handles a search query
            String query = intent.getStringExtra(SearchManager.QUERY);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
