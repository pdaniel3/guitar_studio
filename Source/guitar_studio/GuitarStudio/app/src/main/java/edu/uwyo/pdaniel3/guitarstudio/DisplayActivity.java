package edu.uwyo.pdaniel3.guitarstudio;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class DisplayActivity extends Activity {

    private Context context = this;

    private Button mLoad, mDelete;
    private TextView tablature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        tablature = (TextView) findViewById(R.id.tablature);

        mDelete = (Button) findViewById(R.id.delete);
        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save
                final File[] files = getFileNames();
                final CharSequence[] items = new CharSequence[files.length];
                for (int i = 0; i < files.length; i++) {
                    items[i] = files[i].getName();
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Choose A Recording To Delete");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {



                        // Do something with the selection
                        final File file = files[item];
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder
                                .setTitle("Delete Recording?")
                                .setMessage("Are you sure?")
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        //Yes button clicked, do something
                                        file.delete();
                                    }
                                })
                                .setNegativeButton("No", null)						//Do nothing on no
                                .show();

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        mLoad = (Button) findViewById(R.id.load);
        mLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save
                final File[] files = getFileNames();
                final CharSequence[] items = new CharSequence[files.length];
                for (int i = 0; i < files.length; i++) {
                    items[i] = files[i].getName();
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Choose A Recording To Load");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // Do something with the selection
                        File file = files[item];
                        String text = "";
                        try {
                            FileInputStream is = new FileInputStream(file);
                            int size = 0;
                            try {
                                size = is.available();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            byte[] buffer = new byte[size];
                            try {
                                is.read(buffer);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            try {
                                is.close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            text = new String(buffer);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        tablature.setText(text);
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }

    public File[] getFileNames() {

        ArrayList<File> files = new ArrayList<File>();
        File sdCard = Environment.getExternalStorageDirectory();
        File dir = new File(sdCard.getAbsolutePath() + "/recordings");
        dir.mkdirs();
        for (File f : dir.listFiles()) {
            if (f.isFile()) {
                //String name = f.getName();
                files.add(f);
            }
        }

        return files.toArray(new File[files.size()]);
    }
}
