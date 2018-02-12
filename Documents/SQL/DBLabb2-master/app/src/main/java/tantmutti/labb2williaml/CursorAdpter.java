package tantmutti.labb2williaml;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Kakashi on 2018-02-06.
 */

public class CursorAdpter extends CursorAdapter {

    private final LayoutInflater cursorInflater;

    public CursorAdpter(Context context, Cursor c, boolean autoRequery, LayoutInflater cursorInflater) {
        super(context, c, autoRequery);
        this.cursorInflater = cursorInflater;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return cursorInflater.inflate(R.layout.activity_todo, parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textView = view.findViewById(R.id.titleEditText);
        textView.setText( cursor.getString(1) );
        TextView textView2 = view.findViewById(R.id.moreInfoTextView);
        textView2.setText( Integer.toString(cursor.getInt(2)) );
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
