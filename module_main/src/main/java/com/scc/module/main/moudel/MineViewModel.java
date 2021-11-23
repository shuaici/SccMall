package com.scc.module.main.moudel;

import com.scc.module.main.bean.MineMyBean;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MineViewModel extends ViewModel {
    private List<MineMyBean> myBeanList = new ArrayList<>();
    private MutableLiveData<List<MineMyBean>> listMutableLiveData ;
    public MutableLiveData<List<MineMyBean>> getListMutableLiveData(){
        if (listMutableLiveData == null) {
            listMutableLiveData = new MutableLiveData<>();
            loadListDatas();
        }
        return listMutableLiveData;
    }

    private void loadListDatas() {
        if (myBeanList == null) {
            myBeanList = new ArrayList<>();
        }
        myBeanList.add(new MineMyBean("帅次","https://shuaici.blog.csdn.net/","三人行必有我师焉；择其善者而从之，其不善者而改之","CSDN"));
        myBeanList.add(new MineMyBean("Android帅次","https://juejin.cn/user/3905939872951085","知识的搬运工-掘金行必有我师焉。","稀土掘金"));
        myBeanList.add(new MineMyBean("帅次","https://bbs.huaweicloud.com/community/usersnew/id_1634297193216697","未知","华为云"));
        myBeanList.add(new MineMyBean("帅次","https://www.zhihu.com/people/KuenSuai","CSDN博客专家，公众号「帅次」。","知乎"));
        listMutableLiveData.postValue(myBeanList);
    }

}
