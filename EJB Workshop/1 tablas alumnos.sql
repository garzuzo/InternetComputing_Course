﻿
DROP TABLE T_ALUMNOS CASCADE; 

CREATE TABLE T_ALUMNOS ( 
  CODIGO     VARCHAR (8)  NOT NULL, 
  APELLIDOS  VARCHAR (50)  NOT NULL, 
  NOMBRE     VARCHAR (50)  NOT NULL, 
  SEXO       VARCHAR (1)  NOT NULL, 
  TIPO       VARCHAR (1)  NOT NULL) ; 

 
 ALTER TABLE T_ALUMNOS ADD CONSTRAINT TALUMNOS_PK
  PRIMARY KEY ( CODIGO ); 

COMMENT ON TABLE T_ALUMNOS IS 'Esta tabla contiene la información básico de los estudiantes.';

COMMENT ON COLUMN T_ALUMNOS.APELLIDOS IS 'Apellidos del estudiante';
COMMENT ON COLUMN T_ALUMNOS.CODIGO IS 'Código del estudiante';
COMMENT ON COLUMN T_ALUMNOS.NOMBRE IS 'Nombre del estudiante';
COMMENT ON COLUMN T_ALUMNOS.SEXO IS '''F'': Femenino; ''M'': Masculino';
COMMENT ON COLUMN T_ALUMNOS.TIPO IS '''E'': Estudiante; ''G'': Graduado';

DROP TABLE T_CARRERAS CASCADE; 

CREATE TABLE T_CARRERAS ( 
  CODIGO       VARCHAR (2)  NOT NULL, 
  DESCRIPCION  VARCHAR (100)  NOT NULL, 
  FACULTAD     VARCHAR (2)  NOT NULL ) ; 

 
ALTER TABLE T_CARRERAS ADD CONSTRAINT TCARRERAS_PK
  PRIMARY KEY ( CODIGO );

COMMENT ON TABLE T_CARRERAS IS 'Esta tabla contiene la información básica de las carreras.  
Cada carrera debe pertenecer a un facultad determinada.  
Cada carrera puede tener varios programas de estudios asociados. Por ejemplo: diurno, nocturno, a distancia, on-line, etc.';

COMMENT ON COLUMN T_CARRERAS.CODIGO IS 'Código de la carrera';
COMMENT ON COLUMN T_CARRERAS.DESCRIPCION IS 'Nombre de la carrera';
COMMENT ON COLUMN T_CARRERAS.FACULTAD IS 'Código de la facultad a la que pertenece la carrera';

DROP TABLE T_COHORTES CASCADE; 

CREATE TABLE T_COHORTES ( 
  PROGRAMA_CODIGO  VARCHAR (2)  NOT NULL, 
  MATERIA_CODIGO   VARCHAR (5)  NOT NULL, 
  COHORTE          VARCHAR (3)  NOT NULL, 
  SEMESTRE         VARCHAR (2)  NOT NULL) ; 

  
 ALTER TABLE T_COHORTES ADD CONSTRAINT TCOHORTES_PK
  PRIMARY KEY ( PROGRAMA_CODIGO, COHORTE, MATERIA_CODIGO );


COMMENT ON TABLE T_COHORTES IS 'Esta tabla contiene la información de las cohortes para cada programa de estudios.  
Cohorte es el período académico al que pertenece un currículum determinado.';

COMMENT ON COLUMN T_COHORTES.COHORTE IS 'Cohorte: período del currículum';
COMMENT ON COLUMN T_COHORTES.MATERIA_CODIGO IS 'Código de la materia';
COMMENT ON COLUMN T_COHORTES.PROGRAMA_CODIGO IS 'Código del programa de estudios';
COMMENT ON COLUMN T_COHORTES.SEMESTRE IS 'Semestre académico al que pertenece la materia';

DROP TABLE T_CURSOS CASCADE; 

CREATE TABLE T_CURSOS ( 
  PERIODO_ACAD    VARCHAR (3)  NOT NULL, 
  MATERIA_CODIGO  VARCHAR (5)  NOT NULL, 
  GRUPO           numeric (3)    NOT NULL) ; 

 
