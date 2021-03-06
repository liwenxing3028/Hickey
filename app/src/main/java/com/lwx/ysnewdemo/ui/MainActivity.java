package com.lwx.ysnewdemo.ui;

import android.os.Bundle;
import android.view.View;

import com.lwx.ysnewdemo.R;
import com.lwx.ysnewdemo.base.BaseRxDetailActivity;
import com.lwx.ysnewdemo.callback.JsonConvert;
import com.lwx.ysnewdemo.model.ServerModel;
import com.lwx.ysnewdemo.rx.LwxResponse;
import com.lwx.ysnewdemo.utils.Urls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.ObservableBody;
import com.lzy.okrx2.adapter.ObservableResponse;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends BaseRxDetailActivity {
    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setTitle("OkRx基本请求");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Activity销毁时，取消网络请求
        dispose();
    }

    @OnClick(R.id.commonRequest)
    public void commonRequest(View view) {
        OkGo.<String>post(Urls.URL_METHOD)//
                .headers("aaa", "111")//
                .params("bbb", "222")//
                .converter(new StringConvert())//
                .adapt(new ObservableResponse<String>())//
                .subscribeOn(Schedulers.io())//
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        showLoading();
                    }
                })//
                .observeOn(AndroidSchedulers.mainThread())//
                .subscribe(new Observer<Response<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(@NonNull Response<String> response) {
                        handleResponse(response);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        showToast("请求失败");
                        handleError(null);
                    }

                    @Override
                    public void onComplete() {
                        dismissLoading();
                    }
                });
    }

    @OnClick(R.id.jsonRequest)
    public void jsonRequest(View view) {
        OkGo.<LwxResponse<ServerModel>>get(Urls.URL_JSONOBJECT)//
                .headers("aaa", "111")//
                .params("bbb", "222")//
                .converter(new JsonConvert<LwxResponse<ServerModel>>() {})//
                .adapt(new ObservableBody<LwxResponse<ServerModel>>())//
                .subscribeOn(Schedulers.io())//
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        showLoading();
                    }
                })//
                .map(new Function<LwxResponse<ServerModel>, ServerModel>() {
                    @Override
                    public ServerModel apply(@NonNull LwxResponse<ServerModel> response) throws Exception {
                        return response.data;
                    }
                })//
                .observeOn(AndroidSchedulers.mainThread())//
                .subscribe(new Observer<ServerModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(@NonNull ServerModel serverModel) {
                        handleResponse(serverModel);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();            //请求失败
                        showToast("请求失败");
                        handleError(null);
                    }

                    @Override
                    public void onComplete() {
                        dismissLoading();
                    }
                });
    }

    @OnClick(R.id.jsonArrayRequest)
    public void jsonArrayRequest(View view) {
        OkGo.<LwxResponse<List<ServerModel>>>get(Urls.URL_JSONARRAY)//
                .headers("aaa", "111")//
                .params("bbb", "222")//
                .converter(new JsonConvert<LwxResponse<List<ServerModel>>>() {})//
                .adapt(new ObservableBody<LwxResponse<List<ServerModel>>>())//
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        showLoading();
                    }
                })//
                .map(new Function<LwxResponse<List<ServerModel>>, List<ServerModel>>() {
                    @Override
                    public List<ServerModel> apply(@NonNull LwxResponse<List<ServerModel>> response) throws Exception {
                        return response.data;
                    }
                })//
                .observeOn(AndroidSchedulers.mainThread())//
                .subscribe(new Observer<List<ServerModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(@NonNull List<ServerModel> response) {
                        handleResponse(response);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();            //请求失败
                        showToast("请求失败");
                        handleError(null);
                    }

                    @Override
                    public void onComplete() {
                        dismissLoading();
                    }
                });
    }

    @OnClick(R.id.upString)
    public void upString(View view) {
        OkGo.<String>post(Urls.URL_TEXT_UPLOAD)//
                .headers("aaa", "111")//
                .upString("上传的文本。。。")//
                .converter(new StringConvert())//
                .adapt(new ObservableResponse<String>())//
                .subscribeOn(Schedulers.io())//
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        showLoading();
                    }
                })//
                .observeOn(AndroidSchedulers.mainThread())//
                .subscribe(new Observer<Response<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(@NonNull Response<String> response) {
                        handleResponse(response);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        showToast("请求失败");
                        handleError(null);
                    }

                    @Override
                    public void onComplete() {
                        dismissLoading();
                    }
                });
    }

    @OnClick(R.id.upJson)
    public void upJson(View view) {
        HashMap<String, String> params = new HashMap<>();
        params.put("key1", "value1");
        params.put("key2", "这里是需要提交的json格式数据");
        params.put("key3", "也可以使用三方工具将对象转成json字符串");
        params.put("key4", "其实你怎么高兴怎么写都行");
        JSONObject jsonObject = new JSONObject(params);

        OkGo.<String>post(Urls.URL_TEXT_UPLOAD)//
                .headers("aaa", "111")//
                .upJson(jsonObject)//
                .converter(new StringConvert())//
                .adapt(new ObservableResponse<String>())//
                .subscribeOn(Schedulers.io())//
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        showLoading();
                    }
                })//
                .observeOn(AndroidSchedulers.mainThread())//
                .subscribe(new Observer<Response<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(@NonNull Response<String> response) {
                        handleResponse(response);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        showToast("请求失败");
                        handleError(null);
                    }

                    @Override
                    public void onComplete() {
                        dismissLoading();
                    }
                });
    }
}
