
package com.norra.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.norra.enums.AppConfigKey;
import com.norra.enums.AppConfigType;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "app_config")
public class AppConfig extends GenericEntity {

    private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.STRING)
    private AppConfigType configType;

    @Enumerated(EnumType.STRING)
    private AppConfigKey key;

    private String value;


}