ALTER TABLE T_CURSOS ADD CONSTRAINT TCURSOS_PK
  PRIMARY KEY ( PERIODO_ACAD, MATERIA_CODIGO, GRUPO ) ;

COMMENT ON TABLE T_CURSOS IS 'Esta tabla contiene la información de los grupos que tiene cada materia para un determinado período académico.';

COMMENT ON COLUMN T_CURSOS.GRUPO IS 'Grupo de la materia';
COMMENT ON COLUMN T_CURSOS.MATERIA_CODIGO IS 'Código de la materia';
COMMENT ON COLUMN T_CURSOS.PERIODO_ACAD IS 'Período académico: los dos primeros caracteres identifican los 2 últimos dígitos del año,  y el último caracter identifica el semestre (1 o 2) de dicho año';

DROP TABLE T_FACULTAD CASCADE; 

CREATE TABLE T_FACULTAD ( 
  CODIGO       VARCHAR (2)  NOT NULL, 
  DESCRIPCION  VARCHAR (100)  NOT NULL) ; 

  
 ALTER TABLE T_FACULTAD ADD CONSTRAINT TFACULTAD_PK
  PRIMARY KEY ( CODIGO );


COMMENT ON TABLE T_FACULTAD IS 'Esta tabla contiene la información básica de las facultades.  
Cada facultad puede tener asociadas varias carreras.';

COMMENT ON COLUMN T_FACULTAD.CODIGO IS 'Código de la facultad';
COMMENT ON COLUMN T_FACULTAD.DESCRIPCION IS 'Nombre de la facultad';

DROP TABLE T_HORARIO_CURSOS CASCADE; 

CREATE TABLE T_HORARIO_CURSOS ( 
  PERIODO_ACAD    VARCHAR (3)  NOT NULL, 
  MATERIA_CODIGO  VARCHAR (5)  NOT NULL, 
  GRUPO           numeric (3)    NOT NULL, 
  CEDULA_PROF     VARCHAR (11)  NOT NULL, 
  SALON           VARCHAR (5)  NOT NULL, 
  DIA             VARCHAR (2)  NOT NULL, 
  HORA_INICIO     numeric (5)    NOT NULL, 
  HORA_FIN        numeric (5)    NOT NULL) ; 

  
ALTER TABLE T_HORARIO_CURSOS ADD CONSTRAINT THORARIOCURSOS_PK
  PRIMARY KEY ( PERIODO_ACAD, MATERIA_CODIGO, GRUPO, CEDULA_PROF, DIA, SALON );


COMMENT ON TABLE T_HORARIO_CURSOS IS 'Esta tabla contiene la información de los horarios (día, hora inicial y hora final) de cada grupo de cada materia para un determinado período académico.';

COMMENT ON COLUMN T_HORARIO_CURSOS.CEDULA_PROF IS 'Cédula del profesor';
COMMENT ON COLUMN T_HORARIO_CURSOS.DIA IS 'Día en el que se dicta la clase';
COMMENT ON COLUMN T_HORARIO_CURSOS.GRUPO IS 'Grupo de la materia';
COMMENT ON COLUMN T_HORARIO_CURSOS.HORA_FIN IS 'Hora final de la clase';
COMMENT ON COLUMN T_HORARIO_CURSOS.HORA_INICIO IS 'Hora inicial de la clase';
COMMENT ON COLUMN T_HORARIO_CURSOS.MATERIA_CODIGO IS 'Código de la materia';
COMMENT ON COLUMN T_HORARIO_CURSOS.PERIODO_ACAD IS 'Período académico: los dos primeros caracteres identifican los 2 últimos dígitos del año,  y el último caracter identifica el semestre (1 o 2) de dicho año';
COMMENT ON COLUMN T_HORARIO_CURSOS.SALON IS 'Salón (lugar) en el que se dicta la clase';

DROP TABLE T_MATERIAS CASCADE; 

CREATE TABLE T_MATERIAS ( 
  CODIGO      VARCHAR (5)  NOT NULL, 
  NOMBRE      VARCHAR (100)  NOT NULL, 
  INTENSIDAD  numeric (3)    NOT NULL, 
  CUPO        numeric (3)    NOT NULL ) ; 

  
