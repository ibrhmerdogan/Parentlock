package com.example.ibrhm.parentlock;

/**
 * Created by ibrhm on 2.03.2017.
 */

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ibrhm.parentlock.Operations.StringParserf;
import com.example.ibrhm.parentlock.Utils.Const;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * The Class SendCommand is the Activity class that holds main book_view screen. It shows
 * all the conversation messages between two users and also allows the user to
 * book_view and receive messages.
 */
public class SendCommand extends CustomActivity implements AdapterView.OnItemSelectedListener {

    /**
     * The Conversation list.
     */
    private ArrayList<Conversation> convList;

    /**
     * The book_view adapter.
     */
    private ChatAdapter adp;

    /**
     * The Editext to compose the message.
     */
   // EditText txt;
    private String item,item1 = null;
    private Spinner spinner;


    /**
     * The user name of buddy.
     */
    ListView list;
    private User buddy;
    Button btn,questionBtn,QuestionBtn1;
    TextView sendTxt,sendTxt1;
    ImageView image,image1,image2,image3;
    String replecement;
    /**
     * The date of last message in conversation.
     */
    private Date lastMsgDate;

    /* (non-Javadoc)
     * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final StringParserf stringParserf = new StringParserf();
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/fontawesome-webfont.ttf");
        buddy = (User) getIntent().getSerializableExtra(Const.EXTRA_DATA);
     try {

        if(buddy.getSelection().equals("Kitaplar"))
        {
            setContentView(R.layout.book_view);
            btn = (Button)findViewById(R.id.btnSend);
            final String[] constraint = { "Sayfa Sayisi Seçiniz","10", "15", "20", "25", "30", "40", "50" };
            convList = new ArrayList<Conversation>();
            list = (ListView) findViewById(R.id.list);
            adp = new ChatAdapter();
            list.setAdapter(adp);
            list.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
            list.setStackFromBottom(true);
            spinner = (Spinner) findViewById(R.id.spinner);
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, constraint);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(dataAdapter);
            spinner.setOnItemSelectedListener(this);
            sendTxt = (TextView)findViewById(R.id.textView7);
            sendTxt.setText("");
            sendTxt1 = (TextView)findViewById(R.id.textView8);
            sendTxt1.setText("");
            image = (ImageView)findViewById(R.id.image);
            image1 = (ImageView)findViewById(R.id.imageView);
            image2 = (ImageView)findViewById(R.id.imageView2);
            image3 = (ImageView)findViewById(R.id.imageView3);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        sendTxt1.setText("karamazovkardesler");
                        replecement = "karamazovkardesler";
                        item = stringParserf.stringParse(item1, replecement);
                        btn.setClickable(true);

                }
            });

        }else if(buddy.getSelection().equals("Oyunlar"))
        {

            setContentView(R.layout.game_view);
            btn = (Button)findViewById(R.id.btnSend);
            convList = new ArrayList<Conversation>();
            final String[] constraint = { "" };
            convList = new ArrayList<Conversation>();
            list = (ListView) findViewById(R.id.list);
            adp = new ChatAdapter();
            list.setAdapter(adp);
            list.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
            list.setStackFromBottom(true);
            spinner = (Spinner) findViewById(R.id.spinner);
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, constraint);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(dataAdapter);
            spinner.setOnItemSelectedListener(this);
            image = (ImageView)findViewById(R.id.imageView4);
            image1 = (ImageView)findViewById(R.id.imageView5);
            sendTxt = (TextView)findViewById(R.id.textView7);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btn.setClickable(true);
                   sendTxt.setText("ColorGame");
                    replecement = "ColorGame";
                    item = stringParserf.stringParse(item1,replecement);
                }
            });
            image1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btn.setClickable(true);
                    sendTxt.setText("English");
                    replecement = "English";
                    item = stringParserf.stringParse(item1,replecement);
                }
            });


        }else if(buddy.getSelection().equals("Sorular"))
        {

            setContentView(R.layout.questions_view);
            btn = (Button)findViewById(R.id.btnSend);
            final String[] constraint = {"Soru Sayısı Seçiniz","10", "15", "20", "25", "30", "40", "50" };
            convList = new ArrayList<Conversation>();
           list = (ListView) findViewById(R.id.list);
            adp = new ChatAdapter();
            list.setAdapter(adp);
            list.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
            list.setStackFromBottom(true);
            spinner = (Spinner) findViewById(R.id.spinner);
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, constraint);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(dataAdapter);
            spinner.setOnItemSelectedListener(this);
            sendTxt = (TextView)findViewById(R.id.textView7);
            sendTxt.setText("");
            sendTxt1 = (TextView)findViewById(R.id.textView8);
            sendTxt1.setText("");
            image = (ImageView)findViewById(R.id.math);
            image1 = (ImageView)findViewById(R.id.turkish);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        sendTxt1.setText("Math");
                        replecement = "Math";
                        btn.setClickable(true);
                        item = stringParserf.stringParse(item1,replecement);

                }
            });
            image1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendTxt1.setText("Turkish");
                    replecement = "Turkish";
                        btn.setClickable(true);
                    item = stringParserf.stringParse(item1,replecement);
                }
            });

        }else if(buddy.getSelection().equals("Sessiz"))
        {

            setContentView(R.layout.locked_view);
            btn = (Button)findViewById(R.id.btnSend);
            final String[] constraint = { "Süre Seçiniz","10", "15", "20", "25", "30", "40", "50" };
            convList = new ArrayList<Conversation>();
            list = (ListView) findViewById(R.id.list);
            adp = new ChatAdapter();
            list.setAdapter(adp);
            list.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
            list.setStackFromBottom(true);
            Button questionBtn = (Button) findViewById(R.id.buttonlock);
            questionBtn.setTypeface(custom_font);
            Button QuestionBtn1 = (Button) findViewById(R.id.buttonslient);
            QuestionBtn1.setTypeface(custom_font);
            spinner = (Spinner) findViewById(R.id.spinner);
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, constraint);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(dataAdapter);
            spinner.setOnItemSelectedListener(this);
            sendTxt = (TextView)findViewById(R.id.textView7);
            sendTxt1 = (TextView)findViewById(R.id.textView8);
            sendTxt1.setText("");

            questionBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    sendTxt1.setText("Locked");
                    replecement = "Locked";
                    item = stringParserf.stringParse(item1,replecement);
                        btn.setClickable(true);
                }
            });
            QuestionBtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    sendTxt1.setText("Slient");
                    replecement = "Slient";
                    item = stringParserf.stringParse(item1,replecement);

                }
            });


        }

     }catch (Exception e)
     {
         e.printStackTrace();
     }
      // txt.setInputType(InputType.TYPE_CLASS_TEXT
              //  | InputType.TYPE_TEXT_FLAG_MULTI_LINE);


        sendMessage();
        btn=(Button)findViewById(R.id.btnSend);
      //  setTouchNClick(R.id.btnSend);

        buddy = (User) getIntent().getSerializableExtra(Const.EXTRA_DATA);
        ActionBar actionBar = getActionBar();
        if(actionBar != null)
            actionBar.setTitle(buddy.getSelection());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendNotificationToUser(buddy.getId(),item);
            }
        });

    }
    public static void sendNotificationToUser(String user, final String message) {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        final DatabaseReference notifications = rootRef.child("notificationRequests");

        Map notification = new HashMap<>();
        notification.put("username", user);
        notification.put("message", message);

        notifications.push().setValue(notification);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
       item1= parent.getItemAtPosition(position).toString();
        sendTxt.setText(item1);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onResume() {
        super.onResume();
       // loadConversationList();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
       // if (v.getId() == R.id.btnSend) {
         //   sendMessage();
       // }

    }


    private void sendMessage() {
       // if (spinner.length() == 0)
           // return;

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(spinner.getWindowToken(), 0);



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null) {
            final Conversation conversation = new Conversation(item,
                    Calendar.getInstance().getTime(),
                    user.getUid(),
                    buddy.getId(),
                    "");
            conversation.setStatus(Conversation.STATUS_SENDING);
            convList.add(conversation);
            final String key = FirebaseDatabase.getInstance()
                    .getReference("messages")
                    .push().getKey();
            FirebaseDatabase.getInstance().getReference("messages").child(key)
                    .setValue(conversation)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                               @Override
                                               public void onComplete(@NonNull Task<Void> task) {
                                                   if (task.isSuccessful()) {
                                                       convList.get(convList.indexOf(conversation)).setStatus(Conversation.STATUS_SENT);
                                                   } else {
                                                       convList.get(convList.indexOf(conversation)).setStatus(Conversation.STATUS_FAILED);
                                                   }
                                                   FirebaseDatabase.getInstance()
                                                           .getReference("messages")
                                                           .child(key).setValue(convList.get(convList.indexOf(conversation)))
                                                           .addOnCompleteListener(new
                                                                                          OnCompleteListener<Void>() {
                                                                                              @Override
                                                                                              public void onComplete(@NonNull Task<Void> task) {
                                                                                                  adp.notifyDataSetChanged();
                                                                                              }
                                                                                          });

                                               }
                                           }
                    );
        }
        adp.notifyDataSetChanged();
      //  txt.setText(null);
    }

    /**
     * Load the conversation list from Parse server and save the date of last
     * message that will be used to load only recent new messages
     */
   /* private void loadConversationList() {

        FirebaseDatabase.getInstance().getReference("messages").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user != null) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        String msg = (String) map.get("msg");
                        String receiver = (String) map.get("receiver");
                        String sender =(String) map.get("sender");
                        String photoUrl = (String) map.get("photoUrl");
                        Date date = new Date();
                        date.getDate();
                        Conversation conversation = new Conversation(msg,date,sender,receiver,photoUrl);
                        if (conversation.getReceiver().contentEquals(user.getUid()) || conversation.getSender().contentEquals(user.getUid())) {
                            convList.add(conversation);
                            if (lastMsgDate == null
                                    || lastMsgDate.before(conversation.getDate()))
                                lastMsgDate = conversation.getDate();

                            adp.notifyDataSetChanged();

                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }*/

