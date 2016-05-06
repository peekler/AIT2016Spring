package hu.ait.android.recyclerviewdialogfragmentdemo.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import hu.ait.android.recyclerviewdialogfragmentdemo.R;

public class AddItemDialogFragment extends DialogFragment {

    public static final String TAG = "AddItemDialogFragment";

    public interface ItemAddListener {
        public void onItemAdded(String item);
    }

    private ItemAddListener itemAddListener;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            itemAddListener =
                    (ItemAddListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ItemAddListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Enter item name");
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.item_add_dialog, null);
        final EditText etItem = (EditText) dialogView.findViewById(R.id.etItem);

        builder.setView(dialogView)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Do nothing here because we override this button later to change the close behaviour.
                        //However, we still need this because on older versions of Android unless we
                        //pass a handler the button doesn't get instantiated
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dismiss();
                    }
                });


        AlertDialog alert = builder.create();

        return alert;
    }

    @Override
    public void onStart() {
        super.onStart();    //super.onStart() is where dialog.show() is actually called on the underlying dialog, so we have to do it after this point
        final AlertDialog d = (AlertDialog) getDialog();
        if (d != null) {
            Button positiveButton = (Button) d.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText etItem = (EditText) d.findViewById(R.id.etItem);
                    if ("".equals(etItem.getText().toString())) {
                        etItem.setError("This field can not be empty!");
                    } else {
                        itemAddListener.onItemAdded(etItem.getText().toString());
                        dismiss();
                    }
                }
            });
        }
    }
}
