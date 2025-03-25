Primeira atividade para entrega da disciplina de Java Avançado
Grupo: Igor dos Santos Coelho, Fábio Dias Alencar, Emanuel Silva Lira Brasil

Atenção!!

Para melhor funcionamento do programa, o banco de dados (MySQL) deve estar disponível para conexão, com schema:

```sql

CREATE SCHEMA ouvidoria;

USE ouvidoria;

CREATE TABLE manifestacoes (
  protocolo int NOT NULL AUTO_INCREMENT,
  nomeUsuario varchar(45) DEFAULT NULL,
  emailUsuario varchar(45) DEFAULT NULL,
  tipoManifestacao varchar(30) DEFAULT NULL,
  descricao varchar(100) DEFAULT NULL,
  PRIMARY KEY (protocolo)
  );
  
```
