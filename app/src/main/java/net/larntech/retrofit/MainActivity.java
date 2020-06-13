package net.larntech.retrofit;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class MainActivity extends Activity {

    Toolbar toolbar;
    RecyclerView recyclerView;

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

    public void getAllUsers(){
        Call<List<UserResponse>> userlist = ApiClient.getUserService().getAllUsers();
        userlist.enqueue(new Callback<List<UserResponse>>() {
            @Override
            public void onResponse(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {
                if(response.isSuccessful()){
                    userResponses = response.body();
                    usersAdapter = new UsersAdapter(R.layout.row_users, userResponses, MainActivity.this.getApplicationContext());
                    recyclerView.setAdapter(usersAdapter);
                    usersAdapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                            Intent intent = new Intent();
                            intent.setClass(MainActivity.this, UserDetailsActivity.class);
                            intent.putExtra("data", userResponses.get(position));
                            startActivity(intent);
                            //finish();
                        }
                    });
                    //userResponses = new ArrayList<>();

                    Log.d("Jimmy", new Gson().toJson(userResponses));

                }
            }
            @Override
            public void onFailure(Call<List<UserResponse>> call, Throwable t) {
                Log.e("failure",t.getLocalizedMessage());

            }
        });
    }

//    @Override
//    public void ClickedUser(UserResponse userResponse) {
//
//        startActivity(new Intent(this,UserDetailsActivity.class).putExtra("data",userResponse));
//
//    }
}
