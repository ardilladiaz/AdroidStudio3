# Evaluación Integradora Módulo 6
**Proyecto:** Alke Wallet  
**Desarrollador:** Cinthia Díaz 

---

## Situación inicial 
**Unidad solicitante:** Equipo de desarrollo de una empresa fintech.  

El equipo de desarrollo ha recibido la solicitud de crear una wallet digital para la empresa Alkemy Digital. La problemática a resolver es brindar a los usuarios una solución segura y fácil de usar para administrar sus activos financieros de manera digital. En etapas previas (Módulo 5), se construyó la estructura básica visual, pero para esta fase final, el objetivo radicaba en culminar el proceso de desarrollo habilitando la base de datos local y la conexión con una API REST externa.

## Objetivo 
El objetivo de nuestro proyecto "Alke Wallet" es desarrollar una billetera digital que permita a los usuarios gestionar sus activos financieros de manera segura y conveniente. Nuestro fin es entregar una aplicación funcional, aplicando buenas prácticas de programación, que proporcione a los usuarios una solución confiable para administrar sus activos.

## Pantallas de la App
La interfaz gráfica garantiza una navegación intuitiva y una experiencia de usuario fluida, conformada por:
- Splash Screen  
- Login / Signup Page  
- Login Page  
- Signup Page  
- Home Page  
- Profile Page  
- Send Money  
- Request Money  

---

## Requerimientos Técnicos e Implementación 

Para garantizar la calidad y funcionalidad de la aplicación, el proyecto ha sido desarrollado cumpliendo con los siguientes requerimientos técnicos avanzados:

### 1. Arquitectura y Diseño MVVM
El proyecto respeta estrictamente el patrón de arquitectura MVVM (Modelo-Vista-VistaModelo).
* **Modelo (Model):** Contiene las entidades de datos (`Usuario`, `Transaccion`) y la lógica de acceso a datos locales y remotos.
* **Vista (View):** Conformada por los fragmentos (ej. `HomePage`) y adaptadores (ej. `TransaccionAdapter`). Su única responsabilidad es mostrar la interfaz y reaccionar a los cambios, estando totalmente desacoplada de la lógica de negocio.
* **VistaModelo (ViewModel):** Clases como `UsuarioViewModel` y `TransaccionViewModel` actúan como puente. Coordinan las llamadas asíncronas a Retrofit y Room mediante corrutinas (`viewModelScope.launch`) y actualizan la vista a través de `LiveData`.

### 2. Almacenamiento Local con Room
Para asegurar una respuesta rápida y permitir el acceso sin conexión al historial de movimientos, se implementó la librería Room.
* Se crearon entidades y tablas estructuradas.
* Se desarrolló un DAO (Data Access Object) (`TransaccionDao`) para realizar las operaciones CRUD.
* El ViewModel gestiona la inserción y lectura de estos datos locales, asegurando que estén disponibles aunque el dispositivo pierda la conexión a internet.

### 3. Comunicación con API REST (Retrofit)
Para habilitar transacciones virtuales reales, la aplicación se integró con una API REST externa utilizando Retrofit.
* Se creó una interfaz `ApiService` configurada con los endpoints oficiales para realizar peticiones HTTP (como `@GET` para `obtenerTransacciones` y `obtenerUsuarios`).
* La descarga de información funciona en segundo plano sin congelar la pantalla del usuario.

### 4. Manejo de Errores y Experiencia de Usuario
Se incorporó un manejo de errores robusto para evitar caídas de la aplicación (crashes).
* Se emplearon bloques de seguridad `try-catch` dentro de las corrutinas para gestionar fallos de conexión de Retrofit o de escritura en Room.
* En lugar de exponer errores técnicos o líneas de código al usuario, se implementaron mensajes amigables y comprensibles mediante notificaciones en pantalla (`Toast`), como *"Error de conexión. Verifique su internet y vuelva a intentarlo"*.

### 5. Pruebas Unitarias Automatizadas (Testing)
Se incluyeron pruebas unitarias para certificar la estabilidad de la lógica principal antes del despliegue.
* **Prueba de Base de Datos (`TransaccionDaoTest`):** Se testeó el acceso a Room creando una base de datos en memoria para verificar que las funciones de insertar y leer transacciones operen correctamente.
* **Prueba de Lógica de Negocio (`UsuarioViewModelTest`):** Se verificaron los flujos de autenticación evaluando credenciales válidas e inválidas, asegurando que el inicio de sesión responda correctamente (retornando un usuario o nulo según el caso).

---

## Consideraciones Finales 

* **Evolución de los Datos:** Durante el Módulo 5 de este proyecto, se utilizaron `DataSets` (como `UsuariosDataSet` y `DestinatariosDataSet`) para simular información y probar el funcionamiento visual de la app. Para esta entrega final del **Módulo 6**, esos datos simulados han sido reemplazados exitosamente por información real persistida localmente (Room) y consumida desde internet (Retrofit).
* **Datos de Prueba:** Si se desea probar la lógica de autenticación local sin red, se puede ingresar con el correo: `amanda@gmail.com` y contraseña: `amanda123`. Al crear transacciones, estas se guardarán en la memoria del dispositivo y podrán ser visualizadas en el historial.
