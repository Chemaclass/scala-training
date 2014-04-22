package main

object Main {
	def main(args: Array[String]) {
		Ejemplos ej2 ()
	}
}

object Ejemplos {

	// Funciones parciales
	def ej2() {
		def conectaServidor: Boolean = { return true }
		def limpiaEspacio: Boolean = { return false }
		val traza: PartialFunction[Boolean, String] = {
			case true => "traza.true"
		}
		val error: PartialFunction[Boolean, String] = {
			case false => "error.false"
		}
		val test = traza orElse error
		println(test(conectaServidor))
		println(test(limpiaEspacio))
	}
	//Crear una func a la mitad
	def ej1(a: Int = 2, b: Int = 5) {
		def mul(x: Int, y: Int): Int = return x * y
		val m = mul(a, _: Int)
		printf("%d x %d = " + m(b), a, b) // 10
	}
}