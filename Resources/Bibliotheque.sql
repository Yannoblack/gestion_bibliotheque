PGDMP  4    4                 }            bibliotheque    17.2    17.2                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            	           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            
           1262    16388    bibliotheque    DATABASE     �   CREATE DATABASE bibliotheque WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United Kingdom.1252';
    DROP DATABASE bibliotheque;
                     postgres    false            �            1259    16447    emprunt    TABLE     �   CREATE TABLE public.emprunt (
    id integer NOT NULL,
    membreid integer,
    livreid integer,
    dateemprunt date NOT NULL,
    dateretourprevue date NOT NULL,
    dateretoureffective date
);
    DROP TABLE public.emprunt;
       public         heap r       postgres    false            �            1259    16446    emprunt_id_seq    SEQUENCE     �   CREATE SEQUENCE public.emprunt_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.emprunt_id_seq;
       public               postgres    false    222                       0    0    emprunt_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.emprunt_id_seq OWNED BY public.emprunt.id;
          public               postgres    false    221            �            1259    16427    livre    TABLE     �   CREATE TABLE public.livre (
    id integer NOT NULL,
    titre character varying(255) NOT NULL,
    auteur character varying(255) NOT NULL,
    categorie character varying(100),
    nombreexemplaires integer NOT NULL
);
    DROP TABLE public.livre;
       public         heap r       postgres    false            �            1259    16426    livre_id_seq    SEQUENCE     �   CREATE SEQUENCE public.livre_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.livre_id_seq;
       public               postgres    false    218                       0    0    livre_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.livre_id_seq OWNED BY public.livre.id;
          public               postgres    false    217            �            1259    16436    membre    TABLE     �   CREATE TABLE public.membre (
    id integer NOT NULL,
    nom character varying(255) NOT NULL,
    prenom character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    adhesiondate date NOT NULL
);
    DROP TABLE public.membre;
       public         heap r       postgres    false            �            1259    16435    membre_id_seq    SEQUENCE     �   CREATE SEQUENCE public.membre_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.membre_id_seq;
       public               postgres    false    220                       0    0    membre_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.membre_id_seq OWNED BY public.membre.id;
          public               postgres    false    219            c           2604    16450 
   emprunt id    DEFAULT     h   ALTER TABLE ONLY public.emprunt ALTER COLUMN id SET DEFAULT nextval('public.emprunt_id_seq'::regclass);
 9   ALTER TABLE public.emprunt ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    222    221    222            a           2604    16430    livre id    DEFAULT     d   ALTER TABLE ONLY public.livre ALTER COLUMN id SET DEFAULT nextval('public.livre_id_seq'::regclass);
 7   ALTER TABLE public.livre ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    218    217    218            b           2604    16439 	   membre id    DEFAULT     f   ALTER TABLE ONLY public.membre ALTER COLUMN id SET DEFAULT nextval('public.membre_id_seq'::regclass);
 8   ALTER TABLE public.membre ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    220    219    220                      0    16447    emprunt 
   TABLE DATA           l   COPY public.emprunt (id, membreid, livreid, dateemprunt, dateretourprevue, dateretoureffective) FROM stdin;
    public               postgres    false    222   �                  0    16427    livre 
   TABLE DATA           P   COPY public.livre (id, titre, auteur, categorie, nombreexemplaires) FROM stdin;
    public               postgres    false    218                    0    16436    membre 
   TABLE DATA           F   COPY public.membre (id, nom, prenom, email, adhesiondate) FROM stdin;
    public               postgres    false    220   D                   0    0    emprunt_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.emprunt_id_seq', 6, true);
          public               postgres    false    221                       0    0    livre_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.livre_id_seq', 12, true);
          public               postgres    false    217                       0    0    membre_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.membre_id_seq', 8, true);
          public               postgres    false    219            k           2606    16452    emprunt emprunt_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.emprunt
    ADD CONSTRAINT emprunt_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.emprunt DROP CONSTRAINT emprunt_pkey;
       public                 postgres    false    222            e           2606    16434    livre livre_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.livre
    ADD CONSTRAINT livre_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.livre DROP CONSTRAINT livre_pkey;
       public                 postgres    false    218            g           2606    16445    membre membre_email_key 
   CONSTRAINT     S   ALTER TABLE ONLY public.membre
    ADD CONSTRAINT membre_email_key UNIQUE (email);
 A   ALTER TABLE ONLY public.membre DROP CONSTRAINT membre_email_key;
       public                 postgres    false    220            i           2606    16443    membre membre_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.membre
    ADD CONSTRAINT membre_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.membre DROP CONSTRAINT membre_pkey;
       public                 postgres    false    220            l           2606    16458    emprunt emprunt_livreid_fkey    FK CONSTRAINT     {   ALTER TABLE ONLY public.emprunt
    ADD CONSTRAINT emprunt_livreid_fkey FOREIGN KEY (livreid) REFERENCES public.livre(id);
 F   ALTER TABLE ONLY public.emprunt DROP CONSTRAINT emprunt_livreid_fkey;
       public               postgres    false    4709    222    218            m           2606    16453    emprunt emprunt_membreid_fkey    FK CONSTRAINT     ~   ALTER TABLE ONLY public.emprunt
    ADD CONSTRAINT emprunt_membreid_fkey FOREIGN KEY (membreid) REFERENCES public.membre(id);
 G   ALTER TABLE ONLY public.emprunt DROP CONSTRAINT emprunt_membreid_fkey;
       public               postgres    false    222    220    4713               S   x�u���0���%8!٢d�9
R*^�'��қ�iEs�W�߹z1�g���:d+y���,�?R���x�!��,S!�          .  x�UP;RBA�gO1�����hH�H!�O��L�u꽩ڷ���8��`��]�L&���
�ۛ!ܓ������Z�B�-�TV�5E�X{v�`⢰#� |�����Wڞ��� s6��Au�Yp͡0��R��̊�Ej6�i5�
W���9EJ�Ou�F|R�L�i��	j���Tȶu�V�lۼ7Vװ����������"�0bK�A�a����ndo�50�.��3�c}5��8|Jl��kn��yJ~�ws���!�Cu��������;Ki΄�s�;UbiyF:6��o=��/T��N         �   x�m�A��0��o�K����Ga=����^�up#�$���o� �0�x��޴�M1��=�kY�\�-ߍD�j��}�j��'���l=����1VQR�e��u���?�h9(V)��pZS�!$v8��g�*J�l�����g���R�,#-m����_N�y�1ֽ.�r���,��/a·�ގ���Gu�̳}C?����fo     