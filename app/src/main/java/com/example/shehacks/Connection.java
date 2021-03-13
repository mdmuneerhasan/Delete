package com.example.shehacks;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Connection {
    public static DatabaseReference getUserRef() {
        return FirebaseDatabase.getInstance().getReference("users");
    }
}
