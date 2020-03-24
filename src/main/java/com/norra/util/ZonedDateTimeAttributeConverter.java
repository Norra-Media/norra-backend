package com.norra.util;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.norra.constants.Constants;

@Converter(autoApply = true)
public class ZonedDateTimeAttributeConverter implements AttributeConverter<ZonedDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(ZonedDateTime attribute) {
        if (attribute == null) {
            return null;
        }
        return Timestamp.from(attribute.toInstant());
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(Timestamp dbData) {
        if (dbData == null) {
            return null;
        }
        return ZonedDateTime.ofInstant(
                dbData.toInstant(), ZoneOffset.ofHoursMinutes(Constants.IST_TIMEZONE_HOUR, Constants.IST_TIMEZONE_MIN));
    }
}
