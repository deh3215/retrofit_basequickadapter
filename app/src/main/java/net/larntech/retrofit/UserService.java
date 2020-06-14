package net.larntech.retrofit;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {

    @GET("users/")
    Observable<List<UserResponse>> getAllUsers();

}
