package com.postingan.absenssiswasmkn1bantul.Api;

import com.postingan.absenssiswasmkn1bantul.Api.Response.ChangePasswordResponse;
import com.postingan.absenssiswasmkn1bantul.Api.Response.GetAttendanceResponse;
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
import retrofit2.http.Query;

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
            @Field("key") String key
    );

    @GET("/api/student/me")
    Call<LoginDetailResponse> Me(
            @Header("Authorization") String token
    );

    @FormUrlEncoded
    @POST("/api/student/change-password")
    Call<ChangePasswordResponse> UpdatePass(
            @Header("Authorization") String token,
            @Field("oldPassword") String oldPassword,
            @Field("newPassword") String newPassword
    );

    @GET("/api/student/my-schedules")
    Call<GetScheduleResponse> MySchedule(
            @Header("Authorization") String token,
            @Query("day") String day

    );

    @POST("/api/student/logout")
    Call<LogoutResponse> Logout(
            @Header("Authorization") String token
    );

    @GET("/api/student/my-attendance")
    Call<GetAttendanceResponse> MyAttendance(
            @Header("Authorization") String token,
            @Query("month") Integer month,
            @Query("year") Integer year
    );
}