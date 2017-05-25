package com.example.ibrhm.parentlock;

/**
 * Created by ibrhm on 2.03.2017.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibrhm.parentlock.Scale.Scale;
import com.example.ibrhm.parentlock.Utils.Const;
import com.example.ibrhm.parentlock.View.ViewClass;
import com.example.ibrhm.parentlock.database.ChildDb.ChildDB;
import com.example.ibrhm.parentlock.database.ChildDb.DbOperation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * The Class UserList is the Activity class. It shows a list of all users of
 * this app. It also shows the Offline/Online status of users.
 */
public class UserList extends CustomActivity
{

    Cursor cursor;
    DatabaseReference database;
    Button registerBtn;
    /** The SendCommand list. */
    private ArrayList<User> uList;
    private EditText emailTxt;
    /** The user. */
    public static User user;
    DbOperation operation;
    ChildDB childDB;
    /* (non-Javadoc)
     * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
     */
    Scale scale;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list);
        // Get reference to the Firebase database

        operations();
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResume();
                if(emailTxt.getText()!=null)
                {
                    operation.register(emailTxt.getText().toString(), childDB);
                    emailTxt.setText("");
                }else
                    {
                        Toast.makeText(getApplicationContext(),"please write email address",Toast.LENGTH_LONG).show();
                    }
            }
        });
    }

    public void operations()
    {
        operation =new DbOperation();
        childDB =new ChildDB(this);
        database  = FirebaseDatabase.getInstance().getReference();
        emailTxt =(EditText) findViewById(R.id.edittext);
        registerBtn = (Button)findViewById(R.id.btnRegister);
        getActionBar().setDisplayHomeAsUpEnabled(false);
        updateUserStatus(true);

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        updateUserStatus(false);
    }

    /* (non-Javadoc)
     * @see android.support.v4.app.FragmentActivity#onResume()
     */
    @Override
    protected void onResume()
    {
        super.onResume();
        loadUserList();

    }

    /**
     * Update user status.
     *
     * @param online
     *            true if user is online
     */
    private void updateUserStatus(boolean online)
    {
        // TODO: Add user status updates
    }

    /**
     * Load list of users.
     */
    private void loadUserList()
    {
        final ProgressDialog dia = ProgressDialog.show(this, null,
                getString(R.string.alert_loading));

        // Pull the users list once no sync required.
        database.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {dia.dismiss();
                long size  = dataSnapshot.getChildrenCount();
                if(size == 0) {
                    Toast.makeText(UserList.this,
                            R.string.msg_no_user_found,
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                uList = new ArrayList<User>();

                    for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    User user = ds.getValue(User.class);
                    Logger.getLogger(UserList.class.getName()).log(Level.ALL,user.getUsername());

                        if(!user.getId().contentEquals(FirebaseAuth.getInstance().getCurrentUser().getUid()))

                    cursor = operation.getRegister(childDB);

                        StringBuilder builder = new StringBuilder();
                            while(cursor.moveToNext()){
                                long id = cursor.getLong(cursor.getColumnIndex("id"));
                                String email = cursor.getString((cursor.getColumnIndex("email")));

                                if(email.equals(user.getEmail()))
                                    uList.add(user);
                            }



                }
                ListView list = (ListView) findViewById(R.id.list);
                list.setAdapter(new UserAdapter());
                list.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0,
                                            View arg1, int pos, long arg3)
                    {
                        startActivity(new Intent(UserList.this,
                                ViewClass.class).putExtra(
                                Const.EXTRA_DATA,  uList.get(pos)));
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * The Class UserAdapter is the adapter class for User ListView. This
     * adapter shows the user name and it's only online status for each item.
     */
    private class UserAdapter extends BaseAdapter
    {

        /* (non-Javadoc)
         * @see android.widget.Adapter#getCount()
         */
        @Override
        public int getCount()
        {
            return uList.size();
        }

        /* (non-Javadoc)
         * @see android.widget.Adapter#getItem(int)
         */
        @Override
        public User getItem(int arg0)
        {
            return uList.get(arg0);
        }

        /* (non-Javadoc)
         * @see android.widget.Adapter#getItemId(int)
         */
        @Override
        public long getItemId(int arg0)
        {
            return arg0;
        }

        /* (non-Javadoc)
         * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
         */
        @Override
        public View getView(int pos, View v, ViewGroup arg2)
        {
            if (v == null)
                v = getLayoutInflater().inflate(R.layout.send_item, null);

            User c = getItem(pos);
            TextView lbl = (TextView) v;
            lbl.setText(c.getUsername());
            lbl.setCompoundDrawablesWithIntrinsicBounds(
                    c.isOnline() ? R.drawable.ic_online
                            : R.drawable.ic_offline, 0, R.drawable.arrow, 0);

            return v;
        }

    }
}
/*   <activity
            android:name="UserList"
            android:label="ParentLock"
            android:screenOrientation="portrait"
            android:windowSoftInputMode="stateHidden|adjustResize" >
        </activity>*/