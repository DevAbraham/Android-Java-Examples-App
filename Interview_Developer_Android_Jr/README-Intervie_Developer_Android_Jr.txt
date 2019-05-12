Crear una aplicaci�n que sirva como entretenimiento.

1. La aplicaci�n debe de contener un Login (validado de manera est�tica en c�digo) y una vez iniciada la sesi�n debe de salvar ese estado para no volver a solicitar el inicio de sesi�n.

2. La aplicaci�n contar� con un logout mediante el cual se perder� el estado de inicio de sesi�n, por lo que al reiniciar la aplicaci�n deber� volver a solicitar las credenciales de acceso.

3. En la pantalla principal de la aplicaci�n se mostrar� una imagen cualquiera con un mensaje de bienvenida a esta e invitando al usuario a seleccionar una opci�n del men� lateral.

4. La aplicaci�n al tener un men� lateral mostrar� las opciones para moverse entre ventanas. Las ventanas ser�n Perfil, Principal, Trivia de n�meros, Acontecimientos, Star Wars y Perritos. 

5. La aplicaci�n debe mantener la informaci�n que se est� mostrando en la pantalla a�n si el celular cambia de orientaci�n y las pantallas deben de ser responsivas.

6. La aplicaci�n debe de soportar 2 idiomas (ingl�s y espa�ol) a excepci�n de las respuestas de las APIs

Ventanas:

Principal : 
Scrum 0 - Mensaje de bienvenida 

Perfil : 
Scrum 3 - Se le permitir� cargar al usuario una foto de su galer�a o tomar una foto usando la c�mara del tel�fono, as� como guardar/editar su informaci�n personal. Nombre, Direcci�n, Tel�fono, email. Debe de poder guardar la informaci�n escrita. 

Trivia de n�meros : 
Scrum 5 - Se le mostrar� al usuario un bot�n mediante el cual obtendr� un dato aleatorio sobre un n�mero. Obtener la informaci�n de la API: http://numbersapi.com

Acontecimientos : 
Scrum 8 - El usuario tendr� 2 opciones para buscar acontecimientos hist�ricos. Obtener la informaci�n de la API: http://numbersapi.com 
-La primera opci�n se le solicitar� al usuario que ingrese un valor �nicamente num�rico menor a cuatro d�gitos y la aplicaci�n le devolver� un dato relevante acontecido en ese a�o mostr�ndolo en pantalla. 
-Alternativamente el usuario tambi�n podr� ingresar el mes y d�a para buscar un suceso en esa fecha para cualquier a�o y mostrar la informaci�n en la pantalla.

Star Wars : 
Scrum N - En la ventana de Star Wars de la aplicaci�n mostrar� un men� de 2 secciones, cada secci�n mostrar� diferentes pantallas, las cuales ser�n Listado de Pel�culas, Listado de Personajes. La informaci�n se puede obtener de la siguiente API: https://swapi.co/ 
Scrum 5 - El listado de pel�culas deber� contener la siguiente informaci�n: T�tulo, Episodio, Mensaje inicial, Director, Productor y fecha de lanzamiento.
Scrum 8 - El listado de los nombres de los personajes de las pel�culas de Star Wars, para cada personaje debe de mostrar la siguiente informaci�n: Nombre, Altura, Masa, Genero.
 
Perritos : 
Scrum 8 - Esta ventana mostrar� una lista de 10 fotos de perritos aleatorios cada que se accede a ella. Se obtiene la informaci�n de la siguiente API: https://dog.ceo/dog-api/