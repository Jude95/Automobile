package com.jude.automobile.ui.viewholder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jude.automobile.R;
import com.jude.automobile.data.ManagerModel;
import com.jude.automobile.data.server.ErrorTransform;
import com.jude.automobile.domain.entities.Account;
import com.jude.automobile.ui.UserListActivity;
import com.jude.automobile.utils.ProgressDialogTransform;
import com.jude.beam.expansion.BeamBaseActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.caoyue.util.time.Time;

/**
 * Created by zhuchenxi on 16/1/27.
 */
public class UserViewHolder extends BaseViewHolder<Account> {
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_account)
    TextView tvAccount;
    @Bind(R.id.time)
    TextView time;

    Account data;

    public UserViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_user);
        ButterKnife.bind(this,itemView);
        itemView.setOnClickListener(v->{
            new MaterialDialog.Builder(getContext())
                    .title("授权")
                    .content("确定对用户"+data.getName()+"授权1年吗")
                    .positiveText("确定")
                    .onPositive((dialog, which) -> ManagerModel.getInstance().authorization(data.getId())
                            .compose(new ProgressDialogTransform<>((BeamBaseActivity) getContext(),"授权中"))
                            .compose(new ErrorTransform<>(ErrorTransform.ServerErrorHandler.AUTH_TOAST))
                            .subscribe(info -> {
                                if (getContext() instanceof UserListActivity){
                                    ((UserListActivity)getContext()).getListView().setRefreshing(true,true);
                                }
                            }))
                    .show();
        });
    }

    @Override
    public void setData(Account data) {
        this.data = data;
        tvAccount.setText(data.getAccount());
        tvName.setText(data.getName());
        if (data.getServiceBegin()<=0){
            time.setText("未购买");
        }else {
            time.setText(new Time(data.getServiceBegin()).add(Time.Field.year,1).format("yyyy/MM/dd到期"));
        }

    }
}