    /**
     * The Class ChatAdapter is the adapter class for SendCommand ListView. This
     * adapter shows the Sent or Receieved SendCommand message in each list item.
     */
    private class ChatAdapter extends BaseAdapter {

        /* (non-Javadoc)
         * @see android.widget.Adapter#getCount()
         */
        @Override
        public int getCount() {
            return convList.size();
        }

        /* (non-Javadoc)
         * @see android.widget.Adapter#getItem(int)
         */
        @Override
        public Conversation getItem(int arg0) {
            return convList.get(arg0);
        }

        /* (non-Javadoc)
         * @see android.widget.Adapter#getItemId(int)
         */
        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        /* (non-Javadoc)
         * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
         */
        @SuppressLint("InflateParams")
        @Override
        public View getView(int pos, View v, ViewGroup arg2) {
         /*   Conversation c = getItem(pos);
            if (c.isSent()) {
                v = getLayoutInflater().inflate(R.layout.send_item_sent, null);


                TextView lbl = (TextView) v.findViewById(R.id.lbl1);
                lbl.setText(DateUtils.getRelativeDateTimeString(SendCommand.this, c
                                .getDate().getTime(), DateUtils.SECOND_IN_MILLIS,
                        DateUtils.DAY_IN_MILLIS, 0));

                lbl = (TextView) v.findViewById(R.id.lbl2);
                lbl.setText(c.getMsg());

                lbl = (TextView) v.findViewById(R.id.lbl3);
                if (c.isSent()) {
                    if (c.getStatus() == Conversation.STATUS_SENT)
                        lbl.setText(R.string.delivered_text);
                    else {
                        if (c.getStatus() == Conversation.STATUS_SENDING)
                            lbl.setText(R.string.sending_text);
                        else {
                            lbl.setText(R.string.failed_text);
                        }
                    }
                } else
                    lbl.setText("");
            } else
                v = getLayoutInflater().inflate(R.layout.send_item_rcv, null);*/
                return v;

        }


    }

    /* (non-Javadoc)
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
