Java y POO

1. Diferencia entre clase y objeto.
Respuesta investigada: Una clase es una plantilla o plano conceptual que define atributos y métodos comunes para una entidad. Un objeto es una instancia concreta de esa clase existente en memoria durante la ejecución.
Propias palabras: la clase define los atributos de una entidad y un objeto es algo que existe dentro del programa que tiene sus propios datos y acciones 
Ejemplo: El campo private String isbn; dentro de la clase Libro.

2. ¿Qué es un atributo?
Respuesta investigada: Es una variable declarada dentro de una clase que representa una característica, propiedad o estado del objeto.
Propias palabras: es la informacion que se le guarda al objeto 
Ejemplo: El campo private String isbn; dentro de la clase Libro.

3. ¿Qué es un método?
Respuesta investigada: Es un bloque de código o función definida dentro de una clase que especifica el comportamiento o acciones que un objeto puede realizar.
Propias palabras: es la accion que ejecuta el objeto cuando se lo pide 
Ejemplo: El método prestarLibro() que cambia el estado del libro a "no disponible".

4. ¿Para qué sirve un constructor?
Respuesta investigada: Es un método especial invocado automáticamente al instanciar un objeto (new) cuya función principal es inicializar sus atributos.
Propias palabras: es el bloque que le da los primeros datos a un objeto en el momento que nace  
Ejemplo: public Libro(String titulo, String autor) { this.titulo = titulo; this.autor = autor; }

5. Diferencia entre private, public y protected.
Respuesta investigada: Son modificadores de acceso. public permite acceso global; private restringe el acceso solo a la propia clase; protected permite acceso a la clase, subclases y clases del mismo paquete.
Propias palabras: public es una puerta abierta para todos private solo lo puede ver quien tiene acceso y proyected se comparte solo entre las mismas clases heredadas 
Ejemplo: El atributo private double multa no puede modificarse directamente desde fuera de la clase Usuario.

6. ¿Para qué sirven get y set?
Respuesta investigada: Son métodos accesores (get) y modificadores (set) que permiten leer y actualizar el valor de atributos privados de forma controlada.
Propias palabras: set sirve para cambiar un dato y set 
Ejemplo: getTitulo() muestra el título del libro y setEstado() cambia su disponibilidad.

7. ¿Qué significa encapsulamiento?
Respuesta investigada: Principio de la POO que oculta el estado interno de un objeto y restringe el acceso directo a sus datos, exponiendo solo interfaces seguras.
Propias palabras: consiste en proteger la informacion del objeto
Ejemplo: arcar variables como private y validar en setStock() que no se ingresen números negativos.

8. Diferencia entre clase e interfaz.
Respuesta investigada: Una clase define implementación (atributos y métodos ejecutables). Una interfaz es un contrato abstracto que define únicamente firmas de métodos que otras clases deben implementar.
Propias palabras: la clase dice como se hacen las cosasy la interfaz solo exige que se debe hacer sin detallar como 
Ejemplo: Una interfaz RepositorioLibro que declara guardar(), implementada luego por RepositorioLibroSQL.

9. ¿Qué significa implements?
Respuesta investigada: Palabra clave usada en Java para indicar que una clase se compromete a cumplir el contrato definido por una interfaz.
Propias palabras: es la orden que se le da a una clase 
Ejemplo: public class LibroDAOImpl implements LibroDAO

10. ¿Qué significan static y final?
Respuesta investigada: static indica que un miembro pertenece a la clase y no a las instancias. final define un elemento inmutable (constante, método no sobrescribible o clase no heredable).
Propias palabras: static es un dato compartido por todos los objetos y final significa que no se puede cambiar 
Ejemplo: public static final int MAX_LIBROS_PRESTADOS = 3;



Base de datos y persistencia


11. ¿Qué es una tabla relacional?
Respuesta Investigada: Estructura bidimensional de datos compuesta por filas (registros) y columnas (campos) vinculada con otras mediante relaciones matemáticas.
Propias palabras: es una hoja de calculo donde se guardan los datos organizados por filas y columnas 
Ejemplo: La tabla libros con campos id, titulo y anio_publicacion.

12. ¿Qué es una clave primaria?
Respuesta Investigada: Un identificador único e irrepetible para cada registro dentro de una tabla de base de datos relacional.
Propias palabras: es como un edintificador unico que ya no se puede repetir 
Ejemplo: El campo id_libro en la tabla libros.

13. ¿Qué es una clave foránea?
Respuesta Investigada: Un campo en una tabla que hace referencia a la clave primaria de otra tabla para mantener la integridad referencial.
Propias palabras: es un enlace que coneca un registro con el registro de otra tabla 
Ejemplo: id_usuario guardado dentro de la tabla prestamos.

14. ¿Qué significa CRUD?
Respuesta Investigada: Acrónimo para Create (Crear), Read (Leer), Update (Actualizar) y Delete (Eliminar), las cuatro operaciones básicas en persistencia de datos.
Propias palabras: son las cuatro acciones necesarias que se deben hacer en un registro almacenado 
Ejemplo: Registrar libro (C), buscar catálogo (R), editar autor (U) y borrar libro (D).

15. ¿Qué es JDBC?
Respuesta Investigada: Java Database Connectivity: una API de Java que permite ejecutar sentencias SQL y conectar la aplicación con bases de datos relacionales.
Propias palabras: es el puente que permite a java conectarse con sql 
Ejemplo: El uso de DriverManager.getConnection() para abrir la base de datos de la biblioteca.

