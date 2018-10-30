package com.mykins.linkin.app.kins.profile;

import com.google.gson.internal.LinkedTreeMap;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by yjn on 2017/12/18.
 */

public interface KinsProfileContract {

    interface Presenter {
        /**
         * 关系定位
         */
        void localizationRelation();

        void selectAppellation(LinkedList<Integer> relationsPath);
    }

    interface View {
        /**
         * 加载关系称谓时候现实Loading
         */
        void startRelationProgress();

        void endRelationProgress();

        void startAppellationProgress();
        void endAppellationProgress();

        /**
         * 关系定位选择对话框
         * @param relationTitleList
         */
        void showRelationPicker(LinkedTreeMap<Object, Object> relationTitleList);

        void showAppellationDialog(List<String> appellations);

        void showError(String error);
    }
}
