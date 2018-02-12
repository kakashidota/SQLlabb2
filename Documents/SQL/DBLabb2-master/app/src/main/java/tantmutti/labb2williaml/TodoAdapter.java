package tantmutti.labb2williaml;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kakashi on 2018-02-06.
 */



public class TodoAdapter extends BaseAdapter {

    private List<Todo> list = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;


    public TodoAdapter(Context context, List<Todo> list) {
        this.list = list;
        this.context = context;
        layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void remove(Todo todo)
    {
        list.remove(todo);
        notifyDataSetChanged();
    }

    public void add(Todo todo)
    {
        list.add(todo);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);    }

    @Override
    public long getItemId(int position) {
        return list.get(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null){
            view = layoutInflater.inflate(R.layout.activity_todo,parent,false);
        } else {
            view = convertView;

        }

        TextView textView = view.findViewById(R.id.textViewCell);
        textView.setText(list.get(position).getTodoTitle());
        TextView textView2 = view.findViewById(R.id.moreInfoTextView);
        textView2.setText( Integer.toString( list.get(position).getTodoCategory() ) );
        return view;
    }


}
