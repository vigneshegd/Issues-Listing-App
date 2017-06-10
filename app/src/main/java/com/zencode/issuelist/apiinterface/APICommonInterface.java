package com.zencode.issuelist.apiinterface;





import com.zencode.issuelist.entity.IssueCmtsResponse;
import com.zencode.issuelist.entity.IssueListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

interface APICommonInterface {

    //@FormUrlEncoded
    @GET("issues")
    Call<List<IssueListResponse>> issuList();

    @GET
    Call<List<IssueCmtsResponse>>  cmntList(@Url String url);
}
