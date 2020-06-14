package net.larntech.retrofit;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends Activity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    private final String TAG = "MainActivity";
    UsersAdapter usersAdapter;
    List<UserResponse> userResponses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        getAllUsers();
    }

    public void getAllUsers()   {
        Observable<List<UserResponse>> userObservable = ApiClient.getUserService().getAllUsers();
        userObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<UserResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //Log.e("Jimmy", new Gson().toJson(d));
                    }
                    @Override
                    public void onNext(List<UserResponse> userResponses) {
                        usersAdapter = new UsersAdapter(R.layout.row_users, userResponses, MainActivity.this.getApplicationContext());
                        recyclerView.setAdapter(usersAdapter);
                        usersAdapter.setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                                Intent intent = new Intent();
                                intent.setClass(MainActivity.this, UserDetailsActivity.class);
                                intent.putExtra("data", userResponses.get(position));
                                startActivity(intent);
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


//    public void getAllUsers(){
//        Call<List<UserResponse>> userlist = ApiClient.getUserService().getAllUsers();
//        userlist.enqueue(new Callback<List<UserResponse>>() {
//            @Override
//            public void onResponse(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {
//                if(response.isSuccessful()){
//                    userResponses = response.body();
//                    usersAdapter = new UsersAdapter(R.layout.row_users, userResponses, MainActivity.this.getApplicationContext());
//                    recyclerView.setAdapter(usersAdapter);
//                    usersAdapter.setOnItemClickListener(new OnItemClickListener() {
//                        @Override
//                        public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
//                            Intent intent = new Intent();
//                            intent.setClass(MainActivity.this, UserDetailsActivity.class);
//                            intent.putExtra("data", userResponses.get(position));
//                            startActivity(intent);
//                            //finish();
//                        }
//                    });
//                    //userResponses = new ArrayList<>();
//
//                    Log.d("Jimmy", new Gson().toJson(userResponses));
//
//                }
//            }
//            @Override
//            public void onFailure(Call<List<UserResponse>> call, Throwable t) {
//                Log.e("failure",t.getLocalizedMessage());
//
//            }
//        });
//    }

//    @Override
//    public void ClickedUser(UserResponse userResponse) {
//
//        startActivity(new Intent(this,UserDetailsActivity.class).putExtra("data",userResponse));
//
//    }
}
