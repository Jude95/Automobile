package com.jude.automobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.jude.automobile.R;
import com.jude.automobile.data.DataModel;
import com.jude.automobile.domain.entities.Part;
import com.jude.automobile.presenter.AssemblePresenter;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.jude.tagview.TAGView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by zhuchenxi on 16/2/3.
 */
@RequiresPresenter(AssemblePresenter.class)
public class AssembleActivity extends BeamBaseActivity<AssemblePresenter> {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_model_name)
    TextView tvModelName;
    @Bind(R.id.img_part_avatar)
    ImageView imgPartAvatar;
    @Bind(R.id.tag_part_type)
    TAGView tagPartType;
    @Bind(R.id.tv_part_name)
    TextView tvPartName;
    @Bind(R.id.et_note)
    AppCompatEditText etNote;
    @Bind(R.id.container_part)
    RelativeLayout containerPart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assemble);
        ButterKnife.bind(this);
        tvModelName.setText(getIntent().getStringExtra("name"));
        containerPart.setOnClickListener(v -> {
            List<String> list = DataModel.getInstance().getConstantParams().getPart();
            new MaterialDialog.Builder(this)
                    .title((String) v.getTag())
                    .items(list.toArray(new String[list.size()]))
                    .itemsCallback((dialog, itemView, which, text) -> {
                        if (!TextUtils.isEmpty(text)) {
                            Intent i = new Intent(this, PartActivity.class);
                            i.putExtra("type", text);
                            startActivityForResult(i, 12);
                        }
                    })
                    .show();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            Part part  = (Part) data.getSerializableExtra("data");
            Glide.with(this).load(part.getAvatar())
                    .bitmapTransform(new CropCircleTransformation(this))
                    .into(imgPartAvatar);
            tvPartName.setText(part.getDrawingNumber());
            tagPartType.setText(part.getType());
            tagPartType.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ok, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.ok) {
            getPresenter().publishEdit(etNote.getText().toString());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