ALTER  TABLE T_MATERIAS ADD CONSTRAINT TMATERIAS_PK
  PRIMARY KEY ( CODIGO );


COMMENT ON TABLE T_MATERIAS IS 'Esta tabla contiene la información básica de las materias';

COMMENT ON COLUMN T_MATERIAS.CODIGO IS 'Código de la materia';
COMMENT ON COLUMN T_MATERIAS.CUPO IS 'Cupo de la materia: número máximo de estudiantes permitidos por clase';
COMMENT ON COLUMN T_MATERIAS.INTENSIDAD IS 'Intensidad horaria semanal de la materia';
COMMENT ON COLUMN T_MATERIAS.NOMBRE IS 'Nombre de la materia';

DROP TABLE T_MATXAPROBAR CASCADE; 

CREATE TABLE T_MATXAPROBAR ( 
  ALUMNO    VARCHAR (8)  NOT NULL, 
  PROGRAMA  VARCHAR (2)  NOT NULL, 
  MATERIA   VARCHAR (5)  NOT NULL) ; 

  
ALTER TABLE T_MATXAPROBAR ADD  CONSTRAINT TMATXAPROBAR_PK
  PRIMARY KEY ( ALUMNO, PROGRAMA, MATERIA ) ;


COMMENT ON TABLE T_MATXAPROBAR IS 'Esta tabla contiene la información de las materias por aprobar de cada estudiante en un programa de estudios determinado';

COMMENT ON COLUMN T_MATXAPROBAR.ALUMNO IS 'Código del estudiante';
COMMENT ON COLUMN T_MATXAPROBAR.MATERIA IS 'Código de la materia';
COMMENT ON COLUMN T_MATXAPROBAR.PROGRAMA IS 'Código del programa de estudios';

DROP TABLE T_MATXPROGRAMA CASCADE; 

CREATE TABLE T_MATXPROGRAMA ( 
  MATERIA_CODIGO   VARCHAR (5)  NOT NULL, 
  PROGRAMA_CODIGO  VARCHAR (2)  NOT NULL) ; 

  ALTER  TABLE T_MATXPROGRAMA ADD CONSTRAINT TMATXPROGRAMA_PK
  PRIMARY KEY ( MATERIA_CODIGO, PROGRAMA_CODIGO ) ;


COMMENT ON TABLE T_MATXPROGRAMA IS 'Esta tabla contiene la información de a qué programa de estudios esta asociada cada materia';

COMMENT ON COLUMN T_MATXPROGRAMA.MATERIA_CODIGO IS 'Código de la materia';
COMMENT ON COLUMN T_MATXPROGRAMA.PROGRAMA_CODIGO IS 'Código del programa de estudios';

DROP TABLE T_PREREQ_MAT CASCADE; 

CREATE TABLE T_PREREQ_MAT ( 
  PROGRAMA_CODIGO  VARCHAR (2)  NOT NULL, 
  MATERIA_CODIGO   VARCHAR (5)  NOT NULL, 
  COHORTE          VARCHAR (3)  NOT NULL, 
  PREREQ_CODIGO    VARCHAR (5)  NOT NULL ) ; 

  ALTER  TABLE T_PREREQ_MAT ADD  CONSTRAINT TPREREQMAT_PK
  PRIMARY KEY ( PROGRAMA_CODIGO, MATERIA_CODIGO, COHORTE, PREREQ_CODIGO );


COMMENT ON TABLE T_PREREQ_MAT IS 'Esta tabla contiene la información de los prerequisitos (materias) de cada materia para cada programa de estudios y cohorte';

COMMENT ON COLUMN T_PREREQ_MAT.COHORTE IS 'Cohorte: período académico del currículum';
COMMENT ON COLUMN T_PREREQ_MAT.MATERIA_CODIGO IS 'Código de la materia que tiene prerequisitos';
COMMENT ON COLUMN T_PREREQ_MAT.PREREQ_CODIGO IS 'Código del prerequisito (materia)';
COMMENT ON COLUMN T_PREREQ_MAT.PROGRAMA_CODIGO IS 'Código del programa de estudios';

DROP TABLE T_PROFESORES CASCADE; 

