package com.mw.java.test.framaker;

import com.mw.java.test.domain.CarParts;
import com.mw.java.test.utils.CommonUtil;
import freemarker.cache.MruCacheStorage;
import freemarker.cache.StringTemplateLoader;
import freemarker.log.Logger;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.Version;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.HashMap;
import java.util.Map;

public class FreemarkerUtil {
    private static Configuration cfg; // 模版配置对象
    private static Template t = null;

    @SuppressWarnings("deprecation")
    private static synchronized void init() throws Exception {
        Version version = new Version(2, 3, 23);
        Logger.selectLoggerLibrary(0);
        cfg = new Configuration(version);
//    cfg.setLogTemplateExceptions(true);
        cfg.setCacheStorage(new MruCacheStorage(0, Integer.MAX_VALUE));
        cfg.setObjectWrapper(new DefaultObjectWrapper(version));
//      cfg.setDirectoryForTemplateLoading(new File(FreemarkerUtil.class.getClassLoader().getResource("template").getFile() ));
//      cfg.setClassForTemplateLoading(FreemarkerUtil.class, "template");
        StringTemplateLoader st = new StringTemplateLoader();
//      st.putTemplate("autoMall", "<AutoMall><docid>${autoMall.docid!}</docid><Id>${autoMall.id!}</Id><Name><![CDATA[${autoMall.name!}]]></Name><NameAlias><![CDATA[${autoMall.nameAlias!}]]></NameAlias><ImgUrl><![CDATA[${autoMall.imgUrl!}]]></ImgUrl><CarStyleId>${autoMall.carStyleId!}</CarStyleId><CategoryId>${autoMall.categoryId!}</CategoryId><CategoryLevel>${autoMall.categoryLevel!}</CategoryLevel><BrandId>${autoMall.brandId!}</BrandId><SpecificationsId>${autoMall.specificationsId!}</SpecificationsId><FactoryNumber>${autoMall.factoryNumber!}</FactoryNumber><ShelvesTime>${autoMall.shelvesTime!}</ShelvesTime><RegionId>${autoMall.regionId!}</RegionId><IsHstock>${autoMall.isHstock!}</IsHstock><IsCerGoods>${autoMall.isCerGoods!}</IsCerGoods><IsStarProducts>${autoMall.isStarProducts!}</IsStarProducts><SalesVolume>${autoMall.salesVolume!}</SalesVolume><Evaluation>${autoMall.evaluation!}</Evaluation><Price>${autoMall.price!}</Price><GoodsModel><![CDATA[${autoMall.goodsModel!}]]></GoodsModel><UserDefinedBrandName><![CDATA[${autoMall.userDefinedBrandName!}]]></UserDefinedBrandName><FactoryName><![CDATA[${autoMall.factoryName!}]]></FactoryName><CarStyleName><![CDATA[${autoMall.carStyleName!}]]></CarStyleName><ServiceStationName><![CDATA[${autoMall.serviceStationName!}]]></ServiceStationName><StoreId>${autoMall.storeId!}</StoreId><StoreName><![CDATA[${autoMall.storeName!}]]></StoreName><AttrValue><![CDATA[${autoMall.attrValue!}]]></AttrValue><CarName><![CDATA[${autoMall.carName!}]]></CarName><BrandName><![CDATA[${autoMall.brandName!}]]></BrandName></AutoMall>");

        String[] templates = new String[]{"carParts", "stores", "storesrecomm"};
        for (String template : templates) {
            st.putTemplate(template, CommonUtil.readFileInJar("/template/" + template));
        }
        cfg.setTemplateLoader(st);
    }

    public static void main(String[] args) throws Exception {
        Map<String, Object> dataModel = new HashMap<String, Object>();
        CarParts carParts = new CarParts();
        carParts.setDocid("id");
        carParts.setId("idididid");
        carParts.setProvinceId("11");
        carParts.setCityId("22");
        carParts.setMergerCategoryId("setMergerCategoryId");
        dataModel.put("carParts", carParts);
        long b = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            String str = FreemarkerUtil.process(dataModel, "carParts");
            System.out.println(carParts.toString());
            System.out.println(str.replace("<![CDATA[]]>", ""));
        }
        long e = System.currentTimeMillis();
        System.out.println(e - b);
    }

    public static String process(Map<String, Object> dataModel, String templateName) throws Exception {

        if (cfg == null) {
            init();
        }
        t = cfg.getTemplate(templateName);
        return FreeMarkerTemplateUtils.processTemplateIntoString(t, dataModel);
    }
}
