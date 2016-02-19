package com.jude.automobile.ui.viewholder;

import android.content.Intent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.jude.automobile.R;
import com.jude.automobile.data.DataModel;
import com.jude.automobile.data.ImageModel;
import com.jude.automobile.data.server.ErrorTransform;
import com.jude.automobile.domain.entities.Assemble;
import com.jude.automobile.ui.AssembleActivity;
import com.jude.automobile.ui.PartDetailActivity;
import com.jude.automobile.utils.ProgressDialogTransform;
import com.jude.beam.expansion.BeamBaseActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.tagview.TAGView;
import com.jude.utils.JUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by zhuchenxi on 16/2/18.
 */
public class AssembleEditViewHolder extends BaseViewHolder<Assemble> {
    @Bind(R.id.avatar)
    ImageView avatar;
    @Bind(R.id.type)
    TAGView type;
    @Bind(R.id.delete)
    ImageView delete;
    @Bind(R.id.note)
    TextView note;

    public AssembleEditViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_assemble_edit);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void setData(Assemble data) {
        Glide.with(getContext()).load(ImageModel.getSmallImage(data.getPartAvatar()))
                .bitmapTransform(new CropCircleTransformation(getContext()))
                .into(avatar);
        type.setText(data.getPartType());
        note.setText(data.getNote());
        itemView.setOnClickListener(v->{
            Intent i = new Intent(getContext(), PartDetailActivity.class);
            i.putExtra("id",data.getPartId());
            getContext().startActivity(i);
        });
        delete.setOnClickListener(v->{
            new MaterialDialog.Builder(getContext())
                    .title("确定解除这条绑定吗?")
                    .positiveText("确定")
                    .negativeText("取消")
                    .onPositive((dialog, which) -> {
                        DataModel.getInstance().unAssemble(data.getId())
                                .compose(new ErrorTransform<>(ErrorTransform.ServerErrorHandler.AUTH_TOAST))
                                .compose(new ProgressDialogTransform<>((BeamBaseActivity) getContext(),"解除中"))
                                .subscribe(info -> {
                                    JUtils.Toast("解除成功");
                                    if (getContext() instanceof AssembleActivity){
                                        ((AssembleActivity)getContext()).getListView().setRefreshing(true,true);
                                    }
                                });
                    })
                    .show();
        });
    }
}
