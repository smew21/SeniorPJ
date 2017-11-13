package com.example.project.seniorpj.Result;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.project.seniorpj.Hamburger.HamburgerActivity;

/**
 * Created by Smew on 25/9/2560.
 */

public class AlertDialogResult extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                // Set Dialog Icon
//                .setIcon(R.mipmap.tomatopng)
                // Set Dialog Title
                .setTitle("Confirm")
                // Set Dialog Message
                .setMessage("Are you sure to save this menu ?")

                // Positive button
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something else
                        Intent intent = new Intent(getActivity(), HamburgerActivity.class);
                        startActivity(intent);
                    }
                })

                // Negative Button
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something else
                    }
                }).create();
    }


}