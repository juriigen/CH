package ch.com.constructionhunt.ui.login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ch.com.constructionhunt.R;
import ch.com.constructionhunt.ui.base.BaseActivity;
import ch.com.constructionhunt.ui.driver.driverslc.DriverSlcAvtivity;
import ch.com.constructionhunt.ui.sales.SalesActivity;

/**
 * Created by jurgen on 10/10/2018.
 */
public class LoginActivity extends BaseActivity implements LoginMvpView {

    @Inject
    LoginMvpPresenter<LoginMvpView> mPresenter;

    @BindView(R.id.et_username)
    EditText mUsernameEditText;

    @BindView(R.id.et_password)
    EditText mPasswordEditText;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(LoginActivity.this);

    }

    @OnClick(R.id.btn_server_login)
    void onServerLoginClick(View v) {
        mPresenter.onServerLoginClick(mUsernameEditText.getText().toString(),
                mPasswordEditText.getText().toString());
    }


    @Override
    public void openSalesActivity() {
        Intent intent = SalesActivity.getStartIntent(LoginActivity.this);
        startActivity(intent);
        finish();
    }
    @Override
    public void openDriverSelection() {
        Intent intent = DriverSlcAvtivity.getStartIntent(LoginActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void clearEditTexts() {
        mUsernameEditText.getText().clear();
        mPasswordEditText.getText().clear();
        mPasswordEditText.clearFocus();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void openActivityOnTokenExpire() {

    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage(R.string.quit)
                .setCancelable(false)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton(R.string.no, null)
                .show();
    }
}
