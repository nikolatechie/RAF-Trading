package rs.raf.jun.nikola_grujic_rn2419.data.dataSource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import rs.raf.jun.nikola_grujic_rn2419.data.model.AccountInfo

@Database(entities = [AccountInfo::class], version = 1, exportSchema = false)
abstract class RafDatabase: RoomDatabase() {
    abstract fun accountDao(): AccountDao

    companion object {
        @Volatile
        private var INSTANCE: RafDatabase? = null

        fun getDatabase(context: Context): RafDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null)
                return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RafDatabase::class.java,
                    "raf_database"
                ).allowMainThreadQueries().build()

                INSTANCE = instance
                return instance
            }
        }
    }
}