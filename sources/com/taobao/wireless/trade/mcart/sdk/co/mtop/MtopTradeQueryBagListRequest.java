package com.taobao.wireless.trade.mcart.sdk.co.mtop;

import com.taobao.wireless.trade.mcart.sdk.utils.McartConstants;
import mtopsdk.mtop.domain.IMTOPDataObject;

public class MtopTradeQueryBagListRequest implements IMTOPDataObject {
    private String API_NAME = McartConstants.QUERYBAG_API_NAME;
    private boolean NEED_ECODE = true;
    private boolean NEED_SESSION = true;
    private String VERSION = McartConstants.QUERYBAG_API_VERSION;
    private String cartFrom;
    private String dataMd5 = null;
    private String exParams;
    private int extStatus;
    private String feature;
    private boolean isPage = false;
    private int netType;
    private String p = null;

    public String getCartFrom() {
        return this.cartFrom;
    }

    public void setCartFrom(String cartFrom2) {
        this.cartFrom = cartFrom2;
    }

    public String getDataMd5() {
        return this.dataMd5;
    }

    public void setDataMd5(String dataMd52) {
        this.dataMd5 = dataMd52;
    }

    public String getFeature() {
        return this.feature;
    }

    public void setFeature(String feature2) {
        this.feature = feature2;
    }

    public boolean isPage() {
        return this.isPage;
    }

    public void setPage(boolean isPage2) {
        this.isPage = isPage2;
    }

    public String getP() {
        return this.p;
    }

    public void setP(String p2) {
        this.p = p2;
    }

    public int getNetType() {
        return this.netType;
    }

    public void setNetType(int netType2) {
        this.netType = netType2;
    }

    public int getExtStatus() {
        return this.extStatus;
    }

    public void setExtStatus(int extStatus2) {
        this.extStatus = extStatus2;
    }

    public String getAPI_NAME() {
        return this.API_NAME;
    }

    public void setAPI_NAME(String aPI_NAME) {
        this.API_NAME = aPI_NAME;
    }

    public String getVERSION() {
        return this.VERSION;
    }

    public void setVERSION(String vERSION) {
        this.VERSION = vERSION;
    }

    public boolean isNEED_ECODE() {
        return this.NEED_ECODE;
    }

    public void setNEED_ECODE(boolean nEED_ECODE) {
        this.NEED_ECODE = nEED_ECODE;
    }

    public boolean isNEED_SESSION() {
        return this.NEED_SESSION;
    }

    public void setNEED_SESSION(boolean nEED_SESSION) {
        this.NEED_SESSION = nEED_SESSION;
    }

    public String getExParams() {
        return this.exParams;
    }

    public void setExParams(String exParams2) {
        this.exParams = exParams2;
    }

    public String toString() {
        return "MtopTradeQueryBagListRequest{API_NAME='" + this.API_NAME + '\'' + ", VERSION='" + this.VERSION + '\'' + ", NEED_ECODE=" + this.NEED_ECODE + ", NEED_SESSION=" + this.NEED_SESSION + ", extStatus=" + this.extStatus + ", netType=" + this.netType + ", p='" + this.p + '\'' + ", isPage=" + this.isPage + ", exParams='" + this.exParams + '\'' + ", feature='" + this.feature + '\'' + ", dataMd5='" + this.dataMd5 + '\'' + ", cartFrom='" + this.cartFrom + '\'' + '}';
    }
}