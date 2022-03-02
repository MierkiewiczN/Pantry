package mierkiewicz.natalia.pantry.database

import androidx.room.TypeConverter
import mierkiewicz.natalia.pantry.model.ImportanceLevel
import mierkiewicz.natalia.pantry.model.QuantityLevel

class Converters {

    @TypeConverter
    fun toQuantityLevel(value: String) = enumValueOf<QuantityLevel>(value)

    @TypeConverter
    fun fromQuantityLevel(value: QuantityLevel) = value.name

    @TypeConverter
    fun toImportanceLevel(value: String) = enumValueOf<ImportanceLevel>(value)

    @TypeConverter
    fun fromImportanceLevel(value: ImportanceLevel) = value.name
}