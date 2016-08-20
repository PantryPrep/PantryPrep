package com.sonnytron.sortatech.pantryprep.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.sonnytron.sortatech.pantryprep.Database.IngredientSchema.IngredientsTable;
import com.sonnytron.sortatech.pantryprep.Models.Ingredient;

/**
 * Created by sonnyrodriguez on 8/18/16.
 */
public class IngredientsBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "ingredientsItemsBeta.db";

    public IngredientsBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + IngredientsTable.NAME + "(" +
        IngredientsTable.Cols.UUID + ", " +
        IngredientsTable.Cols.TYPE + ", " +
        IngredientsTable.Cols.PHOTO + ", " +
        IngredientsTable.Cols.EXP + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // We don't do anything with update yet
    }
}
