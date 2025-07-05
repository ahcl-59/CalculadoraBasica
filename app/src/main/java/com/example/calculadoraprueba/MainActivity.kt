package com.example.calculadoraprueba

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculadoraprueba.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var texto: String = ""
    private var numero: Double = 0.0
    private lateinit var operador: String
    private var resultado: Double = 0.0
    private var contador: Int = 0
    private var contadorPercent = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //NÚMEROS
        binding.cero.setOnClickListener(this)
        binding.uno.setOnClickListener(this)
        binding.dos.setOnClickListener(this)
        binding.tres.setOnClickListener(this)
        binding.cuatro.setOnClickListener(this)
        binding.cinco.setOnClickListener(this)
        binding.seis.setOnClickListener(this)
        binding.siete.setOnClickListener(this)
        binding.ocho.setOnClickListener(this)
        binding.nueve.setOnClickListener(this)
        //OPERADORES
        binding.sumar.setOnClickListener(this)
        binding.restar.setOnClickListener(this)
        binding.multi.setOnClickListener(this)
        binding.divi.setOnClickListener(this)
        binding.coma.setOnClickListener(this)
        binding.minus.setOnClickListener(this)
        binding.percent.setOnClickListener(this)
        binding.delete.setOnClickListener(this)
        //IGUAL
        binding.igual.setOnClickListener(this)
        //RESETEAR
        binding.reset.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        // ejecutar cuando un botón o elemento sea pulsado
        // qué botón ha sido pulsado??
        when (v?.id) {
            binding.cero.id -> {
                concatenar("0")
                obtenerNumero()
            }
            binding.uno.id -> {
                concatenar("1")
                obtenerNumero()
            }
            binding.dos.id -> {
                concatenar("2")
                obtenerNumero()
            }
            binding.tres.id -> {
                concatenar("3")
                obtenerNumero()
            }
            binding.cuatro.id -> {
                concatenar("4")
                obtenerNumero()
            }
            binding.cinco.id -> {
                concatenar("5")
                obtenerNumero()
            }
            binding.seis.id -> {
                concatenar("6")
                obtenerNumero()
            }
            binding.siete.id -> {
                concatenar("7")
                obtenerNumero()
            }
            binding.ocho.id -> {
                concatenar("8")
                obtenerNumero()
            }
            binding.nueve.id -> {
                concatenar("9")
                obtenerNumero()
            }

            //OPERACIONES
            binding.sumar.id -> {
                concatenar("+")
                contador = 0
            }
            binding.restar.id -> {
                concatenar("-")
                contador = 0
            }
            binding.multi.id -> {
                concatenar("*")
                contador = 0
            }
            binding.divi.id -> {
                concatenar("/")
                contador = 0
            }


            //BOTONES ESPECIALES
            binding.coma.id -> {
                contador++
                concatenar(".")
            }
            binding.minus.id -> {
                resultado *= (-1)
                binding.screen.text = resultado.toString()
            }
            binding.percent.id -> {
                if (contadorPercent < 1) {
                    contadorPercent++
                    resultado /= 100
                    binding.screen.text = resultado.toString()
                }
            }
            binding.delete.id->{
                if(contador > 1){
                    texto = texto.dropLast(1)
                    contador--
                    obtenerNumero()
                    binding.txtOperaciones.text = texto
                }else if (contador==1){
                    texto = texto.dropLast(1)
                    contador--
                    numero=0.0
                    binding.txtOperaciones.text = texto
                }
            }
            binding.reset.id -> {
                texto = ""
                numero = 0.0
                resultado = 0.0
                contador = 0
                contadorPercent = 0
                binding.txtOperaciones.text = ""
                binding.screen.text = "0"
            }

            binding.igual.id -> {
                if (texto.contains('+')) {
                    suma()
                } else if (texto.contains('-')) {
                    resta()
                } else if (texto.contains('*')) {
                    multiplicacion()
                } else if (texto.contains('/')) {
                    division()
                }
                texto = ""
                contador = 0
                contadorPercent = 0
                binding.screen.text = resultado.toString()

            }
        }
    }

    //FUNCIÓN->CONCATENAR EL TEXTO EN EL TXTOPERACIONES
    fun concatenar(textoAniadido: String) {
        texto += textoAniadido
        binding.txtOperaciones.text = texto
    }

    //FUNCIÓN OBTENER NÚMERO
    private fun obtenerNumero() {
        if (texto.contains('+')) {
            contador++
            numero = texto.takeLast(contador).toDouble()
        } else if (texto.contains('-')) {
            contador++
            numero = texto.takeLast(contador).toDouble()
        } else if (texto.contains('*')) {
            contador++
            numero = texto.takeLast(contador).toDouble()
        } else if (texto.contains('/')) {
            contador++
            numero = texto.takeLast(contador).toDouble()
        } else {
            contador++
            resultado = texto.takeLast(contador).toDouble()
        }
    }

    //FUNCIONES->OPERACIONES
    private fun suma() {
        resultado += numero
        binding.txtOperaciones.text = ""
    }

    private fun resta() {
        resultado -= numero
        binding.txtOperaciones.text = ""
    }

    private fun multiplicacion() {
        resultado = resultado * numero
        binding.txtOperaciones.text = ""
    }

    private fun division() {
        resultado /= numero
        binding.txtOperaciones.text = ""
    }

    /*//MÉTODO ALTERNATIVO
    private fun pulsar(numero:Double){
        if (resultado != 0.0) {
            if (texto.lastOrNull() == '+') {
                concatenar(numero.toString())
                suma(numero)
                binding.screen.text = resultado.toString()
            } else if (texto.lastOrNull() == '-') {
                concatenar(numero.toString())
                resta(numero)
                binding.screen.text = resultado.toString()
            } else if (texto.lastOrNull() == '*') {
                concatenar(numero.toString())
                multiplicacion(numero)
                binding.screen.text = resultado.toString()
            } else if (texto.lastOrNull() == '/') {
                concatenar(numero.toString())
                division(numero)
                binding.screen.text = resultado.toString()
            }
        } else {
            concatenar(numero.toString())
            resultado = numero
        }
    }*/
}