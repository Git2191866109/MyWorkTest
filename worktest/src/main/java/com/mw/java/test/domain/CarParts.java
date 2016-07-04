package com.mw.java.test.domain;

/**
 * 汽车配件
 *
 * @author liuwei01
 */
public class CarParts {

    private String docid;
    private String id;
    private String name;
    private String nameLen;
    private String nameAlias;
    private String brandName;
    private String carStyleName;
    private String factoryNumber;
    private String goodsModel;
    private String unit;
    private String specifications;
    private String categoryId;
    private String brandId;
    private String carStyleId;
    private String categoryAttId;
    private String shelvesTime;
    private String isHstock;
    private String isCerGoods;
    private String isStarProducts;
    private String salesVolume;
    private String evaluation;
    private String price;
    private String shopName;
    private String suitableCarName;
    private String imgUrl;
    private String isImgUrl;
    private String storeId;
    private String storeName;
    private String docWeight;
    private String collectionNum;
    private String purchasePersonTime;
    private String visitsNum;
    private String browsingTimes;
    private String provinceId;
    private String cityId;
    private String regionId;
    private String sendAddressId;
    private String sendAddress;
    private String factoryEnterpriseId;
    private String dealerEnterpriseId;

    private String smallCarStyleId;
    private String bigCarStyleId;
    private String smallCarStyleName;
    private String bigCarStyleName;

    private String goodsId;
    private String attrcode;

    /*聚合分类id*/
    private String mergerCategoryId;

    public String getMergerCategoryId() {
        return mergerCategoryId;
    }

    public void setMergerCategoryId(String mergerCategoryId) {
        this.mergerCategoryId = mergerCategoryId;
    }

    public String getAttrcode() {
        return attrcode;
    }

