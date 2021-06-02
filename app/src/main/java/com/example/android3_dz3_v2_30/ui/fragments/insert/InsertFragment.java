package com.example.android3_dz3_v2_30.ui.fragments.insert;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android3_dz3_v2_30.R;
import com.example.android3_dz3_v2_30.databinding.FragmentInsertBinding;
import com.example.android3_dz3_v2_30.domain.model.Publish;
import com.example.android3_dz3_v2_30.domain.storage.MockerStorage;
import com.example.android3_dz3_v2_30.ui.fragments.home.HomeFragment;

import java.util.List;

public class InsertFragment extends Fragment {

    public static final String PUBLISH = "publish";
    private FragmentInsertBinding ui;
    private NavController navController;
    private final MockerStorage mockerStorage = new MockerStorage();
    private Publish publish;
    boolean update = false;

    private String content;
    private String title;
    private Integer userId;
    private Integer group;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ui = FragmentInsertBinding.inflate(inflater, container, false);
        return ui.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            update = getArguments().getBoolean(HomeFragment.UPDATE);
            if (update == true) {
                publish = (Publish) getArguments().getSerializable(PUBLISH);
                setTextPublish(publish);
            }
        }
        sendPublish();
    }

    private void setTextPublish(Publish publish) {
        ui.etGroup.setText(publish.getGroupID().toString());
        ui.etUser.setText(publish.getUserID().toString());
        ui.etContent.setText(publish.getContent());
        ui.etTitle.setText(publish.getTitle());
    }

    private void sendPublish() {
        ui.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
                if (update == true) {
                    updateRetrofit();
                    getArguments().putBoolean(HomeFragment.UPDATE, false);
                } else {
                    saveRetrofit();
                }
                navController.navigateUp();
            }
        });
    }

    private void updateRetrofit() {
        getTextFromTextView();
        publish.setContent(content);
        publish.setGroupID(group);
        publish.setTitle(title);
        publish.setUserID(userId);

        mockerStorage.updatePublish(publish.getId(), publish, new MockerStorage.MockerCallback<Publish>() {
            @Override
            public void onSuccess(Publish publish) {
            }

            @Override
            public void onSuccessList(List<Publish> publishes) {
            }
        });
    }

    private void saveRetrofit() {
        getTextFromTextView();
        publish = new Publish(title, content, userId, group);

        mockerStorage.createPublish(publish, new MockerStorage.MockerCallback<Publish>() {
            @Override
            public void onSuccess(Publish publish) {
            }

            @Override
            public void onSuccessList(List<Publish> publishes) {
            }
        });
    }

    private void getTextFromTextView() {
        content = ui.etContent.getText().toString();
        title = ui.etTitle.getText().toString();
        userId = Integer.valueOf(ui.etUser.getText().toString());
        group = Integer.valueOf(ui.etGroup.getText().toString());
    }
}