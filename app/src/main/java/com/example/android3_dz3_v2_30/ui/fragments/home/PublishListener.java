package com.example.android3_dz3_v2_30.ui.fragments.home;

import com.example.android3_dz3_v2_30.domain.model.Publish;

public interface PublishListener {
    void onClick(Publish publish, int position);
    void onLongClick(Publish publish, int position);
}
