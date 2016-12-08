package com.mw.test.scalatest

/**
  * Created by wei.ma on 2016/11/1.
  */
class xiebiannibian {
  /*trait Queue[+T] {} 协变情况下:当类型S是类型A的子类型，则Queue[S]也可以认为是Queue[A}的子类型，即Queue[S]可以泛化为Queue[A]。
    也就是被参数化类型的泛化方向与参数类型的方向是一致的，所以称为协变*/

  /*trait Queue[-T] {} 逆变情况下，当类型S是类型A的子类型，则Queue[A]反过来可以认为是Queue[S}的子类型。
  也就是被参数化类型的泛化方向与参数类型的方向是相反的，所以称为逆变。 */

  /*U >: T 这是类型下界的定义，也就是U必须是类型T的父类(或本身，自己也可以认为是自己的父类)。*/
  /*S <: T 这是类型上界的定义，也就是S必须是类型T的子类（或本身，自己也可以认为是自己的子类)。*/
}

//出版物类
class Publication(val title: String)

//书籍类
class Book(title: String) extends Publication(title)

//图书库类
object Library {
  //定义图书库内所有的书籍
  val books: Set[Book] =
    Set(
      new Book("Programming in Scala"),
      new Book("Walden")
    )

  //打印所有图书内容，使用外部传入的函数来实现
  def printBookList(info: Book => AnyRef) {
    //确认Scala中一个参数的函数实际上是Function1特征的实例
    assert(info.isInstanceOf[Function1[_, _]])
    //打印
    for (book <- books)
      println(info(book))
  }

  //打印所有图书内容，使用外部传入的GetInfoAction特征的实例来实现
  def printBokkListByTrait[P >: Book, R <: AnyRef](action: GetInfoAction[P, R]) {
    //打印
    for (book <- books)
      println(action(book))
  }
}

//取得图书内容特征，P类型参数的类型下界是Book，R类型参数的类型上界是AnyRef
//GetInfoAction定义中使用>:和<:来代替了Function1中+和-。那是由于>:使得Publication可以代替Book，由于<:使得String可以代替AnyRef
trait GetInfoAction[P >: Book, R <: AnyRef] {
  //取得图书内容的文本描述，对应（）操作符
  def apply(book: P): R
}

/**
  * 由于-S是逆变，而Publication是Book的父类，所以Publication可以代替（泛化为）Book。
  * 由于+T是协变，而String是AnyRef的子类，所以String可以代替（泛化为）AnyRef。
  * @tparam S:-S类型参数是逆变
  * @tparam T:+T参数是协变
  * apply方法的参数类型是S决定了S一定是逆变，而返回类型是T则决定了T是协变，这也是Scala语言的强制规定。
  */
trait Function1[-S, +T] {
  def apply(x: S): T
}

//单例对象，文件的主程序
object Customer extends App {
  //定义取得出版物标题的函数
  def getTitle(p: Publication): String = p.title

  //使用函数来打印
  Library.printBookList(getTitle)

  //使用特征GetInfoAction的实例来打印
  //  new GetInfoAction[Publication, String] {}和def getTitle(p: Publication): String是等价的
  Library.printBokkListByTrait(new GetInfoAction[Publication, String] {
    def apply(p: Publication): String = p.title
  })
}




