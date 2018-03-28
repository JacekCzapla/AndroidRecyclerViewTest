package pl.asit.nestedrecycleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pl.asit.nestedrecycleview.adapters.BasketDataAdapter;
import pl.asit.nestedrecycleview.model.Article;
import pl.asit.nestedrecycleview.model.Basket;
import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    // na podstawie przykładu z http://khmertechtrain.tk/index.php/2017/10/03/create-a-vertical-scroll-and-horizontal-scroll-app-like-google-play-store/
    private ArrayList<Basket> allSampleData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allSampleData = getSampleData();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        //recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        BasketDataAdapter adapter = new BasketDataAdapter(allSampleData, this);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

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
        as1.add(new Article("art 1 6"));

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
}
