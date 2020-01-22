create table person(
    id integer not null generated always as identity (start with 1, increment by 1),
    name varchar(100) not null,
    primary key (id)
);

create table actor(
    id integer not null generated always as identity (start with 1, increment by 1),
    name varchar(100) not null,
    actor_id integer not null references person on delete restrict,
    primary key (id)
);

create table director(
    id integer not null generated always as identity (start with 1, increment by 1),
    name varchar(100) not null,
    director_id integer not null references person on delete restrict,
    primary key (id)
);

create table movies(
   id integer not null generated always as identity (start with 1, increment by 1),
   name varchar(100) not null,
   primary key (id)
);

create table movie_actors(
    movie_id integer not null references movies on delete restrict,
    actor_id integer not null references actor on delete restrict,
    primary key (movie_id, actor_id)
);

create table movie_directors(
   movie_id integer not null references movies on delete restrict,
   director_id integer not null references director on delete restrict,
   primary key (movie_id, director_id)
);