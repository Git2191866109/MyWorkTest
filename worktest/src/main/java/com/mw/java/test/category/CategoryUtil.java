package com.mw.java.test.category;

import com.mw.java.test.Constant;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 利用递归的方式取数据
 * categoryTree：代表着读取的整个文件中的目录树，相当于资源池
 * part-m-00000：中的数据就包含了一级目录到三级目录的所有id
 */
public class CategoryUtil {
    /*指针，控制是否进入结果集list*/
    private static Integer NOFIRST = 0;

    /**
     * 目标：不管有多少，不要第一个层级
     * @param categoryids
     * @param categoryTree
     * @param list
     */
    public static void categoryTree(String categoryids, Map<String, String> categoryTree, List<String> list) {
        if (StringUtils.isBlank(categoryids)) {
            return;
        }
        String[] categoryidArr = categoryids.split(Constant.SEMICOLON, -1);
        for (String categoryid : categoryidArr) {
            if (StringUtils.isBlank(categoryid)) {
                continue;
            }
            if (NOFIRST != 0) {
                list.add(categoryid);
            }
            if (categoryTree.containsKey(categoryid)) {
                NOFIRST++;
                categoryTree(categoryTree.get(categoryid), categoryTree, list);
            }
        }
        NOFIRST = 0;
    }

    public static void categoryTree_2(String categoryids, Map<String, String> categoryTree, List<String> list) {
        if (StringUtils.isBlank(categoryids)) {
            return;
        }
        String[] categoryidArr = categoryids.split(Constant.SEMICOLON, -1);
        for (String categoryid : categoryidArr) {
            if (StringUtils.isBlank(categoryid)) {
                continue;
            }
            list.add(categoryid);
            if (categoryTree.containsKey(categoryid)) {
                categoryTree_2(categoryTree.get(categoryid), categoryTree, list);
            }
        }

    }

    public static void main(String[] args) throws IOException {
        List<String> listTree = new ArrayList<String>();
        List<String> listLevel = new ArrayList<String>();
        Map<String, String> categoryTree = new HashMap<String, String>();
        Map<String, String> categoryLevel = new HashMap<String, String>();
        List<String> readLines = FileUtils.readLines(new File("E:\\work_test\\TestWork_Zgtx\\Work_Test\\src\\main\\resources\\catgory\\part-m-00000.txt"));
        for (String line : readLines) {
            String[] split = line.split("\01", -1);
            String categoryid = split[0];
            String parentcategoryid = split[1];
            String categorylevel = split[6];

            categoryLevel.put(categoryid, categorylevel);
            if (!parentcategoryid.equals("0")) {
                categoryTree.put(categoryid, parentcategoryid);
            }
        }
//        categoryTree("2423105234730E22E053010210AC6DE2", categoryTree, listTree);
        categoryTree("0F2ACC42F2FB3516F94F943A3CDE0C1A", categoryTree, listTree);
//        categoryTree_2("2423105234730E22E053010210AC6DE2", categoryTree, listTree);
//        categoryTree_2("0F2ACC42F2FB3516F94F943A3CDE0C1A", categoryTree, listTree);
        for (String category : listTree) {
            listLevel.add(categoryLevel.get(category));
        }
        System.out.println(listTree);
        System.out.println(listLevel);
        System.out.println(NOFIRST);

    }
}
