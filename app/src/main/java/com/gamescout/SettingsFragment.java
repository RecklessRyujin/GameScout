package com.gamescout;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        // Buttons
        View themeBtn = root.findViewById(R.id.btnTheme);
        View accentBtn = root.findViewById(R.id.btnAccent);

        themeBtn.setOnClickListener(v -> showThemeDialog());
        accentBtn.setOnClickListener(v -> showAccentDialog());

        // 🔥 APPLY SAVED ACCENT ON OPEN
        String savedAccent = AccentManager.getAccent(requireContext());
        applyAccentToUI(savedAccent);

        return root;
    }

    // =========================
    // THEME DIALOG
    // =========================
    private void showThemeDialog() {

        String[] options = {"System", "Light", "Dark"};

        new AlertDialog.Builder(requireContext())
                .setTitle("Select Theme")
                .setItems(options, (dialog, which) -> {

                    String selected = options[which].toLowerCase();

                    ThemeManager.setTheme(requireContext(), selected);
                })
                .show();
    }

    // =========================
    // ACCENT DIALOG (FIXED)
    // =========================
    private void showAccentDialog() {

        String[] colors = {
                "red", "orange", "yellow", "green", "blue", "purple", "pink"
        };

        new AlertDialog.Builder(requireContext())
                .setTitle("Select Accent Color")
                .setItems(colors, (dialog, which) -> {

                    String selected = colors[which];

                    // Save
                    AccentManager.setAccent(requireContext(), selected);

                    // 🔥 APPLY INSTANTLY (THIS WAS MISSING)
                    applyAccentToUI(selected);
                })
                .show();
    }

    // =========================
    // APPLY ACCENT TO UI
    // =========================
    private void applyAccentToUI(String color) {

        int parsedColor;

        switch (color) {
            case "red":
                parsedColor = 0xFFE53935;
                break;

            case "orange":
                parsedColor = 0xFFFB8C00;
                break;

            case "yellow":
                parsedColor = 0xFFFDD835;
                break;

            case "green":
                parsedColor = 0xFF43A047;
                break;

            case "purple":
                parsedColor = 0xFF8E24AA;
                break;

            case "pink":
                parsedColor = 0xFFD81B60;
                break;

            default:
                parsedColor = 0xFF1E88E5;
                break;
        }

        // Apply to whole fragment background (simple but works)
        View root = getView();
        if (root != null) {
            root.setBackgroundColor(parsedColor);
        }
    }
}
