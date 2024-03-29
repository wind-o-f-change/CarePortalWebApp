create table if not exists ankets
(
    anketa_id integer generated by default as identity
        constraint ankets_pkey
            primary key
        constraint fk7l3w3jg7t8uw489olkv6ctp2v
            references ankets,
    name varchar(255),
    create_dtm timestamp
);

alter table ankets owner to postgres;

create table if not exists answers
(
    answer_id integer generated by default as identity
        constraint answers_pkey
            primary key,
    answer_text varchar(255)
);

alter table answers owner to postgres;

create table if not exists questions
(
    question_id integer generated by default as identity
        constraint questions_pkey
            primary key,
    question_text varchar(255)
);

alter table questions owner to postgres;

create table if not exists anketa_contents
(
    anketa_id integer not null
        constraint fkkb3l1swnxd6rfti15q8o3gu9u
            references ankets,
    question_id integer not null
        constraint fk7mm69im5ef3pnxrnm1a5hgkru
            references questions
);

alter table anketa_contents owner to postgres;

create table if not exists question_contents
(
    question_id integer not null
        constraint fksbkn11en65xl173xp7ujnbmjc
            references questions,
    answer_id integer not null
        constraint fkgvd5aeuj932ofnimmryr38roa
            references answers
);

alter table question_contents owner to postgres;

create table if not exists users
(
    dtype varchar(31) not null,
    user_id integer generated by default as identity
        constraint users_pkey
            primary key,
    created timestamp,
    email varchar(255)
        constraint uk_6dotkott2kjsp8vw4d0m25fb7
            unique,
    enabled boolean not null,
    full_name varchar(255),
    password varchar(255),
    role varchar(255),
    sex varchar(255),
    birth_day timestamp,
    doctor_user_id integer
        constraint fkb0sh7hcseq69m8p6l9bdqg9xf
            references users
);

alter table users owner to postgres;

create table if not exists users_patients
(
    doctor_user_id integer not null
        constraint fkjnm3ofe6o794fbu62jrmw4fm0
            references users,
    patients_user_id integer not null
        constraint uk_faqh44cdj23ocflpvlvovuq8j
            unique
        constraint fkq4jjvhvxrtdqc0f2equj88rov
            references users
);

alter table users_patients owner to postgres;

create table if not exists passes
(
    pass_id integer generated by default as identity
        constraint passes_pkey
            primary key,
    create_dtm timestamp,
    status_id integer,
    anketa_id integer not null
        constraint fkggm43frj8ax0nayth20iu8oa1
            references ankets,
    user_id integer not null
        constraint fk18qdlko1thaqtdent5w2cl00p
            references users
);

alter table passes owner to postgres;

create table if not exists pass_contents
(
    pass_content_id integer generated by default as identity
        constraint pass_contents_pkey
            primary key,
    answer_id integer not null
        constraint fkint65cnkou91is3idwd59dp5t
            references answers,
    pass_id integer not null
        constraint fk1twov9o5bxv6s9kcls6vc0mt7
            references passes,
    question_id integer not null
        constraint fk7eay6cyvt731nx8tc0xdfw1us
            references questions
);

alter table pass_contents owner to postgres;