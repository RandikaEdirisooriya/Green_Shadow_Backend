package lk.ijse.Green_Shadow.Service;

import lk.ijse.Green_Shadow.Dto.Impl.VehicleDto;
import lk.ijse.Green_Shadow.Dto.VehicleStatus;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDto vehicleDto);

    List<VehicleDto> getAllVehicles();

    VehicleStatus getVehicle(String vehicleId);

    void deleteVehicle(String vehicleId);

    void updateVehicle(String vehicleId, VehicleDto vehicleDto);
}
