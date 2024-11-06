package lk.ijse.Green_Shadow.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Field")
public class Field {
    @Id
    private String fieldCode;
    private String fieldName;
    private Point fieldLocation;
    private Double Extent_size;
}
