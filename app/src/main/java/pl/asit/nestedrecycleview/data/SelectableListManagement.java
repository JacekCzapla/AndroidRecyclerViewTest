package pl.asit.nestedrecycleview.data;

/**
 * Created by jczap on 29-03-2018.
 */

public interface SelectableListManagement {
    void onAddToList(int basketPosition, int articlePosition);
    void onRemoveList(int basketPosition, int articlePosition);
}
