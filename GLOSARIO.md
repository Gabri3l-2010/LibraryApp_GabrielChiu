# Glosario TÃ©cnico

## Ficha 1: FXML
1. **TÃ©rmino tÃ©cnico**: FXML
2. **DefiniciÃ³n formal**: Lenguaje de marcado basado en XML diseÃ±ado para definir interfaces de usuario en aplicaciones JavaFX, separando la presentaciÃ³n de la lÃ³gica.
3. **DefiniciÃ³n en mis palabras**: Es un archivo tipo XML donde dibujo cÃ³mo se verÃ¡ la pantalla de la aplicaciÃ³n sin programar.
4. **UbicaciÃ³n en el cÃ³digo**: src/org/gc/view/fxml/
5. **Ejemplo prÃ¡ctico & problema que resuelve**: En LibroView.fxml defino la estructura de la ventana. Resuelve el problema de tener cÃ³digo de diseÃ±o mezclado con cÃ³digo Java.

## Ficha 2: SceneBuilder
1. **TÃ©rmino tÃ©cnico**: SceneBuilder
2. **DefiniciÃ³n formal**: Herramienta de diseÃ±o visual que permite generar cÃ³digo FXML rÃ¡pidamente sin escribir XML manualmente.
3. **DefiniciÃ³n en mis palabras**: Un programa donde arrastro botones y tablas para crear la interfaz visual de forma rÃ¡pida.
4. **UbicaciÃ³n en el cÃ³digo**: Herramienta externa.
5. **Ejemplo prÃ¡ctico & problema que resuelve**: DiseÃ±ar visualmente LibroView.fxml. Resuelve la dificultad de escribir interfaces grÃ¡ficas con puro cÃ³digo XML.

## Ficha 3: Controller
1. **TÃ©rmino tÃ©cnico**: Controller
2. **DefiniciÃ³n formal**: Clase en el patrÃ³n MVC que sirve como intermediario entre la vista (FXML) y el modelo.
3. **DefiniciÃ³n en mis palabras**: Es el archivo Java que le da vida a la pantalla y ejecuta acciones al presionar botones.
4. **UbicaciÃ³n en el cÃ³digo**: src/org/gc/controller/
5. **Ejemplo prÃ¡ctico & problema que resuelve**: LibroController.java se encarga de guardar un libro cuando el usuario presiona "Guardar".

## Ficha 4: ObservableList
1. **TÃ©rmino tÃ©cnico**: ObservableList
2. **DefiniciÃ³n formal**: Interfaz en JavaFX que extiende java.util.List para permitir a los listeners rastrear cambios.
3. **DefiniciÃ³n en mis palabras**: Es una lista especial que avisa a la pantalla si se agregÃ³ o borrÃ³ un elemento para actualizarse sola.
4. **UbicaciÃ³n en el cÃ³digo**: Definido en Controladores.
5. **Ejemplo prÃ¡ctico & problema que resuelve**: Al usar ObservableList para un TableView, la tabla se refresca automÃ¡ticamente al aÃ±adir un nuevo registro.

## Ficha 5: TableView
1. **TÃ©rmino tÃ©cnico**: TableView
2. **DefiniciÃ³n formal**: Control de UI en JavaFX diseÃ±ado para visualizar datos estructurados en filas y columnas.
3. **DefiniciÃ³n en mis palabras**: Es el componente visual de tabla que lista datos en la pantalla.
4. **UbicaciÃ³n en el cÃ³digo**: Archivos FXML y Controladores.
5. **Ejemplo prÃ¡ctico & problema que resuelve**: Listar todos los registros de la base de datos en la pantalla de forma ordenada.
