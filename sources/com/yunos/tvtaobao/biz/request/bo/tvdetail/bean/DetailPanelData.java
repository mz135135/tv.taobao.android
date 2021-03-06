package com.yunos.tvtaobao.biz.request.bo.tvdetail.bean;

import com.yunos.tvtaobao.biz.request.bo.tvdetail.bean.feizu.Services;
import com.yunos.tvtaobao.biz.request.bo.tvdetail.bean.taobao.Unit;
import com.yunos.tvtaobao.biz.request.bo.tvdetail.util.DetailModleType;
import com.yunos.tvtaobao.biz.request.bo.tvdetail.util.DetailShopType;
import com.yunos.tvtaobao.biz.request.bo.tvdetail.util.ResolveResult;
import java.io.Serializable;
import java.util.List;

public class DetailPanelData implements Serializable {
    public String buyText;
    public List<Unit.ResourceBean.CouponBeanFirst.CouponBeanItem> couponBeanItems;
    public String couponIcon;
    public String couponText;
    public String deliverGoods;
    public String depositPriceDesc;
    public String depositText;
    public DetailModleType detailModleType;
    public DetailShopType detailShopType;
    public List<Services.ServicesData.ServicesCells> feizuServices;
    public String flayerTitle;
    public String goodTitle;
    public List<String> goodsImages;
    public boolean hasCoupon;
    public String isSupportAddCart;
    public String isSupportBuy;
    public String itemId;
    public String lastPriceTip;
    public String logo;
    public String mileageTitle;
    public String nowPrice;
    public String nowPriceTitle;
    public String oldPrice;
    public String oldPriceLineThrough = "true";
    public String oldPriceTitle;
    public String orderedItemAmount;
    public String postage;
    public String presellPrice;
    public String presellPriceTitle = "";
    public ResolveResult resolveResult;
    public String rightDesc;
    public List<String> salesPromotionContent;
    public String salesPromotionIconText;
    public String salesPromotionTitle;
    public String sellerId;
    public String sellerNick;
    public String sellerType;
    public List<String> services;
    public String shopId;
    public String shopName;
    public String shopType;
    public String slogo;
    public String soldNum;
    public String status;
    public String tax;
    public TBDetailResultV6 tbDetailResultV6;
    public String typeTime;
    public Unit unit;
    public String weight;
}
