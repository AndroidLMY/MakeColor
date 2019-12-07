package com.androidlmy.makecolor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidlmy.makecolor.R;
import com.androidlmy.makecolor.bean.FriendListBean;
import com.androidlmy.makecolor.utils.GlideImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imkit.RongIM;

/**
 * @功能:
 * @Creat 2019/11/26 9:46
 * @User Lmy
 * @Compony zaituvideo
 */
public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendViewHolder> {



    private Context context;
    private List<FriendListBean.DataBean> friendBeanList;

    public FriendAdapter(Context context, List<FriendListBean.DataBean> friendBeanList) {
        this.context = context;

        this.friendBeanList = friendBeanList;
    }

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_friend, parent, false);
        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder holder, int position) {
        holder.tvName.setText(friendBeanList.get(position).getName());
        GlideImageLoader.loader(context, friendBeanList.get(position).getHeadurl(), holder.icHead);
        holder.llAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RongIM.getInstance().startPrivateChat(context, friendBeanList.get(position).getUser_id(), friendBeanList.get(position).getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return friendBeanList.size();
    }

    class FriendViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ic_head)
        ImageView icHead;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.ll_all)
        LinearLayout llAll;
        public FriendViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
