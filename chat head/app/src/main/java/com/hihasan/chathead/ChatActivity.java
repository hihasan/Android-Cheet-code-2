package com.hihasan.chathead;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class ChatActivity extends Activity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    }
}
