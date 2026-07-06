        }
    }package com.gamescout;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Apply theme FIRST (important)
        ThemeManager.applySavedTheme(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ---------------- ACCENT COLOR APPLY ----------------

        String accent = AccentManager.getAccent(this);

        int color;

        switch (accent) {

            case "red":
                color = 0xFFE53935;
                break;

            case "orange":
                color = 0xFFFB8C00;
                break;

            case "yellow":
                color = 0xFFFDD835;
                break;

            case "green":
                color = 0xFF43A047;
                break;

            case "purple":
                color = 0xFF8E24AA;
                break;

            case "pink":
                color = 0xFFD81B60;
                break;

            default:
                color = 0xFF1E88E5;
                break;
        }

        // Toolbar (safe check so it doesn't crash if missing)
        Toolbar toolbar = findViewById(R.id.toolbar);

        if (toolbar != null) {
            toolbar.setBackgroundColor(color);
        }

        // Optional: status bar color (nice polish)
        getWindow().setStatusBarColor(color);
    }
}
}
