package com.epam.pizza_delivery.service;

import java.util.HashMap;
import java.util.Map;

import static com.epam.pizza_delivery.util.constants.ServiceConstants.*;

public class ServiceFactory {
    private ServiceFactory() { }
    private static volatile ServiceFactory instance;
    private static final Map<String, Service> SERVICE_MAP = new HashMap<>();

    static {
        SERVICE_MAP.put(REGISTER_SERVICE, new UserRegisterService());
        SERVICE_MAP.put(USER_LOGIN_SERVICE, new UserLoginService());
        SERVICE_MAP.put(SHOW_PRODUCTS_BY_CATEGORY_ID_SERVICE, new ShowProductsByCategoryService());
        SERVICE_MAP.put(USER_LOGOUT_SERVICE, new UserLogoutService());
        SERVICE_MAP.put(ADD_TO_CART_SERVICE, new AddToCartService());
        SERVICE_MAP.put(REMOVE_FROM_CART_SERVICE, new RemoveFromCart());
        SERVICE_MAP.put(DISPLAY_CART_SERVICE, new DisplayCartService());
        SERVICE_MAP.put(EDIT_USER_INFO_SERVICE, new EditUserInfoService());
        SERVICE_MAP.put(CHANGE_PASSWORD_SERVICE, new ChangePasswordService());
        SERVICE_MAP.put(ORDER_SERVICE, new OrderService());
        SERVICE_MAP.put(ORDER_HISTORY_SERVICE, new DisplayOrderHistory());
        SERVICE_MAP.put(EDIT_ORDER_STATUS_SERVICE, new EditOrderStatusService());
        SERVICE_MAP.put(ADD_PRODUCT_SERVICE, new AddProductService());
        SERVICE_MAP.put(DELETE_PRODUCT_SERVICE, new DeleteProductService());
        SERVICE_MAP.put(ERROR_SERVICE, new ErrorService());

    }
    public static ServiceFactory getInstance(){
        if (instance == null){
            synchronized (ServiceFactory.class){
                if (instance == null){
                    instance = new ServiceFactory();
                    return new ServiceFactory();
                }
            }
        }
        return instance;
    }

    public static Service getService(String request) {
        Service service = SERVICE_MAP.get(ERROR_SERVICE);

        for (Map.Entry<String, Service> serviceEntry : SERVICE_MAP.entrySet()) {
            if (request.equalsIgnoreCase(serviceEntry.getKey())) {
                service = SERVICE_MAP.get(serviceEntry.getKey());
            }
        }
        return service;
    }

}
