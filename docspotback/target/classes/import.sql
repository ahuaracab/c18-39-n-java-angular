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
INSERT INTO public.users (active, is_deleted, created_at, updated_at, id_user, email, "password") VALUES(true, false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', 'fb611434-d557-478a-946f-de08850ee6aa', 'user4@mail.com', '$2a$10$7fdnIfqecA6VJoY0UgUWFeefElnV9JrQH720d67YNYLvBEiNBVmNS');
INSERT INTO public.users (active, is_deleted, created_at, updated_at, id_user, email, "password") VALUES(true, false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', '73b5362f-ed92-424c-8568-43b082ecdf30', 'doctor1@mail.com', '$2a$10$7fdnIfqecA6VJoY0UgUWFeefElnV9JrQH720d67YNYLvBEiNBVmNS');
INSERT INTO public.users (active, is_deleted, created_at, updated_at, id_user, email, "password") VALUES(true, false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', 'b0dcaba0-743a-4942-a1b1-0a1135d3b96f', 'doctor2@mail.com', '$2a$10$7fdnIfqecA6VJoY0UgUWFeefElnV9JrQH720d67YNYLvBEiNBVmNS');
INSERT INTO public.users (active, is_deleted, created_at, updated_at, id_user, email, "password") VALUES(true, false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', 'ea0c2538-9576-46ee-bc80-05f9e15493e2', 'admin@mail.com', '$2a$10$7fdnIfqecA6VJoY0UgUWFeefElnV9JrQH720d67YNYLvBEiNBVmNS');

-- professionals
INSERT INTO public.professionals (is_deleted, reputation, value_query, created_at, updated_at, mp, id_professional, user_id_user, name_professional) VALUES(false, 0, 50, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', 'abc', 'b307a64e-0eeb-4da2-b9dd-43dad45865c9', '73b5362f-ed92-424c-8568-43b082ecdf30', 'abc');
INSERT INTO public.professionals (is_deleted, reputation, value_query, created_at, updated_at, mp, id_professional, user_id_user, name_professional) VALUES(false, 0, 80, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', 'abc2', '8edec619-5f77-4a21-88b8-5ded6b18eef5', 'b0dcaba0-743a-4942-a1b1-0a1135d3b96f', 'abc2');

-- patients
INSERT INTO public.patients (has_social_work, is_deleted, created_at, updated_at, cellphone_patient, social_work, id_patient, user_id_user, name_patient, photo_patient) VALUES(false, false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', '+1906704366', 'social work', '04f9c587-6fe8-4326-9b1a-3368b9e2e78d', '153bd265-0281-44a9-946a-803977a18260', 'Juan perez', 'https://robohash.org/04f9c587-6fe8-4326-9b1a-3368b9e2e78d');
INSERT INTO public.patients (has_social_work, is_deleted, created_at, updated_at, cellphone_patient, social_work, id_patient, user_id_user, name_patient, photo_patient) VALUES(false, false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', '+1906704367', 'social work', '78c6280f-82ee-4e8f-a9d3-a5c663d7361c', '1df9a905-9600-429a-8d4c-7fdf027f95a4', 'Lucas Freitas', 'https://robohash.org/78c6280f-82ee-4e8f-a9d3-a5c663d7361c');
INSERT INTO public.patients (has_social_work, is_deleted, created_at, updated_at, cellphone_patient, social_work, id_patient, user_id_user, name_patient, photo_patient) VALUES(false, false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', '+1906704368', 'social work', 'bd6da376-037a-458a-97bb-bb7ff5f62c07', '265df1ac-5b3a-47ff-9156-7db7f8c7efae', 'Laura Lopez', 'https://robohash.org/bd6da376-037a-458a-97bb-bb7ff5f62c07');
INSERT INTO public.patients (has_social_work, is_deleted, created_at, updated_at, cellphone_patient, social_work, id_patient, user_id_user, name_patient, photo_patient) VALUES(false, false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', '+1906704369', 'social work', 'b06692df-f731-4b35-b0be-27ec12edceb7', 'fb611434-d557-478a-946f-de08850ee6aa', 'Miguel Bustamante', 'https://robohash.org/b06692df-f731-4b35-b0be-27ec12edceb7');

-- clinical_histories
INSERT INTO public.clinical_stories (is_deleted, created_at, updated_at, id_clinical_story, id_patient) VALUES(false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', 'fcc9af46-3ad3-4cc8-b571-7c6145b2a0d4', '04f9c587-6fe8-4326-9b1a-3368b9e2e78d');
INSERT INTO public.clinical_stories (is_deleted, created_at, updated_at,id_clinical_story, id_patient) VALUES(false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', '2e1325b4-13f8-48a2-9b88-e1f3fb86a3f5', '78c6280f-82ee-4e8f-a9d3-a5c663d7361c');
INSERT INTO public.clinical_stories (is_deleted, created_at, updated_at, id_clinical_story, id_patient) VALUES(false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', 'd0684028-674b-49a4-a4ba-a60e77226e47', 'bd6da376-037a-458a-97bb-bb7ff5f62c07');
INSERT INTO public.clinical_stories (is_deleted, created_at, updated_at, id_clinical_story, id_patient) VALUES(false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', '7eb7ff36-0ac8-4deb-98b3-762439a44d8f', 'b06692df-f731-4b35-b0be-27ec12edceb7');

-- story_details
INSERT INTO public.story_details(is_deleted, created_at, updated_at, clinical_story_id_clinical_story, id_detail, description_detail, recipe) VALUES(false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', 'fcc9af46-3ad3-4cc8-b571-7c6145b2a0d4', '2e85c21c-3763-41a6-a7a9-94fb40027fbb', 'tos cronica', 'ibuprofeno');
INSERT INTO public.story_details(is_deleted, created_at, updated_at, clinical_story_id_clinical_story, id_detail, description_detail, recipe) VALUES(false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', 'fcc9af46-3ad3-4cc8-b571-7c6145b2a0d4', '93e77d66-3a5f-43ea-93c6-218c53ff2d8c', 'tos cronica 2', 'ibuprofeno 2');
INSERT INTO public.story_details(is_deleted, created_at, updated_at, clinical_story_id_clinical_story, id_detail, description_detail, recipe) VALUES(false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', 'fcc9af46-3ad3-4cc8-b571-7c6145b2a0d4', 'ce5767b8-b7b0-4d7e-9688-a0280b631a16', 'tos cronica 3', 'ibuprofeno 3');
INSERT INTO public.story_details(is_deleted, created_at, updated_at, clinical_story_id_clinical_story, id_detail, description_detail, recipe) VALUES(false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', 'fcc9af46-3ad3-4cc8-b571-7c6145b2a0d4', '866020a7-915f-42ca-b300-925a0538fb1e', 'tos cronica 4', 'ibuprofeno 4');
INSERT INTO public.story_details(is_deleted, created_at, updated_at, clinical_story_id_clinical_story, id_detail, description_detail, recipe) VALUES(false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', 'fcc9af46-3ad3-4cc8-b571-7c6145b2a0d4', '1b9b20d8-8d66-40a7-b551-b2059c1aa830', 'tos cronica 5', 'ibuprofeno 5');
INSERT INTO public.story_details(is_deleted, created_at, updated_at, clinical_story_id_clinical_story, id_detail, description_detail, recipe) VALUES(false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', '2e1325b4-13f8-48a2-9b88-e1f3fb86a3f5', 'e46ee9c4-d719-456e-b4c6-8af14d77bfd6', 'tos cronica', 'ibuprofeno');
INSERT INTO public.story_details(is_deleted, created_at, updated_at, clinical_story_id_clinical_story, id_detail, description_detail, recipe) VALUES(false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', '2e1325b4-13f8-48a2-9b88-e1f3fb86a3f5', '21b39b6b-49a1-4c39-913e-1a1f31be87d9', 'tos cronica 2', 'ibuprofeno 2');
INSERT INTO public.story_details(is_deleted, created_at, updated_at, clinical_story_id_clinical_story, id_detail, description_detail, recipe) VALUES(false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', '2e1325b4-13f8-48a2-9b88-e1f3fb86a3f5', '6b8c8f23-7e14-4b6b-b9f3-95da248499b3', 'tos cronica 3', 'ibuprofeno 3');
INSERT INTO public.story_details(is_deleted, created_at, updated_at, clinical_story_id_clinical_story, id_detail, description_detail, recipe) VALUES(false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', '2e1325b4-13f8-48a2-9b88-e1f3fb86a3f5', '34bdc7dd-00b6-4485-99f8-4e154bcfece6', 'tos cronica 4', 'ibuprofeno 4');
INSERT INTO public.story_details(is_deleted, created_at, updated_at, clinical_story_id_clinical_story, id_detail, description_detail, recipe) VALUES(false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', '2e1325b4-13f8-48a2-9b88-e1f3fb86a3f5', '423fe018-9450-43fc-b9af-8f74052cea0a', 'tos cronica 5', 'ibuprofeno 5');
INSERT INTO public.story_details(is_deleted, created_at, updated_at, clinical_story_id_clinical_story, id_detail, description_detail, recipe) VALUES(false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', 'd0684028-674b-49a4-a4ba-a60e77226e47', '79cec71c-7cc6-42d7-b3b1-f1133092dac9', 'tos cronica', 'ibuprofeno');
INSERT INTO public.story_details(is_deleted, created_at, updated_at, clinical_story_id_clinical_story, id_detail, description_detail, recipe) VALUES(false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', 'd0684028-674b-49a4-a4ba-a60e77226e47', '5afbd0cc-9210-48bb-af1e-2f23d7ac6c9b', 'tos cronica 2', 'ibuprofeno 2');
INSERT INTO public.story_details(is_deleted, created_at, updated_at, clinical_story_id_clinical_story, id_detail, description_detail, recipe) VALUES(false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', 'd0684028-674b-49a4-a4ba-a60e77226e47', '19023047-90c9-4fd5-83ee-0d080253e902', 'tos cronica 3', 'ibuprofeno 3');
INSERT INTO public.story_details(is_deleted, created_at, updated_at, clinical_story_id_clinical_story, id_detail, description_detail, recipe) VALUES(false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', 'd0684028-674b-49a4-a4ba-a60e77226e47', 'e107ca89-1a4a-4d99-a838-4b7f66675394', 'tos cronica 4', 'ibuprofeno 4');
INSERT INTO public.story_details(is_deleted, created_at, updated_at, clinical_story_id_clinical_story, id_detail, description_detail, recipe) VALUES(false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', 'd0684028-674b-49a4-a4ba-a60e77226e47', 'f579a25d-b96f-4dbe-b6fa-2f3a45b9c176', 'tos cronica 5', 'ibuprofeno 5');
INSERT INTO public.story_details(is_deleted, created_at, updated_at, clinical_story_id_clinical_story, id_detail, description_detail, recipe) VALUES(false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', '7eb7ff36-0ac8-4deb-98b3-762439a44d8f', '393573e2-9089-44fe-ba28-8d311e0f8b1a', 'tos cronica', 'ibuprofeno');
INSERT INTO public.story_details(is_deleted, created_at, updated_at, clinical_story_id_clinical_story, id_detail, description_detail, recipe) VALUES(false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', '7eb7ff36-0ac8-4deb-98b3-762439a44d8f', '6c81a18e-0df6-40f7-a732-284fd6109cfc', 'tos cronica 2', 'ibuprofeno 2');
INSERT INTO public.story_details(is_deleted, created_at, updated_at, clinical_story_id_clinical_story, id_detail, description_detail, recipe) VALUES(false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', '7eb7ff36-0ac8-4deb-98b3-762439a44d8f', '2bc28ff8-40b9-483c-9fdb-5239464439b9', 'tos cronica 3', 'ibuprofeno 3');
INSERT INTO public.story_details(is_deleted, created_at, updated_at, clinical_story_id_clinical_story, id_detail, description_detail, recipe) VALUES(false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', '7eb7ff36-0ac8-4deb-98b3-762439a44d8f', '609247cb-28b0-4a46-a7bb-f38ae1402e6a', 'tos cronica 4', 'ibuprofeno 4');
INSERT INTO public.story_details(is_deleted, created_at, updated_at, clinical_story_id_clinical_story, id_detail, description_detail, recipe) VALUES(false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', '7eb7ff36-0ac8-4deb-98b3-762439a44d8f', '65c5669d-486d-4fac-8a59-a7afbbfc24d1', 'tos cronica 5', 'ibuprofeno 5');

-- shifts
INSERT INTO public.shifts (is_deleted, repeat_shift, state_shift, end_time, start_time, created_at, updated_at, id_shift, "day") VALUES(false, false, true, '10:00', '09:00', '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', '71000038-b304-441f-b7aa-d50e7c86acc0', 'WEDNESDAY');
INSERT INTO public.shifts (is_deleted, repeat_shift, state_shift, end_time, start_time, created_at, updated_at, id_shift, "day") VALUES(false, false, true, '15:00', '14:00', '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', '09175d7b-9155-4911-b097-ef691da3336a',  'FRIDAY');
INSERT INTO public.shifts (is_deleted, repeat_shift, state_shift, end_time, start_time, created_at, updated_at, id_shift, "day") VALUES(false, false, true, '16:00', '15:00', '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', '68e1308b-6bec-4af1-95f9-73a5749e3e59',  'FRIDAY');
INSERT INTO public.shifts (is_deleted, repeat_shift, state_shift, end_time, start_time, created_at, updated_at, id_shift, "day") VALUES(false, false, true, '15:00', '14:00', '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', '5e5c4610-fcd7-433d-a9dd-cf1cdda35bb8',  'MONDAY');
INSERT INTO public.shifts (is_deleted, repeat_shift, state_shift, end_time, start_time, created_at, updated_at, id_shift, "day") VALUES(false, false, true, '16:00', '15:00', '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', 'abfb5bae-e940-4882-a193-5971b58295fc',  'MONDAY');
INSERT INTO public.shifts (is_deleted, repeat_shift, state_shift, end_time, start_time, created_at, updated_at, id_shift, "day") VALUES(false, false, true, '11:00', '10:00', '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', '491fa123-a7b5-447c-b31d-d2f7557cc5ae',  'MONDAY');

-- reservations
INSERT INTO public.reservations (is_deleted, created_at, updated_at, id_reservation, patient_id_patient, id_shift, query_intent, appointment_date) VALUES(false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', '7a0612f2-122b-459c-b487-d3551db54969', '04f9c587-6fe8-4326-9b1a-3368b9e2e78d', '71000038-b304-441f-b7aa-d50e7c86acc0', 'cita medica 123', '2024-09-09 14:00:00.679985+02');
INSERT INTO public.reservations (is_deleted, created_at, updated_at, id_reservation, patient_id_patient, id_shift, query_intent, appointment_date) VALUES(false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', 'b3b41411-d236-4b67-b9b5-42a336dd7a1a', 'b06692df-f731-4b35-b0be-27ec12edceb7', 'abfb5bae-e940-4882-a193-5971b58295fc', 'cita medica 34345', '2024-09-09 15:00:00.679985+02');
INSERT INTO public.reservations (is_deleted, created_at, updated_at, id_reservation, patient_id_patient, id_shift, query_intent, appointment_date) VALUES(false, '2021-09-27 15:22:53.679985+02', '2021-09-27 15:22:53.679985+02', 'bede9a0b-6513-4345-b528-13bf0ee012a8', 'bd6da376-037a-458a-97bb-bb7ff5f62c07', '491fa123-a7b5-447c-b31d-d2f7557cc5ae', 'cita medica absdd', '2024-09-09 10:00:00.679985+02');

