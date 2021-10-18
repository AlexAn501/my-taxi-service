CREATE SEQUENCE IF NOT EXISTS grade_seq START 1;

CREATE TABLE IF NOT EXISTS grade_taxi_driver (
    id BIGINT NOT NULL DEFAULT nextval('grade_seq' :: regclass),
    driver_id BIGINT,
    grade INT DEFAULT 1 CHECK (grade > 0 AND grade <= 5),
    order_id BIGINT,

    CONSTRAINT grade_taxi_id_pk
        primary key (id),
    CONSTRAINT grade_driver_id_fk FOREIGN KEY (driver_id)
        REFERENCES taxi_drive_info (driver_id),
    CONSTRAINT grade_order_id_fk FOREIGN KEY  (order_id)
        REFERENCES orders (order_id)
);

COMMENT ON TABLE grade_taxi_driver IS 'Информация о отзывах водителей';

COMMENT ON COLUMN grade_taxi_driver.driver_id IS 'Идентификатор водителя';
COMMENT ON COLUMN grade_taxi_driver.grade IS 'Оценка клиента за поездку';