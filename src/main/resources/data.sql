insert into medical_record(date_created)
values ('2000-12-11');
insert into medical_record(date_created)
values ('2000-05-11');

insert into patient(age, date_of_birth, first_name, gender, last_name, medical_record_id)
values (58, '1965-05-11', 'Natasa', 'Zensko', 'Ilic', 1);
insert into patient(age, date_of_birth, first_name, gender, last_name, medical_record_id)
values (99, '1925-05-11', 'Katarina', 'Zensko', 'Klajic', 2);


insert into symptom(name, exam_id)
values ('papule', 1);
insert into symptom(name, exam_id)
values ('svarb', 1);
insert into symptom(name, exam_id)
values ('crvenilo', 1);

insert into symptom(name, exam_id)
values ('crvenilo', 2);


insert into disease(name)
values ('suga');

insert into medication(name, exam_id)
values ('benadrylm', 1);
insert into medication(name, exam_id)
values ('permetrin_krema', 1);
insert into medication(name, exam_id)
values ('sumporna_krema', 2);

insert into additional_exam(name) value ('kompletna_krvna_slika');
insert into additional_exam(name) value ('test_koze');


insert into exam(additional_exam_id, disease_id, patient_id)
values (1, 1, 1);
insert into exam(additional_exam_id, disease_id, patient_id)
values (2, 1, 2);