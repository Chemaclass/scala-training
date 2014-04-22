package main

object Main {
	def main(args: Array[String]) {
		Ejemplos ej3 ()
	}
}

object Ejemplos {

	/**
	 * Funciones currying
	 * Que consisten en convertir funciones que tienen varios
	 * parámetros en encadenamiento de funciones con un solo parámetro
	 */
	def ej3() {
		def multiplica(a: Int)(b: Int) = a * b
		println(multiplica(5)(5))
		//función parcial sobre función currying
		val por2 = multiplica(2)(_)
		println(por2(5))
	}

	// Funciones parciales
	def ej2() {
		def conectaServidor: Boolean = { return true }
		def limpiaEspacio: Boolean = { return false }
		var hora = 10
		val traza: PartialFunction[Boolean, String] = { case true => "traza.true" }
		val error: PartialFunction[Boolean, String] = { case false => "error.false" }
		var errorDeDia: PartialFunction[Boolean, String] = { case false => "errorDeDia.false" }
		def elige(hora: Int): PartialFunction[Boolean, String] = {
			if (hora == 10) {
				return errorDeDia
			} else {
				return error
			}
		}
		var test = traza orElse elige(hora)
		println(test(conectaServidor))
		println(test(limpiaEspacio))
		hora = 12
		test = traza orElse elige(hora)
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