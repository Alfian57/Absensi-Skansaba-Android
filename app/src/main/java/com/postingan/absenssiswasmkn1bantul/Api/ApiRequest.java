package com.postingan.absenssiswasmkn1bantul.Api;

import com.postingan.absenssiswasmkn1bantul.Api.Response.ChangePasswordResponse;
import com.postingan.absenssiswasmkn1bantul.Api.Response.GetScheduleResponse;
import com.postingan.absenssiswasmkn1bantul.Api.Response.LoginDetailResponse;
import com.postingan.absenssiswasmkn1bantul.Api.Response.LogoutResponse;
import com.postingan.absenssiswasmkn1bantul.Api.Response.PresentResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiRequest {
    @FormUrlEncoded
    @POST("/api/student/login")
    Call<LoginDetailResponse> Login(
            @Field("nis") String nis,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("/api/student/present")
    Call<PresentResponse> Absen(
            @Header("Authorization") String token,
            @Field("id") String id,
            @Field("key") String key
    );

    @GET("/api/student/me/{ID}")
    Call<LoginDetailResponse> Me(
            @Header("Authorization") String token,
            @Path(value = "ID", encoded = true) Integer id
    );

    @FormUrlEncoded
    @POST("/api/student/changePassword/{ID}")
    Call<ChangePasswordResponse> UpdatePass(
            @Header("Authorization") String token,
            @Path(value = "ID", encoded = true) Integer id,
            @Field("oldPassword") String oldPassword,
            @Field("newPassword") String newPassword
    );

    @GET("/api/student/myschedules/{id}/day/{day}")
    Call<GetScheduleResponse> MySchedule(
            @Header("Authorization") String token,
            @Path(value = "id", encoded = true) Integer id,
            @Path(value = "day", encoded = true) String day

    );

    @POST("/api/student/logout")
    Call<LogoutResponse> Logout(
            @Header("Authorization") String token
    );
}
