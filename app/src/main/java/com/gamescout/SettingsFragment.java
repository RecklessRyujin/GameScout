package com.gamescout;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(android.R.layout.simple_list_item_1, container, false);

        // Replace simple layout with buttons programmatically
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        Button themeBtn = root.findViewById(R.id.btnTheme);
        Button accentBtn = root.findViewById(R.id.btnAccent);

        themeBtn.setOnClickListener(v -> showThemeDialog());
        accentBtn.setOnClickListener(v -> showAccentDialog());

        return root;
    }

    private void showThemeDialog() {

        String[] options = {"System", "Light", "Dark"};

        new AlertDialog.Builder(getContext())
                .setTitle("Select Theme")
                .setItems(options, (dialog, which) -> {

                    String selected = options[which].toLowerCase();

                    SettingsManager.setTheme(getContext(), selected);

                })
                .show();
    }

    private void showAccentDialog() {

        String[] colors = {
                "Red", "Orange", "Yellow", "Green", "Blue", "Purple", "Pink"
        };

        new AlertDialog.Builder(getContext())
                .setTitle("Select Accent Color")
                .setItems(colors, (dialog, which) -> {

                    SettingsManager.setAccent(getContext(), colors[which].toLowerCase());

                })
                .show();
    }
}
