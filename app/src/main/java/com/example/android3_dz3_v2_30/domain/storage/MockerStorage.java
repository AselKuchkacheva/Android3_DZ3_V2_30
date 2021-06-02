package com.example.android3_dz3_v2_30.domain.storage;

import android.util.Log;

import com.example.android3_dz3_v2_30.domain.model.Publish;
import com.example.android3_dz3_v2_30.domain.remote.MockerApi;
import com.example.android3_dz3_v2_30.domain.remote.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MockerStorage {

    public void getPublish(MockerCallback<Publish> callback) {
        RetrofitBuilder.getInstance().getPublish().enqueue(new Callback<List<Publish>>() {
            @Override
            public void onResponse(Call<List<Publish>> call, Response<List<Publish>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccessList(response.body());

                } else {
                    callback.onFailure("Request error...");
                }
            }

            @Override
            public void onFailure(Call<List<Publish>> call, Throwable t) {
                callback.onFailure(t.getLocalizedMessage());
            }
        });
    }

    public void deletePublish(int id, MockerCallback<Publish> callback) {
        RetrofitBuilder.getInstance().deletePublish(id).enqueue(new Callback<Publish>() {
            @Override
            public void onResponse(Call<Publish> call, Response<Publish> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());

                } else {
                    callback.onFailure("Request error...");
                }
            }

            @Override
            public void onFailure(Call<Publish> call, Throwable t) {
                callback.onFailure(t.getLocalizedMessage());
            }
        });
    }

    public void createPublish(Publish publishBody, MockerCallback<Publish> callback){
        RetrofitBuilder.getInstance().createPublish(publishBody).enqueue(new Callback<Publish>() {
            @Override
            public void onResponse(Call<Publish> call, Response<Publish> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                }else {
                    callback.onFailure("Request error...");
                }
            }
            @Override
            public void onFailure(Call<Publish> call, Throwable t) {
                callback.onFailure(t.getLocalizedMessage());
            }
        });
    }

    public void updatePublish(int id, Publish publishBody, MockerCallback<Publish> callback){
        RetrofitBuilder.getInstance().updatePublish(id, publishBody).enqueue(new Callback<Publish>() {
            @Override
            public void onResponse(Call<Publish> call, Response<Publish> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                }else {
                    callback.onFailure("Request error...");
                }
            }

            @Override
            public void onFailure(Call<Publish> call, Throwable t) {
                callback.onFailure(t.getLocalizedMessage());
            }
        });

    }

    public interface MockerCallback<Data> {
        void onSuccess(Data data);

        void onSuccessList(List<Data> dataList);

        default void onFailure(String errorMsg) {
            Log.d("tag", errorMsg);
        }

        ;
    }
}
