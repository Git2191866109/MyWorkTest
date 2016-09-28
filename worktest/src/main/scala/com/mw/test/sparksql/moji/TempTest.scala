package com.mw.test.sparksql.moji

import com.mw.test.common.SparkUtils.SparkUtils
import com.mw.test.scalatest.DateUitls
import org.apache.spark.mllib.linalg.{Matrices, Matrix}
import org.apache.spark.{Logging, SparkException}

import scala.collection.mutable.ListBuffer


/**
  * Created by wei.ma on 2016/9/8.
  */
class TempTest extends Serializable {
  val startTime = DateUitls.getLastWeekStart()
  val endTime = DateUitls.getLastWeekEnd()
  startTime
  endTime

  def parese(utag: String): ListBuffer[Double] = {
    val list = ListBuffer[Double]()
    val temps = utag.replace("[", "").replace("]", "").replace("u", "").split(",")
    for (temp <- temps) {
      list.append(temp.trim.toDouble)
    }
    list
  }


  /*得到一个用户的tag标签列表  1 * 85*/
  def tagprocess(user_tags: ListBuffer[Double]) = {
    /*用户喜欢的标签*/
    val tags = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 34,
      35, 36, 37, 38, 39, 40, 42, 43, 44, 45, 46, 47, 48, 49, 50, 52, 53, 54, 55, 56, 0, 61, 60, 59, 57, 64, 63,
      65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 105, 142, 145, 148, 149,
      150, 155, 160, 162)
    val temp_list = new Array[Double](tags.length)
    /*判断用户哪些标签在list列表中*/
    for (i <- 0 until (tags.length)) {
      for (j <- 0 until (user_tags.length)) {
        if (user_tags(j) == tags(i)) {
          temp_list(i) = user_tags(j)
        } else {
          temp_list(i) == 0
        }
      }
    }
    temp_list.sorted
  }

  /*计算模型*/
  def user_model_computation(user_tags: Array[Double]) = {
    val tag_temp: Matrix = Matrices.dense(1, 85, user_tags)
    /*加载模型数据*/
//    val model =
    tag_temp
  }

  //  userprofile = np.array(user_tags)
  //  user_weights = userprofile.dot(model)
  //  # print "user_weights: ", user_weights
  //  category_list = [81, 20, 36, 42, 29, 18, 4, 22, 34, 101, 19, 5, 3, 21, 28, 44, 49, 57, 74, 75, 77, 82, 85, 103, 105]
  //  category_weights = []
  //  for i in xrange(len(category_list)):
  //    category_weights.append((category_list[i], user_weights[i]))
  //  ###排序###
  //  category_weights_sorted = sorted(category_weights, key=lambda x: x[1], reverse=True)
  //  return category_weights_sorted

}

object TempTest extends Logging {

  def main(args: Array[String]) {
    val tempTest = new TempTest
    val sc = SparkUtils.sparkEntrance("FEED_USER_PROFILE", "local", null)
    val user_profile = new TempTest

    try {
      val dataPath = "E:\\mojiworkspace\\moji-work\\src\\main\\resources\\data\\*"
      val temp_source = sc.textFile(dataPath).map(line => {
        val tempLits = line.replace("\'", "").split(":")
        tempLits
      }).map(item => {
        val key = "uid:" + item(1) + "fondtag"
        (key, tempTest.parese(item(3)))
      }).map(result_pre => {
        val utags = tempTest.tagprocess(result_pre._2)
        (result_pre._1, utags)
      }).cache()

      val result_rdd = temp_source.map(x => {
        val tag_temp = tempTest.user_model_computation(x._2)
        tag_temp
      })

      println("result_user_select------- " + temp_source.collect.toBuffer)

    } catch {
      case e: Exception => {
        logError("Spark执行失败.log....", e)
        throw new SparkException("Spark执行失败.....", e)
      }
    } finally {
      SparkUtils.sparkStoped(sc)
      logInfo("程序停止......")
    }
  }
}