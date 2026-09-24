# BitÃ¡cora de Componentes

## Componente 1: Vista de Libro
- **Nombre de la clase / paquete**: LibroView.fxml / org.gc.view.fxml
- **Capa arquitectÃ³nica (MVC/DAO)**: Vista
- **Responsabilidad Ãºnica**: Mostrar la interfaz grÃ¡fica para la gestiÃ³n de libros.
- **Dependencias directas**: LibroController.java, LibroView.css
- **Diagrama/Flujo del dato**: Usuario -> Interfaz FXML -> Pasa acciones al Controlador.

## Componente 2: Controlador de Libro
- **Nombre de la clase / paquete**: LibroController.java / org.gc.controller
- **Capa arquitectÃ³nica (MVC/DAO)**: Controlador
- **Responsabilidad Ãºnica**: Manejar los eventos de la vista LibroView y coordinar con el DAO correspondiente.
- **Dependencias directas**: LibroDAO.java, Libro.java, ObservableList, TableView
- **Diagrama/Flujo del dato**: FXML -> Controller -> DAO -> BD -> Controller -> FXML.
