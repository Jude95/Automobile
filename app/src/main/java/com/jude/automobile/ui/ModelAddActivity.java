package com.jude.automobile.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jude.automobile.R;
import com.jude.automobile.data.DataModel;
import com.jude.automobile.domain.entities.Model;
import com.jude.automobile.presenter.ModelAddPresenter;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.data.BeamDataActivity;
import com.jude.utils.JUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhuchenxi on 16/2/2.
 */
@RequiresPresenter(ModelAddPresenter.class)
public class ModelAddActivity extends BeamDataActivity<ModelAddPresenter, Model> {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_type)
    TextView tvType;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_power)
    TextView tvPower;
    @Bind(R.id.tv_displacement)
    TextView tvDisplacement;
    @Bind(R.id.tv_cylinders)
    TextView tvCylinders;
    @Bind(R.id.tv_valve)
    TextView tvValve;
    @Bind(R.id.tv_structure)
    TextView tvStructure;
    @Bind(R.id.tv_drive)
    TextView tvDrive;
    @Bind(R.id.tv_engine)
    TextView tvEngine;
    @Bind(R.id.tv_fuel)
    TextView tvFuel;
    @Bind(R.id.tv_fuel_feed)
    TextView tvFuelFeed;
    @Bind(R.id.tv_tecdoc)
    TextView tvTecdoc;
    @Bind(R.id.tv_engine_code)
    TextView tvEngineCode;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_word)
    TextView tvWord;
    @Bind(R.id.tv_displacement_tech)
    TextView tvDisplacementTech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_add);
        ButterKnife.bind(this);
        tvName.setOnClickListener(v -> {
            new MaterialDialog.Builder(this)
                    .title((String) v.getTag())
                    .inputType(InputType.TYPE_CLASS_TEXT)
                    .input("", getPresenter().data.getName(), (dialog, input) -> {
                        if (input.toString().trim().isEmpty()) {
                            JUtils.Toast("名字不能为空");
                            return;
                        }
                        getPresenter().data.setName(input.toString());
                        getPresenter().refresh();
                    }).show();
        });
        tvPower.setOnClickListener(v -> {
            new MaterialDialog.Builder(this)
                    .title((String) v.getTag())
                    .inputType(InputType.TYPE_CLASS_TEXT)
                    .input("", getPresenter().data.getPower(), (dialog, input) -> {
                        getPresenter().data.setPower(input.toString());
                        getPresenter().refresh();
                    }).show();
        });
        tvDisplacement.setOnClickListener(v -> {
            new MaterialDialog.Builder(this)
                    .title((String) v.getTag())
                    .inputType(InputType.TYPE_CLASS_TEXT)
                    .input("", getPresenter().data.getDisplacement(), (dialog, input) -> {
                        getPresenter().data.setDisplacement(input.toString());
                        getPresenter().refresh();
                    }).show();
        });
        tvCylinders.setOnClickListener(v -> {
            new MaterialDialog.Builder(this)
                    .title((String) v.getTag())
                    .inputType(InputType.TYPE_CLASS_NUMBER)
                    .input("", getPresenter().data.getCylinders(), (dialog, input) -> {
                        getPresenter().data.setCylinders(input.toString());
                        getPresenter().refresh();
                    }).show();
        });
        tvValve.setOnClickListener(v -> {
            new MaterialDialog.Builder(this)
                    .title((String) v.getTag())
                    .inputType(InputType.TYPE_CLASS_NUMBER)
                    .input("", getPresenter().data.getValve(), (dialog, input) -> {
                        getPresenter().data.setValve(input.toString());
                        getPresenter().refresh();
                    }).show();
        });
        tvStructure.setOnClickListener(v -> {
            List<String> list = DataModel.getInstance().getConstantParams().getStructure();
            int index = list.indexOf(getPresenter().data.getStructure());
            new MaterialDialog.Builder(this)
                    .title((String) v.getTag())
                    .items(list.toArray(new String[list.size()]))
                    .itemsCallbackSingleChoice(index, (dialog, view, which, text) -> {
                        if (!TextUtils.isEmpty(text))
                            getPresenter().data.setStructure(text.toString());
                        getPresenter().refresh();
                        return true;
                    })
                    .positiveText("确定")
                    .show();
        });
        tvDrive.setOnClickListener(v -> {
            List<String> list = DataModel.getInstance().getConstantParams().getDrive();
            int index = list.indexOf(getPresenter().data.getDrive());
            new MaterialDialog.Builder(this)
                    .title((String) v.getTag())
                    .items(list.toArray(new String[list.size()]))
                    .itemsCallbackSingleChoice(index, (dialog, view, which, text) -> {
                        if (!TextUtils.isEmpty(text))
                            getPresenter().data.setDrive(text.toString());
                        getPresenter().refresh();
                        return true;
                    })
                    .positiveText("确定")
                    .show();
        });
        tvEngine.setOnClickListener(v -> {
            List<String> list = DataModel.getInstance().getConstantParams().getEngine();
            int index = list.indexOf(getPresenter().data.getEngine());
            new MaterialDialog.Builder(this)
                    .title((String) v.getTag())
                    .items(list.toArray(new String[list.size()]))
                    .itemsCallbackSingleChoice(index, (dialog, view, which, text) -> {
                        if (!TextUtils.isEmpty(text))
                            getPresenter().data.setEngine(text.toString());
                        getPresenter().refresh();
                        return true;
                    })
                    .positiveText("确定")
                    .show();
        });
        tvFuel.setOnClickListener(v -> {
            List<String> list = DataModel.getInstance().getConstantParams().getFuel();
            int index = list.indexOf(getPresenter().data.getEngine());
            new MaterialDialog.Builder(this)
                    .title((String) v.getTag())
                    .items(list.toArray(new String[list.size()]))
                    .itemsCallbackSingleChoice(index, (dialog, view, which, text) -> {
                        if (!TextUtils.isEmpty(text))
                        getPresenter().data.setFuel(text.toString());
                        getPresenter().refresh();
                        return true;
                    })
                    .positiveText("确定")
                    .show();
        });
        tvFuelFeed.setOnClickListener(v -> {
            List<String> list = DataModel.getInstance().getConstantParams().getFuelFeed();
            int index = list.indexOf(getPresenter().data.getFuelFeed());
            new MaterialDialog.Builder(this)
                    .title((String) v.getTag())
                    .items(list.toArray(new String[list.size()]))
                    .itemsCallbackSingleChoice(index, (dialog, view, which, text) -> {
                        if (!TextUtils.isEmpty(text))
                            getPresenter().data.setFuelFeed(text.toString());
                        getPresenter().refresh();
                        return true;
                    })
                    .positiveText("确定")
                    .show();
        });
        tvTecdoc.setOnClickListener(v -> {
            new MaterialDialog.Builder(this)
                    .title((String) v.getTag())
                    .inputType(InputType.TYPE_CLASS_TEXT)
                    .input("", getPresenter().data.getTecdoc(), (dialog, input) -> {
                        getPresenter().data.setTecdoc(input.toString());
                        getPresenter().refresh();
                    }).show();
        });
        tvEngineCode.setOnClickListener(v -> {
            new MaterialDialog.Builder(this)
                    .title((String) v.getTag())
                    .inputType(InputType.TYPE_CLASS_TEXT)
                    .input("", getPresenter().data.getEngineCode(), (dialog, input) -> {
                        getPresenter().data.setEngineCode(input.toString());
                        getPresenter().refresh();
                    }).show();
        });
        tvTime.setOnClickListener(v -> {
            new MaterialDialog.Builder(this)
                    .title((String) v.getTag())
                    .inputType(InputType.TYPE_CLASS_TEXT)
                    .input("", getPresenter().data.getTime(), (dialog, input) -> {
                        getPresenter().data.setTime(input.toString());
                        getPresenter().refresh();
                    }).show();
        });
        tvDisplacementTech.setOnClickListener(v -> {
            new MaterialDialog.Builder(this)
                    .title((String) v.getTag())
                    .inputType(InputType.TYPE_CLASS_NUMBER)
                    .input("", getPresenter().data.getDisplacementTech(), (dialog, input) -> {
                        getPresenter().data.setDisplacementTech(input.toString());
                        getPresenter().refresh();
                    }).show();
        });
        tvWord.setOnClickListener(v -> {
            new MaterialDialog.Builder(this)
                    .title((String) v.getTag())
                    .inputType(InputType.TYPE_CLASS_TEXT)
                    .input("", getPresenter().data.getWord(), (dialog, input) -> {
                        getPresenter().data.setWord(input.toString());
                        getPresenter().refresh();
                    }).show();
        });
    }

    @Override
    public void setData(Model data) {
        setText(tvType, data.getTypeName());
        setText(tvName, data.getName());
        setText(tvPower, data.getPower());
        setText(tvDisplacement, data.getDisplacement());
        setText(tvCylinders, data.getCylinders());
        setText(tvValve, data.getValve());
        setText(tvStructure, data.getStructure());
        setText(tvDrive, data.getDrive());
        setText(tvEngine, data.getEngine());
        setText(tvFuel, data.getFuel());
        setText(tvFuelFeed, data.getFuelFeed());
        setText(tvTecdoc, data.getTecdoc());
        setText(tvEngineCode, data.getEngineCode());
        setText(tvTime, data.getTime());
        setText(tvDisplacementTech, data.getDisplacementTech());
        setText(tvWord, data.getWord());
    }

    void setText(TextView t, String text) {
        if (TextUtils.isEmpty(text)) {
            t.setText((String) t.getTag());
        } else {
            t.setText(text);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_ok, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.ok) {
            getPresenter().publishEdit();
            return true;
        }else if (item.getItemId() == R.id.delete){
            new MaterialDialog.Builder(this)
                    .title("删除")
                    .content("你确定要删除本车款吗?")
                    .positiveText("确定")
                    .negativeText("取消")
                    .onPositive((dialog, which) -> getPresenter().delete())
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }
}
