# Sistema de Gestión de Estudiantes

---

## Parte 1 – Análisis del Programa Original

### ¿Qué partes pueden convertirse en métodos?

Lo primero que hicimos fue leer el codigo original y nos dimos cuenta que el main era muy largo porque tenia todo junto. Decidimos separar cada tarea en su propio metodo:

- Mostrar el menu y manejar las opciones
- Agregar un estudiante
- Mostrar la lista de estudiantes
- Calcular el promedio
- Mostrar al estudiante con la calificacion mas alta
- Pausar el programa esperando que el usuario presione Enter
- Validar que la opcion del menu sea un numero
- Validar que la calificacion sea un numero

### ¿Qué bloques se repiten?

Nos dimos cuenta que la verificacion de si las listas estan vacias aparece en tres metodos distintos. Tambien el recorrer las listas con un `for` se repite en varios lados, y el llamado a `pausar()` aparece despues de casi todas las opciones del switch.

### ¿Qué responsabilidades pueden separarse?

- La responsabilidad de **mostrar informacion** al usuario
- La responsabilidad de **leer y validar** los datos que ingresa el usuario
- La responsabilidad de **calcular resultados** como el promedio o la nota maxima

### ¿Por qué dividir estas tareas mejora el programa?

Porque cuando todo esta en un solo metodo es muy dificil leerlo y entenderlo. Al separarlo en metodos mas pequeños, cada uno hace una sola cosa y si algo falla sabemos exactamente donde buscar. Ademas si en algun momento necesitamos cambiar como funciona algo, solo tocamos ese metodo sin afectar el resto del programa.

---

### Variables locales vs globales

#### ¿Cuáles dejamos como globales?

- `estudiantes` y `calificaciones` – se necesitan en casi todos los metodos del programa
- `sc` – el scanner lo usamos en varios metodos para leer datos del usuario

Las dejamos globales porque tienen que existir durante toda la ejecucion del programa y varios metodos las necesitan. Su **tiempo de vida** es el mismo que el del programa completo.

#### ¿Cuáles dejamos como locales?

- `nombre` – solo se usa mientras se esta agregando un estudiante
- `calificacion`, `nota` – solo se necesitan dentro del metodo de validacion o al agregar
- `suma` y `promedio` – solo existen mientras se calcula el promedio
- `opcion` – solo se necesita dentro del menu para saber que eligio el usuario

Estas variables son locales porque su **alcance (scope)** no necesita salir del metodo donde se usan. Cuando el metodo termina, esas variables desaparecen y eso esta bien porque ya no se necesitan.

#### ¿Por qué es importante esto?

Si declaramos todo como global, cualquier metodo puede cambiar esas variables por accidente. Por ejemplo si `suma` fuera global y dos metodos la modificaran, el resultado del promedio podria estar mal sin que nos demos cuenta facilmente. Entre menos variables globales haya, menos riesgo de que un metodo afecte sin querer el funcionamiento de otro.

---

## Parte 2 – Modularización

Lo que hicimos fue separar el programa en metodos donde cada uno tiene una sola responsabilidad. Todos son llamados desde `main` y el programa sigue funcionando igual que antes:

- `agregarEstudiante()` – llama a las validaciones de nombre y calificacion y agrega el estudiante
- `listaEstudiantes()` – muestra todos los estudiantes registrados
- `promedioCalificaciones()` – calcula y muestra el promedio
- `calificacionAlta()` – busca y muestra al estudiante con la nota mas alta
- `pausar()` – espera que el usuario presione Enter antes de continuar
- `validacionMenu()` – lee y valida que la opcion del menu sea un numero y que no sea negativo
- `validacionCalificacion()` – lee y valida que la calificacion sea un numero y que no sea negativo
- `validacionNombre()` – lee y valida que el nombre no este vacio

El main quedo mas corto y ordenado, cada metodo hace una sola cosa y tiene un nombre que dice exactamente lo que hace.

---

## Parte 3 – Validaciones y Manejo de Excepciones

### ¿Qué errores podian ocurrir?

Cuando revisamos el codigo original encontramos varios problemas:

- Si el usuario escribia letras donde se esperaba un numero, el programa lanzaba un `NumberFormatException` y se cerraba de golpe
- Se podia agregar un estudiante con nombre vacio solo presionando Enter
- No habia ninguna restriccion en el valor de la calificacion, se podian ingresar valores como -50 o 999

### ¿Qué validaciones implementamos?

- Pusimos un `try-catch` con `NumberFormatException` en `validacionMenu()` para que si el usuario escribe letras al elegir una opcion, el programa le avise y le vuelva a pedir el dato
- Agregamos un `IllegalArgumentException` en `validacionMenu()` para que tampoco acepte numeros negativos
- Hicimos lo mismo en `validacionCalificacion()`, validamos que sea un número y que este entre 0 y 100 con `nota<0 || nota>100`, si no cumple lanza un 
- Creamos un metodo separado `validacionNombre()` que usa un `do-while` y lanza un `IllegalArgumentException` si el usuario deja el campo vacio con `nombre.isEmpty()`
- En todos los casos usamos un `do-while` para que el programa siga pidiendo el dato hasta que el usuario ingrese algo valido


### ¿Por qué son importantes?

Porque un programa que se cae cada vez que el usuario comete un error no es util. Con las validaciones el programa le avisa al usuario que hizo mal y le da la oportunidad de corregirlo sin que todo truene.

---

## Parte 4 – Preguntas de Reflexión

### ¿Qué ventajas tiene dividir el código en métodos?

- **Organización**: el codigo queda ordenado y cada metodo tiene su lugar, es mucho mas facil navegar por el programa
- **Claridad**: los nombres de los metodos dicen lo que hacen, entonces cualquiera puede entender el programa sin leer cada linea
- **Mantenimiento**: si algo falla sabemos exactamente en que metodo buscar el error, no hay que revisar 80 lineas juntas
- **Reutilizacion**: por ejemplo `pausar()` lo llamamos en varios lugares sin tener que repetir el mismo codigo cada vez

La diferencia es notable, el main paso de ser un metodo gigante a tener solo dos lineas.

### ¿Por qué no es recomendable usar muchas variables globales?

- **Errores inesperados**: cualquier metodo puede cambiar una variable global sin querer y eso puede afectar el resultado de otro metodo que tambien la usa
- **Dificultad para depurar**: cuando hay un error en una variable global es dificil saber cual de todos los metodos fue el que la modifico mal, hay que revisar todos uno por uno
- **Dependencia entre metodos**: si un metodo depende de que otro haya modificado una variable global primero, los metodos ya no son independientes. Eso hace el codigo mas fragil porque si cambias un metodo puedes romper otro sin querer

Mientras ibamos trabajando nos dimos cuenta de esto, cuanto menos variables globales habia mas facil era entender que hacia cada metodo por su cuenta.

### ¿Cómo mejora la modularización la legibilidad del código?

Antes habia que leer todo el main para entender que hacia el programa. Ahora el main tiene dos lineas y los nombres de los metodos ya dicen lo que hacen. Si alguien lee el codigo por primera vez y ve `agregarEstudiante()` o `promedioCalificaciones()`, entiende al instante para que sirve cada parte sin necesidad de leer el codigo adentro. Esa fue una de las cosas que mas nos gusto del proceso.
