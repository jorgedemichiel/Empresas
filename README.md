# Empresas

### Proyecto en donde creamos distintos endpoints para traer la siguiente información: 

- Uno que traiga las empresas que hicieron transferencias el último mes
- Otro que traiga las empresas que se adhirieron el último mes.
- El ultimo que haga la adhesión de una empresa.

## Uso
Para utilizarlo, es necesario clonar el repositorio, correrlo y luego ejecutar los endpoits que paso a continuación. 

Endpoints de la API
Los siguientes endpoints están disponibles:

***1. Obtener Empresas con Transferencias del último mes.***

curl --location --request GET 'http://localhost:8080/empresas/transferencias-ultimo-mes'

Devuelve una lista de empresas que han tenido transferencias en el último mes.

Respuesta

![image](https://github.com/jorgedemichiel/Empresas/assets/114967638/7afec5f6-10db-482f-b00b-4058746a3cde)


***2. Obtener Empresas con Adhesión Reciente***

curl --location --request GET 'http://localhost:8080/empresas/adhesion-ultimo-mes'

Devuelve una lista de empresas que se han adherido en el último mes.

Respuesta

![image](https://github.com/jorgedemichiel/Empresas/assets/114967638/8d733f08-a27d-417f-bc93-0a30359ee6b2)


***3. Realizar Adhesión de Empresa***

curl --location --request POST 'http://localhost:8080/empresas/adhesion' \
--header 'Content-Type: application/json' \
--data-raw '{
  "cuit": "123456789",
  "razonSocial": "Empresa de ejemplo",
  "fechaAdhesion": "2022-11-01"
}'

Realiza la adhesión de una empresa.

Respuesta
Mensaje: "Adhesión realizada correctamente."

***4. Cargar Empresas y Transferencias***

curl --location --request POST 'http://localhost:8080/empresas/cargar'
Carga empresas y transferencias de ejemplo que ya están en el código a modo de test en la clase EmpresasController.java en la base de datos.

Respuesta
Mensaje: "Empresas y transferencias cargadas exitosamente en la base de datos."
