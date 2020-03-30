package io.github.vi_kas.ignite.core

import org.apache.ignite.lang.IgniteFuture

import scala.concurrent.{Future, Promise}
import scala.util.Try

package object utils {

  implicit class IgniteFutureUtils[T](igniteFuture: IgniteFuture[T]) {
    def toScalaFuture: Future[T] = {
      val promise = Promise[T]()

      igniteFuture.listen { k =>
        promise.tryComplete(Try(k.get))
      }
      promise.future
    }
  }
}
