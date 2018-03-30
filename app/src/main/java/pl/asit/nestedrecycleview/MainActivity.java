package pl.asit.nestedrecycleview;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pl.asit.nestedrecycleview.adapters.ArticleDataAdapter;
import pl.asit.nestedrecycleview.adapters.BasketDataAdapter;
import pl.asit.nestedrecycleview.adapters.ArticleAdapterListener;
import pl.asit.nestedrecycleview.data.SelectableListManagement;
import pl.asit.nestedrecycleview.model.Article;
import pl.asit.nestedrecycleview.model.Basket;


public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, ArticleAdapterListener, SelectableListManagement {

    // na podstawie przykładu z http://khmertechtrain.tk/index.php/2017/10/03/create-a-vertical-scroll-and-horizontal-scroll-app-like-google-play-store/
    private ArrayList<Basket> allSampleData;
    private ActionModeCallback actionModeCallback;
    private ActionMode actionMode;
    RecyclerView recyclerView;
    private BasketDataAdapter mBasketAdapter;
    private ArticleDataAdapter mArticleAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinkedHashMap<String, String> selectedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectedItems = new LinkedHashMap<String, String>();

        allSampleData = getSampleData();
        actionModeCallback = new ActionModeCallback();

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        //recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        mBasketAdapter = new BasketDataAdapter(allSampleData, this, this);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mBasketAdapter);

    }

    private ArrayList<Basket> getSampleData()
    {
        ArrayList<Basket> data = new ArrayList<Basket>();

        ArrayList<Article> as1 = new ArrayList<>();
        as1.add(new Article("art 1 1"));
        as1.add(new Article("art 1 2"));
        as1.add(new Article("art 1 3"));
        as1.add(new Article("art 1 4"));
        as1.add(new Article("art 1 5"));
        as1.add(new Article("art 1 6"));
        as1.add(new Article("art 1 7"));

        data.add(new Basket("Basket 1", as1));

        as1 = new ArrayList<Article>();
        as1.add(new Article("art 2 1"));
        as1.add(new Article("art 2 2"));
        as1.add(new Article("art 2 3"));
        as1.add(new Article("art 2 4"));
        as1.add(new Article("art 2 5"));
        as1.add(new Article("art 2 6"));
        as1.add(new Article("art 2 7"));
        as1.add(new Article("art 2 8"));
        data.add(new Basket("Basket 2", as1));

        as1 = new ArrayList<Article>();
        as1.add(new Article("art 3 1"));
        as1.add(new Article("art 3 2"));
        as1.add(new Article("art 3 3"));
        as1.add(new Article("art 3 4"));
        as1.add(new Article("art 3 5"));
        as1.add(new Article("art 3 6"));
        as1.add(new Article("art 3 7"));
        as1.add(new Article("art 3 8"));
        data.add(new Basket("Basket 3", as1));

        return  data;
    }



    @Override
    public void onRefresh() {
        // swipe refresh is performed, fetch the messages again
        swipeRefreshLayout.setRefreshing(true);
        allSampleData = getSampleData();
        mBasketAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    private void enableActionMode(int basketId, int articleId) {
        if (actionMode == null) {
            actionMode = startSupportActionMode(actionModeCallback);
        }
        toggleSelection(basketId, articleId);
    }

    @Override
    public void onAddToList(int basketPosition, int articlePosition) {

    }

    @Override
    public void onRemoveList(int basketPosition, int articlePosition) {

    }

    private class ActionModeCallback implements ActionMode.Callback {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.menu_nested_actions, menu);

            // disable swipe refresh if action mode is enabled
            swipeRefreshLayout.setEnabled(false);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.remove_from_list:
                    // delete all the selected messages

                    //dodanie zaznacoznych do listy
                    removeFromList();
                    mode.finish();
                    return true;

                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mBasketAdapter.clearSelections();
            swipeRefreshLayout.setEnabled(true);
            actionMode = null;
            selectedItems.clear();
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    //mBasketAdapter.resetAnimationIndex();
                    ;
                    //mBasketAdapter.notifyDataSetChanged();
                }
            });
        }
    }
    @Override
    public void onRowClicked(int basketId, int articleId) {
        // verify whether action mode is enabled or not
        // if enabled, change the row state to activated
        //if (mAdapter.getSelectedItemCount() > 0) {
        if (selectedItems.size() > 0) {
            enableActionMode(basketId, articleId);
        } else {
            Toast.makeText(this, "Podgląd: "+ basketId + "-" + articleId, Toast.LENGTH_SHORT).show();
            // read the message which removes bold from the row
//            StorageOIViewModel soia = soias.get(position);
//            soias.set(position, soia);
//
//            Intent in = new Intent(SoiaListActivity.this, LoadingGroupSOIAActionActivity.class);
//            in.putExtra("soia", JSONBuilder.toJsonString(soia));
//
//            TransportHeader th = TransportUtil.getTransportHeader(transport);
//            in.putExtra("th", JSONBuilder.toJsonString(th));
//
//            startActivity(in);

        }
    }

    @Override
    public void onRowLongClicked(int basketId, int articleId) {
        Toast.makeText(this, String.valueOf(basketId + "-" + articleId), Toast.LENGTH_SHORT).show();
        Basket b = (Basket) allSampleData.get(basketId);
        Article a = (Article)b.getArticles().get(articleId);

        enableActionMode(basketId, articleId);
    }

    private void toggleSelection(int basketId, int articleId) {

        mBasketAdapter.toggleSelection(basketId, articleId);
        String key = basketId+"-"+articleId;
        if(selectedItems.containsKey(key)){
            selectedItems.remove(basketId+"-"+articleId);//, basketId+"-"+articleId);
        }else{
            selectedItems.put(basketId+"-"+articleId, basketId+"-"+articleId);
        }

        int count = selectedItems.size();

        if (count == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(String.valueOf(count));
            actionMode.invalidate();
        }
    }

    private void removeFromList() {

        actionMode.finish();

        List<Article> articles = new ArrayList<Article>();

        Set set2 = selectedItems.entrySet();
        Iterator iterator2 = set2.iterator();
        while(iterator2.hasNext()) {
            Map.Entry mentry2 = (Map.Entry)iterator2.next();
            String key = (String) mentry2.getKey();
            String[] keydata = key.split("-");
            int basketId = Integer.valueOf(keydata[0]);
            int articleId = Integer.valueOf(keydata[1]);

            Article a = allSampleData.get(basketId).getArticles().get(articleId);
            articles.add(a);
        }
        selectedItems.clear();

    }
}
