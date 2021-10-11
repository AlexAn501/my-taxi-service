ALTER TABLE car ALTER COLUMN createdttm TYPE date;

comment on column car.model is 'Модель машины';
comment on column car.createdttm is 'Дата создания а/м';
comment on column car.id is 'Идентификатор машины';