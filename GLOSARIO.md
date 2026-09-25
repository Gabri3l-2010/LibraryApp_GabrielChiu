
glosario semana 1

Clase:Plantilla o modelo abstracto que define los atributos (propiedades) y métodos (comportamientos) que compartirán los objetos creados a partir de ella

Objeto: Una instancia concreta de una clase. Contiene valores específicos para los atributos definidos en la plantilla y puede ejecutar los métodos de su clase.

Encapsulamiento: Principio de la Programación Orientada a Objetos que oculta los detalles internos del estado de un objeto y restringe el acceso directo a sus datos, exponiéndolos únicamente a través de métodos públicos (como *getters* y *setters*).

Tipo Primitivo: Tipo de dato básico integrado en el lenguaje (por ejemplo, `int`, `double`, `boolean`, `char`) que almacena directamente su valor en memoria, sin ser un objeto ni poseer métodos.

Clase Wrapper: Clase de Java (como `Integer`, `Double`, `Boolean`) que "envuelve" un tipo de dato primitivo para convertirlo en un objeto, permitiendo su uso en estructuras de datos que requieren objetos (como `List` o `Map`).

glosario semana 2

DAO: Patrón de diseño que abstrae y separa el acceso a la base de datos de la lógica de negocio de la aplicación, centralizando las operaciones de lectura y escritura (CRUD).

JDBC: API de Java que proporciona un conjunto de clases e interfaces estándar para conectar aplicaciones Java con bases de datos relacionales y ejecutar consultas SQL.

Singleton:Patrón de diseño creacional que garantiza que una clase tenga una única instancia en toda la aplicación y proporciona un punto de acceso global a ella.

PreparedStatement: Interfaz de JDBC que representa una sentencia SQL precompilada. Permite ejecutar consultas de forma eficiente mediante parámetros dinámicos y previene ataques de inyección SQL.

Inyección SQL Vulnerabilidad de seguridad que ocurre cuando un atacante inserta código SQL malicioso en las entradas de datos de una aplicación para manipular o acceder indebidamente a la base de datos.


glosario semana 3


FXML:
Un lenguaje de marcado basado en XML que se utiliza para diseñar la interfaz gráfica de usuario (GUI) en aplicaciones de JavaFX. Permite separar la estructura visual de la lógica del programa.

SceneBuilder:
Una herramienta de diseño visual e interactiva para JavaFX. Permite construir e interfaces FXML mediante arrastrar y soltar (drag and drop) componentes, sin necesidad de escribir el código XML manualmente.

Controller:
Una clase de Java que gestiona la lógica de la interfaz visual creada en FXML. Se encarga de manejar los eventos de la aplicación (como clics de botones o entradas de texto) y conectar la vista con los datos de la aplicación.

ObservableList:
Una interfaz de JavaFX que extiende de java.util.List y permite a los componentes de la interfaz escuchar cuando ocurre un cambio en la lista (como agregar, modificar o eliminar un elemento) para actualizar automáticamente la interfaz gráfica.

TableView:
Un control visual de JavaFX diseñado para mostrar datos e información organizados en filas y columnas (tablas). Se vincula frecuentemente con una ObservableList para actualizar los datos mostrados en tiempo real.


glosario semana 4

Excepción
Un evento anómalo o error que ocurre durante la ejecución de un programa y que interrumpe el flujo normal de sus instrucciones (por ejemplo, intentar dividir por cero o acceder a un archivo que no existe).

Manejo de Errores
El conjunto de técnicas y estructuras de código (como los bloques try-catch) utilizadas para anticipar, detectar y responder adecuadamente a las excepciones o fallos, evitando que el programa se detenga de forma inesperada.

Release
Una versión estable y terminada de un software que se distribuye o despliega para que la utilicen los usuarios finales. Incluye el código fuente compilado, correcciones de errores y nuevas funcionalidades listas para producción.

Tag (Git)
Una marca o referencia fija que apunta a un punto específico en la historia de commits de un repositorio de Git. Se utiliza habitualmente para etiquetar versiones clave del proyecto, como una release (por ejemplo, v1.0.0).