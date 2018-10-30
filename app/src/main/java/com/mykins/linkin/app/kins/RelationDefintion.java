package com.mykins.linkin.app.kins;

/**
 * Created by jerry on 2017/12/22.
 */

public enum RelationDefintion {
    FATHER(91),
    MOTHER(99),
    BROTHER(21),
    BROTHER_ELDER(81),
    SISTER(89),
    SISTER_ELDER(29),
    HUSBAND(51),
    WIFE(59),
    SON(11),
    DAUGHTER(19);

    int value;

    RelationDefintion(int val) {
        this.value = val;
    }
}