    public void setAttrcode(String attrcode) {
        this.attrcode = attrcode;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameLen() {
        return nameLen;
    }

    public void setNameLen(String nameLen) {
        this.nameLen = nameLen;
    }

    public String getNameAlias() {
        return nameAlias;
    }

    public void setNameAlias(String nameAlias) {
        this.nameAlias = nameAlias;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCarStyleName() {
        return carStyleName;
    }

    public void setCarStyleName(String carStyleName) {
        this.carStyleName = carStyleName;
    }

    public String getFactoryNumber() {
        return factoryNumber;
    }

    public void setFactoryNumber(String factoryNumber) {
        this.factoryNumber = factoryNumber;
    }

    public String getGoodsModel() {
        return goodsModel;
    }

    public void setGoodsModel(String goodsModel) {
        this.goodsModel = goodsModel;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getCarStyleId() {
        return carStyleId;
    }

    public void setCarStyleId(String carStyleId) {
        this.carStyleId = carStyleId;
    }

    public String getCategoryAttId() {
        return categoryAttId;
    }

    public void setCategoryAttId(String categoryAttId) {
        this.categoryAttId = categoryAttId;
    }

    public String getShelvesTime() {
        return shelvesTime;
    }

    public void setShelvesTime(String shelvesTime) {
        this.shelvesTime = shelvesTime;
    }

    public String getIsHstock() {
        return isHstock;
    }

    public void setIsHstock(String isHstock) {
        if (isHstock.equals("0")) {
            isHstock = "1";
        } else {
            isHstock = "0";
        }
        this.isHstock = isHstock;
    }

    public String getIsCerGoods() {
        return isCerGoods;
    }

    public void setIsCerGoods(String isCerGoods) {
        this.isCerGoods = isCerGoods;
    }

    public String getIsStarProducts() {
        return isStarProducts;
    }

    public void setIsStarProducts(String isStarProducts) {
        this.isStarProducts = isStarProducts;
    }

    public String getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(String salesVolume) {
        this.salesVolume = salesVolume;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getSuitableCarName() {
        return suitableCarName;
    }

    public void setSuitableCarName(String suitableCarName) {
        this.suitableCarName = suitableCarName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getIsImgUrl() {
        return isImgUrl;
    }

    public void setIsImgUrl(String isImgUrl) {
        if (isImgUrl != null && isImgUrl.length() != 0) {
            isImgUrl = "1";
        } else {
            isImgUrl = "0";
        }
        this.isImgUrl = isImgUrl;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getDocWeight() {
        return docWeight;
    }

    public void setDocWeight(String docWeight) {
        this.docWeight = docWeight;
    }

    public String getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(String collectionNum) {
        this.collectionNum = collectionNum;
    }

    public String getPurchasePersonTime() {
        return purchasePersonTime;
    }

    public void setPurchasePersonTime(String purchasePersonTime) {
        this.purchasePersonTime = purchasePersonTime;
    }

    public String getVisitsNum() {
        return visitsNum;
    }

    public void setVisitsNum(String visitsNum) {
        this.visitsNum = visitsNum;
    }

    public String getBrowsingTimes() {
        return browsingTimes;
    }

    public void setBrowsingTimes(String browsingTimes) {
        this.browsingTimes = browsingTimes;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getSendAddressId() {
        return sendAddressId;
    }

    public void setSendAddressId(String sendAddressId) {
        this.sendAddressId = sendAddressId;
    }

    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }

    public String getFactoryEnterpriseId() {
        return factoryEnterpriseId;
    }

    public void setFactoryEnterpriseId(String factoryEnterpriseId) {
        this.factoryEnterpriseId = factoryEnterpriseId;
    }

    public String getDealerEnterpriseId() {
        return dealerEnterpriseId;
    }

    public void setDealerEnterpriseId(String dealerEnterpriseId) {
        this.dealerEnterpriseId = dealerEnterpriseId;
    }

    public String getSmallCarStyleId() {
        return smallCarStyleId;
    }

    public void setSmallCarStyleId(String smallCarStyleId) {
        this.smallCarStyleId = smallCarStyleId;
    }

    public String getBigCarStyleId() {
        return bigCarStyleId;
    }

    public void setBigCarStyleId(String bigCarStyleId) {
        this.bigCarStyleId = bigCarStyleId;
    }

    public String getSmallCarStyleName() {
        return smallCarStyleName;
    }

    public void setSmallCarStyleName(String smallCarStyleName) {
        this.smallCarStyleName = smallCarStyleName;
    }

    public String getBigCarStyleName() {
        return bigCarStyleName;
    }

    public void setBigCarStyleName(String bigCarStyleName) {
        this.bigCarStyleName = bigCarStyleName;
    }

    @Override
    public String toString() {
        return "CarParts{" +
                "docid='" + docid + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", nameLen='" + nameLen + '\'' +
                ", nameAlias='" + nameAlias + '\'' +
                ", brandName='" + brandName + '\'' +
                ", carStyleName='" + carStyleName + '\'' +
                ", factoryNumber='" + factoryNumber + '\'' +
                ", goodsModel='" + goodsModel + '\'' +
                ", unit='" + unit + '\'' +
                ", specifications='" + specifications + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", brandId='" + brandId + '\'' +
                ", carStyleId='" + carStyleId + '\'' +
                ", categoryAttId='" + categoryAttId + '\'' +
                ", shelvesTime='" + shelvesTime + '\'' +
                ", isHstock='" + isHstock + '\'' +
                ", isCerGoods='" + isCerGoods + '\'' +
                ", isStarProducts='" + isStarProducts + '\'' +
                ", salesVolume='" + salesVolume + '\'' +
                ", evaluation='" + evaluation + '\'' +
                ", price='" + price + '\'' +
                ", shopName='" + shopName + '\'' +
                ", suitableCarName='" + suitableCarName + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", isImgUrl='" + isImgUrl + '\'' +
                ", storeId='" + storeId + '\'' +
                ", storeName='" + storeName + '\'' +
                ", docWeight='" + docWeight + '\'' +
                ", collectionNum='" + collectionNum + '\'' +
                ", purchasePersonTime='" + purchasePersonTime + '\'' +
                ", visitsNum='" + visitsNum + '\'' +
                ", browsingTimes='" + browsingTimes + '\'' +
                ", provinceId='" + provinceId + '\'' +
                ", cityId='" + cityId + '\'' +
                ", regionId='" + regionId + '\'' +
                ", sendAddressId='" + sendAddressId + '\'' +
                ", sendAddress='" + sendAddress + '\'' +
                ", factoryEnterpriseId='" + factoryEnterpriseId + '\'' +
                ", dealerEnterpriseId='" + dealerEnterpriseId + '\'' +
                ", smallCarStyleId='" + smallCarStyleId + '\'' +
                ", bigCarStyleId='" + bigCarStyleId + '\'' +
                ", smallCarStyleName='" + smallCarStyleName + '\'' +
                ", bigCarStyleName='" + bigCarStyleName + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", attrcode='" + attrcode + '\'' +
                ", mergerCategoryId='" + mergerCategoryId + '\'' +
                '}';
    }
}