CREATE TABLE T_PROFESORES ( 
  CEDULA     VARCHAR (11)  NOT NULL, 
  APELLIDOS  VARCHAR (100)  NOT NULL, 
  NOMBRE     VARCHAR (100)  NOT NULL ) ; 

  ALTER TABLE T_PROFESORES ADD   CONSTRAINT TPROFESORES_PK
  PRIMARY KEY ( CEDULA );


COMMENT ON TABLE T_PROFESORES IS 'Esta tabla contiene la información básica de los profesores';

COMMENT ON COLUMN T_PROFESORES.APELLIDOS IS 'Apellidos del profesor';
COMMENT ON COLUMN T_PROFESORES.CEDULA IS 'Número de la cédula del profesor';
COMMENT ON COLUMN T_PROFESORES.NOMBRE IS 'Nombres del profesor';

DROP TABLE T_PROGRAMAS CASCADE; 

CREATE TABLE T_PROGRAMAS ( 
  CODIGO       VARCHAR (2)  NOT NULL, 
  ALIAS        VARCHAR (3)  NOT NULL, 
  DESCRIPCION  VARCHAR (100)  NOT NULL, 
  CARRERA      VARCHAR (2)  NOT NULL ) ; 

  ALTER TABLE T_PROGRAMAS ADD CONSTRAINT TPROGRAMAS_PK
  PRIMARY KEY ( CODIGO );


COMMENT ON TABLE T_PROGRAMAS IS 'Esta tabla contiene la información básica de los programas de estudios.   
Cada programa de estudios debe estar asociado a una carrera determinada.   
Por ejemplo, una carrera puede tener un programa diurno, nocturno, a distancia, etc.';

COMMENT ON COLUMN T_PROGRAMAS.ALIAS IS 'Sigla corta de 3 caracteres (nombre corto) que identifica al programa de estudios';
COMMENT ON COLUMN T_PROGRAMAS.CARRERA IS 'Código de la carrera a la que pertenece el programa de estudios';
COMMENT ON COLUMN T_PROGRAMAS.CODIGO IS 'Código del programa de estudios';
COMMENT ON COLUMN T_PROGRAMAS.DESCRIPCION IS 'Nombre completo del programa';

DROP TABLE T_PROG_ALUMNOS CASCADE; 

CREATE TABLE T_PROG_ALUMNOS ( 
  PERIODO_ACAD     VARCHAR (3)  NOT NULL, 
  ALUMNO_CODIGO    VARCHAR (8)  NOT NULL, 
  PROGRAMA_CODIGO  VARCHAR (3)  NOT NULL, 
  PRINCIPAL        VARCHAR (1)  NOT NULL, 
  SEMESTRE         VARCHAR (2)  NOT NULL, 
  COHORTE          VARCHAR (3)  NOT NULL) ; 

  ALTER TABLE T_PROG_ALUMNOS ADD CONSTRAINT TPROGALUMNO_PK
  PRIMARY KEY ( PERIODO_ACAD, ALUMNO_CODIGO, PROGRAMA_CODIGO, PRINCIPAL ) ;


COMMENT ON TABLE T_PROG_ALUMNOS IS 'Esta tabla contiene la información histórica (período por período) de cada estudiante en cuanto a qué programa de estudios pertenece, en qué semestre se encuentra, y cuál es su cohorte académica';

