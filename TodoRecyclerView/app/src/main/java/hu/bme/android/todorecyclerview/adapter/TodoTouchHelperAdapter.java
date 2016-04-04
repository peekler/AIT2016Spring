package hu.bme.android.todorecyclerview.adapter;

public interface TodoTouchHelperAdapter {

    void onItemDismiss(int position);

    void onItemMove(int fromPosition, int toPosition);

}
