package com.example.android3_dz3_v2_30.domain.remote;

import com.example.android3_dz3_v2_30.databinding.ItemPublishBinding;
import com.example.android3_dz3_v2_30.domain.model.Publish;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MockerApi {

    @GET("posts")
    Call<List<Publish>> getPublish();

    @DELETE("posts/{id}")
    Call<Publish> deletePublish(
            @Path("id") Integer publishId);

    @POST("posts")
    Call<Publish> createPublish(
            @Body Publish publish
    );

    @PUT("posts/{id}")
    Call<Publish> updatePublish(
            @Path("id") Integer publishId,
            @Body Publish publish
    );
}
