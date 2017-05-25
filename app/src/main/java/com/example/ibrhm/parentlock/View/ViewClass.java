package com.example.ibrhm.parentlock.View;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.ibrhm.parentlock.R;
import com.example.ibrhm.parentlock.SendCommand;
import com.example.ibrhm.parentlock.User;
import com.example.ibrhm.parentlock.Utils.Const;

/**
 * Created by ibrhm on 6.04.2017.
 */

public class ViewClass extends Activity {

    private User buddy;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_item_view);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/fontawesome-webfont.ttf");

        Button bookButton = (Button) findViewById(R.id.bookbutton);
        bookButton.setTypeface(custom_font);
        Button gameButton = (Button) findViewById(R.id.gamebutton);
        gameButton.setTypeface(custom_font);
        Button questionButton = (Button) findViewById(R.id.questionbutton);
        questionButton.setTypeface(custom_font);
        Button lockButton = (Button) findViewById(R.id.lockbutton);
        lockButton.setTypeface(custom_font);
        buddy = (User) getIntent().getSerializableExtra(Const.EXTRA_DATA);

        ActionBar actionBar = getActionBar();
        if(actionBar != null)
            actionBar.setTitle(buddy.getUsername());
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buddy.setSelection("Kitaplar");
                 startActivity(new Intent(ViewClass.this, SendCommand.class).putExtra(
                         Const.EXTRA_DATA,  buddy));
            }
        });
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buddy.setSelection("Oyunlar");
                startActivity(new Intent(ViewClass.this, SendCommand.class).putExtra(
                        Const.EXTRA_DATA,  buddy));

            }
        });
        questionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buddy.setSelection("Sorular");
                startActivity(new Intent(ViewClass.this, SendCommand.class).putExtra(
                        Const.EXTRA_DATA,  buddy));
            }
        });
        lockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buddy.setSelection("Sessiz");
                startActivity(new Intent(ViewClass.this, SendCommand.class).putExtra(
                        Const.EXTRA_DATA,  buddy));
            }
        });
    }
}
