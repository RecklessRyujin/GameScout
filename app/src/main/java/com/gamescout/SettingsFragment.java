package com.gamescout;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        Button themeBtn = root.findViewById(R.id.btnTheme);
        Button accentBtn = root.findViewById(R.id.btnAccent);

        themeBtn.setOnClickListener(v -> showThemeDialog());
        accentBtn.setOnClickListener(v -> showAccentDialog());

        return root;
    }

    private void showThemeDialog() {

    String[] options = {"System", "Light", "Dark"};

    new AlertDialog.Builder(requireContext())
            .setTitle("Select Theme")
            .setItems(options, (dialog, which) -> {

                String selected;

                switch (which) {
                    case 1:
                        selected = "light";
                        break;
                    case 2:
                        selected = "dark";
                        break;
                    default:
                        selected = "system";
                        break;
                }

                ThemeManager.saveTheme(requireContext(), selected);
                ThemeManager.applyTheme(selected);

                requireActivity().recreate(); // 👈 applies instantly
            })
            .show();
    }

    private void showAccentDialog() {

        String[] colors = {
                "Red", "Orange", "Yellow", "Green", "Blue", "Purple", "Pink"
        };

        new AlertDialog.Builder(requireContext())
                .setTitle("Select Accent Color")
                .setItems(colors, (dialog, which) -> {

                    SettingsManager.setAccent(requireContext(), colors[which].toLowerCase());
                })
                .show();
    }
}
