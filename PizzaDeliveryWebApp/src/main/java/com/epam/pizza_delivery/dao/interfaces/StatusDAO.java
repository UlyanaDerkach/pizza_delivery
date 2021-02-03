package com.epam.pizza_delivery.dao.interfaces;

import com.epam.pizza_delivery.entity.StatusDict;
import java.util.List;

public interface StatusDAO {

    List<StatusDict> getAll();
}
