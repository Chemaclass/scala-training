package main

object Main {
	def main(args: Array[String]) {
		Ejemplos ej12 ()
	}
}

object Ejemplos {

	// Tipos parametrizables
	def ej12() {
		class Almacen[param] {
			var array: List[param] = Nil
			def add(elemento: param) { array = elemento :: array }
			def lista() {
				array foreach { i => println(i.toString()) }
			}
		}
		var almacen = new Almacen[Int]
		almacen.add(1)
		almacen.add(2)
		almacen.add(3)
		almacen.lista()
	}

	// Herencia 
	def ej11() {
		class Objeto {
			var x, y, ancho, alto: Int = 0
		}
		trait ObjetoGrafico {
			def pintar() {
				println("ObjetoGrafico.pintar()")
			}
			def mover()
		}
		class Pato extends Objeto with ObjetoGrafico {
			override def mover() {
				println("Pato.mover()")
			}
		}
		class Nube extends Objeto with ObjetoGrafico {
			override def mover() {
				println("Nube.mover()")
			}
			override def pintar() {
				println("Nube.pintar()")
			}
		}
		val lista = List(new Pato(), new Nube())
		for (obj <- lista) {
			obj.pintar()
			obj.mover()
		}
	}

	//Clousures
	def ej10() {
		var a = 10
		var foo = (x: Int) => x + a
		printf("a\t= %d\n", a)
		printf("foo(5)\t= %d\n", foo(5))
		printf("a\t= %d\n", a)
		a = a + 1
		printf("foo(5)\t= %d\n", foo(5))
	}

	//Iterar sobre un mapa
	def ej9() {
		val mapa = Map(
			"Chema" -> "Desarrollador",
			"Juan" -> "Publicista",
			"Jesús" -> "Ingeniero",
			"Ángel" -> "Matemático")
		def pintarMapa(mapa: Map[String, String]) {
			mapa foreach { i => printf("clave %s, valor %s\n", i._1, i._2) }
		}
		pintarMapa(mapa)
		val mapaMayus = mapa map { i => (i._1.toUpperCase(), i._2.toUpperCase()) }
		pintarMapa(mapaMayus)
	}

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