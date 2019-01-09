/**
  * Created by mtumilowicz on 2019-01-09.
  */

object KleisliWriterCategory {
  def identity[A]: Function[A, Writer[A]] = new Writer(_, "")

  def compose[A, B, C](f1: Function[A, Writer[B]], f2: Function[B, Writer[C]]): Function[A, Writer[C]] = (a: A) => {
    val f1value = f1.apply(a)
    val f2value = f2.apply(f1value.result)
    new Writer(f2value.result, f1value.log + f2value.log)
  }
}