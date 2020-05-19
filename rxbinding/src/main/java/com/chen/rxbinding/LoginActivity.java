package com.chen.rxbinding;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * <pre>
 *     author : chenyizhou
 *     e-mail : chenyizhou0595@qq.com
 *     time   : 2020/04/16
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class LoginActivity extends AppCompatActivity {

    private EditText etPhone;
    private EditText etPassword;
    private Button btnLogin;

    private ValidationResult result;
    private Disposable validationDisposable;
    private Disposable loginDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setEnabled(false);

        Observable<CharSequence> phoneObservable = RxTextView.textChanges(etPhone);
        Observable<CharSequence> passwordObservable = RxTextView.textChanges(etPassword);
        validationDisposable = Observable.combineLatest(phoneObservable, passwordObservable, new BiFunction<CharSequence, CharSequence, ValidationResult>() {
            @Override
            public ValidationResult apply(CharSequence phone, CharSequence password) throws Exception {
                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
                    btnLogin.setEnabled(false);
                } else {
                    btnLogin.setEnabled(true);
                }
                ValidationResult validationResult = new ValidationResult();
                if (TextUtils.isEmpty(phone) || phone.length() == 0) {
                    validationResult.setFlag(false);
                    validationResult.setMessage("手机号不能为空！");
                } else if (phone.length() != 11 || !TextUtils.isDigitsOnly(phone)) {
                    validationResult.setFlag(false);
                    validationResult.setMessage("手机号需要11位数字！");
                } else if (TextUtils.isEmpty(password)) {
                    validationResult.setFlag(false);
                    validationResult.setMessage("密码不能为空！");
                } else {
                    validationResult.setFlag(true);
                    validationResult.setMessage("");
                }
                return validationResult;
            }
        }).subscribe(new Consumer<ValidationResult>() {
            @Override
            public void accept(ValidationResult validationResult) throws Exception {
                result = validationResult;
            }
        });

        loginDisposable = RxView.clicks(btnLogin)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (result.isFlag()) {
                            Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (validationDisposable != null && !validationDisposable.isDisposed()) {
            validationDisposable.dispose();
        }
        if (loginDisposable != null && !loginDisposable.isDisposed()) {
            loginDisposable.dispose();
        }
    }
}
