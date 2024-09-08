# Ejercicio Backend BP

## Descripción
Este proyecto es una aplicación backend desarrollada con **Spring Boot** y **Java 17**, diseñada para ejecutarse en contenedores Docker mediante **Docker Compose**. Proporciona una API backend que puede ser desplegada y gestionada fácilmente en entornos de contenedores.

## Requisitos Previos
Asegúrate de tener instalados los siguientes componentes:

- **Java 17**.
- **Gradle** para la gestión de dependencias y construcción del proyecto.
- **Docker** y **Docker Compose** para la orquestación de contenedores.

## Instalación

### Clonar el repositorio
Clona el repositorio en tu máquina local:

```bash
git clone https://github.com/icaceres/ejercicio-bp.git
cd ejercicio-bp
```

### Compilar el proyecto
El proyecto utiliza Gradle como herramienta de compilación. Para compilarlo, ejecuta en cada directorio de **bp-account** y **bp-client** respectivamente:

```bash
./gradlew build
```
Este comando generará el archivo JAR dentro de build/libs.

### Ejecución con Docker Compose
Iniciar los servicios
Para iniciar la aplicación y todos los servicios definidos en el archivo docker-compose.yml, simplemente ejecuta:

```bash
docker-compose up --build
```

### Detener los servicios
Para detener los contenedores:

```bash
docker-compose down
```
