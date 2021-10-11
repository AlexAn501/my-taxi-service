ALTER TABLE taxi_drive_info DROP COLUMN IF EXISTS city_id;

ALTER TABLE taxi_drive_info DROP CONSTRAINT taxi_drive_city_id;

ALTER TABLE taxi_drive_info DROP COLUMN rating;

ALTER TABLE taxi_drive_info DROP COLUMN busy;

ALTER TABLE taxi_drive_info RENAME COLUMN car_id TO car_model;

ALTER TABLE taxi_drive_info DROP CONSTRAINT taxi_drive_car;

ALTER TABLE taxi_drive_info DROP CONSTRAINT  car_id_uq;
