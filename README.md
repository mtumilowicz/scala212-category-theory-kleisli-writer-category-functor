# scala212-category-theory-kleisli-writer-category-functor
_Reference_: https://bartoszmilewski.com/2015/02/03/functoriality/  

# preface
Please refer my other github repository: 
* https://github.com/mtumilowicz/java11-category-theory-kleisli-category
for most basic knowledge about Kleisli Category.
* https://github.com/mtumilowicz/java11-category-theory-optional-is-not-functor
for most basic knowledge functors.

# project description
1. We have simple class `Writer`
    ```
    class Writer[X](val result: X, val log: String) {}
    ```
1. And its Kleisli Category
    ```
    object KleisliWriterCategory {
      def identity[A]: Function[A, Writer[A]] = new Writer(_, "")
    
      def compose[A, B, C](f1: Function[A, Writer[B]], f2: Function[B, Writer[C]]): Function[A, Writer[C]] = (a: A) => {
        val f1value = f1.apply(a)
        val f2value = f2.apply(f1value.result)
        new Writer(f2value.result, f1value.log + f2value.log)
      }
    }
    ```
    function `compose` is `>=>`
1. We could easily lift `Writer` to the functor, by implementing
the `map` function
    ```
    fmap f = id >=> (\x -> return (f x))
    ```
    ```
    class Writer[X](val result: X, val log: String) {
      def map[Y](f: Function[X, Y]): Writer[Y] = 
        KleisliWriterCategory.compose[Writer[X], X, Y](identity, KleisliWriterCategory.identity.compose(f))(this)
    }
    ```