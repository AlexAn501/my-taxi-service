package ru.digitalleague.taxi_company.api;

/**
 * Сервис для работы с таблицей car
 */
public interface CarService {

    long findIdByModel(String model);
}
