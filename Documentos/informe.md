# INFORME DEL EXAMEN FINAL

## Base de datos

He utilizado una imagen de postgres en Docker

**Query de creacion de la BD**
![BD](./img/createDB.png)

## Eureka Server

### Instancias de ms-auth

![](./img/ms-auth.png)

### Instancias de ms-productos

![](./img/ms-productos.png)

### Instancias de ms-pedidos

![](./img/ms-ordenes.png)

### Instancias en Eureka Web

![](./img/eureka-service-web.png)

## Vault

### Todos los secrets

![](./img/secrets-vault.png)

### ms-auth vault

![](./img/ms-auth-vault.png)

### ms-productos vault

![](./img/ms-productos-vault.png)

### ms-ordenes vault

![](./img/ms-ordenes-vault.png)

## ms-auth

### ENDPOINT: register

**La tabla usuarios antes**
![userBefore](./img/user-before.png)

#### Registro de un user

![](./img/registerUser.png)

#### Registro de un admin

![](./img/registerAdmin.png)

#### Registro de un superadmin

![](./img/registerSuperadmin.png)

**La tabla usuarios despues**
![userAfter](./img/usuario-after.png)

### ENDPOINT: login

![](./img/login.png)

### ENDPOINT: validate

![](./img/validate.png)

### ENDPOINT: listar usuarios

![](./img/test-user.png)

### ENDPOINT: listar admins

![](./img/test-admin.png)

### ENDPOINT: listar superadmins

![](./img/test-superadmin.png)

## ms-producto

### ENDPOINT: crear producto

**La tabla productos antes**
![productBefore](./img/producto-before.png)

#### Creacion de un producto

![](./img/crearProducto.png)

**La tabla productos despues**
![productoAfter](./img/producto-after.png)

### ENDPOINT: listar productos

![](./img/listarProducto.png)

### ENDPOINT: editar producto

#### Edicion del producto

![](./img/crearProducto.png)

**La tabla productos despues**
![productAfterUpdate](./img/producto-after-update.png)

### ENDPOINT: eliminar producto

![](./img/eliminarProducto.png)

## ms-ordenes

### ENDPOINT: crear orden

**La tabla orden antes**
![orderBefore](./img/orden-before.png)

**La tabla producto-orden antes**
![productOrderBefore](./img/orden-producto-before.png)

#### Creacion de una orden

![](./img/crearOrden.png)

**La tabla orden despues**
![orderAfter](./img/orden-after.png)

**La tabla producto-orden despues**
![productOrderAfter](./img/orden-producto-after.png)
