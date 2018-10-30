package com.mykins.linkin.app.kins.profile;

import android.arch.lifecycle.LifecycleOwner;

import com.google.gson.internal.LinkedTreeMap;
import com.mykins.linkin.bean.MyKinsServiceResponse;
import com.mykins.linkin.component.AppSchedulers;
import com.mykins.linkin.repostory.remote.KinsProfileService;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by yjn on 2017/12/18.
 */

class KinsProfilePresenter implements KinsProfileContract.Presenter {

    private AppSchedulers schedulers;
    private KinsProfileContract.View view;
    private KinsProfileService service;
    private LifecycleOwner lifecycleOwner;
    private AutoDisposeConverter<MyKinsServiceResponse<LinkedTreeMap<Object, Object>>> autoDisposeConverter;

    @Inject
    public KinsProfilePresenter(KinsProfileService service,
                                AppSchedulers schedulers,
                                KinsProfileContract.View view,
                                LifecycleOwner lifecycleOwner) {
        if (view == null) throw new IllegalArgumentException("view == null");
        this.schedulers = schedulers;
        this.view = view;
        this.service = service;
        this.lifecycleOwner = lifecycleOwner;
        this.autoDisposeConverter = AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner));
    }

    @Override
    public void localizationRelation() {
        if (view != null) {
            view.startRelationProgress();
        }
        service.getRelationTitleList()
                .observeOn(schedulers.mainThread())
                .doFinally(() -> {
                    if (view != null) view.endRelationProgress();
                })
                .as(this.autoDisposeConverter)
                .subscribe(d -> {
                            if (view != null) {
                                view.showRelationPicker(d.getData());
                            }
                        },
                        e -> {
                            if (view != null) view.showError(e.getMessage());
                        });

    }

    @Override
    public void selectAppellation(LinkedList<Integer> relationsPath) {
        view.startAppellationProgress();
        service.getRelationTitleList()
                .observeOn(schedulers.mainThread())
                .doFinally(() -> view.endAppellationProgress())
                .as(this.autoDisposeConverter)
                .subscribe(d -> {
                            if (d != null && d.getData() != null) {
                                List<String> appellations = null;
                                LinkedTreeMap<Object, Object> data = d.getData();
                                LinkedTreeMap<Object, Object> childNode = null;
                                int size = relationsPath.size();
                                for (int i = 0; i < size; i++) {
                                    int value = relationsPath.get(i);
                                    if (childNode == null) {
                                        childNode = (LinkedTreeMap<Object, Object>) data.get(value + "");
                                    } else {
                                        childNode = (LinkedTreeMap<Object, Object>) childNode.get(value + "");
                                    }

                                    if (i == size - 1) {
                                        appellations = (List<String>) childNode.get("label");
                                        break;
                                    }
                                }
                                view.showAppellationDialog(appellations);
                            }
                        },
                        e -> {

                        });
    }

}
