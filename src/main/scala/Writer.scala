/**
  * Created by mtumilowicz on 2019-01-09.
  */
class Writer[X](val result: X, val log: String) {
  def map[Y](f: Function[X, Y]): Writer[Y] =
    KleisliWriterCategory.compose[Writer[X], X, Y](identity, KleisliWriterCategory.identity.compose(f))(this)
}