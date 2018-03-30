package pl.asit.nestedrecycleview.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import pl.asit.nestedrecycleview.R;
import pl.asit.nestedrecycleview.model.Article;

/**
 * Created by jczap on 28-03-2018.
 */

public class ArticleDataAdapter extends RecyclerView.Adapter<ArticleDataAdapter.ArticleViewHolder> {

    private ArrayList<Article> articles;
    private Context mContext;

    private ArticleAdapterListener listener;
    private LinkedHashMap<Integer, Boolean> selectedItemsInt;
    private ViewGroup parentView;
    private int parentId;

    // array used to perform multiple animation at once
    private SparseBooleanArray animationItemsIndex;
    private boolean reverseAllAnimations = false;

    private static int currentSelectedIndex = -1;

    public ArticleDataAdapter(ArrayList<Article> itemModels, Context mContext, ArticleAdapterListener listener, int parentId) {
        this.articles = itemModels;
        this.mContext = mContext;
        this.listener = listener;
        this.parentId = parentId;
        selectedItemsInt = new LinkedHashMap<Integer, Boolean>();
        animationItemsIndex = new SparseBooleanArray();
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_article, null);
        parentView = parent;
        //parent.

        ArticleViewHolder articleViewHolder = new ArticleViewHolder(v);
        return articleViewHolder;
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, final int position) {
        Article article = articles.get(position);
        holder.articleName.setText(article.getName());

        // handle icon animation
        applyIconAnimation(holder, position);

        holder.articleContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSelection(position);
                listener.onRowClicked(parentId, position);
            }
        });
        holder.articleContainer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                toggleSelection(position);
                listener.onRowLongClicked(parentId, position);
                view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                return true;
            }
        });
    }
    public void toggleSelection(int pos) {


        currentSelectedIndex = pos;
        //if (selectedItems.get(pos, false)) {
        if (animationItemsIndex.get(pos, false)) {
        //if (selectedItemsInt.containsKey(pos)){

            //selectedItems.delete(pos);
            //selectedItemsInt.remove(pos);
            animationItemsIndex.delete(pos);
        } else {
            //selectedItems.put(pos, true);
            //selectedItemsInt.put(pos, true);
            animationItemsIndex.put(pos, true);
        }
        notifyItemChanged(pos);
    }
    @Override
    public int getItemCount() {
        return (null != articles ? articles.size() : 0);
    }

    private void applyIconAnimation(ArticleViewHolder holder, int position) {

        //if (selectedItems.get(position, false)) {
        //if(selectedItemsInt.containsKey(position)){
        if (animationItemsIndex.get(position, false)) {
            holder.iconFront.setVisibility(View.GONE);
            resetIconYAxis(holder.iconBack);
            holder.iconBack.setVisibility(View.VISIBLE);
            holder.iconBack.setAlpha(1);
            if (currentSelectedIndex == position) {
                //FlipAnimator.flipView(mContext, holder.iconBack, holder.iconFront, true);
                resetCurrentIndex();
            }
        } else {
            holder.iconBack.setVisibility(View.GONE);
            resetIconYAxis(holder.iconFront);
            holder.iconFront.setVisibility(View.VISIBLE);
            holder.iconFront.setAlpha(1);
            if ((reverseAllAnimations && animationItemsIndex.get(position, false)) || currentSelectedIndex == position) {
                //FlipAnimator.flipView(mContext, holder.iconBack, holder.iconFront, false);
                resetCurrentIndex();
            }
        }
    }
    private void resetCurrentIndex() {
        currentSelectedIndex = -1;
    }

    // As the views will be reused, sometimes the icon appears as
    // flipped because older view is reused. Reset the Y-axis to 0
    private void resetIconYAxis(View view) {
        if (view.getRotationY() != 0) {
            view.setRotationY(0);
        }
    }

    public void resetAnimationIndex() {
        reverseAllAnimations = false;
        animationItemsIndex.clear();
    }

    public void clearSelections() {
        //reverseAllAnimations = true;
        //selectedItems.clear();
        selectedItemsInt.clear();
        notifyDataSetChanged();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{

        protected TextView articleName;
        public LinearLayout articleContainer;
        public RelativeLayout iconContainer, iconBack, iconFront;
        //protected ImageView itemImage;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            this.articleName = itemView.findViewById(R.id.articleName);
            //this.itemImage = itemView.findViewById(R.id.itemImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), articleName.getText(), Toast.LENGTH_SHORT).show();
                }
            });
            itemView.setOnLongClickListener(this);
            articleContainer = itemView.findViewById(R.id.article_container);

            iconContainer = itemView.findViewById(R.id.icon_container);
            iconBack = itemView.findViewById(R.id.icon_back);
            iconFront = itemView.findViewById(R.id.icon_front);
        }

        @Override
        public boolean onLongClick(View view) {
            int i = getAdapterPosition();

            listener.onRowLongClicked(parentId, i);
            view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
            return true;
        }
    }




}
