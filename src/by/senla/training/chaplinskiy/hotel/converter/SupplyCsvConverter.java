package by.senla.training.chaplinskiy.hotel.converter;

import by.senla.training.chaplinskiy.hotel.entity.Supply;
import by.senla.training.chaplinskiy.hotel.entity.SupplyType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SupplyCsvConverter implements CsvConverter<Supply> {

    private static SupplyCsvConverter supplyCsvConverter;

    private SupplyCsvConverter() {
    }

    public static SupplyCsvConverter getSupplyCsvConverter() {
        if (supplyCsvConverter == null) {
            supplyCsvConverter = new SupplyCsvConverter();
        }
        return supplyCsvConverter;
    }

    @Override
    public List<Supply> getFromStrings(List<String> lines) {
        List<Supply> supplyList = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            Supply supply = getSupplyFromString(lines.get(i));
            supplyList.add(supply);
        }
        return supplyList;
    }

    private Supply getSupplyFromString(String line) {
        String[] split = line.split(",");
        Long id = Objects.equals(split[0], "") ? null : Long.parseLong(split[0]);
        SupplyType serviceType = SupplyType.valueOf(split[1]);
        int price = Integer.parseInt(split[2]);
        Supply supply = new Supply(serviceType, price);
        supply.setId(id);
        return supply;
    }

    @Override
    public List<String> getStrings(List<Supply> supplyList) {
        List<String> lines = new ArrayList<>();
        lines.add("id,serviceType,price");
        for (Supply supply : supplyList) {
            String line = supply.getId() + "," + supply.getServiceType() + "," + supply.getPrice();
            lines.add(line);
        }
        return lines;
    }
}
