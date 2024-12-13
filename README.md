Aquí tienes un **README.md** detallado, bien estructurado y documentado para el repositorio **ToDoList App**. He incluido la descripción de cada branch, la arquitectura utilizada, la estructura de carpetas, y cómo se manejan las capas en cada implementación.

---

# 📋 **ToDoList App**

Este repositorio contiene una aplicación **ToDoList** desarrollada tanto para **Android** como para **iOS**, aplicando los principios **SOLID**, **CLEAN Architecture** y **MVVM** en ambas plataformas. Cada implementación se encuentra en su respectiva branch y se explica detalladamente para facilitar su comprensión y mantenimiento.

## 🚀 **Branches Disponibles**

El repositorio está dividido en las siguientes branches:

1. [**main**](#main)
2. [**feature/android_mvvm**](#featureandroid_mvvm)
3. [**feature/mvvm-swiftui-simply**](#featuremvvm-swiftui-simply)
4. [**feature/vm-swiftui-advanced**](#featurevm-swiftui-advanced)

---

## 🗂️ **1. main**

La branch principal contiene únicamente el **README.md** y documentación relacionada con el proyecto. No incluye código fuente de ninguna de las plataformas. Aquí encontrarás una descripción de las implementaciones en Android e iOS y los principios aplicados.

---

## 🤖 **2. feature/android_mvvm**

Esta branch contiene la implementación de la aplicación **ToDoList** para **Android** utilizando **Java** y aplicando los principios **MVVM** (Model-View-ViewModel), **SOLID** y **CLEAN Architecture**.

### 📂 **Estructura del Proyecto**

```
ToDoList/
│-- app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── mike/
│   │   │   │           └── todolist/
│   │   │   │               ├── MainActivity.java
│   │   │   │               ├── adapter/
│   │   │   │               │   └── TaskAdapter.java
│   │   │   │               ├── data/
│   │   │   │               │   ├── database/
│   │   │   │               │   │   └── TaskDatabase.java
│   │   │   │               │   └── model/
│   │   │   │               │       └── Task.java
│   │   │   │               ├── presentation/
│   │   │   │               │   └── addtask/
│   │   │   │               │       └── AddTaskActivity.java
│   │   │   │               └── viewmodel/
│   │   │   │                   └── TaskViewModel.java
│   │   │   └── res/
│   │   │       ├── layout/
│   │   │       │   ├── activity_main.xml
│   │   │       │   └── activity_add_task.xml
│   │   │       └── menu/
│   │   │           └── menu_main.xml
│   └── build.gradle
```

### 🧩 **Capas del Proyecto**

1. **Capa de Presentación** (`presentation`):
   - **`MainActivity.java`**: Muestra la lista de tareas y permite agregar nuevas tareas.
   - **`AddTaskActivity.java`**: Permite agregar o editar una tarea existente.

2. **Capa de Adaptadores** (`adapter`):
   - **`TaskAdapter.java`**: Adaptador para mostrar las tareas en un `RecyclerView`.

3. **Capa de Datos** (`data`):
   - **`Task.java`**: Modelo de datos que representa una tarea.
   - **`TaskDatabase.java`**: Base de datos utilizando **Room**.
   - **`TaskDao.java`**: Interfaz DAO para operaciones de base de datos.

4. **Capa de ViewModel** (`viewmodel`):
   - **`TaskViewModel.java`**: Maneja la lógica de negocio y se comunica con la base de datos.

### 🛠️ **Principios Aplicados**

1. **SOLID**:
   - **Single Responsibility**: Cada clase tiene una única responsabilidad.
   - **Open/Closed**: Las clases están abiertas para extensión pero cerradas para modificación.
   - **Dependency Inversion**: Las dependencias se inyectan a través del `ViewModel`.

2. **CLEAN Architecture**:
   - Separación clara de responsabilidades entre las capas de presentación, datos y lógica de negocio.

---

## 🍏 **3. feature/mvvm-swiftui-simply**

Esta branch contiene la implementación para **iOS** utilizando **SwiftUI** con una arquitectura **MVVM** simplificada.

### 📂 **Estructura del Proyecto**

```
ToDoList/
│-- ToDoListApp/
│   ├── ContentView.swift
│   ├── AddTaskView.swift
│   ├── model/
│   │   └── Task.swift
│   ├── viewmodel/
│   │   └── TaskViewModel.swift
│   └── ToDoListApp.swift
```

### 🧩 **Capas del Proyecto**

1. **Capa de Presentación**:
   - **`ContentView.swift`**: Vista principal que muestra la lista de tareas.
   - **`AddTaskView.swift`**: Vista para agregar o editar una tarea.

2. **Capa de Modelo**:
   - **`Task.swift`**: Define el modelo de una tarea.

3. **Capa de ViewModel**:
   - **`TaskViewModel.swift`**: Gestiona la lógica de negocio.

---

## 🍎 **4. feature/vm-swiftui-advanced**

Esta branch contiene una implementación avanzada de **SwiftUI** con una estructura **MVVM** más detallada y robusta.

### 📂 **Estructura del Proyecto**

```
ToDoList/
│-- ToDoListApp/
│   ├── ContentView.swift
│   ├── AddTaskView.swift
│   ├── model/
│   │   └── Task.swift
│   ├── viewmodel/
│   │   └── TaskViewModel.swift
│   ├── services/
│   │   └── TaskService.swift
│   └── ToDoListApp.swift
```

### 🧩 **Capas del Proyecto**

1. **Capa de Presentación**:
   - **`ContentView.swift`**: Lista principal con búsqueda y filtrado.
   - **`AddTaskView.swift`**: Vista detallada para gestionar tareas.

2. **Capa de Modelo**:
   - **`Task.swift`**: Modelo de datos.

3. **Capa de ViewModel**:
   - **`TaskViewModel.swift`**: Lógica avanzada para manipular tareas.

4. **Capa de Servicios**:
   - **`TaskService.swift`**: Gestiona operaciones complejas relacionadas con tareas.

### 🛠️ **Características Avanzadas**

- **Búsqueda y Filtrado**: Implementación de búsqueda y filtrado de tareas.
- **Animaciones**: Interfaz con animaciones.
- **Principios SOLID**: Aplicación detallada de los principios SOLID para escalabilidad.

---

## 💡 **Cómo Usar Este Repositorio**

1. **Clonar el Repositorio**:

   ```bash
   git clone https://github.com/tu-usuario/todolist.git
   ```

2. **Cambiar a una Branch Específica**:

   - Para Android:

     ```bash
     git checkout feature/android_mvvm
     ```

   - Para iOS (implementación simple):

     ```bash
     git checkout feature/mvvm-swiftui-simply
     ```

   - Para iOS (implementación avanzada):

     ```bash
     git checkout feature/vm-swiftui-advanced
     ```

3. **Abrir el Proyecto**:

   - **Android**: Abre la carpeta `ToDoList` en **Android Studio**.
   - **iOS**: Abre el archivo `.xcodeproj` o `.xcworkspace` en **Xcode**.

---

## 📚 **Licencia**

Este proyecto está bajo la licencia **MIT**.

---

¡Espero que esta guía te sea útil para comprender y trabajar con el proyecto **ToDoList App**! 😊🚀
