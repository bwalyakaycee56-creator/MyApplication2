package com.example.myapplication;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class NotificationsActivity extends AppCompatActivity {

    private RecyclerView rvNotifications;
    private NotificationsAdapter adapter;
    private final List<NotificationItem> allNotifications = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        loadNotifications();

        rvNotifications = findViewById(R.id.rvNotifications);
        rvNotifications.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NotificationsAdapter(allNotifications, item ->
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show());
        rvNotifications.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                filterNotifications(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }

    private void loadNotifications() {
        allNotifications.add(new NotificationItem(
                NotificationItem.Type.REGISTRATION,
                "Registration confirmed", "Your account has been verified",
                "Today - 10:24 AM", true, false));

        allNotifications.add(new NotificationItem(
                NotificationItem.Type.GROUP_MEETING,
                "Group meeting updated", "New schedule for Lab Group 02",
                "Yesterday - 04:32 PM", false, false));

        allNotifications.add(new NotificationItem(
                NotificationItem.Type.SYSTEM,
                "System message", "Welcome to MUConnect",
                "Yesterday - 09:12 AM", false, false));

        allNotifications.add(new NotificationItem(
                NotificationItem.Type.GROUP_CHANGE,
                "Group change request", "Your request for G01 is pending",
                "Sep 12, 2025 - 02:45 PM", false, true));

        allNotifications.add(new NotificationItem(
                NotificationItem.Type.ANNOUNCEMENT,
                "Announcement", "Exams timetable is now available",
                "Sep 10, 2025 - 10:23 AM", false, true));

        allNotifications.add(new NotificationItem(
                NotificationItem.Type.GROUP_FULL,
                "Group full", "G03 is now full (15/15)",
                "Sep 8, 2025 - 04:17 PM", false, true));
    }

    private void filterNotifications(int tabPosition) {
        List<NotificationItem> filtered = new ArrayList<>();
        for (NotificationItem item : allNotifications) {
            switch (tabPosition) {
                case 1: // Unread
                    if (item.isUnread()) filtered.add(item);
                    break;
                case 2: // Important
                    if (item.isImportant()) filtered.add(item);
                    break;
                default: // All
                    filtered.add(item);
            }
        }
        adapter.updateItems(filtered);
    }
}