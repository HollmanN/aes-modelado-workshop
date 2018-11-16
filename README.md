# Pontifica Universidad Javeriana

 Modelado y validación de la Arquitectura

* Hollman B. Nuñez Castro 
* Juan Carlos Dueñas Rojas
* Miguel A. Arroyo Salazar

## Decisiones de Aquitectura

* La generación de los artefactos es posible gracias al uso de la herramienta *Swagger* en la definición de los contratos, y complementado con *JSON Schema* para definir las entidades, el tener esto desacoplado facilita las tareas de gobierno y mantenibilidad. Adicionalemente, las transformaciones fueron posibls con el uso de *templates* de *JSON* junto a las configuraciones de los orígenes de datos.

* **Inventario de servicios**
	* 	**Domain Inventory:** Se definió un inventario de dominio que agrupar los servicios encargados de prestar las principales funcionalidades de la solución, dado el bien definido alcance de la necesidad propuesta: Portal de autoservicio para pagos, los servicios definidos dentro del *Domain Inventory* son: 
		* Consultar saldo.
		* Pagar Servicio.
		* Compensar.
	* **Enterprise Inventory:** Al ser servicios transversales a la organización, ya que pueden ser usados por cualquier actos o sistema que se ubique dentro de la misma, el servicio de notificaciones hace parte del *Enterprise Inventory*.  
	
* La implementación de los inventarios empresariales se complementa con la integración de la herramienta Eureka, para el descubrimiento de los servicios definidos previamente. 

* Se utilizó un *Legacy Wrapper* para envolver el servicio *SOAP*. Este patrón nos brinda el soporte para lograr la integración necesaria con el servicio definido previamente.
