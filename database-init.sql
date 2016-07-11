--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.7
-- Dumped by pg_dump version 9.5.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: sudokupuzzles; Type: TABLE; Schema: public; Owner: sudoku
--

CREATE TABLE sudokupuzzles (
    problem character varying(89),
    solution character varying(89)
);


ALTER TABLE sudokupuzzles OWNER TO sudoku;

--
-- Name: problem_un; Type: INDEX; Schema: public; Owner: sudoku
--

CREATE UNIQUE INDEX problem_un ON sudokupuzzles USING btree (problem);


--
-- PostgreSQL database dump complete
--

