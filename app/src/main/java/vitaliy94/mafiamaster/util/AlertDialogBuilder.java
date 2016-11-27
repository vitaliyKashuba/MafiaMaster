package vitaliy94.mafiamaster.util;

import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

import vitaliy94.mafiamaster.R;

/**
 * Contains common for alert dialogs
 * at the moment of creation uses 3 times as introduction alert dialog
 * instead of fabric
 */
public class AlertDialogBuilder
{
    private static DialogInterface.OnClickListener onOkListener = new DialogInterface.OnClickListener()
    {
        @Override
        public void onClick(DialogInterface dialogInterface, int i)
        {
            dialogInterface.cancel();
        }
    };

    private static DialogInterface.OnClickListener onDontShowAgainListener = new DialogInterface.OnClickListener()
    {
        @Override
        public void onClick(DialogInterface dialogInterface, int i)
        {
            //TODO disable this intro
            dialogInterface.cancel();
        }
    };

    /**
     * builds intro dialog
     *
     * @param builder should be initialized in activity
     * @param msg mssage to display
     * @return alert to call .show();
     */
    public static AlertDialog buildIntroAlertDialog(AlertDialog.Builder builder, String msg)
    {
        builder.setTitle(R.string.intro_title)
                //.setMultiChoiceItems(s, b, onCheckListener)
                .setMessage(msg)
                .setPositiveButton("ok", onOkListener)
                .setNegativeButton(R.string.intro_do_not_show_again, onDontShowAgainListener);

        return builder.create();
    }
}
