package com.example.ibrhm.parentlock;

/**
 * Created by ibrhm on 2.03.2017.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ibrhm.parentlock.Utils.Utils;
import com.example.ibrhm.parentlock.database.NotForgetSignDB.DBOperations;
import com.example.ibrhm.parentlock.database.NotForgetSignDB.SignDB;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Login extends CustomActivity
{

    /** The username edittext. */
    private EditText user;

    /** The password edittext. */
    private EditText pwd;

    private CheckBox checkBox;

    /** Login progress dialog */
    private ProgressDialog loginProgressDlg;

    /* (non-Javadoc)
	 * @see com.chatt.custom.CustomActivity#onCreate(android.os.Bundle)
	 */
    SignDB signDB;
    DBOperations operations;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        setTouchNClick(R.id.btnLogin);
        setTouchNClick(R.id.btnReg);
        signDB = new SignDB(this);
        operations = new DBOperations();
        checkBox = (CheckBox)findViewById(R.id.checkBox);
        user = (EditText) findViewById(R.id.user);
        pwd = (EditText) findViewById(R.id.pwd);
       Cursor cursor = operations.getRegister(signDB);
        if(cursor.moveToNext())
        {
            user.setText(cursor.getString(cursor.getColumnIndex("email")));
            pwd.setText(cursor.getString(cursor.getColumnIndex("password")));
        }

    }

    /* (non-Javadoc)
     * @see com.chatt.custom.CustomActivity#onClick(android.view.View)
     */
    @Override
    public void onClick(View v)
    {
        super.onClick(v);
        if (v.getId() == R.id.btnReg)
        {
            startActivityForResult(new Intent(this, Register.class), 10);
        }
        else
        {
            // Extract form fields
            String user = this.user.getText().toString();
            String password = pwd.getText().toString();
            if (user.length() == 0 || password.length() == 0)
            {
                Utils.showDialog(this, R.string.err_fields_empty);
                return;
            }else
                {
                    if(checkBox.isChecked())
                    {
                        operations.register(user,password,signDB);
                    }

                }

            // Do the user authentication
            FirebaseAuth.getInstance().signInWithEmailAndPassword(user, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Logger.getLogger(Login.class.getName()).log(Level.ALL, "signInWithEmail:onComplete:" + task.isSuccessful());
                            loginProgressDlg.dismiss();
                            if (!task.isSuccessful()) {

                                Logger.getLogger(Login.class.getName()).log(Level.ALL, "signInWithEmail", task.getException());
                                Toast.makeText(Login.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                            else {
                                ArrayList<String> defaultRoom = new ArrayList<String>();
                                defaultRoom.add("home");
                                UserList.user = new User(task.getResult().getUser().getUid(),task.getResult().getUser().getDisplayName(), task.getResult().getUser().getEmail(),true,defaultRoom,null);
                                startActivity(new Intent(Login.this, UserList.class));
                                finish();
                            }

                        }
                    });

            loginProgressDlg = ProgressDialog.show(this, null,
                    getString(R.string.alert_wait));

        }
    }

    /* (non-Javadoc)
     * @see android.support.v4.app.FragmentActivity#onActivityResult(int, int, android.content.Intent)
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == RESULT_OK)
            finish();

    }
}

