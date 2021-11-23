package com.scc.module.main.moudel;

import com.scc.lib.utils.MLog;
import com.scc.module.main.bean.HomeFriendBean;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeFragmentViewModel extends ViewModel {
    private List<HomeFriendBean> homeFriendBeanList = new ArrayList<>();
    private MutableLiveData<List<HomeFriendBean>> mutableLiveDataHomeList;
    public LiveData<List<HomeFriendBean>> getHomeFriends(){
        if (mutableLiveDataHomeList == null) {
            mutableLiveDataHomeList = new MutableLiveData<>();
            loadHomeFriends();
        }
        return mutableLiveDataHomeList;
    }

    private void loadHomeFriends() {
        //添加数据
        homeFriendBeanList = new ArrayList<>();
        homeFriendBeanList.add(new HomeFriendBean("嬴政"));
        homeFriendBeanList.add(new HomeFriendBean("帅次"));
        homeFriendBeanList.add(new HomeFriendBean("刘邦"));
        mutableLiveDataHomeList.postValue(homeFriendBeanList);
    }
    public void setHomeFriends() {
        //新增数据
        if (homeFriendBeanList != null) {
            MLog.e("setHomeFriends:"+homeFriendBeanList.size());
            homeFriendBeanList.add(new HomeFriendBean("韩信"));
            homeFriendBeanList.add(new HomeFriendBean("萧何"));
            homeFriendBeanList.add(new HomeFriendBean("张良"));
            mutableLiveDataHomeList.postValue(homeFriendBeanList);
            MLog.e("setHomeFriends:"+ getHomeFriends().getValue().size());
        }
    }
}
