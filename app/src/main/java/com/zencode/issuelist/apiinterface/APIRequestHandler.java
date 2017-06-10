package com.zencode.issuelist.apiinterface;



import com.zencode.issuelist.entity.IssueCmtsResponse;
import com.zencode.issuelist.entity.IssueListResponse;
import com.zencode.issuelist.main.BaseActivity;
import com.zencode.issuelist.utils.AppConstants;
import com.zencode.issuelist.utils.DialogManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIRequestHandler {

    private static final APIRequestHandler sInstance = new APIRequestHandler();
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private APICommonInterface mServiceInterface = retrofit.create(APICommonInterface.class);

    public static APIRequestHandler getInstance() {
        return sInstance;
    }


    //Signin API Call
    public void getIssueList(final BaseActivity baseActivity) {
        DialogManager.showProgress(baseActivity);

        mServiceInterface.issuList().enqueue(new Callback<List<IssueListResponse>>() {

            @Override
            public void onResponse(Call<List<IssueListResponse>> call, Response<List<IssueListResponse>> response) {
                DialogManager.hideProgress();
                if (response.body() != null) {
                    baseActivity.onRequestSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<IssueListResponse>> call, Throwable throwableMsg) {
                DialogManager.hideProgress();
                baseActivity.onRequestFailure(throwableMsg);
            }
        });
    }

        public void getCommentList(String url,final BaseActivity baseActivity) {
        DialogManager.showProgress(baseActivity);

        mServiceInterface.cmntList(url).enqueue(new Callback<List<IssueCmtsResponse>>() {

            @Override
            public void onResponse(Call<List<IssueCmtsResponse>> call, Response<List<IssueCmtsResponse>> response) {
                DialogManager.hideProgress();
                if (response.body() != null) {
                    baseActivity.onRequestSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<IssueCmtsResponse>> call, Throwable throwableMsg) {
                DialogManager.hideProgress();
                baseActivity.onRequestFailure(throwableMsg);
            }
        });
    }



}
