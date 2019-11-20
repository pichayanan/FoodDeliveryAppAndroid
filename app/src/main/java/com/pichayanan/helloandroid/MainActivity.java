package com.pichayanan.helloandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.pichayanan.helloandroid.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testFirestrore();
    }



    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayNameActivity.class);
        EditText editText = findViewById(R.id.editText);
        String message = editText.getText().toString();
        Log.d("editText", message);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Login Sucessful.", Toast.LENGTH_SHORT).show();
    }
    public void openListPage (View view){
        Intent intent = new Intent(this,ListActivity.class);
        startActivity(intent);
    }

    public void testFirestrore() {
        {
            final String TAG = "testFirestore";
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("recommended")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d(TAG, document.getId() + " => " + document.getData());
                                }
                            } else {
                                Log.w(TAG, "Error getting documents.", task.getException());
                            }
                        }
                    });
        }
    }
}