Crear una aplicación que sirva como entretenimiento.

1. La aplicación debe de contener un Login (validado de manera estática en código) y una vez iniciada la sesión debe de salvar ese estado para no volver a solicitar el inicio de sesión.

2. La aplicación contará con un logout mediante el cual se perderá el estado de inicio de sesión, por lo que al reiniciar la aplicación deberá volver a solicitar las credenciales de acceso.

3. En la pantalla principal de la aplicación se mostrará una imagen cualquiera con un mensaje de bienvenida a esta e invitando al usuario a seleccionar una opción del menú lateral.

4. La aplicación al tener un menú lateral mostrará las opciones para moverse entre ventanas. Las ventanas serán Perfil, Principal, Trivia de números, Acontecimientos, Star Wars y Perritos. 

5. La aplicación debe mantener la información que se está mostrando en la pantalla aún si el celular cambia de orientación y las pantallas deben de ser responsivas.

6. La aplicación debe de soportar 2 idiomas (inglés y español) a excepción de las respuestas de las APIs

Ventanas:

Principal : 
Scrum 0 - Mensaje de bienvenida 

Perfil : 
Scrum 3 - Se le permitirá cargar al usuario una foto de su galería o tomar una foto usando la cámara del teléfono, así como guardar/editar su información personal. Nombre, Dirección, Teléfono, email. Debe de poder guardar la información escrita. 

Trivia de números : 
Scrum 5 - Se le mostrará al usuario un botón mediante el cual obtendrá un dato aleatorio sobre un número. Obtener la información de la API: http://numbersapi.com

Acontecimientos : 
Scrum 8 - El usuario tendrá 2 opciones para buscar acontecimientos históricos. Obtener la información de la API: http://numbersapi.com 
-La primera opción se le solicitará al usuario que ingrese un valor únicamente numérico menor a cuatro dígitos y la aplicación le devolverá un dato relevante acontecido en ese año mostrándolo en pantalla. 
-Alternativamente el usuario también podrá ingresar el mes y día para buscar un suceso en esa fecha para cualquier año y mostrar la información en la pantalla.

Star Wars : 
Scrum N - En la ventana de Star Wars de la aplicación mostrará un menú de 2 secciones, cada sección mostrará diferentes pantallas, las cuales serán Listado de Películas, Listado de Personajes. La información se puede obtener de la siguiente API: https://swapi.co/ 
Scrum 5 - El listado de películas deberá contener la siguiente información: Título, Episodio, Mensaje inicial, Director, Productor y fecha de lanzamiento.
Scrum 8 - El listado de los nombres de los personajes de las películas de Star Wars, para cada personaje debe de mostrar la siguiente información: Nombre, Altura, Masa, Genero.
 
Perritos : 
Scrum 8 - Esta ventana mostrará una lista de 10 fotos de perritos aleatorios cada que se accede a ella. Se obtiene la información de la siguiente API: https://dog.ceo/dog-api/