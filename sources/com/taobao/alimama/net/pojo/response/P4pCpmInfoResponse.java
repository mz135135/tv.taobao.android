package com.taobao.alimama.net.pojo.response;

import mtopsdk.mtop.domain.BaseOutDo;
import mtopsdk.mtop.domain.IMTOPDataObject;

public class P4pCpmInfoResponse extends BaseOutDo implements IMTOPDataObject {
    private static final long serialVersionUID = -3871455972294398291L;
    private Object location;

    public Object getData() {
        return this.location;
    }

    public void setData(Object obj) {
        this.location = obj;
    }
}