COMMENT ON COLUMN T_PROG_ALUMNOS.ALUMNO_CODIGO IS 'Código del estudiante';
COMMENT ON COLUMN T_PROG_ALUMNOS.COHORTE IS 'Cohorte (período del currículum) del estudiante en el período académico';
COMMENT ON COLUMN T_PROG_ALUMNOS.PERIODO_ACAD IS 'Período académico: los dos primeros caracteres identifican los 2 últimos dígitos del año,  y el último caracter identifica el semestre (1 o 2) de dicho año';
COMMENT ON COLUMN T_PROG_ALUMNOS.PRINCIPAL IS '''S'': Programa principal; ''N'': Programa alterno. Un estudiante puede estar cursando simultáneamente dos programas de estudios en un período determinado.';
COMMENT ON COLUMN T_PROG_ALUMNOS.PROGRAMA_CODIGO IS 'Código del programa de estudios al que pertenece el estudiante en el período académico';
COMMENT ON COLUMN T_PROG_ALUMNOS.SEMESTRE IS 'Semestre del estudiante en el período académico';

ALTER TABLE T_CARRERAS ADD  CONSTRAINT TCARRERAS_FACULTAD_FK
 FOREIGN KEY (FACULTAD) 
  REFERENCES T_FACULTAD (CODIGO) ;

ALTER TABLE T_COHORTES ADD  CONSTRAINT TCOHORTES_MATXPROGRAMA_FK
 FOREIGN KEY (MATERIA_CODIGO, PROGRAMA_CODIGO) 
  REFERENCES T_MATXPROGRAMA (MATERIA_CODIGO, PROGRAMA_CODIGO) ;

ALTER TABLE T_CURSOS ADD  CONSTRAINT TCURSOS_MATERIA_FK
 FOREIGN KEY (MATERIA_CODIGO) 
  REFERENCES T_MATERIAS (CODIGO) ;

ALTER TABLE T_HORARIO_CURSOS ADD  CONSTRAINT THORARIOCURSOS_CURSOS_FK
 FOREIGN KEY (PERIODO_ACAD, MATERIA_CODIGO, GRUPO) 
  REFERENCES T_CURSOS (PERIODO_ACAD, MATERIA_CODIGO, GRUPO) ;

ALTER TABLE T_HORARIO_CURSOS ADD  CONSTRAINT THORARIOCURSOS_PROFESORES_FK
 FOREIGN KEY (CEDULA_PROF) 
  REFERENCES T_PROFESORES (CEDULA) ;

ALTER TABLE T_MATXAPROBAR ADD  CONSTRAINT TMATXAPROBAR_ALUMNO_FK
 FOREIGN KEY (ALUMNO) 
  REFERENCES T_ALUMNOS (CODIGO) ;

ALTER TABLE T_MATXAPROBAR ADD  CONSTRAINT TMATXAPROBAR_PROGRAMA_FK
 FOREIGN KEY (PROGRAMA) 
  REFERENCES T_PROGRAMAS (CODIGO) ;

ALTER TABLE T_MATXAPROBAR ADD  CONSTRAINT TMATXAPROBAR_MATERIA_FK
 FOREIGN KEY (MATERIA) 
  REFERENCES T_MATERIAS (CODIGO) ;

ALTER TABLE T_MATXPROGRAMA ADD  CONSTRAINT TMAT_MATERIA_FK
 FOREIGN KEY (MATERIA_CODIGO) 
  REFERENCES T_MATERIAS (CODIGO) ;

ALTER TABLE T_MATXPROGRAMA ADD  CONSTRAINT TMAT_PROGRAMA_FK
 FOREIGN KEY (PROGRAMA_CODIGO) 
  REFERENCES T_PROGRAMAS (CODIGO) ;

ALTER TABLE T_PREREQ_MAT ADD  CONSTRAINT TPREREQMAT_MATERIAPRE_FK
 FOREIGN KEY (PREREQ_CODIGO) 
  REFERENCES T_MATERIAS (CODIGO) ;

ALTER TABLE T_PREREQ_MAT ADD  CONSTRAINT TPREREQMAT_COHORTE_FK
 FOREIGN KEY (PROGRAMA_CODIGO, COHORTE, MATERIA_CODIGO) 
  REFERENCES T_COHORTES (PROGRAMA_CODIGO, COHORTE, MATERIA_CODIGO) ;

ALTER TABLE T_PROGRAMAS ADD  CONSTRAINT TPROGRAMAS_CARRERA_FK
 FOREIGN KEY (CARRERA) 
  REFERENCES T_CARRERAS (CODIGO) ;

ALTER TABLE T_PROG_ALUMNOS ADD  CONSTRAINT TPROGALUMNOS_ALUMNOS_FK
 FOREIGN KEY (ALUMNO_CODIGO) 
  REFERENCES T_ALUMNOS (CODIGO) ;

ALTER TABLE T_PROG_ALUMNOS ADD  CONSTRAINT TPROGALUMNOS_PROGRAMA_FK
 FOREIGN KEY (PROGRAMA_CODIGO) 
  REFERENCES T_PROGRAMAS (CODIGO) ;

