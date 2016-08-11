CREATE TABLE vinho
(
  codigo serial NOT NULL,
  nome text NOT NULL,
  tipo text NOT NULL,
  safra integer NOT NULL,
  volume integer NOT NULL,
  valor numeric NOT NULL
)
WITH (
  OIDS=FALSE
);
ALTER TABLE vinho
  OWNER TO postgres;
