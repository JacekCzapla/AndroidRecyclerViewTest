package pl.asit.nestedrecycleview.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    private ArticleAdapterListener listener;

    public BasketDataAdapter(ArrayList<Basket> dataList, Context mContext, ArticleAdapterListener listener) {
        this.baskets = dataList;
        this.mContext = mContext;
        this.listener = listener;
        recycledViewPool = new RecyclerView.RecycledViewPool();
    }

    @Override
    public BasketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_basket, null);
        BasketViewHolder basketHolder = new BasketViewHolder(v);
        return basketHolder;
    }

    public void toggleSelection(int basketId, int articleId) {

        ArticleDataAdapter aadapt = (ArticleDataAdapter)this.basketHolder.recyclerView.getAdapter();
        //aadapt.
        ArticleDataAdapter.ArticleViewHolder h = (ArticleDataAdapter.ArticleViewHolder)this.basketHolder.recyclerView.getChildViewHolder(
                this.basketHolder.recyclerView.getChildAt(basketId)
        );


//        if(h != null){
//            h.
//        }
//        //this.basketHolder.recyclerView.getChildAt(basketId);
//
//                holder.recyclerView.getChildAt()
//        currentSelectedIndex = pos;
//        //if (selectedItems.get(pos, false)) {
//        if (selectedItemsInt.containsKey(pos)){
//
//            //selectedItems.delete(pos);
//            selectedItemsInt.remove(pos);
//            animationItemsIndex.delete(pos);
//        } else {
//            //selectedItems.put(pos, true);
//            selectedItemsInt.put(pos, true);
//            animationItemsIndex.put(pos, true);
//        }
//        notifyItemChanged(pos);
    }

    public void clearSelections() {
        //reverseAllAnimations = true;
        //selectedItems.clear();
        //selectedItemsInt.clear();
        notifyDataSetChanged();
    }

    private BasketViewHolder basketHolder;
    private ArticleDataAdapter articleAdapter;
    @Override
    public void onBindViewHolder(BasketViewHolder holder, int position) {
        this.basketHolder = holder;
        final String sectionName = baskets.get(position).getName();
        ArrayList articles = baskets.get(position).getArticles();


        holder. basketName.setText(sectionName);
        articleAdapter = new ArticleDataAdapter(articles, mContext, listener, position);



        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));//, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setAdapter(articleAdapter);
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

//    public void resetAnimationIndex() {
//
//        animationItemsIndex.clear();
//    }


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