16. ¿Qué es un procedimiento almacenado?
Respuesta Investigada: Un conjunto de instrucciones SQL guardado y ejecutado directamente en el servidor de base de datos.
Propias palabras: pasos para que sql se guarde y ejecute en el servidor 
Ejemplo: El procedimiento sp_registrar_prestamo() en MySQL.

17. ¿Qué es DAO?
Respuesta Investigada: Data Access Object: patrón de diseño que aísla la lógica de negocio del acceso a los datos persistentes.
Propias palabras: es una clase dedicada solo a leer y escribir datos de la base de datos 
Ejemplo: LibroDAO centraliza las consultas SQL de la entidad Libro.

18. ¿Por qué separar SQL del Controller?
Respuesta investigada: Para mantener el principio de responsabilidad única, evitar el acoplamiento y facilitar el mantenimiento del código.
Propias palabras: si se cambia la base de datos se cambia automaticamente too lo demas 
Ejemplo: LibroController responde a los clics, mientras LibroDAO ejecuta los INSERT INTO.


JavaFX y arquitectura

19. ¿Qué es JavaFX?
Respuesta Investigada: Plataforma de software para crear aplicaciones de escritorio con interfaces gráficas ricas en Java.
Propias palabras: la herramienta de java que sirve para todo lo visual: ventanas, botones y pantallas visuales 
Ejemplo: El archivo VistaLibros.fxml.

20. ¿Qué es FXML?
Respuesta Investigada: Un lenguaje basado en XML utilizado en JavaFX para definir la estructura de la interfaz de usuario de forma declarativa.
Propias palabras: es un archivo que guarda el diseño 
Ejemplo: El archivo VistaLibros.fxml.

21. ¿Qué función cumple SceneBuilder?
Respuesta Investigada:
Propias palabras: es un programa visual donde se acomoda todos los botones, 
Ejemplo: 

22. ¿Qué es un Controller?
Respuesta Investigada: s una clase Java en JavaFX que actúa como intermediario, manejando la lógica de la interfaz de usuario y respondiendo a los eventos generados en la vista.
Propias palabras: es lo que hace que funcione los botones cuando los precionamos 
Ejemplo: Una clase llamada MiControlador.java con un método que valida si el usuario puso la contraseña correcta.

23. ¿Qué significa @FXML?
Respuesta Investigada:Es una anotación de JavaFX utilizada para vincular variables o métodos del archivo Java con los componentes definidos en el archivo FXML.
Propias palabras: Es una etiqueta para indicarle a Java cuál botón o caja de texto del diseño visual le pertenece a cada variable del código.
Ejemplo: @FXML private Button btnGuardar;

24. ¿Qué significa MVC?
Respuesta Investigada: Es el patrón de arquitectura de software Modelo-Vista-Controlador que separa los datos, la interfaz de usuario y la lógica de control.
Propias palabras: es una forma de organizar el proyecto dividiendolo en tres partes para lo mezclar el diseño con el codigo ni con los datos 
Ejemplo: Tener carpetas separadas en el proyecto llamadas model, view y controller.

25. ¿Qué responsabilidad tiene el modelo?
Respuesta Investigada: Gestiona los datos, la lógica del negocio y las reglas del sistema, de manera independiente a la interfaz de usuario.
Propias palabras: es el encargado de procesar y guardar la informacion real de la aplicacion 
Ejemplo: Una clase Usuario con atributos como nombre, correo y funciones para calcular si es mayor de edad.

26. ¿Qué responsabilidad tiene la vista?
Respuesta Investigada: Representa la interfaz gráfica de usuario (GUI) y se encarga de mostrar la información al usuario final.
Propias palabras: Es la pantalla que ve el usuario, con sus colores, ventanas, inputs y botones.
Ejemplo: Un archivo login.fxml dibujado con inputs de texto para usuario y clave.

27. ¿Qué responsabilidad tiene el controlador?
Respuesta Investigada: Captura los eventos del usuario desde la vista, procesa las acciones interactuando con el modelo y actualiza la vista.
Propias palabras: Es el puente que recibe lo que hace el usuario en la pantalla y le pide al modelo que procese los datos.
Ejemplo: Leer el texto ingresado en la pantalla de Login y enviarlo al modelo para verificar si el usuario existe.

28. Dibuja el recorrido de un dato hasta MySQL.
Respuesta Investigada: Vista (FXML) -> Controlador (Java) -> Modelo / DAO (Java) -> Conexión JDBC -> Base de Datos (MySQL).
Propias palabras: El dato entra por la pantalla, el controlador lo recibe, el modelo lo prepara y por JDBC se guarda en MySQL.
Ejemplo: 
[Usuario escribe en Vista (FXML)]
       ↓
[Controlador captura evento del botón]
       ↓
[Modelo/DAO crea la consulta SQL]
       ↓
[JDBC envía el 'INSERT INTO...']
       ↓
[Tabla en MySQL]


cuestionario extra semana 2

Cuestionamiento extra: ¿Qué es la "Inyección SQL" y cómo la previenen PreparedStatement y CallableStatement?

Qué es la Inyección SQL?
Es una vulnerabilidad que ocurre al concatenar directamente datos del usuario en una consulta SQL sin validación. Permite a un atacante ejecutar código malicioso para eludir autenticaciones, acceder a datos confidenciales o manipular la base de datos.

Ejemplo de código vulnerable:
String query = "SELECT * FROM usuarios WHERE usuario = '" + usuario + "' AND password = '" + password + "'";


cuestionario semana 3 
 por que JavaFX utiliza ObserbableList en lugar de las listas de Java (ArrayList para nutrit componentes como TableView)?

