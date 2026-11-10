INSERT INTO driving_school (id, name, email, phone_number, street, street_number, city, state, zip, country,
                            country_code, created_at, updated_at)
VALUES ('school-1', 'Fahrschule DriveFlow Bochum', 'info@driveflow-bochum.de', '+49234123456',
        'Universitätsstraße','150', 'Bochum', 'Nordrhein-Westfalen', '44801', 'Deutschland', 'DE',
        '2026-01-01 08:00:00','2026-01-01 08:00:00'),
       ('school-2', 'Fahrschule City-Drive Dortmund', 'kontakt@citydrive-dortmund.de', '+49231987654', 'Westenhellweg',
        '42', 'Dortmund', 'Nordrhein-Westfalen', '44137', 'Deutschland', 'DE',
        '2026-01-02 08:00:00','2026-01-02 08:00:00');

-- =================================================================
-- 2. LICENSE CLASSES (LicenseClass)
-- =================================================================

-- For School 1
INSERT INTO license_class (id, tenant_id, code, name, description, created_at, updated_at)
VALUES
    ('lic-b-1', 'school-1', 'B', 'Klasse B', 'PKW und Anhänger bis 3.5t', '2026-01-01 09:00:00', '2026-01-01 09:00:00'),
    ('lic-a-1', 'school-1', 'A', 'Klasse A', 'Motorräder unbeschränkt', '2026-01-01 09:00:00', '2026-01-01 09:00:00');

-- For School 2
INSERT INTO license_class (id, tenant_id, code, name, description, created_at, updated_at)
VALUES
    ('lic-b-2', 'school-2', 'B', 'Klasse B', 'PKW Standard', '2026-01-02 09:00:00', '2026-01-02 09:00:00');


-- =================================================================
-- 3. INSTRUCTORS (Instructor) - 7 instructors total
-- =================================================================

-- Instructors for School 1 (4)
INSERT INTO instructor (id, tenant_id, first_name, last_name, email, phone_number, created_at, updated_at)
VALUES
    ('inst-1', 'school-1', 'Thomas', 'Schmidt', 'thomas.schmidt@driveflow.de', '+491511111111', '2026-01-03 10:00:00', '2026-01-03 10:00:00'),
    ('inst-2', 'school-1', 'Michael', 'Weber', 'michael.weber@driveflow.de', '+491512222222', '2026-01-03 10:00:00', '2026-01-03 10:00:00'),
    ('inst-3', 'school-1', 'Sabine', 'Meyer', 'sabine.meyer@driveflow.de', '+491513333333', '2026-01-03 10:00:00', '2026-01-03 10:00:00'),
    ('inst-4', 'school-1', 'Andreas', 'Wagner', 'andreas.wagner@driveflow.de', '+491514444444', '2026-01-03 10:00:00', '2026-01-03 10:00:00');

-- Instructors for School 2 (3)
INSERT INTO instructor (id, tenant_id, first_name, last_name, email, phone_number, created_at, updated_at)
VALUES
    ('inst-5', 'school-2', 'Stefan', 'Becker', 'stefan.becker@citydrive.de', '+491525555555', '2026-01-04 10:00:00', '2026-01-04 10:00:00'),
    ('inst-6', 'school-2', 'Julia', 'Hoffmann', 'julia.hoffmann@citydrive.de', '+491526666666', '2026-01-04 10:00:00', '2026-01-04 10:00:00'),
    ('inst-7', 'school-2', 'Christian', 'Koch', 'christian.koch@citydrive.de', '+491527777777', '2026-01-04 10:00:00', '2026-01-04 10:00:00');


-- =================================================================
-- 4. MANY-TO-MANY: Instructors & License Classes
-- =================================================================

