package com.example.android3_dz3_v2_30.ui.fragments.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.android3_dz3_v2_30.R;
import com.example.android3_dz3_v2_30.databinding.FragmentHomeBinding;
import com.example.android3_dz3_v2_30.domain.model.Publish;
import com.example.android3_dz3_v2_30.domain.storage.MockerStorage;
import com.example.android3_dz3_v2_30.ui.fragments.insert.InsertFragment;

import java.util.List;

public class HomeFragment extends Fragment {

    public static final String UPDATE = "update";
    private HomeAdapter adapter;
    private NavController navController;
    private FragmentHomeBinding ui;
    private int currentPosition;
    private final MockerStorage mockerStorage = new MockerStorage();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ui = FragmentHomeBinding.inflate(inflater, container, false);
        return ui.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        getPublish();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new HomeAdapter();
        ui.rvPublish.setAdapter(adapter);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        setListener();
        clickToFloatBtn();
        getPublish();
    }

    private void clickToFloatBtn() {
        ui.flBtnAdd.setOnClickListener(
                v -> navController.navigate(R.id.action_homeFragment_to_insertFragment));
    }

    private void setListener() {
        adapter.setPublishListener(new PublishListener() {
            @Override
            public void onClick(Publish publish, int position) {
                currentPosition = position;
                Bundle bundle = new Bundle();
                bundle.putSerializable(InsertFragment.PUBLISH, publish);
                bundle.putBoolean(UPDATE, true);
                navController.navigate(R.id.action_homeFragment_to_insertFragment, bundle);
            }

            @Override
            public void onLongClick(Publish publish, int position) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setMessage("Удалить этот пост?");
                alertDialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mockerStorage.deletePublish(publish.getId(), new MockerStorage.MockerCallback<Publish>() {
                            @Override
                            public void onSuccess(Publish publish) {
                                adapter.deletePublish(publish, position);
                                Toast.makeText(requireContext(), "Удалён!", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onSuccessList(List<Publish> publishes) {
                            }
                        });
                    }
                });
                alertDialog.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alertDialog.create().show();
            }
        });
    }

    private void getPublish() {
        mockerStorage.getPublish(new MockerStorage.MockerCallback<Publish>() {
            @Override
            public void onSuccess(Publish publish) {
            }

            @Override
            public void onSuccessList(List<Publish> publishes) {
                adapter.addListPublish(publishes);
            }
        });
    }
}
