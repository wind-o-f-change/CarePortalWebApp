INSERT INTO public.users (dtype, created, email, enabled, full_name, password, role, sex, birth_day,
                          doctor_user_id)
VALUES
('Admin', '2020-08-14 02:14:00.001000', 'admin@admin.com', true, 'Лютый Владислав Владиславович',
'$2a$10$16WeJxKLX8rtJ3HJibZ2huEQYrwe5VfjciPXNvyxJcRCshl8qSDue', 'ROLE_ADMIN', 'MAN', '1991-01-01', null),

('Admin', '2020-08-14 02:14:00.001000', 'admin2@admin.com', true, 'Строгая Ирина Витальевна',
 '$2a$10$16WeJxKLX8rtJ3HJibZ2huEQYrwe5VfjciPXNvyxJcRCshl8qSDue', 'ROLE_ADMIN', 'WOMAN', '1991-01-01', null),

('Patient', '2020-08-14 02:14:00.001000', 'ivanov@patient.com', false, 'Иванов Игорь Владиславович',
 '$2a$10$16WeJxKLX8rtJ3HJibZ2huEQYrwe5VfjciPXNvyxJcRCshl8qSDue', 'ROLE_PATIENT', 'MAN', '1991-01-01', null),

('Patient', '2020-08-14 02:14:00.001000', 'ivanova@patient.com', false, 'Иванова Елена Олеговна',
 '$2a$10$16WeJxKLX8rtJ3HJibZ2huEQYrwe5VfjciPXNvyxJcRCshl8qSDue', 'ROLE_PATIENT', 'WOMAN', '1991-01-01', null),

('Doctor', '2020-08-14 02:14:00.001000', 'gripp@doctor.com', false, 'Грипп Виктор Викторович',
 '$2a$10$16WeJxKLX8rtJ3HJibZ2huEQYrwe5VfjciPXNvyxJcRCshl8qSDue', 'ROLE_DOCTOR', 'MAN', '1991-01-01', null),

('Doctor', '2020-08-14 02:14:00.001000', 'flu@doctor.com', false, 'Простудова Анна Игоревна',
 '$2a$10$16WeJxKLX8rtJ3HJibZ2huEQYrwe5VfjciPXNvyxJcRCshl8qSDue', 'ROLE_DOCTOR', 'WOMAN', '1991-01-01', null)
;

INSERT INTO public.ankets (name, create_dtm)
VALUES
('Анкета онколога', '2020-08-14 02:14:00.001000')
;

INSERT INTO public.questions (question_text)
VALUES
('Состоите ли Вы сами на учете у онколога?'),
('У Вас находили хроническое заболевание легких?'),
('Вас беспокоит кашель больше 1 месяца?'),
('У Вас находили полипы в желудке?'),
('Вы отмечаете у себя излишнюю бледность кожи?')
;

INSERT INTO public.answers (answer_text)
VALUES
('Да'),
('Нет'),
('Иногда')
;

INSERT INTO public.question_contents (question_id, answer_id)
VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(2, 2),
(2, 3),
(3, 1),
(3, 2),
(3, 3),
(4, 1),
(4, 2),
(5, 1),
(5, 2)
;

INSERT INTO public.anketa_contents (anketa_id, question_id)
VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5)
;
