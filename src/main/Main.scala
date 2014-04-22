package main

object Main {

	def main(args: Array[String]) {
		def mul(a: Int, b: Int): Int = return a * b
		val m2 = mul(2, _: Int)
		println("2 x 5 = " + m2(5)) // 10
	}

	def multiplica(a: Int, b: Int): Int = {
		return a * b
	}
}