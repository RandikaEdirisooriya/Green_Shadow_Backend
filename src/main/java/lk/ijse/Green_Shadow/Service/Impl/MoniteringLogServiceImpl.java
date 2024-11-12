package lk.ijse.Green_Shadow.Service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.Green_Shadow.Dao.MoniteringLogDao;
import lk.ijse.Green_Shadow.Dto.Impl.MoniteringLogDto;
import lk.ijse.Green_Shadow.Dto.MoniteringLogStatus;
import lk.ijse.Green_Shadow.Entity.Impl.MoniteringLog;
import lk.ijse.Green_Shadow.Service.MoniteringLogService;
import lk.ijse.Green_Shadow.customStatusCodes.SelectedErrorStatus;
import lk.ijse.Green_Shadow.exception.DataPersistException;
import lk.ijse.Green_Shadow.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MoniteringLogServiceImpl implements MoniteringLogService {
    @Autowired
    private MoniteringLogDao moniteringLogDao;
    @Autowired
    private Mapping logsMapping;

    @Override
    public void saveLogs(MoniteringLogDto moniteringLogDto) {
        moniteringLogDto.getLogCode();
        MoniteringLog savedLogs =
                moniteringLogDao.save(logsMapping.toMoniteringLogEntity(moniteringLogDto));
        if(savedLogs == null){
            throw new DataPersistException("Logs not saved");
        }
    }
    @Override
    public List<MoniteringLogDto> getAllLogs() {
        return logsMapping.asLogsDTOList(moniteringLogDao.findAll());
    }

    @Override
    public MoniteringLogStatus getLogs(String logId) {
        if(moniteringLogDao.existsById(logId)){
            var selectedlog = moniteringLogDao.getReferenceById(logId);
            return logsMapping.toMoniteringLogDto(selectedlog);
        }else {
            return new SelectedErrorStatus(2,"Selected Logs not found");
        }
    }

    @Override
    public void deleteLogs(String logId) {
        Optional<MoniteringLog> foundLogs = moniteringLogDao.findById(logId);
        if (!foundLogs.isPresent()) {
            throw new DataPersistException("Logs not found");
        }else {
            moniteringLogDao.deleteById(logId);
        }
    }

    @Override
    public void updatedLogs(String logId, MoniteringLogDto logDto) {
        Optional<MoniteringLog> foundLogs = moniteringLogDao.findById(logId);
        if (!foundLogs.isPresent()) {
            throw new DataPersistException("Logs not found");
        }else {

            foundLogs.get().setLogDate(logDto.getLogDate());
            foundLogs.get().setLogDetails(logDto.getLogDetails());
            foundLogs.get().setObservedImage(logDto.getObservedImage());


        }
    }
}
