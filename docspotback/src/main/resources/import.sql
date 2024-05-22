-- ######## SEED File #########

-- roles
INSERT INTO public.roles (is_deleted, created_at, updated_at, id_role, name_role, description_role) VALUES(false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', '1cf97381-3bb1-4e37-9140-8b9039f14cff', 'PATIENT', 'Usuario paciente');
INSERT INTO public.roles (is_deleted, created_at, updated_at, id_role, name_role, description_role) VALUES(false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', '03c1a1de-30b8-437d-8743-a70179d0adc3', 'PROFESIONAL', 'Profesional de la salud');
INSERT INTO public.roles (is_deleted, created_at, updated_at, id_role, name_role, description_role) VALUES(false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', 'f02ca294-794b-4eaa-b95e-dccca57c4460', 'ADMIN', 'Usuario administrador del sistema');

-- specialities
INSERT INTO public.specialties (is_deleted, created_at, updated_at, id_specialty, name_specialty, description_specialty) VALUES(false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', '88d191ef-44b0-4222-8cd1-cc2d9ac998de', 'Cardiología', 'Área que se encarga del corazón');
INSERT INTO public.specialties (is_deleted, created_at, updated_at, id_specialty, name_specialty, description_specialty) VALUES(false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', 'd421ed25-c472-4e7f-9694-e86e051132ee', 'Dermatología', 'Área que se encarga de la piel humana');
INSERT INTO public.specialties (is_deleted, created_at, updated_at, id_specialty, name_specialty, description_specialty) VALUES(false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', 'c629bec5-3ea7-482f-84a0-d78b3e968de3', 'Medicina General', 'Área donde se hacen estudios generales');

-- users
INSERT INTO public.users (active, is_deleted, created_at, updated_at, id_user, email, "password") VALUES(true, false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', '153bd265-0281-44a9-946a-803977a18260', 'user1@mail.com', '$2a$10$7fdnIfqecA6VJoY0UgUWFeefElnV9JrQH720d67YNYLvBEiNBVmNS');
INSERT INTO public.users (active, is_deleted, created_at, updated_at, id_user, email, "password") VALUES(true, false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', '1df9a905-9600-429a-8d4c-7fdf027f95a4', 'user2@mail.com', '$2a$10$7fdnIfqecA6VJoY0UgUWFeefElnV9JrQH720d67YNYLvBEiNBVmNS');
INSERT INTO public.users (active, is_deleted, created_at, updated_at, id_user, email, "password") VALUES(true, false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', '265df1ac-5b3a-47ff-9156-7db7f8c7efae', 'user3@mail.com', '$2a$10$7fdnIfqecA6VJoY0UgUWFeefElnV9JrQH720d67YNYLvBEiNBVmNS');
INSERT INTO public.users (active, is_deleted, created_at, updated_at, id_user, email, "password") VALUES(true, false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', '73b5362f-ed92-424c-8568-43b082ecdf30', 'doctor1@mail.com', '$2a$10$7fdnIfqecA6VJoY0UgUWFeefElnV9JrQH720d67YNYLvBEiNBVmNS');
INSERT INTO public.users (active, is_deleted, created_at, updated_at, id_user, email, "password") VALUES(true, false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', 'b0dcaba0-743a-4942-a1b1-0a1135d3b96f', 'doctor2@mail.com', '$2a$10$7fdnIfqecA6VJoY0UgUWFeefElnV9JrQH720d67YNYLvBEiNBVmNS');
INSERT INTO public.users (active, is_deleted, created_at, updated_at, id_user, email, "password") VALUES(true, false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', 'ea0c2538-9576-46ee-bc80-05f9e15493e2', 'admin@mail.com', '$2a$10$7fdnIfqecA6VJoY0UgUWFeefElnV9JrQH720d67YNYLvBEiNBVmNS');

-- professionals
INSERT INTO public.professionals (is_deleted, reputation, value_query, created_at, updated_at, mp, id_professional, user_id_user, name_professional) VALUES(false, 0, 50, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', 'abc', 'b307a64e-0eeb-4da2-b9dd-43dad45865c9', '73b5362f-ed92-424c-8568-43b082ecdf30', 'abc');
INSERT INTO public.professionals (is_deleted, reputation, value_query, created_at, updated_at, mp, id_professional, user_id_user, name_professional) VALUES(false, 0, 80, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', 'abc2', '8edec619-5f77-4a21-88b8-5ded6b18eef5', 'b0dcaba0-743a-4942-a1b1-0a1135d3b96f', 'abc2');
