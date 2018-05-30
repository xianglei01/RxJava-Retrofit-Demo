package com.example.lei.rxjavaretrofit.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.lei.rxjavaretrofit.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.txt)
    TextView mTxt;
    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = new MainPresenter(this, new MainResposity());
    }

    @OnClick(R.id.btn)
    public void onClick() {
        if (mPresenter != null) {
            mPresenter.request();
        }
    }

    @Override
    public void showResult(String result) {
        mTxt.setText(result);
    }
}
