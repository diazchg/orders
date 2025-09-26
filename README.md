# Orders API

Una API para gestionar órdenes de clientes, permitiendo crear y procesar pedidos con información de clientes y productos.

## Instalación

Sigue estos pasos para instalar y ejecutar el proyecto localmente:

1. Clona el repositorio:
   ```bash
   git clone https://github.com/diazchg/orders.git
   ```

2. Navega a la carpeta del proyecto:
   ```bash
   cd orders
   ```

3. Ejecuta el proyecto usando Docker Compose:
   ```bash
   docker compose up
   ```

## Uso

Una vez que el proyecto está corriendo, puedes probar la API utilizando el siguiente endpoint:

**Endpoint**: `POST http://localhost:8080/orders`

**Ejemplo de cuerpo JSON**:
```json
{
    "client": {
        "name": "Gus",
        "email": "gusdiaz@gmail.com"
    },
    "products": [
        {
            "name": "peras",
            "quantity": 1,
            "unitPrice": 100
        },
        {
            "name": "manzanas",
            "quantity": 1,
            "unitPrice": 100
        }
    ]
}
```