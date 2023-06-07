package co.il.dimobile.loginpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    GoogleSignInAccount account;
    MessageAdapter adapter;

    RequestQueue mRequestQue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mRequestQue = Volley.newRequestQueue(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            account = (GoogleSignInAccount) extras.get("user");
        }

        TextView welcome = findViewById(R.id.welcomeTV);
        welcome.setText("Welcome, " + account.getDisplayName());

        ImageView userImage = findViewById(R.id.mainUserImage);
        Glide.with(this).load(account.getPhotoUrl()).into(userImage);

        adapter = new MessageAdapter(account.getId());
        RecyclerView recycler = findViewById(R.id.chatRV);
        recycler.setHasFixedSize(false);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getApplicationContext(), 1);
        recycler.setLayoutManager(manager);

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                recycler.smoothScrollToPosition(adapter.getItemCount() - 1);
            }
        });

        recycler.addOnLayoutChangeListener((view, i, i1, i2, i3, i4, i5, i6, i7) -> {
            if (adapter.getItemCount() > 0) {
                recycler.smoothScrollToPosition(adapter.getItemCount() - 1);
            }
        });

        recycler.setAdapter(adapter);

        FloatingActionButton btn = findViewById(R.id.addMessageBtn);
        btn.setOnClickListener(view -> {
            EditText text = findViewById(R.id.messageET);
            String messageText = text.getText().toString();
            chatMessage m = new chatMessage(account.getPhotoUrl().toString(), account.getDisplayName(), account.getId(), messageText);
            adapter.addMessage(m);
            text.setText("");
            sendCloudMessage(messageText);
        });
    }

    private void sendCloudMessage(String message) {
        String serverKey = "AAAAVKKMYTk:APA91bGY5kzO-5NYqRCU-DAu2rJdF1fLnLImjbDLj8JCk8pBGN2ahHIcv8hAX2woGmCdFfdaha0m5NsQpZmExZUZK15W1aahu2Orv_9TTibBYXDQn03bC76Rivuiq9qdOfdqC6Z2uYrO";
        String topic = "chat";

        JSONObject json = new JSONObject();
        try {
            json.put("to", "/topics/" + topic);
            JSONObject notificationObj = new JSONObject();
            notificationObj.put("title", account.getDisplayName());
            notificationObj.put("body", message);
            json.put("notification", notificationObj);

            String URL = "https://fcm.googleapis.com/fcm/send";

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL,
                    json,
                    response -> Log.d("MUR", "onResponse: " + response.toString()),
                    error -> Log.d("MUR", "onError: " + error.networkResponse)
            ) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> header = new HashMap<>();
                    header.put("content-type", "application/json");
                    header.put("authorization", "key=" + serverKey);
                    return header;
                }
            };

            mRequestQue.add(request);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
