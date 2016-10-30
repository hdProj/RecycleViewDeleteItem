package com.hd.hedong.recycleviewdeleteitem;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.recycleview)
    RecyclerView recyclerView;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.tv_complete)
    TextView tvComplete;
    @BindView(R.id.tv_edt)
    TextView tvEdt;

    ExmpleAdapter adapter;
    private List<Boolean> listCheck;
    private List<String> list;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
        onClickListnner();

    }

    private void initView() {
        list = new ArrayList<>();
        listCheck = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("这是第" + i + "个item");
            listCheck.add(false);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ExmpleAdapter(this, list, listCheck);
        recyclerView.setAdapter(adapter);


        adapter.setOnItemListener(new ExmpleAdapter.OnItemClickListener() {

            @Override
            public void setOnItemClickListener(View view, int position) {
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void setDeleteListener(View view, int position) {
                adapter.notifyItemRemoved(position);
                list.remove(position);
                adapter.notifyItemRangeChanged(0, adapter.getItemCount());
                notifyDataSetChanged();
            }
        });


    }


    private void onClickListnner() {
        tvEdt.setOnClickListener(this);
        tvComplete.setOnClickListener(this);
        tvDelete.setOnClickListener(this);
    }

    //编辑
    protected void edtListItem() {
        adapter.isShow = true;
        tvComplete.setVisibility(View.VISIBLE);
        tvDelete.setVisibility(View.VISIBLE);
        tvEdt.setVisibility(View.GONE);
        for (int i = 0; i < listCheck.size(); i++) {
            listCheck.set(i, true);
        }
        notifyDataSetChanged();
    }

    //删除
    protected void deleteAllItem() {
        if (list.size() > 0) {
            list.clear();
            notifyDataSetChanged();
        }
    }

    //完成
    protected void complete() {
        tvComplete.setVisibility(View.GONE);
        tvDelete.setVisibility(View.GONE);
        tvEdt.setVisibility(View.VISIBLE);
        if (adapter.getItemCount() > 0) {
            for (int i = 0; i < listCheck.size(); i++) {
                listCheck.set(i, false);
            }
        }
        adapter.isShow = false;
        notifyDataSetChanged();
    }

    private void notifyDataSetChanged() {
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_edt:
                edtListItem();
                break;
            case R.id.tv_complete:
                complete();
                break;
            case R.id.tv_delete:
                deleteAllItem();
                break;
        }
    }
}
