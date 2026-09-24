1.Clase
Definición formal: Plantilla o molde ejecutable en programación orientada a objetos que define los atributos (propiedades) y métodos (comportamientos) comunes a todos los objetos creados a partir de ella.
 mis palabras: El plano arquitectónico o molde que dice qué datos va a tener y qué puede hacer un elemento del programa.
Ubicación en el código: src/main/java/com/proyecto/model/Usuario.java (en la declaración public class Usuario).
Ejemplo: Permite definir la estructura de un cliente (nombre, correo) una sola vez en lugar de duplicar variables sueltas por todo el proyecto.

2. Objeto
Definición formal: Instancia concreta de una clase creada en tiempo de ejecución, que posee un estado definido por sus atributos y un comportamiento definido por sus métodos.
mis palabras: Un elemento real creado con la plantilla de la clase, con sus datos específicos cargados en memoria.
Ubicación en el código: src/main/java/com/proyecto/controller/LoginController.java (al instanciar con new Usuario()).
Ejemplo: Usuario user1 = new Usuario("Juan", "juan@mail.com");. Resuelve la necesidad de manipular datos reales e individuales de usuarios en el sistema.

3. Encapsulamiento
Definición formal: Principio de la POO que oculta el estado interno de un objeto restringiendo el acceso directo a sus atributos mediante modificadores de visibilidad (private) y exponiendo solo métodos de acceso (getters y setters).
mis palabras: Poner en privado los datos de una clase para que nadie los cambie directamente desde afuera sin pasar por reglas de validación.
Ubicación en el código: src/main/java/com/proyecto/model/Producto.java (variables private double precio; con sus getPrecio() y setPrecio()).
Ejemplo: Evita que otro programa ponga un precio negativo directamente (producto.precio = -500;), obligando a usar un método que valide el valor.

4. Tipo Primitivo
Definición formal: Tipo de dato básico proporcionado por el lenguaje Java que almacena directamente un valor simple en memoria y no posee métodos ni atributos.
Definición en mis palabras: Una variable básica y liviana que guarda un dato directo (como números o booleanos) sin ser un objeto complejo.
Ubicación en el código: src/main/java/com/proyecto/model/Usuario.java (declaración private int edad; o private boolean activo;).
Ejemplo: Permite realizar operaciones matemáticas directas y consumir la mínima cantidad de memoria RAM al manejar contadores o estados simples.

5. Clase Wrapper
Definición formal: Clase envolvente del paquete java.lang que representa un tipo de dato primitivo como un objeto completo (ej. Integer para int), permitiendo su uso en colecciones o con valores null.
mis palabras: Una versión "avanzada" de un dato básico que lo convierte en objeto para poder usarlo en listas o asignarle valor nulo.
Ubicación en el código: src/main/java/com/proyecto/dao/UsuarioDAO.java (al definir un List<Integer> o convertir texto con Integer.parseInt()).
Ejemplo: Permite guardar números enteros dentro de un ArrayList<Integer>, algo que Java no permite hacer directamente con el tipo primitivo int.

Glosario semana 2

1.DAO (Data Access Object)
Patrón de diseño que abstrae e aísla los mecanismos de acceso a la base de datos del resto de la aplicación, proporcionando una interfaz limpia para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar).

2.Inyección SQL
Vulnerabilidad de seguridad en la que un atacante inserta o "inyecta" código SQL malicioso a través de los datos de entrada del usuario para manipular las consultas a la base de datos y acceder o modificar datos no autorizados.

3.JDBC (Java Database Connectivity)
API estándar de Java que permite a las aplicaciones conectarse e interactuar con bases de datos relacionales mediante la ejecución de consultas y sentencias SQL.

4.PreparedStatement
Interfaz de JDBC que representa una sentencia SQL precompilada. Permite la ejecución eficiente de consultas repetitivas y ayuda a prevenir ataques de **Inyección SQL** mediante la parametrización de datos.

5.Singleton
Patrón de diseño creacional que garantiza que una clase tenga únicamente una instancia en toda la aplicación y proporciona un punto de acceso global a ella.L