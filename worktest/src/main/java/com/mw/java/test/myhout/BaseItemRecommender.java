package com.mw.java.test.myhout;

import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.precompute.example.GroupLensDataModel;

import java.io.File;
import java.util.List;

/**
 * Describe:
 * 与基于用户的技术不同的是，这种方法比较的是内容项与内容项之间的相似度。
 * Item-based 方法同样需要进行三个步骤获得推荐：
 * 1）得到内容项（Item）的历史评分数据；
 * 2）针对内容项进行内容项之间的相似度计算，找到目标内容项的“最近邻居”；
 * 3）产生推荐。这里内容项之间的相似度是通过比较两个内容项上的用户行为选择矢量得到的。
 * 第二代协同过滤算法
 * Data:     2015/11/26.
 */
public class BaseItemRecommender {

    public static void main(String[] args) throws Exception {
        //准备数据 这里是电影评分数据
        File file = new File("E:\\itcast\\项目中心\\大数据课程研发\\大数据课程-参考资料\\推荐系统\\数据\\ml-10m\\ml-10M100K\\ratings.dat");
        //将数据加载到内存中，GroupLensDataModel是针对开放电影评论数据的
        DataModel dataModel = new GroupLensDataModel(file);
        //计算相似度，相似度算法有很多种，欧几里得、皮尔逊等等。
        ItemSimilarity itemSimilarity = new PearsonCorrelationSimilarity(dataModel);
        //构建推荐器，协同过滤推荐有两种，分别是基于用户的和基于物品的，这里使用基于物品的协同过滤推荐
        GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(dataModel, itemSimilarity);
        //给用户ID等于5的用户推荐10个月2398相似的商品
        List<RecommendedItem> recommendedItemList = recommender.recommendedBecause(5, 2398, 10);
        //打印推荐的结果
        System.out.println("使用基于物品的协同过滤算法");
        System.out.println("根据用户5当前浏览的商品2398，推荐10个相似的商品");
        for (RecommendedItem recommendedItem : recommendedItemList) {
            System.out.println(recommendedItem);
        }
    }
}
