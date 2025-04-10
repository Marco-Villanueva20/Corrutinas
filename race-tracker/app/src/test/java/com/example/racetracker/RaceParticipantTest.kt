/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.racetracker

import com.example.racetracker.ui.RaceParticipant
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class RaceParticipantTest {
    // Se crea una instancia del participante de la carrera para las pruebas:
    private val raceParticipant = RaceParticipant(
        name = "Test",                // Nombre del participante para identificarlo en la prueba
        maxProgress = 100,            // Progreso máximo para que la carrera finalice (100)
        progressDelayMillis = 500L,   // Cada 500 milisegundos se actualiza el progreso
        initialProgress = 0,          // Progreso inicial en 0
        progressIncrement = 1         // Cada actualización incrementa el progreso en 1
    )

    /*
    * runTest: Un bloque que ejecuta la prueba dentro de un entorno controlado para corutinas.
    * Permite simular el paso del tiempo y ejecutar las corutinas de forma determinista.
    * advanceTimeBy(raceParticipant.progressDelayMillis)
    * Avanza de forma simulada el tiempo en la prueba en 500 milisegundos,
    * lo que hace que se "cumpla" el delay del primer ciclo del bucle en run().
    *
    * runCurrent()
    * Ejecuta las tareas pendientes en la cola de corutinas que ya han quedado programadas.
    * Esto asegura que la acción pendiente (el incremento del progreso) se ejecute inmediatamente.
    * 
    * assertEquals(exceptedProgress, raceParticipant.currentProgress)
    * Se verifica que el progreso actual (currentProgress) sea igual al valor esperado (1).
    * Esto valida que después de 500 ms de delay, el metodo run() hizo el incremento correcto.
    *
    * */

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun raceParticipant_RaceStarted_ProgressUpdate() = runTest {
        val exceptedProgress = 1 // Se espera que, después de un delay, el progreso sea 1
        launch {
            raceParticipant.run()
        }
        advanceTimeBy(raceParticipant.progressDelayMillis)
        runCurrent()
        assertEquals(exceptedProgress, raceParticipant.currentProgress)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun raceParticipant_RaceFinished_ProgressUpdate() = runTest {
        launch { raceParticipant.run() }
        advanceTimeBy(raceParticipant.maxProgress * raceParticipant.progressDelayMillis)
        runCurrent()
        assertEquals(100, raceParticipant.currentProgress)
    }
}
