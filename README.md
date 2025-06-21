## Proyecto de validación de código QR

Este proyecto se plantea la construcción de un ticket de pago para una ruleta del tipo juegos de azar, dicho ticket posee un código QR, que debe ser validado para garantizar la autenticidad, del premio.

## Estructura del Proyecto

El proyecto tiene una estructura de implementación de patrones de programación para la futura extensión, y garantía de solides en el producto.

Principalmente los paquetes encargados del flujo de construcción del ticket son:

- `ticketmodelo`: Construye la estructura de datos que debe contener el ticket, mismos que son cifrados.
- `ticketimpresion`: Declara las utilidades para la impresión, y en caso de un error por parte de la impresora, muesta el ticket en pantalla.

Para la validación y encriptación se encargan los paquetes:

- `validarQR`: Crea estrategias de validación que pueden ser extendidas, la finalidad es verificar un formato de guardado de la data del ticket.
- `encriptadorQR`: Usando la encriptación base64, cifra la información en el QR, con la finalidad de ser asegurada para el posterior proceso de cobro en taquilla.

## ✅ Requisitos del Proyecto

Este proyecto está desarrollado en Java y requiere los siguientes componentes para su correcta ejecución:

### ☕ Requisitos de Java
- **JDK**: Java Development Kit 8 o superior.

### 📁 Estructura del Proyecto
- Código fuente: `src/`
- Salida de compilación: `bin/`
- Bibliotecas externas: `lib/`

### 📦 Dependencias

Asegúrate de que las siguientes bibliotecas estén presentes en el directorio `lib/` del proyecto:

| Archivo JAR             | Descripción breve                                           |
|-------------------------|-------------------------------------------------------------|
| `core-3.5.3.jar`        | Núcleo de la biblioteca ZXing para generación de códigos QR. |
| `icu4j-76.1.jar`        | International Components for Unicode (formato y locales).   |
| `javase-3.5.3.jar`      | Soporte Java SE para ZXing (impresión, imagen, etc.).       |
| `json-20240303.jar`     | Biblioteca para manejo de datos JSON.                       |

> Estas bibliotecas son requeridas para funciones como: generación y validación de códigos QR, manejo de formatos regionales y procesamiento de datos JSON.
