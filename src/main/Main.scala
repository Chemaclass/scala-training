package main

object Main {

	def main(args: Array[String]) {
		val m2 = multiplica(2, _: Int)
		println("2 x 5 = " + m2(5)) // 10
	}

	def multiplica(a: Int, b: Int): Int = {
		return a * b
	}
}