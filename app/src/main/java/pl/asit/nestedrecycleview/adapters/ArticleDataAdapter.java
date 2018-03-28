package pl.asit.nestedrecycleview.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import pl.asit.nestedrecycleview.R;
import pl.asit.nestedrecycleview.model.Article;

/**
 * Created by jczap on 28-03-2018.
 */

public class ArticleDataAdapter extends RecyclerView.Adapter<ArticleDataAdapter.ArticleViewHolder> {

    private ArrayList<Article> articles;
    private Context mContext;

    public ArticleDataAdapter(ArrayList<Article> itemModels, Context mContext) {
        this.articles = itemModels;
        this.mContext = mContext;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_article, null);
        ArticleViewHolder articleViewHolder = new ArticleViewHolder(v);
        return articleViewHolder;
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.articleName.setText(article.getName());
    }

    @Override
    public int getItemCount() {
        return (null != articles ? articles.size() : 0);
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {

        protected TextView articleName;
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
        }
    }
}
