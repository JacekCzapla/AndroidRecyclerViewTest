package pl.asit.nestedrecycleview.adapters;

/**
 * Created by jczap on 29-03-2018.
 */

public interface ArticleAdapterListener {

//        void onIconClicked(int position);
//        void onIconImportantClicked(int position);
        void onRowClicked(int basketId, int articleId);

    void onRowLongClicked(int basketId, int articleId);
}
