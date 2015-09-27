package kuvioton.conversion

import shapeless._

trait DepthSyntax {
  def depth: Int
}

object DepthSyntax {
  implicit class depthSynta[T](val a: T) extends AnyVal {
    def depth(implicit st: Depth[T]) = st.depth(a)
  }
}

trait Depth[T] {
  def depth(t: T): Int
}

class SingleLevelDepth[T] extends Depth[T] {
  override def depth(t: T): Int = 1
}

class NoDepth[T] extends Depth[T] {
  override def depth(t: T): Int = 0
}

object Depth extends LabelledTypeClassCompanion[Depth] {
  implicit def stringDepth: Depth[String] = new SingleLevelDepth

  implicit def intDepth: Depth[Int] = new SingleLevelDepth

  implicit def floatDepth: Depth[Float] = new SingleLevelDepth

  implicit def booleanDepth: Depth[Boolean] = new SingleLevelDepth

  implicit def listDepth[T](implicit S: Depth[T]): Depth[List[T]] = new Depth[List[T]] {
    override def depth(t: List[T]): Int = {
      if (t.isEmpty) 1
      else t.map(S.depth).max
    }
  }

  object typeClass extends LabelledTypeClass[Depth] {
    def emptyProduct = new SingleLevelDepth

    def product[F, T <: HList](name: String, sh: Depth[F], st: Depth[T]) = new Depth[F :: T] {
      def depth(ft: F :: T) = {
        val head = sh.depth(ft.head) + 1
        val tail = st.depth(ft.tail)
        val d = head max tail
        d
      }
    }

    def emptyCoproduct = new NoDepth

    def coproduct[L, R <: Coproduct](name: String, sl: => Depth[L], sr: => Depth[R]) = new Depth[L :+: R] {
      def depth(lr: L :+: R) = lr match {
        case Inl(l) => sl.depth(l)
        case Inr(r) => sr.depth(r)
      }
    }

    def project[F, G](instance: => Depth[G], to: F => G, from: G => F) = new Depth[F] {
      def depth(f: F) = instance.depth(to(f))
    }
  }
}