INSERT INTO instructor_license_classes (instructor_id, license_id)
VALUES
    ('inst-1', 'lic-b-1'),
    ('inst-1', 'lic-a-1'),
    ('inst-2', 'lic-b-1'),
    ('inst-3', 'lic-b-1'),
    ('inst-3', 'lic-a-1'),
    ('inst-4', 'lic-b-1'),
    ('inst-5', 'lic-b-2'),
    ('inst-6', 'lic-b-2'),
    ('inst-7', 'lic-b-2');


-- =================================================================
-- 5. STUDENTS (Student) - 15 students total
-- =================================================================

-- Students for School 1 (assigned to inst-1 to inst-4)
INSERT INTO student (id, tenant_id, first_name, last_name, email, phone_number, instructor_id, created_at, updated_at)
VALUES
    ('stud-1', 'school-1', 'Maximilian', 'Mustermann', 'max.m@web.de', '+491701111111', 'inst-1', '2026-01-05 11:00:00', '2026-01-05 11:00:00'),
    ('stud-2', 'school-1', 'Laura', 'Klein', 'laura.klein@gmx.de', '+491702222222', 'inst-1', '2026-01-05 11:05:00', '2026-01-05 11:05:00'),
    ('stud-3', 'school-1', 'Jan', 'Böhmer', 'jan.boehmer@outlook.com', '+491703333333', 'inst-2', '2026-01-05 11:10:00', '2026-01-05 11:10:00'),
    ('stud-4', 'school-1', 'Sarah', 'Fischer', 'sarah.f@gmail.com', '+491704444444', 'inst-2', '2026-01-05 11:15:00', '2026-01-05 11:15:00'),
    ('stud-5', 'school-1', 'Tim', 'Schneider', 'tim.schneider@web.de', '+491705555555', 'inst-3', '2026-01-06 12:00:00', '2026-01-06 12:00:00'),
    ('stud-6', 'school-1', 'Anna', 'Wolf', 'anna.wolf@t-online.de', '+491706666666', 'inst-3', '2026-01-06 12:05:00', '2026-01-06 12:05:00'),
    ('stud-7', 'school-1', 'Felix', 'Neumann', 'felix.n@gmail.com', '+491707777777', 'inst-4', '2026-01-06 12:10:00', '2026-01-06 12:10:00'),
    ('stud-8', 'school-1', 'Marie', 'Schwarz', 'marie.schwarz@web.de', '+491708888888', 'inst-4', '2026-01-06 12:15:00', '2026-01-06 12:15:00');

-- Students for School 2 (assigned to inst-5 to inst-7)
INSERT INTO student (id, tenant_id, first_name, last_name, email, phone_number, instructor_id, created_at, updated_at)
VALUES
    ('stud-9',  'school-2', 'Leon',  'Zimmermann', 'leon.z@gmx.de',        '+491751111111', 'inst-5', '2026-01-07 13:00:00', '2026-01-07 13:00:00'),
    ('stud-10', 'school-2', 'Mia',   'Braun',       'mia.braun@gmail.com',  '+491752222222', 'inst-5', '2026-01-07 13:05:00', '2026-01-07 13:05:00'),
    ('stud-11', 'school-2', 'David', 'Krüger',      'david.k@outlook.com',  '+491753333333', 'inst-6', '2026-01-07 13:10:00', '2026-01-07 13:10:00'),
    ('stud-12', 'school-2', 'Lena',  'Hofmann',     'lena.h@web.de',        '+491754444444', 'inst-6', '2026-01-07 13:15:00', '2026-01-07 13:15:00'),
    ('stud-13', 'school-2', 'Jonas', 'Hartmann',    'jonas.h@t-online.de',  '+491755555555', 'inst-7', '2026-01-08 14:00:00', '2026-01-08 14:00:00'),
    ('stud-14', 'school-2', 'Clara', 'Lange',       'clara.lange@gmail.com','+491756666666', 'inst-7', '2026-01-08 14:05:00', '2026-01-08 14:05:00'),
    ('stud-15', 'school-2', 'Elias', 'Werner',      'elias.w@web.de',       '+491757777777', 'inst-7', '2026-01-08 14:10:00', '2026-01-08 14:10:00');