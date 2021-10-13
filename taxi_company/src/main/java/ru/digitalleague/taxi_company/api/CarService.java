package ru.digitalleague.taxi_company.api;

/**
 * Сервис для работы с таблицей car
 */
@Deprecated
public interface CarService {

    long findIdByModel(String model);
}
