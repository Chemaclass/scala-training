package main

object Main {
	def main(args: Array[String]) {
		Ejemplos ej1 ()
	}
}

object Ejemplos {

	//Crear una func a la mitad
	def ej1(a: Int = 2, b: Int = 5) {
		def mul(x: Int, y: Int): Int = return x * y
		val m = mul(a, _: Int)
		printf("%d x %d = " + m(b), a, b) // 10
	}
}