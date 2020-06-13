package net.larntech.retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class UsersAdapter extends BaseQuickAdapter<UserResponse, BaseViewHolder> {
    //BaseQuickAdapter<TALeaveHistoricalYearDataResponse, BaseViewHolder>
    //private List<UserResponse> userResponseList;
    private Context context;
//    private ClickedItem clickedItem;

//    public UsersAdapter(ClickedItem clickedItem) {
//        this.clickedItem = clickedItem;
//    }

//    public void setData(List<UserResponse> userResponseList) {
//        this.userResponseList = userResponseList;
//        notifyDataSetChanged();
//    }

    public UsersAdapter(int layoutResId, @Nullable List<UserResponse> data, Context context) {
        super(layoutResId, data);
        //this.userResponseList = userResponseList;
        this.context = context;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, UserResponse userResponse) {
        String prefix;
        if(userResponse.isIs_active()){
            prefix = "A";
        }else{
            prefix = "D";
        }
        baseViewHolder.setText(R.id.username, userResponse.getUsername())
                .setText(R.id.prefix, prefix);

    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

//    @NonNull
//    @Override
//    public UserAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        context = parent.getContext();
//        return new UsersAdapter.UserAdapterVH(LayoutInflater.from(context).inflate(R.layout.row_users,parent,false));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull UserAdapterVH holder, int position) {
//
//        UserResponse userResponse = userResponseList.get(position);
//
//        String username = userResponse.getUsername();
//        String prefix;
//        if(userResponse.isIs_active()){
//            prefix = "A";
//        }else{
//            prefix = "D";
//        }
//
//        holder.prefix.setText(prefix);
//        holder.username.setText(username);
//        holder.imageMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                clickedItem.ClickedUser(userResponse);
//            }
//        });
//
//    }
//
//    public interface ClickedItem{
//        public void ClickedUser(UserResponse userResponse);
//    }
//
//    @Override
//    public int getItemCount() {
//        return userResponseList.size();
//    }
//
//    public class UserAdapterVH extends RecyclerView.ViewHolder {
//
//        TextView username;
//        TextView prefix;
//        ImageView imageMore;
//
//        public UserAdapterVH(@NonNull View itemView) {
//            super(itemView);
//            username = itemView.findViewById(R.id.username);
//            prefix = itemView.findViewById(R.id.prefix);
//            imageMore = itemView.findViewById(R.id.imageMore);
//
//
//        }
//    }
}
