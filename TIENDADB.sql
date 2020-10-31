CREATE TABLE Medida (
    idmedida SERIAL NOT NULL,
    descripcion VARCHAR(50) NOT NULL,
    CONSTRAINT "medida_PK" PRIMARY KEY  ("idmedida")
);
INSERT INTO Medida (descripcion) VALUES ('onza');
INSERT INTO Medida (descripcion) VALUES ('onza fluida');
INSERT INTO Medida (descripcion) VALUES ('centimetros cubicos');

create TABLE Proveedor (
	idproveedor SERIAL NOT NULL,
	nombre VARCHAR(255),
	CONSTRAINT "proveedor_PK" PRIMARY KEY ("idproveedor")
)

CREATE TABLE Tienda (
    idtienda SERIAL NOT NULL,
    direccion VARCHAR(80) NOT NULL,
    telefono VARCHAR(50) NOT NULL,
    CONSTRAINT "tienda_PK" PRIMARY KEY  ("idtienda")
);

CREATE TABLE Empleado (
    idempleado SERIAL NOT NULL,
    idtienda INTEGER NOT NULL,
    pnombre VARCHAR(30) NOT NULL,
    snombre VARCHAR(30),
    papelli VARCHAR(30) NOT NULL,
    sapelli VARCHAR(30),
    CONSTRAINT "empleado_PK" PRIMARY KEY ("idempleado")
);

CREATE TABLE Producto (
    idproducto SERIAL NOT NULL,
    descripcion VARCHAR(100) NOT NULL,
    paraVenta INTEGER NOT NULL,
    esProducido INTEGER NOT NULL,
    idmedida INTEGER,
    CONSTRAINT "producto_PK" PRIMARY KEY  ("idproducto")
);

CREATE TABLE Transaccion_Inventario (
    idempleado INTEGER NOT NULL,
    CONSTRAINT "transaccion_inventario_PK" PRIMARY KEY  ("idempleado")
);


CREATE TABLE Inventario (
    idtienda INTEGER NOT NULL,
    idproducto INTEGER NOT NULL,
    saldo NUMERIC(12,2) NOT NULL,
    CONSTRAINT "inventario_PK" PRIMARY KEY  ("idtienda", "idproducto")
);

CREATE TABLE Transaccion_Inventario_Det (
	idtransacciondet SERIAL NOT NULL,
	idempleado INTEGER NOT null,
    idproducto INTEGER NOT NULL,
    unidades NUMERIC(12,2) NOT NULL,
    costototal NUMERIC(12,2) NOT null,
    fecha VARCHAR(100) NOT NULL,
    CONSTRAINT "transaccion_inventario_det_PK" PRIMARY KEY  ("idtransacciondet")
);

CREATE TABLE Periodo_Contable (
    anio INTEGER NOT NULL,
    idperiodo INTEGER NOT NULL,
    inicio DATE NOT NULL,
    fin DATE NOT NULL,
    cerrado INTEGER NOT NULL,
    CONSTRAINT "periodo_contable_PK" PRIMARY KEY ("anio", "idperiodo")
);

CREATE TABLE Tipo_Transaccion (
    idtipotran SERIAL NOT NULL,
    descripcion VARCHAR(100) NOT NULL,
    factor INTEGER NOT NULL,
    CONSTRAINT "tipo_transaccion_PK" PRIMARY KEY ("idtipotran")
);

CREATE TABLE Receta (
    idproductosalida INTEGER NOT NULL,
    CONSTRAINT "receta_PK" PRIMARY KEY ("idproductosalida")
);

CREATE TABLE Receta_Detalle (
	idrecetadetalle SERIAL not null,
    idproductosalida INTEGER NOT NULL,
    idproductoentrada INTEGER NOT NULL,
    cantidad NUMERIC(12,2) NOT NULL,
    idmedida INTEGER NOT NULL
);

CREATE TABLE Menu (
    idmenu SERIAL NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    idmenupadre INTEGER NOT NULL,
    urlmenu VARCHAR(255) NOT NULL,
	constraint "menu_PK" PRIMARY KEY ("idmenu")
);

CREATE TABLE Rol (
    idrol SERIAL NOT NULL,
    descripcion VARCHAR(100) NOT NULL,
    activo INTEGER NOT NULL,
    constraint "rol_PK" PRIMARY KEY ("idrol")
);

CREATE TABLE Menu_Rol (
    idmenu INTEGER NOT NULL,
    idrol INTEGER NOT NULL
);

CREATE TABLE Usuario (
    idusuario SERIAL NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    contrasenia VARCHAR(255) NOT NULL,
    idempleado INTEGER NOT NULL,
	constraint "usuario_PK" PRIMARY KEY ("idusuario")
);

