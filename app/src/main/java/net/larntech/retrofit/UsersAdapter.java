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
    private Context context;

    public UsersAdapter(int layoutResId, @Nullable List<UserResponse> data, Context context) {
        super(layoutResId, data);
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
}
