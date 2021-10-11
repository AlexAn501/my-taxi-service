ALTER TABLE taxi_drive_info ADD COLUMN IF NOT EXISTS city_id BIGINT;

ALTER TABLE taxi_drive_info ADD CONSTRAINT taxi_drive_city_id FOREIGN KEY (city_id)
REFERENCES city_queue (city_id);

ALTER TABLE taxi_drive_info ADD COLUMN rating INT DEFAULT 1 CHECK (rating > 0 AND rating <= 5);

ALTER TABLE taxi_drive_info ADD COLUMN busy BOOLEAN DEFAULT false;

ALTER TABLE taxi_drive_info RENAME COLUMN car_model TO car_id;

ALTER TABLE taxi_drive_info ALTER COLUMN car_id TYPE bigint USING car_id::bigint;

ALTER TABLE taxi_drive_info ADD CONSTRAINT car_id_uq UNIQUE (car_id);

ALTER TABLE taxi_drive_info ADD CONSTRAINT taxi_drive_car FOREIGN KEY (car_id)
REFERENCES car (id);

comment on column taxi_drive_info.minute_cost is 'Стоимость минуты';
comment on column taxi_drive_info.rating is 'Рейтинг водителя (от 1 до 5)';
comment on column taxi_drive_info.city_id is 'Таксопарк(Город) к которому прикреплен водитель';
comment on column taxi_drive_info.busy is 'Занят ли водитель сейчас';
comment on column taxi_drive_info.car_id is'Идентификатор машины';