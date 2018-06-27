package com.accentrix.hku.exception;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

public abstract class ReturnIdsException extends Exception {

    private static final long serialVersionUID = 1L;

    private final List<String> undeletableObjIds;

    // For SystemExceptionMapper to create the Exception
    public ReturnIdsException(String errorMsg) {
        super(errorMsg);
        undeletableObjIds = null;
    }

    public ReturnIdsException(List<String> invalidStatusObjIds) {
        super(turnObjIdsToString(invalidStatusObjIds));
        undeletableObjIds = invalidStatusObjIds;
    }

    private static String turnObjIdsToString(List<String> invalidStatusObjIds) {
        String result = "";
        for (int i = 0; i < invalidStatusObjIds.size(); i++) {
            result = result + invalidStatusObjIds.get(i) + ", ";
        }
        return result.substring(0, result.length() - 2);
    }

    public List<String> getUndeletableObjIds() {
        if (CollectionUtils.isNotEmpty(undeletableObjIds)) {
            return undeletableObjIds;
        }

        else if (getMessage() != null) {
            String ObjIdsString = getMessage();
            ObjIdsString = ObjIdsString.substring(8);
            List<String> ObjIds = new ArrayList<String>();
            for (String idSting : ObjIdsString.split(", ")) {
                ObjIds.add(idSting);
            }
            return ObjIds;
        }

        return null;
    }
}
