package com.gamescout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {

    private Button themeBtn;
    private Button accentBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        themeBtn = root.findViewById(R.id.btnTheme);
        accentBtn = root.findViewById(R.id.btnAccent);

        themeBtn.setOnClickListener(v -> showThemeDialog());
        accentBtn.setOnClickListener(v -> showAccentDialog());

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        applyAccentToUI();
    }

    // ---------------- THEME ----------------

    private void showThemeDialog() {

        String[] options = {"system", "light", "dark"};

        new AlertDialog.Builder(requireContext())
                .setTitle("Select Theme")
                .setItems(options, (dialog, which) -> {

                    String selected = options[which];

                    ThemeManager.setTheme(requireContext(), selected);
                })
                .show();
    }

    // ---------------- ACCENT ----------------

    private void showAccentDialog() {

        String[] colors = {
                "red", "orange", "yellow", "green", "blue", "purple", "pink"
        };

        new AlertDialog.Builder(requireContext())
                .setTitle("Select Accent Color")
                .setItems(colors, (dialog, which) -> {

                    String selected = colors[which];

                    AccentManager.setAccent(requireContext(), selected);

                    applyAccentToUI(); // update instantly
                })
                .show();
    }

    // ---------------- APPLY ACCENT ----------------

    private void applyAccentToUI() {

        if (getView() == null) return;

        String accent = AccentManager.getAccent(requireContext());

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

        // Apply to buttons
        if (themeBtn != null) themeBtn.setBackgroundColor(color);
        if (accentBtn != null) accentBtn.setBackgroundColor(color);

        // Optional: status bar color
        if (getActivity() != null && getActivity().getWindow() != null) {
            getActivity().getWindow().setStatusBarColor(color);
        }
    }
}            case "pink":
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
