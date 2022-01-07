package annotationconfig;

import by.senla.training.chaplinskiy.hotel.service.PropertiesService;

import java.lang.reflect.Field;

public class ConfigPropertyProcessor {

    private static ConfigPropertyProcessor configPropertyProcessor;
    private final PropertiesService propertiesService;

    private ConfigPropertyProcessor() {
        this.propertiesService = PropertiesService.getPropertiesService();
    }

    public static ConfigPropertyProcessor getConfigPropertyProcessor() {
        if (configPropertyProcessor == null) {
            configPropertyProcessor = new ConfigPropertyProcessor();
        }
        return configPropertyProcessor;
    }

    public void processAnnotation(Object object) {
        Class<?> aClass = object.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(ConfigProperty.class)) {
                ConfigProperty annotation = field.getAnnotation(ConfigProperty.class);
                String key = annotation.key();
                String value = propertiesService.getValue(key);
                field.setAccessible(true);
                try {
                    field.set(object, value);
                } catch (IllegalAccessException e) {
                    System.out.println("не туда поставили аннотацию");
                }
            }
        }
    }

}
