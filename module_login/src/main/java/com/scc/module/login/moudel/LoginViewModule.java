package com.scc.module.login.moudel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModule extends ViewModel {

    private MutableLiveData<String> logingName ;
    private MutableLiveData<String> logingPassword;

    public MutableLiveData<String> getLogingName() {
        if (logingName == null) {
            logingName = new MutableLiveData<>();
        }
        return logingName;
    }

    public void setLogingName(MutableLiveData<String> logingName) {
        this.logingName = logingName;
    }

    public MutableLiveData<String> getLogingPassword() {
        if (logingPassword == null) {
            logingPassword = new MutableLiveData<>();
        }
        return logingPassword;
    }

    public void setLogingPassWord(MutableLiveData<String> logingPassword) {
        this.logingPassword = logingPassword;
    }

}
