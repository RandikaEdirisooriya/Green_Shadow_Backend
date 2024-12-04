package lk.ijse.Green_Shadow.Service;

import lk.ijse.Green_Shadow.Dto.Impl.MoniteringLogDto;
import lk.ijse.Green_Shadow.Dto.MoniteringLogStatus;

import java.util.List;

public interface MoniteringLogService {
    void saveLogs(MoniteringLogDto moniteringLogDto);

    List<MoniteringLogDto> getAllLogs();

    MoniteringLogStatus getLogs(String logId);

    void deleteLogs(String logId);

    void updatedLogs(String logId, MoniteringLogDto logDto);

    String findLastLogsCode();

    long getCount();

    List<String> getAllIds();
}