CREATE TABLE Usuario_Rol (
    idusuario INTEGER NOT NULL,
    idrol INTEGER NOT NULL
);

create table Tipo_Documento (
  idtipodocto SERIAL not null, 
  descripcion varchar(100), 
  constraint "tipo_documento_PK" primary key ("idtipodocto")
 );


-- FOREIGN KEYS 
alter table Inventario add constraint "producto_inventario_FK" foreign key ("idproducto") 
references Producto ("idproducto");

alter table Inventario add constraint "tienda_inventario_FK" foreign key ("idtienda") 
references Tienda ("idtienda");

alter table Empleado add constraint "tienda_empleado_FK" foreign key ("idtienda")
references Tienda ("idtienda");

alter table Usuario add constraint "empleado_usuario_FK" foreign key ("idempleado")
references Empleado ("idempleado");

alter table Usuario_Rol add constraint "usuario_id_rol_FK" foreign key ("idusuario")
references Usuario ("idusuario");

alter table Usuario_Rol add constraint "rol_usuario_FK" foreign key ("idrol")
references Rol ("idrol");

alter table Menu_Rol add constraint "rol_menu_FK" foreign key ("idrol")
references Rol ("idrol");

alter table Menu_Rol add constraint "menu_rol_FK" foreign key ("idmenu")
references Menu ("idmenu");

alter table Receta add constraint "receta_producto_salida_FK" foreign key ("idproductosalida")
references Producto ("idproducto");

alter table Receta_Detalle add constraint "receta_detalle_producto_salida_FK" foreign key ("idproductosalida")
references Receta ("idproductosalida");

alter table Receta_Detalle add constraint "receta_detalle_producto_entrada_FK" foreign key ("idproductoentrada")
references Producto ("idproducto");

alter table Receta_Detalle add constraint "medida_receta_detalle_FK" foreign key ("idmedida")
references Medida ("idmedida");

alter table Producto add constraint "medida_producto_FK" foreign key ("idmedida")
references Medida ("idmedida");

alter table Transaccion_Inventario add constraint "proveedor_transaccion_inventario_FK" foreign key ("idproveedor")
references Proveedor ("idproveedor");

alter table Transaccion_Inventario add constraint "tipo_documento_FK" foreign key ("idtipodocto")
references Tipo_Documento ("idtipodocto");

alter table Transaccion_Inventario add constraint "periodo_transaccion_FK" foreign key ("anio", "idperiodo")
references Periodo_Contable ("anio", "idperiodo");

alter table Transaccion_Inventario add constraint "tipo_transaccion_FK" foreign key ("idtipotran")
references Tipo_Transaccion ("idtipotran");

alter table Transaccion_Inventario add constraint "empleado_transaccion_inventario_FK" foreign key ("idempleado")
references Empleado ("idempleado");

alter table Transaccion_Inventario_Det add constraint "transaccion_inventario_FK" foreign key ("idempleado")
references Transaccion_Inventario ("idempleado");

alter table Transaccion_Inventario add constraint "transaccion_inventario_origen_FK" foreign key ("transaccionorigen")
references Transaccion_Inventario ("idtransaccion");


create function insertartraninv() returns trigger
as
$$
declare
begin
	insert into Transaccion_Inventario values (new.idempleado);
return new;
end
$$
language plpgsql;
create trigger trinsertartraninv after insert on Empleado
for each row 
execute procedure insertartraninv();

create function borratraninv() returns trigger
as
$$
declare
begin
	--IF old.idproducto = idproductosalida THEN
		delete from Transaccion_Inventario where idempleado = old.idempleado;
	--END IF;
return old;
end
$$
language plpgsql;
create trigger trborrartraninv before delete on Empleado
for each row
execute procedure borratraninv();


create function insertarreceta() returns trigger
as
$$
declare
begin
	IF NEW.esproducido = 1 THEN
		insert into Receta (idproductosalida) values (new.idproducto);
	END IF;
return new;
end
$$
language plpgsql;
create trigger trinsertarreceta after insert on Producto
for each row 
execute procedure insertarreceta();

create function borrareceta() returns trigger
as
$$
declare
begin
	--IF old.idproducto = idproductosalida THEN
		delete from Receta where idproductosalida = old.idproducto;
	--END IF;
return old;
end
$$
language plpgsql;
create trigger trborrarreceta before delete on Producto
for each row
execute procedure borrareceta();

DROP TRIGGER trborrarreceta ON Transaccion_Inventario;
DROP FUNCTION borrareceta cascade;
