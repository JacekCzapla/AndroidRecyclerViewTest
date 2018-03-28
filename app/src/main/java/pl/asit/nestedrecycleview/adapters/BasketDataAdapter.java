package pl.asit.nestedrecycleview.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import pl.asit.nestedrecycleview.R;
import pl.asit.nestedrecycleview.model.Basket;

/**
 * Created by jczap on 28-03-2018.
 */

public class BasketDataAdapter extends RecyclerView.Adapter<BasketDataAdapter.BasketViewHolder> {

    private ArrayList<Basket> baskets;
    private Context mContext;
    private RecyclerView.RecycledViewPool recycledViewPool;

    public BasketDataAdapter(ArrayList<Basket> dataList, Context mContext) {
        this.baskets = dataList;
        this.mContext = mContext;
        recycledViewPool = new RecyclerView.RecycledViewPool();
    }

    @Override
    public BasketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_basket, null);
        BasketViewHolder basketHolder = new BasketViewHolder(v);
        return basketHolder;
    }

    @Override
    public void onBindViewHolder(BasketViewHolder holder, int position) {
        final String sectionName = baskets.get(position).getName();
        ArrayList singleSectionItems = baskets.get(position).getItems();

        holder. basketName.setText(sectionName);
        ArticleDataAdapter adapter = new ArticleDataAdapter(singleSectionItems, mContext);

        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));//, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setAdapter(adapter);
        holder.recyclerView.setRecycledViewPool(recycledViewPool);
//        holder.btnMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(), "Button More Clicked!" + sectionName, Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return (baskets != null ? baskets.size() : 0);
    }


    public class BasketViewHolder extends RecyclerView.ViewHolder {
        protected TextView basketName;
        protected RecyclerView recyclerView;


        public BasketViewHolder(View itemView) {
            super(itemView);
            this.basketName = itemView.findViewById(R.id.basketName);
            this.recyclerView = itemView.findViewById(R.id.recycler_view_list);
            //this.btnMore = itemView.findViewById(R.id.btnMore);
        }
    }
}
