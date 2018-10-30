package com.mykins.linkin.app.kins;

import com.mykins.linkin.app.BasePresenter;
import com.mykins.linkin.bean.GroupBean;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jerry on 2017/9/4.
 */

public interface KinsContract {
    interface Presenter extends BasePresenter {

    }

    interface View {
        public void showData(ArrayList<String[]> data);

        public void showIndexer(ArrayList<String> letters, HashMap<String, Integer> lettersMappingPosition);

        public void showGroup(ArrayList<GroupBean> groups);
    }
}
