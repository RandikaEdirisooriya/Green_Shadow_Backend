package lk.ijse.Green_Shadow.Service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.Green_Shadow.Dao.VehicleDao;
import lk.ijse.Green_Shadow.Dto.Impl.VehicleDto;
import lk.ijse.Green_Shadow.Dto.VehicleStatus;
import lk.ijse.Green_Shadow.Entity.Impl.Vehicle;
import lk.ijse.Green_Shadow.Service.VehicleService;
import lk.ijse.Green_Shadow.customStatusCodes.SelectedErrorStatus;
import lk.ijse.Green_Shadow.exception.DataPersistException;
import lk.ijse.Green_Shadow.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleDao vehicleDao;
    @Autowired
    private Mapping vehicleMapping;

    @Override
    public void saveVehicle(VehicleDto vehicleDto) {
        vehicleDto.getVehicleCode();
        Vehicle savedVehicle =
                vehicleDao.save(vehicleMapping.toVehicleEntity(vehicleDto));
        if (savedVehicle == null) {
            throw new DataPersistException("Vehicle not saved");
        }
    }

    @Override
    public List<VehicleDto> getAllVehicles() {
        return vehicleMapping.asVehicleDTOList(vehicleDao.findAll());
    }

    @Override
    public VehicleStatus getVehicle(String vehicleId) {
        if (vehicleDao.existsById(vehicleId)) {
            var selectedVehicle = vehicleDao.getReferenceById(vehicleId);
            return vehicleMapping.toVehicleDTO(selectedVehicle);
        } else {
            return new SelectedErrorStatus(2, "Selected Vehicle not found");
        }
    }

    @Override
    public void deleteVehicle(String vehicleId) {
        Optional<Vehicle> foundVehicle = vehicleDao.findById(vehicleId);
        if (!foundVehicle.isPresent()) {
            throw new DataPersistException("Vehicle not found");
        } else {
            vehicleDao.deleteById(vehicleId);
        }
    }

    @Override
    public void updateVehicle(String vehicleId, VehicleDto vehicleDto) {
        Optional<Vehicle> foundVehicle = vehicleDao.findById(vehicleId);
        if (!foundVehicle.isPresent()) {
            throw new DataPersistException("Vehicle not found");
        } else {

            foundVehicle.get().setLicensePlateNumber(vehicleDto.getLicensePlateNumber());
            foundVehicle.get().setVehicleCategory(vehicleDto.getVehicleCategory());
            foundVehicle.get().setFuelType(vehicleDto.getFuelType());
            foundVehicle.get().setStatus(vehicleDto.getStatus());


        }
    }

    @Override
    public String findLastVehicleCode() {
        return vehicleDao.findLastCode();
    }

    @Override
    public long getCount() {
        return vehicleDao.getCount();
    }
    @Override
    public List<String> getAllIds() {
        return vehicleDao.getAllIds(); // Fetch all crop IDs from DB
    }

}
