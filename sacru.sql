CREATE TABLE parametros (
	codigo int4 NOT NULL,
	salario_minimo int4 NOT NULL
);
ALTER TABLE parametros ADD CONSTRAINT pk_codigo_parametros PRIMARY KEY(codigo);
CREATE TABLE curso_inscrito (
	codigo int4 NOT NULL,
	codigo_estudiante int4 NOT NULL,
	hora_inscripcion time NOT NULL,
	fecha_inscripcion date NOT NULL,
	ip_usuario varchar(30) NOT NULL,
	nota_final float8 NOT NULL,
	ano_inscripcion int4 NOT NULL,
	periodo_inscripcion int4 NOT NULL,
	estado varchar(20) NOT NULL
);
ALTER TABLE curso_inscrito ADD CONSTRAINT pk_codigo_curso_inscrito PRIMARY KEY(codigo);
CREATE TABLE admin (
	codigo int4 NOT NULL,
	cargo varchar(40) NOT NULL,
	numero_celular int8 NOT NULL,
	fecha_ingreso date NOT NULL,
	ano_ingreso int4 NOT NULL,
	periodo_ingreso int4 NOT NULL,
	numero_documento varchar(20) NOT NULL
);
ALTER TABLE admin ADD CONSTRAINT pk_codigo_admin PRIMARY KEY(codigo);
CREATE TABLE facultad (
	codigo int4 NOT NULL,
	nombre varchar(50) NOT NULL,
	correo_institucional varchar(60) NOT NULL,
	fecha_creacion date NOT NULL,
	ano_creacion int4 NOT NULL,
	periodo_creacion int4 NOT NULL,
	estado varchar(20) NOT NULL,
	sede varchar(20) NOT NULL
);
ALTER TABLE facultad ADD CONSTRAINT pk_codigo_facultad PRIMARY KEY(codigo);
CREATE TABLE recibo_liquidacion (
	codigo int4 NOT NULL,
	codigo_estudiante int4 NOT NULL,
	derechos_matricula int4 NOT NULL,
	servicios_universitarios int4 NOT NULL,
	derechos_academicos int4 NOT NULL,
	servicio_transporte int4 NOT NULL,
	seguro_estudiantil int4 NOT NULL,
	total_pagar int4 NOT NULL,
	estado varchar(40) NOT NULL,
	fecha_limite date NOT NULL,
	fecha_creacion date NOT NULL,
	ano_creacion int4 NOT NULL,
	periodo_creacion int4 NOT NULL,
	hora_liquidacion time NOT NULL
);
ALTER TABLE recibo_liquidacion ADD CONSTRAINT pk_codigo_recibo PRIMARY KEY(codigo);
CREATE TABLE login (
	codigo int4 NOT NULL,
	usuario varchar(60) NOT NULL,
	password varchar(500) NOT NULL,
	fecha_creacion date NOT NULL,
	ano_creacion int4 NOT NULL,
	periodo_creacion int4 NOT NULL,
	numero_documento varchar(20) NOT NULL
);
ALTER TABLE login ADD CONSTRAINT pk_codigo_login PRIMARY KEY(codigo);
CREATE TABLE bienestar (
	codigo int4 NOT NULL,
	codigo_estudiante int4 NOT NULL,
	nombre_descuento varchar(50) NOT NULL,
	tipo_descuento varchar(45) NOT NULL,
	porcentaje numeric(5, 0) NOT NULL,
	estado varchar(25) NOT NULL,
	hora time NOT NULL,
	fecha_creacion date NOT NULL,
	ano_creacion int4 NOT NULL,
	periodo_creacion int4 NOT NULL
);
ALTER TABLE bienestar ADD CONSTRAINT pk_codigo_bienestar PRIMARY KEY(codigo);
CREATE TABLE estudiante (
	codigo int4 NOT NULL,
	estado varchar(20) NOT NULL,
	periodo_ingreso int4 NOT NULL,
	fecha_ingreso date NOT NULL,
	ano_ingreso int4 NOT NULL,
	numero_documento varchar(20) NOT NULL,
	codigo_programa int4 NOT NULL
);
ALTER TABLE estudiante ADD CONSTRAINT pk_codigo_estudiante PRIMARY KEY(codigo);
CREATE TABLE programa (
	codigo int4 NOT NULL,
	nombre varchar(50) NOT NULL,
	codigo_facultad int4 NOT NULL,
	correo_institucional varchar(60) NOT NULL,
	nivel varchar(40) NOT NULL,
	periodicidad varchar(20) NOT NULL,
	estado varchar(20) NOT NULL,
	duracion varchar(20) NOT NULL,
	fecha_creacion date NOT NULL,
	ano_creacion int4 NOT NULL,
	periodo_creacion int4 NOT NULL,
	metodologia varchar(50) NOT NULL
);
ALTER TABLE programa ADD CONSTRAINT pk_codigo_programa PRIMARY KEY(codigo);
CREATE TABLE bitacora (
	codigo int4 NOT NULL,
	usuario varchar(60) NOT NULL,
	fecha date NOT NULL,
	hora time NOT NULL,
	ip varchar(30) NOT NULL,
	operacion varchar(40) NOT NULL,
	tabla varchar(40) NOT NULL,
	atributo varchar(40) NOT NULL
);
ALTER TABLE bitacora ADD CONSTRAINT pk_codigo_bitacora PRIMARY KEY(codigo);
CREATE TABLE calendario (
	codigo int4 NOT NULL,
	fecha_inicio date NOT NULL,
	fecha_fin date NOT NULL,
	nombre_actividad varchar(40) NOT NULL,
	descripcion_actividad varchar(100) NOT NULL,
	soporte_legal bytea,
	fecha_creacion date NOT NULL,
	ano_creacion int4 NOT NULL,
	periodo_creacion int4 NOT NULL
);
ALTER TABLE calendario ADD CONSTRAINT pk_codigo_calendario PRIMARY KEY(codigo);
CREATE TABLE multas_sanciones (
	codigo int4 NOT NULL,
	codigo_estudiante int4 NOT NULL,
	nombre varchar(50) NOT NULL,
	estado varchar(45) NOT NULL,
	valor int4 NOT NULL,
	descripcion varchar(50) NOT NULL,
	dependencia varchar(50) NOT NULL,
	fecha_creacion date NOT NULL,
	hora_creacion time NOT NULL,
	ano_creacion int4 NOT NULL,
	periodo_creacion int4 NOT NULL
);
ALTER TABLE multas_sanciones ADD CONSTRAINT pk_codigo_creacion PRIMARY KEY(codigo);
CREATE TABLE persona (
	numero_documento varchar(20) NOT NULL,
	nombre_1 varchar(50) NOT NULL,
	nombre_2 varchar(50) NOT NULL,
	apellido_1 varchar(50) NOT NULL,
	apellido_2 varchar(50) NOT NULL,
	tipo_documento varchar(20) NOT NULL,
	edad int4 NOT NULL,
	sexo varchar(1) NOT NULL,
	correo_personal varchar(100) NOT NULL,
	correo_institucional varchar(100) NOT NULL
);
ALTER TABLE persona ADD CONSTRAINT pk_numero_documento PRIMARY KEY(numero_documento);
CREATE TABLE registro_semestre (
	codigo integer NOT NULL,
	semestre integer NOT NULL,
	periodo integer NOT NULL,
	ano integer NOT NULL,
	cantidad_materias_perdidas integer NOT NULL,
	porcentaje_materias_perdidas numeric(5, 0) NOT NULL,
	promedio_semestre float8 NOT NULL,
	codigo_estudiante int4 NOT NULL
);
ALTER TABLE registro_semestre ADD CONSTRAINT pk_codigo PRIMARY KEY(codigo);
ALTER TABLE curso_inscrito ADD CONSTRAINT fk_codigo_estudiante_cinscrito FOREIGN KEY (codigo_estudiante) REFERENCES estudiante(codigo) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE admin ADD CONSTRAINT fk_numero_documento_persona_r2 FOREIGN KEY (numero_documento) REFERENCES persona(numero_documento) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE recibo_liquidacion ADD CONSTRAINT fk_codigo_estudiante_recibo FOREIGN KEY (codigo_estudiante) REFERENCES estudiante(codigo) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE login ADD CONSTRAINT fk_numero_documento_r3 FOREIGN KEY (numero_documento) REFERENCES persona(numero_documento) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE bienestar ADD CONSTRAINT fk_codigo_estudiante_bienestar FOREIGN KEY (codigo_estudiante) REFERENCES estudiante(codigo) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE estudiante ADD CONSTRAINT fk_numero_documento_persona FOREIGN KEY (numero_documento) REFERENCES persona(numero_documento) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE estudiante ADD CONSTRAINT fk_codigo_programa FOREIGN KEY (codigo_programa) REFERENCES programa(codigo) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE programa ADD CONSTRAINT fk_codigo_facultad FOREIGN KEY (codigo_facultad) REFERENCES facultad(codigo) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE multas_sanciones ADD CONSTRAINT fk_codigo_estudiante_multas FOREIGN KEY (codigo_estudiante) REFERENCES estudiante(codigo) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE registro_semestre ADD CONSTRAINT fk_codigo_estudiante_registro FOREIGN KEY (codigo_estudiante) REFERENCES estudiante(codigo) ON DELETE CASCADE ON UPDATE CASCADE;
INSERT INTO login VALUES (12,'admin','fae0b27c451c728867a567e8c1bb4e53','2020-02-03',2020,2,1121958834);
INSERT INTO login VALUES (10,'estudiante','f1c1592588411002af340cbaedd6fc33','2020-02-03',2020,2,1121958833);
ALTER TABLE parametros ALTER COLUMN salario_minimo SET DATA TYPE float8;
INSERT INTO parametros VALUES (0,877.803);
