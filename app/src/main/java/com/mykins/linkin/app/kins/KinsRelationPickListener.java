package com.mykins.linkin.app.kins;

import java.util.LinkedList;

/**
 * Created by yjn on 2017/12/31.
 */

public interface KinsRelationPickListener {
    void onPicked(final String relationPathTitle, final LinkedList<Integer> relations);
}
