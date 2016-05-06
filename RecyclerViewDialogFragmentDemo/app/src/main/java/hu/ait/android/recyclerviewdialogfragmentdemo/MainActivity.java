package hu.ait.android.recyclerviewdialogfragmentdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import hu.ait.android.recyclerviewdialogfragmentdemo.adapter.ItemsAdapter;
import hu.ait.android.recyclerviewdialogfragmentdemo.data.Item;
import hu.ait.android.recyclerviewdialogfragmentdemo.fragment.AddItemDialogFragment;

public class MainActivity extends AppCompatActivity implements AddItemDialogFragment.ItemAddListener {

    private ItemsAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemsAdapter = new ItemsAdapter(this);
        final RecyclerView recyclerView =
                (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        // RecyclerView layout manager
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(itemsAdapter);

        FloatingActionButton fab =
                (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddItemDialogFragment().show(getSupportFragmentManager(),AddItemDialogFragment.TAG);
            }
        });
    }

    public void showItemText(String itemText) {
        Toast.makeText(MainActivity.this, "ITEM: " + itemText, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemAdded(String itemText) {
        itemsAdapter.addItem(new Item(itemText));
    }
}
