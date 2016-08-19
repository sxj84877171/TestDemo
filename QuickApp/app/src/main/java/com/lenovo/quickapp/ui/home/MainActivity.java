package com.lenovo.quickapp.ui.home;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.lenovo.quickapp.App;
import com.lenovo.quickapp.R;
import com.lenovo.quickapp.base.BaseActivity;
import com.lenovo.quickapp.data.local.db.bean.Note;
import com.lenovo.quickapp.data.local.db.dao.DaoSession;

import org.greenrobot.greendao.rx.RxDao;
import org.greenrobot.greendao.rx.RxQuery;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnItemClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class MainActivity extends BaseActivity implements HomeView{

    private RxDao<Note, Long> noteDao;
    private RxQuery<Note> notesQuery;

    @Bind(R.id.add_note)
    public Button addNote;

    @Bind(R.id.note_content)
    public EditText note_content;

    @Bind(R.id.query_notes)
    public Button query ;

    @Bind(R.id.show)
    public ListView listView ;

    private List<Note> notes ;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        noteDao = daoSession.getNoteDao().rx();

        List<Note> list = noteDao.getDao().loadAll();

        notesQuery = daoSession.getNoteDao().queryBuilder().rx();

        if (list != null && list.size() > 0) {
            notes = list ;
            Toast.makeText(getApplicationContext(), list.get(0).getComment(), Toast.LENGTH_LONG).show();
        }


        notesQuery.list().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<Note>>() {
            @Override
            public void call(List<Note> notes) {
                if (notes != null && notes.size() > 0) {
                    Toast.makeText(getApplicationContext(), notes.get(0).getComment(), Toast.LENGTH_LONG).show();
                }
                MainActivity.this.notes = notes ;
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Note note = notes.get(i);
                noteDao.delete(note).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    @OnClick(R.id.query_notes)
    public void query(View view){
        notesQuery.list().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<Note>>() {
            @Override
            public void call(List<Note> notes) {
                if (notes != null && notes.size() > 0) {
                    Toast.makeText(getApplicationContext(), notes.get(0).getComment(), Toast.LENGTH_LONG).show();
                }
                MainActivity.this.notes = notes ;
                showResult(notes);
            }
        });
    }

    @OnClick(R.id.add_note)
    public void onClick(View view) {
        String content = note_content.getText().toString();
        note_content.setText("");

        Note note = new Note();
        note.setComment(content);
        note.setText("not empty");
        noteDao.insert(note).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Note>() {
            @Override
            public void call(Note note) {
                Toast.makeText(getApplicationContext(), note.getComment(), Toast.LENGTH_LONG).show();
            }
        });

    }




    @Override
    public void showResult(List<Note> notes) {
        List<String> list = new ArrayList<>();
        for(Note note : notes){
            list.add(note.getComment());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
    }

    @Override
    public void showSuccess() {

    }

    @Override
    public void showFail() {

    }
}
