package main

object Main {
	def main(args: Array[String]) {
		Ejemplos ej8 ()
	}
}

object Ejemplos {

	// Iterar sobre un mapa de una lista
	def ej8() {
		var lista = List(1, 2, 3, 4, 5, 6)
		val listaPor2 = lista map (_ * 2)
		listaPor2 foreach { n => printf("El número es %d\n", n) }
	}

	// Listas basadas en patrones regulares
	def ej7() {
		val telefono = """([^,]+) ([^,]+) ([^,]+) ([0-9]+)""".r
		var lista = List("Chema Valera Reales 1234567",
			"Javier Ruiz Torre 987654312",
			"Pedro Mora Soria mi@mail.lo",
			"Juan Jesús Ángel Alfredo Viallanueba 123987487")
		for (contacto <- lista) {
			contacto match {
				case telefono(n, a1, a2, tel) => println("Llamar a " + n + " al " + tel)
				case entry => println("No reconozco patrón")
			}
		}
	}

	// Bucles sobre listas
	def ej6() {
		val lista = List("Enero", "Febrero", "Marzo", "Abril", "Mayo", "junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre")
		for (mes <- lista if !mes.contains("r")) {
			println(mes)
		}
	}

	// Llamar a una función por nombre (y no por valor)
	def ej5() {
		def dameNombre(paramPorNombre: => Boolean)(cuerpo: => Unit) {
			if (paramPorNombre) {
				cuerpo
			} else {
				println("retornamos otra cosa")
			}
		}
		dameNombre((7 * 3 % 2) == 0) {
			println("es un resultado par")
		}
	}

	// Atributos implícitos en las funciones
	def ej4() {
		def multiplica(a: Int)(implicit b: Int) = a * b
		println(multiplica(5)(5))
		implicit val b = 10
		println(multiplica(5))
	}

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